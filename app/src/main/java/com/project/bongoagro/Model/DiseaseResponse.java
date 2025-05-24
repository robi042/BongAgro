package com.project.bongoagro.Model;

import java.util.List;

public class DiseaseResponse {
    private int id;
    private int productId;
    private String name;
    private String imageUrl;
    private List<Symptom> symptoms;
    private List<Symptom> treatments;

    public DiseaseResponse(int id, int productId, String name, String imageUrl, List<Symptom> symptoms, List<Symptom> treatments) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.symptoms = symptoms;
        this.treatments = treatments;
    }

    // Getters and Setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public List<Symptom> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Symptom> treatments) {
        this.treatments = treatments;
    }
}