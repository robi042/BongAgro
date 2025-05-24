package com.project.bongoagro.Model;

import java.util.List;

public class PriceResponse {
    private String result;
    private List<PriceItem> data;

    public PriceResponse(String result, List<PriceItem> data) {
        this.result = result;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<PriceItem> getData() {
        return data;
    }

    public void setData(List<PriceItem> data) {
        this.data = data;
    }
}
