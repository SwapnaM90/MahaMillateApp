package com.mninetytechnology.mahamillateapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mninetytechnology.mahamillateapp.databinding.SingleLeaderBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;

import java.util.List;

public class LeaderboardListAdapter extends RecyclerView.Adapter<LeaderboardListAdapter.LeaderboardHolder> {
    private static final String TAG = LeaderboardListAdapter.class.getName();
    private final Context context;
    private final List<Leaderboard> stateList;
    private final OnItemClickListener mListener;

    public LeaderboardListAdapter(Context context, List<Leaderboard> stateList, OnItemClickListener mListener) {
        this.context = context;
        this.stateList = stateList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public LeaderboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardHolder(SingleLeaderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardHolder holder, int position) {
        Leaderboard listItem = stateList.get(position);
        holder.binding.setLeader(listItem);

    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class LeaderboardHolder extends RecyclerView.ViewHolder {
        private final SingleLeaderBinding binding;

        public LeaderboardHolder(SingleLeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Leaderboard listModel, int position);
    }
}
