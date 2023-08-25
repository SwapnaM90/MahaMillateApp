package com.mninetytechnology.mahamillateapp.acitivities.ui.user;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivitySelectUserBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.models.contracts.SelectUserContract;
import com.mninetytechnology.mahamillateapp.presenter.SelectUserPresenter;

public class SelectUserActivity extends BaseActivity implements SelectUserContract.ViewModel {
    private ActivitySelectUserBinding binding;
    private SelectUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SelectUserPresenter(this);
        presenter.rootView = binding.getRoot();
        binding.setPresenter(presenter);
        binding.rgSelectUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_organisation) {
                    saveUserType(AppKeys.ORGANISATION);
                }else if (i == R.id.rb_user) {
                    saveUserType(AppKeys.USER);
                }
            }
        });
    }

    /**
     * saves UserType in shared pref
     * @param user_type
     */
    private void saveUserType(String user_type) {
        getGlobalHelper().getSharedPreferencesHelper().setUser(user_type);
        startActivityOnTop(LoginActivity.class,true);
    }

    @Override
    public void startAnotherActivity(Class<?> cls, boolean finishCurrent) {
        startActivityOnTop(cls, finishCurrent);
    }
}