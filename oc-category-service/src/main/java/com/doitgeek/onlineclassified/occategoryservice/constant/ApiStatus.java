package com.doitgeek.onlineclassified.occategoryservice.constant;

public enum ApiStatus {
    SUCCESS("Success"),
    ERROR("Error")
    ;

    private final String status;

    ApiStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
