package com.project.bongoagro.Model;

public class ResetPasswordRequest {
    private String username;
    private String password;

    public ResetPasswordRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
