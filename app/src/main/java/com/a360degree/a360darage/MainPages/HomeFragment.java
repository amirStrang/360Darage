package com.a360degree.a360darage.MainPages;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a360degree.a360darage.Models.VRPhotoCard;
import com.a360degree.a360darage.R;
import com.a360degree.a360darage.Utils.VRLibManager;
import com.a360degree.a360darage.Utils.VRPhotoCardsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    ContentPagerAdapter mContentPagerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        init(homeFragmentView);

        return homeFragmentView;
    }

    private void init(View parent) {
        mTabLayout = (TabLayout) parent.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) parent.findViewById(R.id.view_pager_content);
        //setup tabs
        mTabLayout.setupWithViewPager(mViewPager, true);
        // setup the ViewPager
        setupViewPager();
    }

    private void setupViewPager() {
        mContentPagerAdapter = new ContentPagerAdapter(getChildFragmentManager());
        mContentPagerAdapter.addFragment(new HomePicsFragment(), getResources().getString(R.string.photos));
        mContentPagerAdapter.addFragment(new HomeVideosFragment(), getResources().getString(R.string.videos));
        mViewPager.setAdapter(mContentPagerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private class ContentPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ContentPagerAdapter(FragmentManager fm) {
            super(fm);
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

    public static class HomePicsFragment extends Fragment {

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private VRLibManager mVRManager;

        private List<VRPhotoCard> mVRPhotoCardList = new ArrayList<VRPhotoCard>();


        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_home_pics, container, false);

            init(rootView);

            return rootView;
        }

        private void init(View rootView) {
            mVRManager = new VRLibManager(this.getContext());

            mRecyclerView = rootView.findViewById(R.id.rec_home_pic);
            // for improve performance
            mRecyclerView.setHasFixedSize(true);
            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(rootView.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            //create list
            VRPhotoCard a = new VRPhotoCard("https://api.androidhive.info/json/movies/4.jpg", "amad bahare janha", false, false,
                    "http://360darage.com/images/picture_thumbnail/fUNDEYHKg8r84GBclDtO3hHjuMQABJ3o.jpg", "title", false, "8 feb",
                    56, 74);
            mVRPhotoCardList.add(a);
            a = new VRPhotoCard("https://api.androidhive.info/json/movies/4.jpg", "سلام من به تو ای یار قدیمی گلم پیش من بمون نرو نگو چرا گل می خورم", true, true,
                    "http://360darage.com/images/picture_thumbnail/bYwzyviPaTSWjNmDnAzJsTGdZw17kMiq.jpg", "سلام من به تو ای یار قدیمی گلم پیش من بمون نرو نگو چرا گل می خورم", true, "8 feb",
                    56, 74);
            mVRPhotoCardList.add(a);

            a = new VRPhotoCard("https://api.androidhive.info/json/movies/4.jpg", "amad bahare janha", false, true,
                    "http://360darage.com/images/picture_thumbnail/DnKC8291A6CgIxG6a5tZ0rnfVNdBeQC6.jpg", "title", false, "8 feb",
                    56, 74);
            mVRPhotoCardList.add(a);

            a = new VRPhotoCard("https://api.androidhive.info/json/movies/4.jpg", "سلام من به تو ای یار قدیمی", true, false,
                    "http://360darage.com/picture_thumbnail/rybshZh5XAUMT7CB4XEousPiylr5swJj.jpg", "یه پست مشتی باحال پر از اتفاقای جالیب و جذاب", false, "8 feb",
                    56, 74);
            mVRPhotoCardList.add(a);

            a = new VRPhotoCard("https://api.androidhive.info/json/movies/4.jpg", "amad bahare janha", false, false,
                    "http://360darage.com/picture_thumbnail/DnKC8291A6CgIxG6a5tZ0rnfVNdBeQC6.jpg", "title", true, "10 دقیقه پیش",
                    56008, 745864);
            mVRPhotoCardList.add(a);

            a = new VRPhotoCard("https://api.androidhive.info/json/movies/4.jpg", "amad bahare janha", false, false,
                    "http://360darage.com/picture_thumbnail/3EfXzjVXfc9HZSOaVyrpnNdkwDrF0s7g.jpg", "title", false, "8 feb",
                    56, 74);
            mVRPhotoCardList.add(a);

            // specify an adapter
            mAdapter = new VRPhotoCardsAdapter(rootView.getContext(), mVRPhotoCardList, mVRManager);
            mRecyclerView.setAdapter(mAdapter);
            registerForContextMenu(mRecyclerView);
        }

        @Override
        public void onResume() {
            super.onResume();
            mVRManager.fireResumed();
        }

        @Override
        public void onPause() {
            super.onPause();
            mVRManager.firePaused();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mVRManager.fireDestroy();
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }

    }

    public static class HomeVideosFragment extends Fragment {

        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_home_videos, container, false);
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }
    }
}
