package com.mninetytechnology.mahamillateapp.acitivities.ui.user;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityRegistrationBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleAddressBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.RegisterContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.SingleClass;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.presenter.RegisterPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RegistrationActivity extends BaseActivity implements RegisterContract.ViewModel {
    private ActivityRegistrationBinding binding;
    private RegisterPresenter presenter;
    private GlobalHelper helper;
    private SingleAddressBinding addressBinding;
    private AlertDialog dialog;
    private String phone_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        helper = new GlobalHelper(this);
        presenter = new RegisterPresenter(this, this);
        Intent intent = getIntent();
        if (intent.hasExtra(AppKeys.PHONE_NUMBER)) {
            phone_number = intent.getStringExtra(AppKeys.PHONE_NUMBER);
            presenter.phone_number.set(phone_number);
        }
        binding.setPresenter(presenter);
        presenter.getClassData();
    }

    @Override
    public void register(UserLoginObject userLoginObject, String token) {
        try {
            Calendar calendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dateTime = sdf.format(calendar.getTime());
            helper.getSharedPreferencesHelper().setLoginDateTimeData(dateTime);

            //save user id and role in shared preference
            Gson gson = new Gson();
            String user = gson.toJson(userLoginObject, UserLoginObject.class);
            helper.getSharedPreferencesHelper().setPrefLoginUser(user);
            helper.getSharedPreferencesHelper().setLoginServerUserId(userLoginObject.get_id());
            helper.getSharedPreferencesHelper().setLoginServerUserClass(userLoginObject.getMyclass());
            helper.getSharedPreferencesHelper().setLoginKey(token);

            //start otp check activity
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(AppKeys.loginUserId, userLoginObject.get_id());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityOnTop(false, intent);
        } catch (Exception e) {
            Log.d(TAG, "login: " + e.getMessage());
            ScreenHelper.showErrorSnackBar(binding.getRoot(), e.getMessage());
        }
    }

    @Override
    public void showRegisterFailed(String error) {
        ScreenHelper.showErrorSnackBar(binding.getRoot(), error);
    }

    @Override
    public void setUpClass(List<SingleClass> classes) {
        ArrayAdapter<SingleClass> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, classes);
        binding.sprClassText.setAdapter(adapter);
        binding.sprClassText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.user_class.set(classes.get(i)._id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setUpAddress() {
        dialog = new AlertDialog.Builder(RegistrationActivity.this).create();
        dialog.setCancelable(false);
        addressBinding = SingleAddressBinding.inflate(getLayoutInflater());
        dialog.setView(addressBinding.getRoot());
        setUpAddressView();
        dialog.show();
    }

//    @Override
//    public void setUpDivision(List<Division> divisions) {
//        ArrayAdapter<Division> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item,divisions);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, district);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, talukas);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, village);
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
//                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.please_select_division), Toast.LENGTH_SHORT).show();
//                }else
                if (presenter.district.get().isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.please_select_district), Toast.LENGTH_SHORT).show();
                } else if (presenter.taluka.get().isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.please_select_taluka), Toast.LENGTH_SHORT).show();
                } else if (presenter.village.get().isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.please_select_village), Toast.LENGTH_SHORT).show();
                } else {
                    presenter.address.set(presenter.village.get() + "," + presenter.taluka.get() + "," + presenter.district.get() + "," + getResources().getString(R.string.maharashtra));
//                    presenter.address.set(presenter.village.get()+","+presenter.taluka.get()+","+presenter.district.get()+","+presenter.division.get()+","+getResources().getString(R.string.maharashtra));
                    getGlobalHelper().getSharedPreferencesHelper().setAddress(presenter.address.get());
                    dialog.dismiss();
                }
            }
        });
        presenter.getDistrict();
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(LoginActivity.class, true);
    }
}