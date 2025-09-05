package com.govi_mithuro.app.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "t_user")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId; //long id
    private String userName;
    private String email;
    private String password;
    private String role;
    private String OTPCode;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOTPCode() {
        return OTPCode;
    }

    public void setOTPCode(String OTPCode) {
        this.OTPCode = OTPCode;
    }
}
