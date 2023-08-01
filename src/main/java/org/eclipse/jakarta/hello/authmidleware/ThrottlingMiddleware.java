package org.eclipse.jakarta.hello.authmidleware;

import org.eclipse.jakarta.hello.exceptions.ServiceException;

public class ThrottlingMiddleware extends AuthMiddleware {

    private final int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            throw new ServiceException(1, "Too many login request.");
        }
        return checkNext(email, password);
    }
}