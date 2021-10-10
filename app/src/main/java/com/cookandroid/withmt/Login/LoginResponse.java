package com.cookandroid.withmt.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("userId")
    private String userId;
    @SerializedName("passwd")
    private String passwd;
}
