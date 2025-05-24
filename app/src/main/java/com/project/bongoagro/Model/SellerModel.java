package com.project.bongoagro.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("areaId")
    @Expose
    private Integer areaId;
    @SerializedName("sellerTypeId")
    @Expose
    private Integer sellerTypeId;
    @SerializedName("subCategoryId")
    @Expose
    private Integer subCategoryId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("sellerType")
    @Expose
    private String sellerType;
    @SerializedName("subCategory")
    @Expose
    private String subCategory;

    public SellerModel(Integer id, String name, Integer areaId, Integer sellerTypeId, Integer subCategoryId, Integer userId, String contactNumber, String area, String sellerType, String subCategory) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.sellerTypeId = sellerTypeId;
        this.subCategoryId = subCategoryId;
        this.userId = userId;
        this.contactNumber = contactNumber;
        this.area = area;
        this.sellerType = sellerType;
        this.subCategory = subCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getSellerTypeId() {
        return sellerTypeId;
    }

    public void setSellerTypeId(Integer sellerTypeId) {
        this.sellerTypeId = sellerTypeId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
