package com.aditya.collpolltest.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.aditya.collpolltest.Fragments.FeedFragment;
import com.aditya.collpolltest.Fragments.SavedFeedFragment;

public class PagerAdapterTabs extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapterTabs(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 1:
                return new SavedFeedFragment();
            case 0:
            default:
                return new FeedFragment();
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
