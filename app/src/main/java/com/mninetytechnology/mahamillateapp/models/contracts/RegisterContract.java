package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.SingleClass;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

import java.util.List;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Contract for register
 */
public interface RegisterContract {

    interface ViewModel {
        void register(UserLoginObject userLoginObject, String token);

        void showRegisterFailed(String error);

        void setUpClass(List<SingleClass> classes);

        void setUpAddress();

        //void setUpDivision(List<Division> divisions);
        void setUpDistrict(List<String> district);

        void setUpTaluka(List<String> talukas);

        void setUpVillage(List<String> village);
    }

    interface Presenter {
        void doRegistration();

        void getClassData();

        //void getDivision();
        //void getDistrict(String divisionCode);
        void getDistrict();

        void getTaluka(String districtCode);

        void getVillage(String talukaCode);

        void populateOrganisation();
    }
}
