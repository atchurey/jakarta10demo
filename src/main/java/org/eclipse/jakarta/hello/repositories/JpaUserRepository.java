package org.eclipse.jakarta.hello.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.eclipse.jakarta.hello.entities.User;
import org.eclipse.jakarta.hello.exceptions.ServiceException;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class JpaUserRepository implements UserRepository, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = entityManager
                    .find(User.class, id);
        } catch (NoResultException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user;
        try {
            TypedQuery<User> query = entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        try {
            return entityManager
                    .createQuery("SELECT u FROM User u", User.class)
                    .getResultList();
        } catch (Exception ex) {
            throw new ServiceException(1, ex.getMessage());
        }
    }

    @Override
    public void save(User user) {
        //try {
            entityManager.persist(user);
        /*} catch (Exception ex) {
            throw new ServiceException(1, ex.getMessage());
        }*/

    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

}
