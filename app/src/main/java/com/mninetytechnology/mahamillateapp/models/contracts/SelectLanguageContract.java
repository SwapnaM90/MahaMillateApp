package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Contract for splash
 */
public interface SelectLanguageContract {

    interface ViewModel {
        void startAnotherActivity(Class<?> cls, boolean finishCurrent);
    }

    interface Presenter {
        void continueNext();
    }
}
