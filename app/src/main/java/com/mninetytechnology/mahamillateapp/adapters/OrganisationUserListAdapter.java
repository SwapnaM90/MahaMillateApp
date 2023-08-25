package com.mninetytechnology.mahamillateapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mninetytechnology.mahamillateapp.databinding.SingleLeaderBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleOrganisationUserBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

import java.util.List;

public class OrganisationUserListAdapter extends RecyclerView.Adapter<OrganisationUserListAdapter.UserLoginObjectHolder> {
    private static final String TAG = OrganisationUserListAdapter.class.getName();
    private final Context context;
    private final List<UserLoginObject> stateList;
    private final OnItemClickListener mListener;

    public OrganisationUserListAdapter(Context context, List<UserLoginObject> stateList, OnItemClickListener mListener) {
        this.context = context;
        this.stateList = stateList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public UserLoginObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserLoginObjectHolder(SingleOrganisationUserBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserLoginObjectHolder holder, int position) {
        UserLoginObject listItem = stateList.get(position);
        holder.binding.setUser(listItem);

    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class UserLoginObjectHolder extends RecyclerView.ViewHolder {
        private final SingleOrganisationUserBinding binding;

        public UserLoginObjectHolder(SingleOrganisationUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(UserLoginObject listModel, int position);
    }
}
