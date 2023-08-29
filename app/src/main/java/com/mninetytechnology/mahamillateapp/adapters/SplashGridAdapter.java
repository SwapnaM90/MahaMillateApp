package com.mninetytechnology.mahamillateapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mninetytechnology.mahamillateapp.databinding.SingleBlogBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleSplashCardBinding;

import java.util.List;

public class SplashGridAdapter extends ArrayAdapter<Integer> {
    private Integer[] objects;
    private ViewHolder holder;

    public SplashGridAdapter(@NonNull Context context, @NonNull Integer[] objects) {
        super(context, 0, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Layout Inflater inflates each item to be displayed in GridView.
        holder = new ViewHolder(SingleSplashCardBinding.inflate(LayoutInflater.from(getContext()),parent,false));
        holder.binding.imgSplash.setImageResource(getItem(position));

        return holder.binding.getRoot();
    }

    @Nullable
    @Override
    public Integer getItem(int position) {
        return objects[position];
    }

    public class ViewHolder {
        private final SingleSplashCardBinding binding;

        public ViewHolder(SingleSplashCardBinding binding) {
            this.binding = binding;
        }
    }
}
