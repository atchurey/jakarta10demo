package org.eclipse.jakarta.hello.exceptions;

public class BaseError {

    private String url;
    private int errorCode;
    private String errorMessage;
    private String userErrorMessage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserErrorMessage() {
        return userErrorMessage;
    }

    public void setUserErrorMessage(String userErrorMessage) {
        this.userErrorMessage = userErrorMessage;
    }
}
