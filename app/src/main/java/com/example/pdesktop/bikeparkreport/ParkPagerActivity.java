package com.example.pdesktop.bikeparkreport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by Andres Acuna on 4/14/2016.
 */
public class ParkPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ArrayList<ParkItem> mParks;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Create local ViewPager as the Context so it does it without a layout.xml file
        mViewPager = new ViewPager(this);

        //view pager needs a resource, found in values/ids.xml
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        //get the values
        mParks = ParksListManager.get(this).getParks();

        FragmentManager fm = getSupportFragmentManager();

        //The Adapater Helps determine which park to show.
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public Fragment getItem(int pos) {
                ParkItem p = mParks.get(pos);
                return ParkFragment.newInstance(p.getParkName());
            }

            @Override
            public int getCount(){
                return mParks.size();
            }

        });
    }

}
