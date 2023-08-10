package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.District;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Division;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Taluka;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Village;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for profile
 */
public interface ProfileContract {

    interface ViewModel {
        void setUpAddress();
        void setUpDivision(List<Division> divisions);
        void setUpDistrict(List<District> district);
        void setUpTaluka(List<Taluka> talukas);
        void setUpVillage(List<Village> village);
        void showProfileFailed(String error);
    }
    interface Presenter {
        void getDivision();
        void getDistrict(String divisionCode);
        void getTaluka(String districtCode);
        void getVillage(String talukaCode);
    }
}
