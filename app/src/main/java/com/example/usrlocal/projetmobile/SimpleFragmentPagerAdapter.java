package com.example.usrlocal.projetmobile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return TabStatisticsFragment.newInstance(8);
            case 1: return TabStatisticsFragment.newInstance(12);
            case 2: return TabStatisticsFragment.newInstance(16);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0: return "Facile";
            case 1: return "Moyen";
            case 2: return "Difficile";
            default: return null;
        }
    }
}
