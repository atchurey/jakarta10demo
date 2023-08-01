package org.eclipse.jakarta.hello.repositories;

import org.eclipse.jakarta.hello.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Optional<User> findById(Long id);
  Optional<User> findByEmail(String email);
  List<User> findAll();
  void save(User user);
  void update(User user);

}
