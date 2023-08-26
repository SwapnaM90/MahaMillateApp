package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface LeaderboardContract {

    interface ViewModel {
        void setUpLeaderboardAdapter(List<UserLoginObject> leaderboards);

        void showLeaderboardFailed(String error);
    }

    interface Presenter {
        void getLeaderboards();
    }
}
