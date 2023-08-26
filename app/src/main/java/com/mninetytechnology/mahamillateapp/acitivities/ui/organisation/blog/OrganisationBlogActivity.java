package com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.blog;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.OrganisationMainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.adapters.BlogListAdapter;
import com.mninetytechnology.mahamillateapp.databinding.ActivityOrganisationBlogBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.BlogContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.presenter.OrganisationBlogPresenter;

import java.util.List;

public class OrganisationBlogActivity extends BaseActivity implements BlogContract.ViewModel {
    private ActivityOrganisationBlogBinding mBinding;
    private OrganisationBlogPresenter mPresenter;
    private BlogListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_organisation_blog);
        setSupportActionBar(mBinding.toolbar2);
        mBinding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mPresenter = new OrganisationBlogPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.getBlogs();
    }

    @Override
    public void setUpBlogAdapter(List<Blog> blogs) {
        adapter = new BlogListAdapter(OrganisationBlogActivity.this, blogs, new BlogListAdapter.OnItemClickListener() {
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
        mBinding.rvBlog.setLayoutManager(new LinearLayoutManager(OrganisationBlogActivity.this));
        mBinding.rvBlog.setAdapter(adapter);
    }

    @Override
    public void showBlogFailed(String error) {
        showErrorSnackBar(mBinding.getRoot(), error);
    }

    @Override
    public void shareBlog(Blog blog) {

    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(OrganisationMainActivity.class, true);
    }
}