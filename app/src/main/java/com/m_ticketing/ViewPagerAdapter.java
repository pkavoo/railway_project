package com.m_ticketing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by CYMMOH on 8/12/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Fragment_OneWay oneway = new Fragment_OneWay();
            return oneway;
        } else if (position == 1) {
            Fragment_TwoWay twoway = new Fragment_TwoWay();
            return twoway;
        } else {
            Fragment_FareInquery fareInquery = new Fragment_FareInquery();
            return fareInquery;

        }
    }

        @Override
        public CharSequence getPageTitle(int position) {
            return Titles[position];
        }

        // This method return the Number of tabs for the tabs Strip

        @Override
        public int getCount() {
            return NumbOfTabs;
        }
}
