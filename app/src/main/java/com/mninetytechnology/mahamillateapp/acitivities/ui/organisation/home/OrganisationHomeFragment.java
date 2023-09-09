package com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.OrganisationMainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.blog.OrganisationBlogActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.news.OrganisationNewsActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.videos.OrganisationVideosActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.SelectLanguageActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.blog.BlogActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.news.NewsActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.videos.VideosActivity;
import com.mninetytechnology.mahamillateapp.databinding.FragmentHomeBinding;
import com.mninetytechnology.mahamillateapp.databinding.FragmentOrganisationHomeBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.YoutubeVideo;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationLoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.VideoResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;
import com.mninetytechnology.mahamillateapp.utils.StringUtil;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;

import org.eazegraph.lib.models.PieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganisationHomeFragment extends Fragment {
    private FragmentOrganisationHomeBinding binding;
    private OrganisationMainActivity mActivity;
    private YoutubeVideo youtubeVideo;
    private final DisplayMetrics displayMetrics = new DisplayMetrics();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrganisationHomeBinding.inflate(inflater, container, false);
        mActivity = (OrganisationMainActivity) getActivity();

        binding.imgVideo.setOnClickListener(view -> openVideosActivity());

        binding.tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideosActivity();
            }
        });

        binding.llBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogActivity();
            }
        });

        binding.imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogActivity();
            }
        });

        binding.tvBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogActivity();
            }
        });

        binding.imgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewsActivity();
            }
        });

        binding.tvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewsActivity();
            }
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getOrganisationVerification();

        return binding.getRoot();
    }

    private void openVideosActivity() {
        Intent intent = new Intent(getActivity(), OrganisationVideosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openBlogActivity() {
        Intent intent = new Intent(getActivity(), OrganisationBlogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openNewsActivity() {
        Intent intent = new Intent(getActivity(), OrganisationNewsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getOrganisationVerification() {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
            RetrofitClient.getApiService().getOrganisationByID(userId).enqueue(new Callback<OrganisationLoginResponseModel>() {
                @Override
                public void onResponse(Call<OrganisationLoginResponseModel> call, Response<OrganisationLoginResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null && response.body().getData() != null) {
                        OrganisationLoginObject userLoginObject = response.body().getData();
                        Gson gson = new Gson();
                        String user = gson.toJson(userLoginObject, OrganisationLoginObject.class);
                        mActivity.getGlobalHelper().getSharedPreferencesHelper().setPrefLoginUser(user);
                        mActivity.getGlobalHelper().getSharedPreferencesHelper().setLoginServerUserId(userLoginObject.get_id());
                        mActivity.getGlobalHelper().getSharedPreferencesHelper().setUser(AppKeys.ORGANISATION);
                        if (userLoginObject.isVerified()) {
                            binding.tvVerification.setVisibility(View.VISIBLE);
                        } else {
                            binding.tvVerification.setVisibility(View.GONE);
                        }
                        getVideos();
                    } else {
                        mActivity.showErrorSnackBar(binding.getRoot(), mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<OrganisationLoginResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mActivity.showErrorSnackBar(binding.getRoot(), mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }
    public void getVideos() {
        if (mActivity.isInternetConnected()) {
            //mActivity.startProgressDialog(mActivity);

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getVideos().enqueue(new Callback<VideoResponseModel>() {
                @Override
                public void onResponse(Call<VideoResponseModel> call, Response<VideoResponseModel> response) {
              //      mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null && response.body().getData() != null) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            YoutubeVideo video = response.body().getData().get(i);
                            if (video.isSpecial) {
                                youtubeVideo = video;
                                break;
                            }
                        }
                        setUpVideo();
                    } else {
                        mActivity.showErrorSnackBar(binding.getRoot(), mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<VideoResponseModel> call, Throwable t) {
                //    mActivity.dismissProgressDialog();
                    mActivity.showErrorSnackBar(binding.getRoot(), mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    private void setUpVideo() {
        mActivity.getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        String video_id = StringUtil.getYoutubeID(youtubeVideo.getUrl());
        mActivity.getLifecycle().addObserver(binding.youtubeView);
        binding.textViewTitle.setText(youtubeVideo.title);
        binding.youtubeView.getPlayerUIController();
        binding.youtubeView.initialize(
                initializedYouTubePlayer -> initializedYouTubePlayer.addListener(
                        new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady() {
                                initializedYouTubePlayer.loadVideo(video_id, 0);
                                initializedYouTubePlayer.pause();
                            }
                        }), true);
    }
}