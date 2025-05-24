package com.project.bongoagro.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subCategoryId")
    @Expose
    private Integer subCategoryId;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("title")
    @Expose
    private String title;

    public ProductModel(Integer id, Integer subCategoryId, String name, String imageUrl, Boolean isDeleted, String title, Integer categoryId) {
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.isDeleted = isDeleted;
        this.title = title;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
