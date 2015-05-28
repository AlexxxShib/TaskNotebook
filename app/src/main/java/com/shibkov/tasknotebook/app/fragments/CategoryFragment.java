package com.shibkov.tasknotebook.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shibkov.tasknotebook.app.activities.CreateTaskActivity;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.managers.TaskNoteManager;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.models.TaskNote;
import com.shibkov.tasknotebook.app.views.adapters.TaskNoteAdapter;

/**
 * Created by alexxxshib
 */
public class CategoryFragment extends Fragment {

    private static final String CATEGORY_ARG_KEY = "category_arg_key";
    private static final int CREATE_ACTIVITY_CODE = 1;

    public static CategoryFragment newInstance(Category category) {

        Bundle arguments = new Bundle();
        arguments.putParcelable(CATEGORY_ARG_KEY, category);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private TaskNoteManager mTaskNoteManager;
    private RecyclerView.LayoutManager mLayoutManager;
    private Category mCategory;

    private TaskNoteAdapter taskNoteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        mTaskNoteManager = new TaskNoteManager(DatabaseManager.getHelper(getActivity()));

        mCategory = getArguments().getParcelable(CATEGORY_ARG_KEY);

        mLayoutManager = new LinearLayoutManager(getActivity());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        taskNoteAdapter = new TaskNoteAdapter(mTaskNoteManager.getByCategory(mCategory), new TaskNoteAdapter.TaskListListener() {
            @Override
            public void onItemClicked(TaskNote taskNote) {
                //
            }
        });
        recyclerView.setAdapter(taskNoteAdapter);
        recyclerView.setLayoutManager(mLayoutManager);

        view.findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CreateTaskActivity.class), CREATE_ACTIVITY_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CreateTaskActivity.RESULT_FAILED || data == null) {
            return;
        }

        switch (requestCode) {

            case CREATE_ACTIVITY_CODE:
                createTaskNote(
                        data.getStringExtra(CreateTaskActivity.K_HEADER),
                        data.getStringExtra(CreateTaskActivity.K_DESCRIPTION)
                );
                break;

        }
    }

    private void createTaskNote(String header, String description) {
        TaskNote taskNote = new TaskNote();
        taskNote.setHeader(header);
        taskNote.setBody(description);
        taskNote.setCategory(mCategory);

        taskNoteAdapter.addItem(taskNote);
        mTaskNoteManager.add(taskNote);
    }
}
