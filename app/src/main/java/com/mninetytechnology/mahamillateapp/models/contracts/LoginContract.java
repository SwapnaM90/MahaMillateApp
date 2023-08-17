package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Contract for login
 */
public interface LoginContract {

    interface ViewModel {
        void login(UserLoginObject userLoginObject, String token);

        void showLoginFailed(String error);
    }

    interface Presenter {
        void doLogin();
    }
}
