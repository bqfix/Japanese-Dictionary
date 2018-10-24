package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WordFragmentPagerAdapter extends FragmentPagerAdapter {

    public WordFragmentPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return WordFragment.newInstance("numbers");
            case 1: return WordFragment.newInstance("colors");
            case 2: return WordFragment.newInstance("phrases");
            case 3: return WordFragment.newInstance("family");
            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Numbers";
            case 1: return "Colors";
            case 2: return "Phrases";
            case 3: return "Family";
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
