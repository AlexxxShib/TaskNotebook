package com.shibkov.tasknotebook.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.shibkov.tasknotebook.app.fragments.ChoiceIconDialogFragment;

/**
 * Created by alexxxshib
 */
public class CreateCategoryActivity extends ActionBarActivity {

    private EditText headerEdit;
    private EditText descriptionEdit;
    private LinearLayout iconSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        headerEdit = (EditText)findViewById(R.id.headerEdit);
        descriptionEdit = (EditText)findViewById(R.id.descriptionEdit);
        iconSelector = (LinearLayout)findViewById(R.id.selectIconLayout);
        iconSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceIconDialogFragment.newInstance().show(getFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_task, menu);
        return true;
    }
}
