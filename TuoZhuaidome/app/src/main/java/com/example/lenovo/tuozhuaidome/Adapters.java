package com.example.lenovo.tuozhuaidome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KING on 2017/8/31 14:13
 * 邮箱:992767879@qq.com
 */

public class Adapters extends FragmentPagerAdapter {
    private List<Fragment> listF;
    private ArrayList<String> list1;

    public Adapters(FragmentManager fm, List<Fragment> listF, ArrayList<String> list1) {
        super(fm);
        this.listF = listF;
        this.list1 = list1;
    }

    @Override
    public Fragment getItem(int position) {
        return listF.get(position);
    }

    @Override
    public int getCount() {
        return listF.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list1.get(position);
    }
}
