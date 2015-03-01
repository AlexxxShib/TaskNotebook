package com.shibkov.tasknotebook.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.shibkov.tasknotebook.app.models.Category;

/**
 * Created by alexxxshib
 */
public class CategoryFragment extends Fragment {

    private static final String CATEGORY_ARG_KEY = "category_arg_key";

    public static CategoryFragment newInstance(Category category) {

        Bundle arguments = new Bundle();
        arguments.putParcelable(CATEGORY_ARG_KEY, category);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
