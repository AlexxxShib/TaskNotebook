package com.shibkov.tasknotebook.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.activities.CreateTaskActivity;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.database.managers.TaskNoteManager;
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
        taskNoteListAdapter.setActionListener(new TaskNoteListAdapter.OnActionClickListener() {
            @Override
            public void removeItem(long id) {

            }
            @Override
            public void editItem(long id) {
                startActivity(new Intent(getActivity(), CreateTaskActivity.class)
                        .putExtra(CreateTaskActivity.KEY_TASK_NOTE_ID, id));
            }
        });

        ListView tasksListView = (ListView) view.findViewById(R.id.list_view);
        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                taskNoteListAdapter.action(position);
            }
        });
        tasksListView.setAdapter(taskNoteListAdapter);
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
