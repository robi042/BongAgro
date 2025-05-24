package com.project.bongoagro.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginModel {

    private String fullName;
    private boolean isLoginSuccess;
    private String token;
    private String errorMessage;
    private Integer roleId;
    private Integer userId;

    // Constructor
    public LoginModel(String fullName, boolean isLoginSuccess, String token, String errorMessage, Integer roleId, Integer userId) {
        this.fullName = fullName;
        this.isLoginSuccess = isLoginSuccess;
        this.token = token;
        this.errorMessage = errorMessage;
        this.roleId = roleId;
        this.userId = userId;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getRoleName() {
        return roleId;
    }

    public void setRoleName(Integer roleName) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}