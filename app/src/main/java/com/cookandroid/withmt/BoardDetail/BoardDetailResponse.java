package com.cookandroid.withmt.BoardDetail;

import com.google.gson.annotations.SerializedName;

public class BoardDetailResponse {
    @SerializedName("user")
    private User user;
    @SerializedName("board")
    private Board board;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "BoardDetailResponse{" +
                "user=" + user +
                ", board=" + board +
                '}';
    }
}
