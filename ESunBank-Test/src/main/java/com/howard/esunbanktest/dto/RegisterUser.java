package com.howard.esunbanktest.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

public class RegisterUser {

    @NotNull
    private String phone_number;
    @NotNull
    private String password;
    @NotNull
    private String user_name;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
