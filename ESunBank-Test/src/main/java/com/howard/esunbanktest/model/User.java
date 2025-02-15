package com.howard.esunbanktest.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {

    private Integer user_id;
    private String phone_number;
    private String password;
    private String user_name;
    private LocalDateTime registration_time;
    private LocalDateTime last_login_time;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

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

    public LocalDateTime getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(LocalDateTime registration_time) {
        this.registration_time = registration_time;
    }

    public LocalDateTime getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(LocalDateTime last_login_time) {
        this.last_login_time = last_login_time;
    }

}
