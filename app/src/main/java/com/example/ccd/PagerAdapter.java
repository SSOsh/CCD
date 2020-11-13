package com.example.ccd;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter  {
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
                homeFragment homefragment = new homeFragment();
                return homefragment;
//            case 1:
//                bookStatusFragment bookstatusfragment = new bookStatusFragment();
//                return bookstatusfragment;
//            case 2:
//                chatbotFragment chatbotfragment = new chatbotFragment();
//                return chatbotfragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
