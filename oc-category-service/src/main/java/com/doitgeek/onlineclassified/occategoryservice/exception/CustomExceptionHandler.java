package com.doitgeek.onlineclassified.occategoryservice.exception;

import com.doitgeek.onlineclassified.occategoryservice.constant.ApiStatus;
import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.model.ApiResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseModel<Void>> handleAllException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        ApiResponseModel<Void> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setErrorMessage(e.getMessage());
        apiResponseModel.setStatus(ApiStatus.ERROR.getStatus());
        return new ResponseEntity<>(apiResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CategoryNotFoundException.class, CategoryPropertyNotFoundException.class})
    public ResponseEntity<ApiResponseModel<Void>> handleCategoryNotFoundException(Exception e) {
        LOGGER.info(e.getMessage(), e);
        ApiResponseModel<Void> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setErrorMessage(e.getMessage());
        apiResponseModel.setStatus(ApiStatus.ERROR.getStatus());
        return new ResponseEntity<>(apiResponseModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryServiceException.class)
    public ResponseEntity<ApiResponseModel<Void>> handleCategoryServiceException(CategoryServiceException e) {
        LOGGER.info(e.getMessage(), e);
        ApiResponseModel<Void> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setErrorMessage(e.getMessage());
        apiResponseModel.setStatus(ApiStatus.ERROR.getStatus());
        return new ResponseEntity<>(apiResponseModel, HttpStatus.BAD_REQUEST);
    }

    // Handle validation errors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        Map<String, String> errorMessageMap = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> errorMessageMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ApiResponseModel<Void> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setStatus(ApiStatus.ERROR.getStatus());
        apiResponseModel.setErrorMessage(ErrorMessage.VALIDATION_FAILED);
        apiResponseModel.setErrorMessages(errorMessageMap);

        return new ResponseEntity<>(apiResponseModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseModel<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        LOGGER.info(e.getMessage(), e);

        ApiResponseModel<Void> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setErrorMessage(ErrorMessage.BAD_REQUEST);
        apiResponseModel.setStatus(ApiStatus.ERROR.getStatus());

        return new ResponseEntity<>(apiResponseModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseModel<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.info(e.getMessage(), e);

        ApiResponseModel<Void> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setErrorMessage(ErrorMessage.INVALID_RESOURCE_ACCESS);
        apiResponseModel.setStatus(ApiStatus.ERROR.getStatus());

        return new ResponseEntity<>(apiResponseModel, HttpStatus.BAD_REQUEST);
    }
}
