package com.mninetytechnology.mahamillateapp.acitivities.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private MainActivity mActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mActivity = (MainActivity) getActivity();
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.getGlobalHelper().getSharedPreferencesHelper().clear();
                mActivity.startActivityOnTop(LoginActivity.class,true);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}