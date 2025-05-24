package com.project.bongoagro.Model;

public class PriceItem {
    private int id;
    private int productId;
    private String productName;
    private String sellDate;
    private double productPrice;

    public PriceItem(int id, int productId, String productName, String sellDate, double productPrice) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.sellDate = sellDate;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
