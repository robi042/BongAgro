package com.project.bongoagro.Model;

import java.util.List;

public class ColdStorageResponseModelClass {
    private int id;
    private String name;
    private String capacity;
    private String district;
    private String ownerName;
    private String licenseNumber;
    private String gpsLocation;
    private String address;
    private String imageUrl;
    private List<ColdStorageType> coldStorageTypes;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ColdStorageType> getColdStorageTypes() {
        return coldStorageTypes;
    }

    public void setColdStorageTypes(List<ColdStorageType> coldStorageTypes) {
        this.coldStorageTypes = coldStorageTypes;
    }
}
