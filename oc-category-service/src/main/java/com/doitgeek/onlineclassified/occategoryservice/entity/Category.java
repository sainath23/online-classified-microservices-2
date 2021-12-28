package com.doitgeek.onlineclassified.occategoryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "PARENT_CATEGORY_ID")
    private Long parentCategoryId;

    @Column(name = "MAX_IMAGES_ALLOWED")
    private Integer maxImagesAllowed;

    @Column(name = "POST_VALIDITY_DAYS")
    private Integer postValidityDays;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID", insertable = false, updatable = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CategoryProperty> categoryProperties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getMaxImagesAllowed() {
        return maxImagesAllowed;
    }

    public void setMaxImagesAllowed(Integer maxImagesAllowed) {
        this.maxImagesAllowed = maxImagesAllowed;
    }

    public Integer getPostValidityDays() {
        return postValidityDays;
    }

    public void setPostValidityDays(Integer postValidityDays) {
        this.postValidityDays = postValidityDays;
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
        Category category = (Category) o;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                ", maxImagesAllowed=" + maxImagesAllowed +
                ", postValidityDays=" + postValidityDays +
                '}';
    }
}
