package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.SingleClass;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

import java.util.List;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Contract for register
 */
public interface RegisterContract {

    interface ViewModel {
        void register(UserLoginObject userLoginObject,String token);
        void showRegisterFailed(String error);

        void setUpClass(List<SingleClass> classes);
    }
    interface Presenter {
        void doRegistration();

        void getClassData();
    }
}
