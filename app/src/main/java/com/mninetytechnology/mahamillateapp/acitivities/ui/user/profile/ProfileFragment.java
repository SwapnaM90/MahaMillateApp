package com.mninetytechnology.mahamillateapp.acitivities.ui.user.profile;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.SelectUserActivity;
import com.mninetytechnology.mahamillateapp.databinding.FragmentProfileBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleAddressBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.ProfileContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.presenter.ProfilePresenter;

import java.util.List;

public class ProfileFragment extends Fragment implements ProfileContract.ViewModel {

    private FragmentProfileBinding binding;
    private MainActivity mActivity;
    private ProfilePresenter presenter;
    private SingleAddressBinding addressBinding;
    private AlertDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mActivity = (MainActivity) getActivity();
        presenter = new ProfilePresenter(mActivity, this);
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.getGlobalHelper().getSharedPreferencesHelper().clear();
                mActivity.startActivityOnTop(SelectUserActivity.class, true);
            }
        });
        if (!mActivity.getGlobalHelper().getSharedPreferencesHelper().getAddress().trim().isEmpty()) {
            presenter.address.set(mActivity.getGlobalHelper().getSharedPreferencesHelper().getAddress());
        }
        Gson gson = new Gson();
        String userStr = mActivity.getGlobalHelper().getSharedPreferencesHelper().getPrefLoginUser();
        UserLoginObject obj = gson.fromJson(userStr, UserLoginObject.class);
        if (mActivity.getGlobalHelper().getSharedPreferencesHelper().getOrganisationId().trim().isEmpty()) {
            presenter.organisation.set(getString(R.string.change_organisation));
        } else {
            String organisationId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getOrganisationId();
            OrganisationLoginObject obj_org = gson.fromJson(organisationId, OrganisationLoginObject.class);
            presenter.organisation.set(obj_org.getName());
        }
        if (obj != null) {
            binding.tvUserName.setText(obj.getName());
            binding.tvRefralCode.setText(obj.getReferral_code());
            presenter.address.set("" + obj.getVillage() + "," + obj.getDistrict() + "," + obj.getState());
            presenter.district.set(obj.getDistrict());
            presenter.village.set(obj.getVillage());
        }
        binding.setPresenter(presenter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setUpAddress() {
        dialog = new AlertDialog.Builder(mActivity).create();
        dialog.setCancelable(false);
        addressBinding = SingleAddressBinding.inflate(getLayoutInflater());
        dialog.setView(addressBinding.getRoot());
        setUpAddressView();
        dialog.show();
    }

//    @Override
//    public void setUpDivision(List<Division> divisions) {
//        ArrayAdapter<Division> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item,divisions);
//        addressBinding.sprDivision.setAdapter(adapter);
//        addressBinding.sprDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Division selectedDivision = divisions.get(i);
//                presenter.division.set(selectedDivision.divname11);
//                presenter.getDistrict(selectedDivision.dvncode);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

    @Override
    public void setUpDistrict(List<String> district) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item, district);
        addressBinding.sprDistrict.setAdapter(adapter);
        addressBinding.sprDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDistrict = district.get(i);
                presenter.district.set(selectedDistrict);
                presenter.getTaluka(selectedDistrict);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setUpTaluka(List<String> talukas) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item, talukas);
        addressBinding.sprTaluka.setAdapter(adapter);
        addressBinding.sprTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedTaluka = talukas.get(i);
                presenter.taluka.set(selectedTaluka);
                presenter.getVillage(selectedTaluka);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setUpVillage(List<String> village) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item, village);
        addressBinding.sprVillage.setAdapter(adapter);
        addressBinding.sprVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedVillage = village.get(i);
                presenter.village.set(selectedVillage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpAddressView() {
        addressBinding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (presenter.division.get().isEmpty()) {
//                    Toast.makeText(mActivity, getResources().getString(R.string.please_select_division), Toast.LENGTH_SHORT).show();
//                }else
                if (presenter.district.get().isEmpty()) {
                    Toast.makeText(mActivity, getResources().getString(R.string.please_select_district), Toast.LENGTH_SHORT).show();
                } else if (presenter.taluka.get().isEmpty()) {
                    Toast.makeText(mActivity, getResources().getString(R.string.please_select_taluka), Toast.LENGTH_SHORT).show();
                } else if (presenter.village.get().isEmpty()) {
                    Toast.makeText(mActivity, getResources().getString(R.string.please_select_village), Toast.LENGTH_SHORT).show();
                } else {
                    presenter.address.set(presenter.village.get() + "," + presenter.taluka.get() + "," + presenter.district.get() + "," + getResources().getString(R.string.maharashtra));
//                    presenter.address.set(presenter.village.get()+","+presenter.taluka.get()+","+presenter.district.get()+","+presenter.division.get()+","+getResources().getString(R.string.maharashtra));
                    mActivity.getGlobalHelper().getSharedPreferencesHelper().setAddress(presenter.address.get());
                    dialog.dismiss();
                    presenter.updateAddress();
                }
            }
        });
        presenter.getDistrict();
    }

    @Override
    public void showProfileFailed(String error) {

    }

}