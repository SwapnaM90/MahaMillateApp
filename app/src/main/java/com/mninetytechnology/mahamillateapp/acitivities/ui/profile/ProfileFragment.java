package com.mninetytechnology.mahamillateapp.acitivities.ui.profile;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.RegistrationActivity;
import com.mninetytechnology.mahamillateapp.databinding.FragmentProfileBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleAddressBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.ProfileContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.District;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Division;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Taluka;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Village;
import com.mninetytechnology.mahamillateapp.presenter.ProfilePresenter;

import java.util.List;

public class ProfileFragment extends Fragment implements ProfileContract.ViewModel {

    private FragmentProfileBinding binding;
    private MainActivity mActivity;
    private ProfilePresenter presenter;
    private SingleAddressBinding addressBinding;

    private AlertDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mActivity = (MainActivity) getActivity();
        presenter = new ProfilePresenter(mActivity,this);
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.getGlobalHelper().getSharedPreferencesHelper().clear();
                mActivity.startActivityOnTop(LoginActivity.class,true);
            }
        });
        if (!mActivity.getGlobalHelper().getSharedPreferencesHelper().getAddress().trim().isEmpty()) {
            presenter.address.set(mActivity.getGlobalHelper().getSharedPreferencesHelper().getAddress());
        }
        Gson gson = new Gson();
        String userStr = mActivity.getGlobalHelper().getSharedPreferencesHelper().getPrefLoginUser();
        UserLoginObject obj = gson.fromJson(userStr,UserLoginObject.class);
        if (obj != null) {
            binding.tvUserName.setText(obj.getName());
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
    public void setUpDistrict(List<District> district) {
        ArrayAdapter<District> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item,district);
        addressBinding.sprDistrict.setAdapter(adapter);
        addressBinding.sprDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                District selectedDistrict = district.get(i);
                presenter.district.set(selectedDistrict.district);
                presenter.getTaluka(selectedDistrict.district_code_2011);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setUpTaluka(List<Taluka> talukas) {
        ArrayAdapter<Taluka> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item,talukas);
        addressBinding.sprTaluka.setAdapter(adapter);
        addressBinding.sprTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Taluka selectedTaluka = talukas.get(i);
                presenter.taluka.set(selectedTaluka.taluka);
                presenter.getVillage(selectedTaluka.taluka_code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setUpVillage(List<Village> village) {
        ArrayAdapter<Village> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item,village);
        addressBinding.sprVillage.setAdapter(adapter);
        addressBinding.sprVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Village selectedVillage = village.get(i);
                presenter.village.set(selectedVillage.village);
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
                }else if (presenter.taluka.get().isEmpty()) {
                    Toast.makeText(mActivity, getResources().getString(R.string.please_select_taluka), Toast.LENGTH_SHORT).show();
                }else if (presenter.village.get().isEmpty()) {
                    Toast.makeText(mActivity, getResources().getString(R.string.please_select_village), Toast.LENGTH_SHORT).show();
                } else {
                    presenter.address.set(presenter.village.get()+","+presenter.taluka.get()+","+presenter.district.get()+","+getResources().getString(R.string.maharashtra));
//                    presenter.address.set(presenter.village.get()+","+presenter.taluka.get()+","+presenter.district.get()+","+presenter.division.get()+","+getResources().getString(R.string.maharashtra));
                    mActivity.getGlobalHelper().getSharedPreferencesHelper().setAddress(presenter.address.get());
                    dialog.dismiss();
                }
            }
        });
        presenter.getDistrict();
    }

    @Override
    public void showProfileFailed(String error) {

    }

}