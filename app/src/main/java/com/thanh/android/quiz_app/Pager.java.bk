package com.thanh.android.quiz_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

/**
 * Created by Belal on 2/3/2016.
 */
//    todo use FragmentStatePagerAdapter 11:Extending FragmentStatePagerAdapter -> Tab.java
public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    public void addTab(){
        tabCount +=1;
    }
    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        return Tab.newInstance(position);
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

}
