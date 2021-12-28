package com.doitgeek.onlineclassified.occategoryservice.constant;

public final class ErrorMessage {

    private ErrorMessage() {}

    public static final String PARENT_CATEGORY_EXIST = "Parent category with id '%d' exist";
    public static final String CATEGORY_NAME_EXIST = "Category with name '%s' exist";
    public static final String CATEGORY_NOT_FOUND = "Category with id %d is not found";
    public static final String CATEGORY_NAME_NOT_FOUND = "Category with name %s is not found";
    public static final String PARENT_CATEGORY_NOT_FOUND = "Parent category with id %s is not found";
    public static final String CATEGORY_PROP_NOT_FOUND = "Category property with id '%d' is not found";
    public static final String PROP_FOR_CAT_ID_NOT_FOUND = "Property with id '%d' does not exists for category with id '%d'";
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String CATEGORY_NAME_NOT_EMPTY = "Category name should not be empty";
    public static final String CATEGORY_NAME_SIZE = "Category name should be between 3 to 100 characters";
    public static final String MAX_IMAGES_NOT_NULL = "Max images allowed should not be null";
    public static final String POST_VALIDITY_NOT_NULL = "Post validity days should not be null";
    public static final String CATEGORY_PROP_ID_NOT_NULL = "Category property id should not be null";
    public static final String CATEGORY_PROP_NAME_NOT_EMPTY = "Category property name should not be empty";
    public static final String CATEGORY_PROP_NAME_SIZE = "Category property name should be between 3 to 100 characters";
    public static final String CATEGORY_PROP_UNIT_SIZE = "Category property unit should be between 3 to 100 characters";
    public static final String CATEGORY_PROP_IS_MANDATORY_NOT_NULL = "Category property is mandatory should not be null";
    public static final String BAD_REQUEST = "Bad request";
    public static final String INVALID_RESOURCE_ACCESS = "Invalid resource access";
    public static final String FLAG_MUST_BE_VALID = "Flag must be valid";
    public static final String MISMATCH_CATEGORY_IDS = "Mismatched category ids - '%d' and '%d'";
    public static final String JSON_MAP_TO_STRING_FAIL = "Could not convert JSON Map to String.";
    public static final String JSON_STRING_TO_MAP_FAIL = "Could not convert JSON String to Map.";
}
