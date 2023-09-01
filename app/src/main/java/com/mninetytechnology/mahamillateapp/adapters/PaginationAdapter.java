package com.mninetytechnology.mahamillateapp.adapters;

import android.content.Context;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.databinding.ItemProgressBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleLeaderBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleOrganisationBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private List<OrganisationLoginObject> organisations;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;
    private List<OrganisationLoginObject> organisationListFiltered;
    private OnItemClickListener mListener;

    public PaginationAdapter(Context context, OnItemClickListener mListener) {
        this.context = context;
        this.mListener = mListener;
        organisations = new ArrayList<>();
        organisationListFiltered = new ArrayList<>();
    }

    public void setOrganisationLoginObjectList(List<OrganisationLoginObject> organisations1) {
        this.organisations = organisations1;
        this.organisationListFiltered = organisations1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                return new PaginationAdapter.OrganisationViewHolder(SingleOrganisationBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));
            case LOADING:
                return new PaginationAdapter.LoadingViewHolder(ItemProgressBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));
        }
        return new PaginationAdapter.OrganisationViewHolder(SingleOrganisationBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                OrganisationViewHolder organisationViewHolder = (OrganisationViewHolder) holder;
                OrganisationLoginObject organisation = organisationListFiltered.get(position);
                organisationViewHolder.binding.setOrganisation(organisation);
                organisationViewHolder.binding.textViewTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mListener != null) {
                            mListener.onItemClicked(organisation, holder.getAdapterPosition());
                        }
                    }
                });
                break;

            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.binding.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return organisationListFiltered == null ? 0 : organisationListFiltered.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == organisationListFiltered.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new OrganisationLoginObject());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = organisationListFiltered.size() - 1;
        OrganisationLoginObject result = getItem(position);

        if (result != null) {
            organisations.remove(position);
            organisationListFiltered.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(OrganisationLoginObject organisation) {
        organisationListFiltered.add(organisation);
        organisations.add(organisation);
        notifyItemInserted(organisationListFiltered.size() - 1);
    }

    public void addAll(List<OrganisationLoginObject> organizationResult) {
        for (OrganisationLoginObject result : organizationResult) {
            add(result);
        }
    }

    public OrganisationLoginObject getItem(int position) {
        return organisationListFiltered.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    organisationListFiltered = organisations;
                } else {
                    List<OrganisationLoginObject> filteredList = new ArrayList<>();
                    for (OrganisationLoginObject organisation : organisations) {
                        if (organisation.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(organisation);
                        }
                    }
                    organisationListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = organisationListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                organisationListFiltered = (ArrayList<OrganisationLoginObject>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class OrganisationViewHolder extends RecyclerView.ViewHolder {
        private SingleOrganisationBinding binding;
        public OrganisationViewHolder(SingleOrganisationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        private ItemProgressBinding binding;

        public LoadingViewHolder(ItemProgressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(OrganisationLoginObject listModel, int position);
    }

}
