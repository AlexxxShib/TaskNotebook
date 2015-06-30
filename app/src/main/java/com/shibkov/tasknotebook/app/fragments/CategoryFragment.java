package com.shibkov.tasknotebook.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.managers.TaskNoteManager;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.models.TaskNote;
import com.shibkov.tasknotebook.app.views.adapters.TaskNoteListAdapter;

import java.util.ArrayList;

/**
 * Created by alexxxshib
 */
public class CategoryFragment extends Fragment {

    private static final String ARG_CATEGORY = "ARG_CATEGORY";

    public static CategoryFragment newInstance(Category category) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_CATEGORY, category);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private TaskNoteManager mTaskNoteManager;
    private Category mCategory;

    private TaskNoteListAdapter taskNoteListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        mTaskNoteManager = new TaskNoteManager(DatabaseManager.getHelper(getActivity()));

        mCategory = getArguments().getParcelable(ARG_CATEGORY);
        taskNoteListAdapter = new TaskNoteListAdapter(getActivity(), new ArrayList<TaskNote>());

        ListView tasks = (ListView) view.findViewById(R.id.list_view);
        tasks.setAdapter(taskNoteListAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    //todo make async
    private void updateList() {
        taskNoteListAdapter.clear();
        taskNoteListAdapter.addAll(mTaskNoteManager.getByCategory(mCategory));
    }
}
