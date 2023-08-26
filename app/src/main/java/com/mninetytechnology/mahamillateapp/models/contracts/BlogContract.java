package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface BlogContract {

    interface ViewModel {
        void setUpBlogAdapter(List<Blog> blogs);

        void showBlogFailed(String error);

        void shareBlog(Blog blog);
    }

    interface Presenter {
        void getBlogs();

        void updateLike(boolean isAdd, String blogId);

        void updateDislike(boolean isAdd, String blogId);

        void updateShare(Blog blog);

        void updateView(String blogId);
    }
}
