package com.shibkov.tasknotebook.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.fragments.ChoiceIconDialogFragment;
import com.shibkov.tasknotebook.app.utils.Logger;

/**
 * Created by alexxxshib
 */
public class CreateCategoryActivity extends AppCompatActivity
        implements ChoiceIconDialogFragment.OnFinishChoiceIconDialog, View.OnClickListener {

    private EditText headerEdit;
    private EditText descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(R.string.abc_action_create_category);

        headerEdit = (EditText) findViewById(R.id.headerEdit);
        descriptionEdit = (EditText) findViewById(R.id.descriptionEdit);

        findViewById(R.id.selectIconLayout).setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_task, menu);
        return false;
    }

    @Override
    public void selectedIcon(String iconPath) {
        Logger.error(iconPath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.selectIconLayout:
                ChoiceIconDialogFragment dialog = ChoiceIconDialogFragment.newInstance();
                dialog.show(getFragmentManager(), "dialog");
                break;
        }
    }
}
