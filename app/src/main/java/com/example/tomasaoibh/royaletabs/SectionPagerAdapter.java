package com.example.tomasaoibh.royaletabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Tomas & Aoibh on 17/04/2016.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

    Bundle info;
    final int pageCount=6;
    final String[] tabTitles = {"Appetizers","Soup","Main Course","Drink","Side","Extra"};

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        info = new Bundle();
        switch(position){
            case 0:
               AppetizersFrag starters = new AppetizersFrag();
                info.putInt("currentPage",position++);
                starters.setArguments(info);
                return starters;
            case 1:
               SoupFrag soup = new SoupFrag();
                info.putInt("currentPage",position++);
                soup.setArguments(info);
                return soup;
            case 2:
                MainCourseFrag mainCourse = new MainCourseFrag();
                info.putInt("currentPage",position++);
                mainCourse.setArguments(info);
                return mainCourse;
            case 3:
                Vegetables vegetables = new Vegetables();
                info.putInt("currentPage",position++);
                vegetables.setArguments(info);
                return vegetables;
            case 4:
               SideOrderFrag side = new SideOrderFrag();
                info.putInt("currentPage",position++);
                side.setArguments(info);
                return side;
            case 5:
                ExtraOrderFrag extras = new ExtraOrderFrag();
                info.putInt("currentPage",position++);
                extras.setArguments(info);
                return extras;

        }

        return null;
    }

    @Override
    public int getCount() {
        return pageCount;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
