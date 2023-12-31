package com.mninetytechnology.mahamillateapp.acitivities.ui.user.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.adapters.LeaderboardListAdapter;
import com.mninetytechnology.mahamillateapp.databinding.FragmentLeaderboardBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.LeaderboardContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.presenter.LeaderboardPresenter;

import java.util.List;

public class LeaderboardFragment extends Fragment implements LeaderboardContract.ViewModel {
    private FragmentLeaderboardBinding binding;
    private LeaderboardPresenter presenter;
    private LeaderboardListAdapter mAdapter;
    private MainActivity mActivity;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false);
        mActivity = (MainActivity) getActivity();

        presenter = new LeaderboardPresenter(mActivity, this);
        binding.setPresenter(presenter);
        presenter.getLeaderboards();

        return binding.getRoot();
    }

    @Override
    public void setUpLeaderboardAdapter(List<UserLoginObject> leaderboard) {
        mAdapter = new LeaderboardListAdapter(mActivity, leaderboard, (listModel, position) -> {

        });
        binding.rvLeaderboard.setLayoutManager(new LinearLayoutManager(mActivity));
        binding.rvLeaderboard.setAdapter(mAdapter);
    }

    @Override
    public void showLeaderboardFailed(String error) {
        mActivity.showErrorSnackBar(binding.getRoot(), error);
    }

}
