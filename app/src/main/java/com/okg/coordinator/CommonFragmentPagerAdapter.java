package com.okg.coordinator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author oukanggui
 * @date 2020-03-07
 * 描述：公用的FragmentPagerAdapter
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        return mTitles != null ? mTitles.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments != null && mFragments.size() > 0 ? mFragments.get(position) : null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles != null && mTitles.size() > 0 ? mTitles.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        int itemId = position;
        if (mFragments != null && mFragments.get(position) != null) {
            //根据Fragment对象来创建id，保证重新设置Adapter后，拿到新创建的Fragment实例
            itemId = mFragments.get(position).hashCode();
        }
        return itemId;
    }
}
