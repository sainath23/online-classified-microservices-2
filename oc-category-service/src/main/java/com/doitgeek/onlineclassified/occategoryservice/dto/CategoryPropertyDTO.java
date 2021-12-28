package com.doitgeek.onlineclassified.occategoryservice.dto;

import com.doitgeek.onlineclassified.occategoryservice.annotation.Flag;
import com.doitgeek.onlineclassified.occategoryservice.constant.ErrorMessage;
import com.doitgeek.onlineclassified.occategoryservice.constant.ServiceConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

public class CategoryPropertyDTO {
    @NotNull(message = ErrorMessage.CATEGORY_PROP_ID_NOT_NULL)
    private Long categoryId;

    @NotBlank(message = ErrorMessage.CATEGORY_PROP_NAME_NOT_EMPTY)
    @Size(min = ServiceConstant.PROPERTY_MIN, max = ServiceConstant.PROPERTY_MAX, message = ErrorMessage.CATEGORY_PROP_NAME_SIZE)
    private String propertyName;

    @Size(min = ServiceConstant.PROPERTY_MIN, max = ServiceConstant.PROPERTY_MAX, message = ErrorMessage.CATEGORY_PROP_UNIT_SIZE)
    private String propertyUnit;

    @Flag
    @NotNull(message = ErrorMessage.CATEGORY_PROP_IS_MANDATORY_NOT_NULL)
    private Character isMandatory;

    private Map<String, Object> possibleValues;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyUnit() {
        return propertyUnit;
    }

    public void setPropertyUnit(String propertyUnit) {
        this.propertyUnit = propertyUnit;
    }

    public Character getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Character isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Map<String, Object> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Map<String, Object> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
