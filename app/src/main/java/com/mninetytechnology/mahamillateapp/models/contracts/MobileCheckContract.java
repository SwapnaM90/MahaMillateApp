package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface MobileCheckContract {

    interface ViewModel {
        void sendOtp();
        void goToNextPage(String otp);
        void showResponseFailed(String error);
    }
    interface Presenter {
        void validateData();
        void getOtp();
    }
}
