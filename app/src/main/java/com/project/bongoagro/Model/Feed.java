package com.project.bongoagro.Model;

public class Feed {
    private String CreatedAt;
    private int EntryId;
    private String Temperature;
    private String Humidity;
    private String Potassium;

    // Getters and Setters
    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.CreatedAt = createdAt;
    }

    public int getEntryId() {
        return EntryId;
    }

    public void setEntryId(int entryId) {
        this.EntryId = entryId;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        this.Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        this.Humidity = humidity;
    }

    public String getPotassium() {
        return Potassium;
    }

    public void setPotassium(String potassium) {
        this.Potassium = potassium;
    }
}

