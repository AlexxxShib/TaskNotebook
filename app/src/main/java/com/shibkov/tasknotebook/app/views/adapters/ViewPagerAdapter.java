package com.shibkov.tasknotebook.app.views.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.fragments.CategoryFragment;
import com.shibkov.tasknotebook.app.managers.CategoryManager;
import com.shibkov.tasknotebook.app.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexxxshib
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Category> mTabCategories;

    public ViewPagerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        mTabCategories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(mTabCategories.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabCategories.get(position).getValue();
    }

    @Override
    public int getCount() {
        return mTabCategories.size();
    }
}
