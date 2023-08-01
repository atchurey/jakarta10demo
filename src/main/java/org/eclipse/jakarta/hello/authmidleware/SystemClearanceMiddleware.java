package org.eclipse.jakarta.hello.authmidleware;

import org.eclipse.jakarta.hello.exceptions.ServiceException;

public class SystemClearanceMiddleware extends AuthMiddleware {

    private AuthManager authManager;

    public SystemClearanceMiddleware(AuthManager authManager) {
        this.authManager = authManager;
    }

    public boolean check(String email, String password) {

        if (!authManager.isCleared(email)) {
            throw new ServiceException(1, "User blocked");
        }
        return checkNext(email, password);
    }
}