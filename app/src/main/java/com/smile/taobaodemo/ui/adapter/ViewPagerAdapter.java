package com.smile.taobaodemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smile.taobaodemo.ui.fragment.NavHomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile Wei
 * @since 2016/8/1.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavHomeFragment.newInstance());

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
