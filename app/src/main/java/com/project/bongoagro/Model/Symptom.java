package com.project.bongoagro.Model;

public class Symptom {
    private int id;
    private int diseaseId;
    private String content;

    public Symptom(int id, int diseaseId, String content) {
        this.id = id;
        this.diseaseId = diseaseId;
        this.content = content;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}