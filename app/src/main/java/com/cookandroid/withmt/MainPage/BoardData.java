package com.cookandroid.withmt.MainPage;

import com.google.gson.annotations.SerializedName;

public class BoardData {

    @SerializedName("title")
    private String title;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("date")
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}