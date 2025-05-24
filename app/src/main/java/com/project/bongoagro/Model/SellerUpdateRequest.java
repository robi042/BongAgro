package com.project.bongoagro.Model;

public class SellerUpdateRequest {
    private Integer id;
    private String name;
    private Integer areaId;
    private Integer sellerTypeId;
    private Integer subCategoryId;
    private String contactNumber;
    private Integer userId;

    public SellerUpdateRequest(Integer id, String name, Integer areaId, Integer sellerTypeId, Integer subCategoryId, String contactNumber, Integer userId) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.sellerTypeId = sellerTypeId;
        this.subCategoryId = subCategoryId;
        this.contactNumber = contactNumber;
        this.userId = userId;
    }
}
