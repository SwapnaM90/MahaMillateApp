package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;

import java.util.List;

public class LeaderboardResponseModel {
    List<Leaderboard> Data;

    public List<Leaderboard> getData() {
        return Data;
    }

    public void setData(List<Leaderboard> data) {
        Data = data;
    }
}
