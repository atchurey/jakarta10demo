package org.eclipse.jakarta.hello.repositories;

import org.eclipse.jakarta.hello.entities.Role;

import java.util.Optional;

public interface RoleRepository {
  Optional<Role> findByCode(int code);
  void save(Role role);
}
