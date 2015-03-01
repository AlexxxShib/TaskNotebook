package com.shibkov.tasknotebook.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shibkov.tasknotebook.app.R;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, true);
        ((TextView)view.findViewById(R.id.text_main)).setText(
                ((Category)getArguments().getParcelable(CATEGORY_ARG_KEY)).getValue()
        );
        return view;
    }
}
