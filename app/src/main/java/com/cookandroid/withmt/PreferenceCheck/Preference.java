package com.cookandroid.withmt.PreferenceCheck;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Preference {

    @SerializedName("friendship")
    public String friendship;

    @SerializedName("climbingMate")
    private String climbingMate;

    @SerializedName("climbingLevel")
    private String climbingLevel;

    @SerializedName("difficulty")
    private String difficulty;

    @SerializedName("exercise")
    private String exercise;

    @SerializedName("frequency")
    private String frequency;

    public String getFriendship ()
    {
        return friendship;
    }

    public void setFriendship (String friendship)
    {
        this.friendship = friendship;
    }

    public String getClimbingMate ()
    {
        return climbingMate;
    }

    public void setClimbingMate (String climbingMate)
    {
        this.climbingMate = climbingMate;
    }

    public String getClimbingLevel ()
    {
        return climbingLevel;
    }

    public void setClimbingLevel (String climbingLevel)
    {
        this.climbingLevel = climbingLevel;
    }

    public String getDifficulty ()
    {
        return difficulty;
    }

    public void setDifficulty (String difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getExercise ()
    {
        return exercise;
    }

    public void setExercise (String exercise)
    {
        this.exercise = exercise;
    }

    public String getFrequency ()
    {
        return frequency;
    }

    public void setFrequency (String frequency)
    {
        this.frequency = frequency;
    }

    public Preference(String climbingLevel, String difficulty, String exercise, String frequency, String friendship, String climbingMate ) {
        this.climbingLevel = climbingLevel;
        this.difficulty = difficulty;
        this.exercise = exercise;
        this.frequency = frequency;
        this.friendship = friendship;
        this.climbingMate = climbingMate;
    }

    @Override
    public String toString()
    {
        return "PutPreference [difficulty = "+difficulty+", friendship = "+friendship+", climbingMate = "+climbingMate+", exercise = "+exercise+", climbingLevel = "+climbingLevel+", frequency = "+frequency+"]";
    }

}
