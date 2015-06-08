package com.shibkov.tasknotebook.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.shibkov.tasknotebook.app.R;


public class CreateTaskActivity extends AppCompatActivity {

    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED  = -1;

    public static final String K_HEADER      = "header_key";
    public static final String K_DESCRIPTION = "description_key";

    private EditText headerEdit;
    private EditText descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        headerEdit = (EditText)findViewById(R.id.headerEdit);
        descriptionEdit = (EditText)findViewById(R.id.descriptionEdit);
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
                if (!checkAndSaveResult()) {
                    Toast.makeText(this, R.string.abc_need_header, Toast.LENGTH_LONG).show();
                }
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkAndSaveResult() {
        String header = headerEdit.getText().toString();
        if (header.isEmpty()) return false;

        String description = descriptionEdit.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(K_HEADER, header);
        intent.putExtra(K_DESCRIPTION, description);
        setResult(RESULT_SUCCESS, intent);
        finish();
        return true;
    }
}
