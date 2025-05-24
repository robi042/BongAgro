package com.project.bongoagro.Model;

public class PriceUpdateRequest {
    private int id;
    private int productId;
    private double ProductPrice;
    private int authorizedUserId;

    // Full constructor
    public PriceUpdateRequest(int id, int productId, double ProductPrice, int authorizedUserId) {
        this.id = id;
        this.productId = productId;
        this.ProductPrice = ProductPrice;
        this.authorizedUserId = authorizedUserId;
    }
}
