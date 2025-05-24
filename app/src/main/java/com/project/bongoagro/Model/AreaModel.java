package com.project.bongoagro.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;

    public AreaModel(Integer id, String name, Integer districtId, String address, String sortBy) {
        this.id = id;
        this.name = name;
        this.districtId = districtId;
        this.address = address;
        this.sortBy = sortBy;
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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
