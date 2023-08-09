package com.mninetytechnology.mahamillateapp.acitivities.ui.donate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mninetytechnology.mahamillateapp.databinding.FragmentDonateBinding;


public class DonateFragment extends Fragment {

    private FragmentDonateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonateViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DonateViewModel.class);

        binding = FragmentDonateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDonate;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}