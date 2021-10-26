package com.cookandroid.withmt.Writing;

import com.google.gson.annotations.SerializedName;

public class WritingRequest {
    @SerializedName("userId")
    private String userId;
    @SerializedName("title")
    private String title;
    @SerializedName("member")
    private String member;
    @SerializedName("link")
    private String link;
    @SerializedName("content")
    private String content;
    @SerializedName("gender")
    private int gender;
    @SerializedName("date")
    private String date;

    public WritingRequest(String userId, String title, String member, String link, String content, int gender, String date){
        this.userId = userId;
        this.title = title;
        this.member = member;
        this.link = link;
        this.content = content;
        this.gender = gender;
        this.date = date;
    }
}
