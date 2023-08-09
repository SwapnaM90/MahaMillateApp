package com.mninetytechnology.mahamillateapp.acitivities.ui.quiz;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.blog.BlogActivity;
import com.mninetytechnology.mahamillateapp.adapters.LeaderboardListAdapter;
import com.mninetytechnology.mahamillateapp.databinding.ActivityLeaderboardBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.LeaderboardContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.presenter.LeaderboardPresenter;

import java.util.List;

public class LeaderboardActivity extends BaseActivity implements LeaderboardContract.ViewModel {
    private ActivityLeaderboardBinding binding;
    private LeaderboardPresenter presenter;
    private LeaderboardListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_leaderboard);
        presenter = new LeaderboardPresenter(this,this);
        binding.setPresenter(presenter);
        presenter.getLeaderboards();
        binding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setUpLeaderboardAdapter(List<Leaderboard> leaderboard) {
        mAdapter = new LeaderboardListAdapter(this, leaderboard, new LeaderboardListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Leaderboard listModel, int position) {

            }
        });
        binding.rvLeaderboard.setLayoutManager(new LinearLayoutManager(LeaderboardActivity.this));
        binding.rvLeaderboard.setAdapter(mAdapter);
    }

    @Override
    public void showLeaderboardFailed(String error) {
        showErrorSnackBar(binding.getRoot(),error);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(QuizMainActivity.class,true);
    }
}