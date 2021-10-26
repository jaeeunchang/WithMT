package com.cookandroid.withmt.MainPage;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("imoji")
    private String imoji;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImoji() {
        return imoji;
    }

    public void setImoji(String imoji) {
        this.imoji = imoji;
    }
}
