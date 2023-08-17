package com.mninetytechnology.mahamillateapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mninetytechnology.mahamillateapp.databinding.SingleBlogBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;

import java.util.List;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogHolder> {
    private static final String TAG = BlogListAdapter.class.getName();
    private final Context context;
    private final List<Blog> stateList;
    private final OnItemClickListener mListener;

    public BlogListAdapter(Context context, List<Blog> stateList, OnItemClickListener mListener) {
        this.context = context;
        this.stateList = stateList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public BlogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlogHolder(SingleBlogBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlogHolder holder, int position) {
        Blog listItem = stateList.get(position);
        holder.binding.setBlog(listItem);
        Glide.with(context)
                .load(listItem.image)
                .into(holder.binding.imgBlog);
        holder.binding.imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClicked(listItem, holder.getAdapterPosition());
                }
            }
        });
        holder.binding.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClicked(listItem, holder.getAdapterPosition());
                }
            }
        });
        holder.binding.imgLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    int like = stateList.get(holder.getAdapterPosition()).getLike();
                    like++;
                    stateList.get(holder.getAdapterPosition()).setLike(like);
                    notifyDataSetChanged();
                    if (mListener != null) {
                        mListener.updateLike(listItem, true, like);
                    }
                } else {
                    int like = stateList.get(holder.getAdapterPosition()).getLike();
                    like--;
                    stateList.get(holder.getAdapterPosition()).setLike(like);
                    notifyDataSetChanged();
                    if (mListener != null) {
                        mListener.updateLike(listItem, false, like);
                    }
                }
            }
        });

        holder.binding.imgDislike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    int dislike = stateList.get(holder.getAdapterPosition()).getDislike();
                    dislike++;
                    stateList.get(holder.getAdapterPosition()).setDislike(dislike);
                    notifyDataSetChanged();
                    if (mListener != null) {
                        mListener.updateDislike(listItem, true, dislike);
                    }
                } else {
                    int dislike = stateList.get(holder.getAdapterPosition()).getDislike();
                    dislike--;
                    stateList.get(holder.getAdapterPosition()).setDislike(dislike);
                    notifyDataSetChanged();
                    if (mListener != null) {
                        mListener.updateDislike(listItem, false, dislike);
                    }
                }
            }
        });


        holder.binding.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int share = stateList.get(holder.getAdapterPosition()).getShare();
                share++;
                stateList.get(holder.getAdapterPosition()).setShare(share);
                notifyDataSetChanged();
                if (mListener != null) {
                    mListener.updateShare(listItem, share);
                }
                holder.binding.imgShare.setEnabled(false);
            }
        });
        holder.binding.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int views = stateList.get(holder.getAdapterPosition()).getViews();
                views++;
                stateList.get(holder.getAdapterPosition()).setViews(views);
                notifyDataSetChanged();
                if (mListener != null) {
                    mListener.updateView(listItem, views);
                }
                holder.binding.imgView.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class BlogHolder extends RecyclerView.ViewHolder {
        private final SingleBlogBinding binding;

        public BlogHolder(SingleBlogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Blog listModel, int position);

        void updateLike(Blog listModel, boolean isAdd, int like);

        void updateDislike(Blog listModel, boolean isAdd, int dislike);

        void updateShare(Blog listModel, int share);

        void updateView(Blog listModel, int view);
    }
}
