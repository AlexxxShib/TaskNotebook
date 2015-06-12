package com.shibkov.tasknotebook.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.fragments.dialogs.ChoiceDateDialog;
import com.shibkov.tasknotebook.app.fragments.dialogs.ChoiceTimeDialog;
import com.shibkov.tasknotebook.app.managers.TaskNoteManager;
import com.shibkov.tasknotebook.app.models.TaskNote;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreateTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "HH:mm";

    private EditText headerEdit;
    private EditText descriptionEdit;

    private Button dateButton;
    private Button timeButton;

    private TaskNoteManager taskNoteManager;

    private Calendar dieTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        headerEdit = (EditText) findViewById(R.id.headerEdit);
        descriptionEdit = (EditText) findViewById(R.id.descriptionEdit);

        dateButton = (Button) findViewById(R.id.date_button);
        timeButton = (Button) findViewById(R.id.time_button);

        dateButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);

        taskNoteManager = new TaskNoteManager(DatabaseManager.getHelper(this));
        initTimeValues();
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
                checkAndSaveResult();
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_button:
                showDateDialog();
                break;

            case R.id.time_button:
                showTimeDialog();
                break;
        }
    }

    private void initTimeValues() {
        if (dieTime == null) {
            dieTime = Calendar.getInstance();
            dieTime.setTimeInMillis(System.currentTimeMillis());
            dieTime.add(Calendar.DAY_OF_YEAR, 1);
        }
        Date date = dieTime.getTime();
        timeButton.setText(new SimpleDateFormat(TIME_FORMAT).format(date));
        dateButton.setText(new SimpleDateFormat(DATE_FORMAT).format(date));
    }

    private void showDateDialog() {
        ChoiceDateDialog.getInstance(dieTime.getTimeInMillis(),
                new ChoiceDateDialog.ChoiceDateListener() {
                    @Override
                    public void dateSelected(long date) {
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(date);

                        if (isEarlyTime(c)) {
                            Calendar now = Calendar.getInstance();
                            now.add(Calendar.HOUR, 1);
                            dieTime.set(Calendar.HOUR, now.get(Calendar.HOUR));
                            dieTime.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
                        }

                        dieTime.set(Calendar.YEAR, c.get(Calendar.YEAR));
                        dieTime.set(Calendar.MONTH, c.get(Calendar.MONTH));
                        dieTime.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));

                        initTimeValues();
                    }
                }).show(getFragmentManager(), "choice_date");
    }

    private void showTimeDialog() {
        ChoiceTimeDialog.getInstance(dieTime.getTimeInMillis(),
                new ChoiceTimeDialog.ChoiceTimeListener() {
                    @Override
                    public void timeSelected(long time) {
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(time);

                        if (isEarlyTime(c)) {
                            return;
                        }

                        dieTime.set(Calendar.HOUR, c.get(Calendar.HOUR));
                        dieTime.set(Calendar.MINUTE, c.get(Calendar.MINUTE));

                        initTimeValues();
                    }
                }).show(getFragmentManager(), "choice_time");
    }

    private boolean isEarlyTime(Calendar c) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        return now.after(c);
    }

    private void checkAndSaveResult() {
        String header = headerEdit.getText().toString();
        if (header.isEmpty()) {
            Toast.makeText(this, R.string.abc_need_header, Toast.LENGTH_LONG).show();
            return;
        }
        String description = descriptionEdit.getText().toString();

        TaskNote taskNote = new TaskNote(-1, dieTime.getTime(), false, header, description);
        taskNoteManager.add(taskNote);

        onBackPressed();
    }
}
