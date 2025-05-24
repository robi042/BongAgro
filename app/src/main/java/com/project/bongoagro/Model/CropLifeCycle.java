package com.project.bongoagro.Model;

import java.util.List;

public class CropLifeCycle {
    private int id;
    private int cultivationGuideId;
    private String subTitle;
    private List<CropLifeCycleDetail> cropLifeCycleDetails;

    public CropLifeCycle(int id, int cultivationGuideId, String subTitle, List<CropLifeCycleDetail> cropLifeCycleDetails) {
        this.id = id;
        this.cultivationGuideId = cultivationGuideId;
        this.subTitle = subTitle;
        this.cropLifeCycleDetails = cropLifeCycleDetails;
    }

    public int getId() { return id; }
    public int getCultivationGuideId() { return cultivationGuideId; }
    public String getSubTitle() { return subTitle; }
    public List<CropLifeCycleDetail> getCropLifeCycleDetails() { return cropLifeCycleDetails; }

    public void setId(int id) {
        this.id = id;
    }

    public void setCultivationGuideId(int cultivationGuideId) {
        this.cultivationGuideId = cultivationGuideId;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setCropLifeCycleDetails(List<CropLifeCycleDetail> cropLifeCycleDetails) {
        this.cropLifeCycleDetails = cropLifeCycleDetails;
    }
}
