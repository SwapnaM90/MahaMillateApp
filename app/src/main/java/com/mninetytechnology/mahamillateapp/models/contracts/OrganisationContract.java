package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface OrganisationContract {

    interface ViewModel {
        void organisationChanged(OrganisationLoginObject organisation);

        void showOrganisationLoginObjectFailed(String error);
    }

    interface Presenter {
        void changeOrganisation(OrganisationLoginObject organisation);
    }
}
