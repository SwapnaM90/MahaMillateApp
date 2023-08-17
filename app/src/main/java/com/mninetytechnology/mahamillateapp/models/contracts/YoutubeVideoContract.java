package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.YoutubeVideo;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for youtube video
 */
public interface YoutubeVideoContract {

    interface ViewModel {
        void setUpYoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos);

        void showYoutubeVideoFailed(String error);
    }

    interface Presenter {
        void getYoutubeVideos();
    }
}
