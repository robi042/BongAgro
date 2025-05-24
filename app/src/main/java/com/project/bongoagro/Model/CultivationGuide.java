package com.project.bongoagro.Model;

import java.util.List;

public class CultivationGuide {
    private int id;
    private int productId;
    private String title;
    private String description;
    private String name;
    private String imageUrl;
    private String code;
    private List<CropLifeCycle> cropLifeCycleViewModels;
    private List<Symptom> symptoms;
    private List<Symptom> treatments;

    // Constructor
    public CultivationGuide(int id, int productId, String title, String description, String imageUrl, String code, List<CropLifeCycle> cropLifeCycleViewModels, String name, List<Symptom> symptoms, List<Symptom> treatments) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.code = code;
        this.cropLifeCycleViewModels = cropLifeCycleViewModels;
        this.name = name;
        this.symptoms = symptoms;
        this.treatments = treatments;
    }

    // Getters
    public int getId() { return id; }
    public int getProductId() { return productId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getCode() { return code; }
    public String getName() {
        return name;
    }

    public List<CropLifeCycle> getCropLifeCycleViewModels() { return cropLifeCycleViewModels; }
    public List<Symptom> getSymptoms() {
        return symptoms;
    }
    public List<Symptom> getTreatments() {
        return treatments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCropLifeCycleViewModels(List<CropLifeCycle> cropLifeCycleViewModels) {
        this.cropLifeCycleViewModels = cropLifeCycleViewModels;
    }
    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
    public void setTreatments(List<Symptom> treatments) {
        this.treatments = treatments;
    }
}
