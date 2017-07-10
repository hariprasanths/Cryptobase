package com.example.android.cryptobase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hari on 09-07-2017.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {


    public CustomFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new Rot13Fragment();
        else if (position == 1)
            return new OneTimePadFragment();
        else if (position == 2)
            return new AffineFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "ROT13";
        else if (position == 1)
            return "One Time Pad";
        else if (position == 2)
            return "Affine";

        return super.getPageTitle(position);
    }
}
