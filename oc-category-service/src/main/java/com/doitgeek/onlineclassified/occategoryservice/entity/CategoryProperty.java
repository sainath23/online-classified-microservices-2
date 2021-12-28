package com.doitgeek.onlineclassified.occategoryservice.entity;

import com.doitgeek.onlineclassified.occategoryservice.converter.MapToJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY_PROPERTY")
public class CategoryProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "PROPERTY_NAME")
    private String propertyName;

    @Column(name = "PROPERTY_UNIT")
    private String propertyUnit;

    @Column(name = "IS_MANDATORY")
    private Character isMandatory;

    @Column(name = "POSSIBLE_VALUES")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> possibleValues;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryProperty that = (CategoryProperty) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CategoryProperty{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyUnit='" + propertyUnit + '\'' +
                ", isMandatory=" + isMandatory +
                ", possibleValues=" + possibleValues +
                '}';
    }
}
