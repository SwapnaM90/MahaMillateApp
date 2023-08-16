package com.mninetytechnology.mahamillateapp.acitivities.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.blog.BlogActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.news.NewsActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.QuizMainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.videos.VideosActivity;
import com.mninetytechnology.mahamillateapp.databinding.FragmentHomeBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.YoutubeVideo;
import com.mninetytechnology.mahamillateapp.network.responsemodel.VideoResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;
import com.mninetytechnology.mahamillateapp.utils.StringUtil;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;

import org.eazegraph.lib.models.PieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainActivity mActivity;
    private YoutubeVideo youtubeVideo;
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        mActivity = (MainActivity) getActivity();

        binding.pieChart.addPieSlice(
                new PieModel(
                        "Grain",
                        400,
                        Color.parseColor("#D7AF19")));
        binding.pieChart.addPieSlice(
                new PieModel(
                        "Money",
                        600,
                        Color.parseColor("#AE5D57")));

        // To animate the pie chart
        binding.pieChart.startAnimation();

        binding.imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideosActivity();
            }
        });

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

        binding.llQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });

        binding.imgQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
            }
        });

        binding.tvQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizActivity();
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

        getVideos();

        return binding.getRoot();
    }

    private void openVideosActivity() {
        Intent intent = new Intent(getActivity(), VideosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openBlogActivity() {
        Intent intent = new Intent(getActivity(), BlogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openQuizActivity() {
        Intent intent = new Intent(getActivity(), QuizMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    private void openNewsActivity() {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void getVideos() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getVideos().enqueue(new Callback<VideoResponseModel>() {
                @Override
                public void onResponse(Call<VideoResponseModel> call, Response<VideoResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null && response.body().getData() != null) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            YoutubeVideo video = response.body().getData().get(i);
                            if(video.isSpecial) {
                                youtubeVideo = video;
                                break;
                            }
                        }
                        setUpVideo();
                    } else {
                        mActivity.showErrorSnackBar(binding.getRoot(),mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<VideoResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mActivity.showErrorSnackBar(binding.getRoot(),mActivity.getResources().getString(R.string.invalid_response));
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