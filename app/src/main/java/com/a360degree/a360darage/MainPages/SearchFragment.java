package com.a360degree.a360darage.MainPages;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a360degree.a360darage.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class SearchFragment extends Fragment {

    TabLayout mTabLayoutCats;
    ViewPager mViwPager;
    CatsStatePagerAdapter mCatsStatePagerAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View searchFragmentView = inflater.inflate(R.layout.fragment_search, container, false);

        init(searchFragmentView);

        return searchFragmentView;
    }

    private void init(View parent) {
        mTabLayoutCats = (TabLayout) parent.findViewById(R.id.tab_layout_cats);
        mViwPager = (ViewPager) parent.findViewById(R.id.view_pager_cats_content);
        //setup tabs
        mTabLayoutCats.setupWithViewPager(mViwPager, true);
        // setup the ViewPager
        setupViewPager();
    }

    private void setupViewPager() {
        mCatsStatePagerAdapter = new CatsStatePagerAdapter(getChildFragmentManager());
        //sample
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.photos));
        mCatsStatePagerAdapter.addFragment(new CategoryPageFragment(), getResources().getString(R.string.videos));

        mViwPager.setAdapter(mCatsStatePagerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class CatsStatePagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public CatsStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public CatsStatePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> fragmentTitleList) {
            super(fm);
            mFragmentList.addAll(fragmentList);
            mFragmentTitleList.addAll(fragmentTitleList);
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            //returning current tabs
            return mFragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    public static class CategoryPageFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_search_category_page, container, false);
        }
    }

}
