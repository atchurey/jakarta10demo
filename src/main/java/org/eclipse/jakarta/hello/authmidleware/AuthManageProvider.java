package org.eclipse.jakarta.hello.authmidleware;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.jakarta.hello.repositories.RoleRepository;
import org.eclipse.jakarta.hello.repositories.UserRepository;

import java.io.Serializable;

@ApplicationScoped
public class AuthManageProvider implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private UserRepository userRepository;
  @Inject
  private RoleRepository roleRepository;

  @Produces
  public AuthManager provide() {

    AuthManager authManager = new AuthManager(userRepository, roleRepository);

    AuthMiddleware middleware = AuthMiddleware.link(
            new ThrottlingMiddleware(100),
            new SystemClearanceMiddleware(authManager),
            new CredentialsValidatorMiddleware(authManager)
    );

    authManager.setMiddleware(middleware);

    return authManager;
  }

}
