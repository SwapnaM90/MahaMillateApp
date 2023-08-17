package com.mninetytechnology.mahamillateapp.acitivities.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.intro.Info1Fragment;
import com.mninetytechnology.mahamillateapp.databinding.ActivityIntroBinding;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class IntroActivity extends BaseActivity implements View.OnClickListener {
    private ActivityIntroBinding binding;

    private int selectPage = 0;
    private MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_intro);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        if (!getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId().trim().isEmpty()) {
            startActivityOnTop(MainActivity.class,true);
        }
        binding.vpPager.setAdapter(adapterViewPager);
        DotsIndicator extensiblePageIndicator = (DotsIndicator) findViewById(R.id.flexibleIndicator);
        extensiblePageIndicator.setViewPager(binding.vpPager);
        binding.vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("data", "jsadlj");
            }

            @Override
            public void onPageSelected(int position) {
                selectPage = position;

//                if (position == 0 || position == 1) {
//
//                    binding.btnNext.setText("Next");
//                } else
                    if (position == 0) {

                    binding.btnNext.setText("Finish");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("sjlkj", "sjahdal");
            }
        });
        binding.btnNext.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_next) {
//            if (selectPage == 0) {
//                binding.vpPager.setCurrentItem(1);
//            } else if (selectPage == 1) {
//                binding.vpPager.setCurrentItem(2);
//            } else
            if (selectPage == 0) {
                if (getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId().trim().isEmpty()) {
                    startActivityOnTop(LoginActivity.class,true);
                } else {
                    startActivityOnTop(MainActivity.class,true);
                }
            }
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private final int numItems = 1;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return numItems;
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return Info1Fragment.newInstance();
//                case 1:
//                    return Info2Fragment.newInstance();
//                case 2:
//                    return Info3Fragment.newInstance();
            }
            return null;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.e("page", "" + position);
            return "Page " + position;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            return fragment;
        }

    }
}