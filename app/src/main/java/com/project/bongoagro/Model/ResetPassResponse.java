package com.project.bongoagro.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassResponse {
    @SerializedName("authCode")
    @Expose
    private Integer authCode;
    @SerializedName("username")
    @Expose
    private String username;
    public ResetPassResponse(Integer authCode, String username) {
        this.authCode = authCode;
        this.username = username;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
