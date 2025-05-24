
package com.project.bongoagro.Model;


public class RegistrationModel {

    private String username;
    private boolean isSignupSuccess;
    private String errorMessage;
    private String successMessage;

    // Constructor
    public RegistrationModel(String username, boolean isSignupSuccess, String errorMessage, String successMessage) {
        this.username = username;
        this.isSignupSuccess = isSignupSuccess;
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSignupSuccess() {
        return isSignupSuccess;
    }

    public void setSignupSuccess(boolean signupSuccess) {
        isSignupSuccess = signupSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
