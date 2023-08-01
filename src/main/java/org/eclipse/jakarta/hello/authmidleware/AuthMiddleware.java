package org.eclipse.jakarta.hello.authmidleware;

public abstract class AuthMiddleware {
    private AuthMiddleware next;

    public static AuthMiddleware link(AuthMiddleware first, AuthMiddleware... chain) {
        AuthMiddleware head = first;
        for (AuthMiddleware nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
