package com.cookandroid.withmt.MainPage;

import com.google.gson.annotations.SerializedName;

public class MainList {

    @SerializedName("user")
    private UserData user;

    @SerializedName("board")
    private BoardData board;

    @SerializedName("distance")
    private Double distance;

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public BoardData getBoard() {
        return board;
    }

    public void setBoard(BoardData board) {
        this.board = board;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
