package com.cookandroid.withmt.MainPage;

public class MainList {
    private String titleStr ;
    private String dateStr ;
    private String genderStr ;
    private String ageStr ;
//    private String user_iconStr ;
//    private String userStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDate(String date) {
        dateStr = date ;
    }
    public void setGender(String gender) {
        genderStr = gender ;
    }
    public void setAge(String age) {
        ageStr = age ;
    }
//    public void setUserIcon(String user_icon) {
//        user_iconStr = user_icon ;
//    }
//    public void setUser(String user) {
//        userStr = user ;
//    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDate() {
        return this.dateStr ;
    }
    public String getGender() {
        return this.genderStr ;
    }
    public String getAge() {
        return this.ageStr ;
    }
//    public String getUserIcon() {
//        return this.user_iconStr ;
//    }
//    public String getUser() {
//        return this.userStr ;
//    }
}
