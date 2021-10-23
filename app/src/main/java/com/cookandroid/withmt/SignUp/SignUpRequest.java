package com.cookandroid.withmt.SignUp;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("userId")
    private String userId;
    @SerializedName("passwd")
    private String passwd;
    @SerializedName("gender")
    private String gender;
    @SerializedName("age")
    private String age;
    @SerializedName("imoji")
    private String imoji;

    public SignUpRequest(String nickname, String userId, String passwd, String gender, String age, String imoji){
        this.nickname = nickname;
        this.userId = userId;
        this.passwd = passwd;
        this.gender = gender;
        this.age = age;
        this.imoji = imoji;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImoji() {
        return imoji;
    }

    public void setImoji(String imoji) {
        this.imoji = imoji;
    }
}
