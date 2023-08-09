package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Leaderboard {
    public String _id;
    public int firstLevel;
    public int secondLevel;
    public int thirdLevel;
    public LeaderboardUser userId;
    @SerializedName("class")
    public String myclass;
    public Date createdAt;
    public Date updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(int firstLevel) {
        this.firstLevel = firstLevel;
    }

    public int getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(int secondLevel) {
        this.secondLevel = secondLevel;
    }

    public int getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(int thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public LeaderboardUser getUserId() {
        return userId;
    }

    public void setUserId(LeaderboardUser userId) {
        this.userId = userId;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
