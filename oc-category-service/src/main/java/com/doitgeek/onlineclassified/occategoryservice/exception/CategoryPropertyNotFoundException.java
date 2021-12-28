package com.doitgeek.onlineclassified.occategoryservice.exception;

public class CategoryPropertyNotFoundException extends CategoryServiceException {
    private static final long serialVersionUID = 177169443493666290L;

    public CategoryPropertyNotFoundException(String message) {
        super(message);
    }

    public CategoryPropertyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryPropertyNotFoundException(Throwable cause) {
        super(cause);
    }
}
