package com.doitgeek.onlineclassified.occategoryservice.interceptor;

import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.entity.Category;
import com.doitgeek.onlineclassified.occategoryservice.exception.CategoryNotFoundException;
import com.doitgeek.onlineclassified.occategoryservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Component
public class RequestInterceptor implements AsyncHandlerInterceptor {

    private final CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    public RequestInterceptor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        LOGGER.info("pathVariables: {}", pathVariables);

        if (pathVariables.containsKey("categoryId")) {
            Long categoryId = Long.valueOf(pathVariables.get("categoryId"));
            Optional<Category> optionalCategory = categoryService.findById(categoryId);
            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFoundException(String.format(ErrorMessage.CATEGORY_NOT_FOUND, categoryId));
            }
        }

        return true;
    }
}
