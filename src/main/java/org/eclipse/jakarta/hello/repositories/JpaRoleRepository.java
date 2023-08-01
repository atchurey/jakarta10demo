package org.eclipse.jakarta.hello.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.eclipse.jakarta.hello.entities.Role;

import java.io.Serializable;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class JpaRoleRepository implements RoleRepository, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Role> findByCode(int code) {
        Role role;
        try {
            role = entityManager
                    .find(Role.class, code);
        } catch (NoResultException e) {
            role = null;
        }

        return Optional.ofNullable(role);
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }
}
