package com.shibkov.tasknotebook.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.fragments.dialogs.ChoiceIconDialog;
import com.shibkov.tasknotebook.app.database.managers.CategoryManager;

/**
 * Created by alexxxshib
 */
public class CreateCategoryActivity extends AppCompatActivity
        implements ChoiceIconDialog.OnFinishChoiceIconDialog, View.OnClickListener {

    private String iconPath = "ic_ring.png";
    private EditText headerEdit;
    private EditText descriptionEdit;

    private CategoryManager categoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        categoryManager = new CategoryManager(DatabaseManager.getHelper(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_ready:
                saveResult();
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedIcon(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.selectIconLayout:
                ChoiceIconDialog dialog = ChoiceIconDialog.newInstance();
                dialog.show(getFragmentManager(), "dialog");
                break;
        }
    }

    private void saveResult() {
        if (!validateDate()) {
            return;
        }
    }

    private boolean validateDate() {
        return !headerEdit.getText().toString().trim().isEmpty();
    }
}
