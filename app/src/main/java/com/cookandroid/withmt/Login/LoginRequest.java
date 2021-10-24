package com.cookandroid.withmt.Login;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("userId")
    private String userId;
    @SerializedName("passwd")
    private String passwd;

    public LoginRequest(String userId, String passwd){
        this.userId = userId;
        this.passwd = passwd;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "{ userId = " + userId + ", passwd = "+ passwd + "}";
    }
}
