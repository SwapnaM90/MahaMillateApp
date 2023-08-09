package com.mninetytechnology.mahamillateapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.SingleVideoBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.YoutubeVideo;
import com.mninetytechnology.mahamillateapp.utils.StringUtil;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;

import java.util.List;

public class YoutubeVideoListAdapter extends RecyclerView.Adapter<YoutubeVideoListAdapter.YoutubeVideoHolder> {
    private static final String TAG = YoutubeVideoListAdapter.class.getName();
    private final BaseActivity context;
    private final List<YoutubeVideo> stateList;
    private final OnItemClickListener mListener;

    DisplayMetrics displayMetrics = new DisplayMetrics();

    public YoutubeVideoListAdapter(BaseActivity context, List<YoutubeVideo> stateList, OnItemClickListener mListener) {
        this.context = context;
        this.stateList = stateList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public YoutubeVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YoutubeVideoHolder(SingleVideoBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoHolder holder, int position) {
        YoutubeVideo listItem = stateList.get(position);
        holder.binding.setYoutubeVideo(listItem);
        holder.binding.imageViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClicked(listItem, holder.getAdapterPosition());
                }
            }
        });
        context.getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        String video_id = StringUtil.getYoutubeID(listItem.getUrl());
        String image_url = StringUtil.getYoutubThumbnailUrl(listItem.getUrl());
        Glide.with(context)
                .load(image_url).
                apply(new RequestOptions().override(width - 36, 200))
                .into(holder.binding.imageViewItem);
        context.getLifecycle().addObserver(holder.binding.youtubeView);
        holder.binding.youtubeView.getPlayerUIController();
        holder.binding.youtubeView.initialize(
                initializedYouTubePlayer -> initializedYouTubePlayer.addListener(
                        new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady() {
                                initializedYouTubePlayer.loadVideo(video_id, 0);
                            }
                        }), true);
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class YoutubeVideoHolder extends RecyclerView.ViewHolder {
        private final SingleVideoBinding binding;

        public YoutubeVideoHolder(SingleVideoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void  onItemClicked(YoutubeVideo listModel, int position);
    }
}
