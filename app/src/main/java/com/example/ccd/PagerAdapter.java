package com.example.ccd;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                homeFragment tabFragment1 = new homeFragment();
                return tabFragment1;
            case 1:
                bookLookupFragment tabFragment2 = new bookLookupFragment();
                return tabFragment2;
            case 2:
                chatbotFragment tabFragment3 = new chatbotFragment();
                return tabFragment3;
            case 3:
                likeFragment tabFragment4 = new likeFragment();
                return tabFragment4;
            case 4:
                bestsellerLookupFragment tabFragment5 = new bestsellerLookupFragment();
                return tabFragment5;
            case 5:
                ForumFragment tabFragment6 = new ForumFragment();
                return tabFragment6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
