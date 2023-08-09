package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Contract for register
 */
public interface RegisterContract {

    interface ViewModel {
        void register(UserLoginObject userLoginObject,String token);
        void showRegisterFailed(String error);
    }
    interface Presenter {
        void doRegistration();
    }
}
