package com.project.bongoagro.Model;

public class CropLifeCycleDetail {
    private int id;
    private int cropLifeCycleId;
    private String content;

    public CropLifeCycleDetail(int id, int cropLifeCycleId, String content) {
        this.id = id;
        this.cropLifeCycleId = cropLifeCycleId;
        this.content = content;
    }

    public int getId() { return id; }
    public int getCropLifeCycleId() { return cropLifeCycleId; }
    public String getContent() { return content; }

    public void setId(int id) {
        this.id = id;
    }

    public void setCropLifeCycleId(int cropLifeCycleId) {
        this.cropLifeCycleId = cropLifeCycleId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
