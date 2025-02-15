package com.howard.esunbanktest.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
