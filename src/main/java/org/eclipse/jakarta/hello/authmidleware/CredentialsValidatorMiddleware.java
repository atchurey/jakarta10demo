package org.eclipse.jakarta.hello.authmidleware;

public class CredentialsValidatorMiddleware extends AuthMiddleware {

    private final AuthManager authManager;

    public CredentialsValidatorMiddleware(AuthManager authManager) {
        this.authManager = authManager;
    }

    public boolean check(String email, String password) {
        if (!authManager.hasEmail(email)) {
            return false;
        }
        if (!authManager.isValidPassword(email, password)) {
            return false;
        }
        return checkNext(email, password);
    }
}