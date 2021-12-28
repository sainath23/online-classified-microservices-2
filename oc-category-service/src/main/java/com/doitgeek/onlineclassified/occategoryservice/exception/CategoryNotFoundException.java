package com.doitgeek.onlineclassified.occategoryservice.exception;

public class CategoryNotFoundException extends CategoryServiceException {
    private static final long serialVersionUID = -7731101907022092388L;

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
