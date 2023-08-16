package com.mninetytechnology.mahamillateapp.acitivities.ui.videos;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.adapters.YoutubeVideoListAdapter;
import com.mninetytechnology.mahamillateapp.databinding.ActivityVideosBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.YoutubeVideoContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.YoutubeVideo;
import com.mninetytechnology.mahamillateapp.presenter.YoutubeVideoPresenter;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends BaseActivity implements YoutubeVideoContract.ViewModel {
    private ActivityVideosBinding mBinding;
    private YoutubeVideoPresenter mPresenter;

    private YoutubeVideoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_videos);
        setSupportActionBar(mBinding.toolbar2);
        mBinding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mPresenter = new YoutubeVideoPresenter(this,this);
        mBinding.setPresenter(mPresenter);
        mPresenter.getYoutubeVideos();
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(MainActivity.class,true);
    }

    @Override
    public void setUpYoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos) {
        List<YoutubeVideo> youtubeVideoList = new ArrayList<>();
        for (int i = 0; i < youtubeVideos.size(); i++) {
            if (!youtubeVideos.get(i).isSpecial) {
                youtubeVideoList.add(youtubeVideos.get(i));
            }
        }
        adapter = new YoutubeVideoListAdapter(VideosActivity.this, youtubeVideoList, new YoutubeVideoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(YoutubeVideo listModel, int position) {

            }
        });
        mBinding.rvVideos.setLayoutManager(new LinearLayoutManager(VideosActivity.this));
        mBinding.rvVideos.setAdapter(adapter);
    }

    @Override
    public void showYoutubeVideoFailed(String error) {
        showErrorSnackBar(mBinding.getRoot(),error);
    }
}