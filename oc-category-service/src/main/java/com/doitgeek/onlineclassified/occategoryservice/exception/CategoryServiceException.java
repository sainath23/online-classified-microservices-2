package com.doitgeek.onlineclassified.occategoryservice.exception;

public class CategoryServiceException extends RuntimeException {

    private static final long serialVersionUID = -1714767072347977260L;

    public CategoryServiceException(String message) {
        super(message);
    }

    public CategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryServiceException(Throwable cause) {
        super(cause);
    }
}
