package com.project.bongoagro.Model;

public class Channel {
    private int Id;
    private String Name;
    private String Latitude;
    private String Longitude;
    private String Field1;
    private String Field2;
    private String Field3;
    private String CreatedAt;
    private String UpdatedAt;
    private int LastEntryId;

    // Getters and Setters
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        this.Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        this.Longitude = longitude;
    }

    public String getField1() {
        return Field1;
    }

    public void setField1(String field1) {
        this.Field1 = field1;
    }

    public String getField2() {
        return Field2;
    }

    public void setField2(String field2) {
        this.Field2 = field2;
    }

    public String getField3() {
        return Field3;
    }

    public void setField3(String field3) {
        this.Field3 = field3;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.UpdatedAt = updatedAt;
    }

    public int getLastEntryId() {
        return LastEntryId;
    }

    public void setLastEntryId(int lastEntryId) {
        this.LastEntryId = lastEntryId;
    }
}

