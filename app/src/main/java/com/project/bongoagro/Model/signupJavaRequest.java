package com.project.bongoagro.Model;

public class signupJavaRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private Integer roleId;

    public signupJavaRequest(String username, String password, String confirmPassword, Integer roleId) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roleId = roleId;
    }
}
