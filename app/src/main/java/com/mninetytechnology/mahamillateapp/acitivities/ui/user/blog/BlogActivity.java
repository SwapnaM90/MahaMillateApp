package com.mninetytechnology.mahamillateapp.acitivities.ui.user.blog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;

import androidx.core.text.HtmlKt;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.adapters.BlogListAdapter;
import com.mninetytechnology.mahamillateapp.databinding.ActivityBlogBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.BlogContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.presenter.BlogPresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BlogActivity extends BaseActivity implements BlogContract.ViewModel {
    private ActivityBlogBinding mBinding;
    private BlogPresenter mPresenter;
    private BlogListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_blog);
        setSupportActionBar(mBinding.toolbar2);
        mBinding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mPresenter = new BlogPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.getBlogs();
    }

    @Override
    public void setUpBlogAdapter(List<Blog> blogs) {
        adapter = new BlogListAdapter(BlogActivity.this, blogs, new BlogListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Blog listModel, int position) {

            }

            @Override
            public void updateLike(Blog listModel, boolean isAdd, int like) {
                mPresenter.updateLike(isAdd, listModel._id);
            }

            @Override
            public void updateDislike(Blog listModel, boolean isAdd, int dislike) {
                mPresenter.updateDislike(isAdd, listModel._id);
            }

            @Override
            public void updateShare(Blog listModel, int share) {
                mPresenter.updateShare(listModel);
            }

            @Override
            public void updateView(Blog listModel, int view) {
                mPresenter.updateView(listModel._id);
            }

        });
        mBinding.rvBlog.setLayoutManager(new LinearLayoutManager(BlogActivity.this));
        mBinding.rvBlog.setAdapter(adapter);
    }

    @Override
    public void showBlogFailed(String error) {
        showErrorSnackBar(mBinding.getRoot(), error);
    }

    @Override
    public void shareBlog(Blog blog) {
        String blogDescription = String.valueOf(HtmlKt.parseAsHtml(blog.description, Html.FROM_HTML_MODE_LEGACY,null,null));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, blogDescription);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM,getImageUri(blog.getImg()));
        intent.setType("*/*");
        intent.setPackage("com.whatsapp");
        startActivity(intent);

    }

    private Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), inImage, "share_image", null);
        return Uri.parse(path);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(MainActivity.class, true);
    }
}