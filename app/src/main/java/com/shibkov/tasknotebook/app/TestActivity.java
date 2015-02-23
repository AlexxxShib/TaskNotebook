package com.shibkov.tasknotebook.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.shibkov.tasknotebook.app.database.Contract;
import com.shibkov.tasknotebook.app.database.DatabaseHelper;
import com.shibkov.tasknotebook.app.database.DatabaseManager;


public class TestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onStart() {
        super.onStart();

        testDBInsert();
    }

    private void testDBInsert() {
        DatabaseManager.initializeInstance(new DatabaseHelper(this));
        SQLiteDatabase database = DatabaseManager.getInstance().openWritableDB();


        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            values.put(Contract.CTaskNote._ID, i);
            values.put(Contract.CTaskNote.CATEGORY, i);
            values.put(Contract.CTaskNote.IS_DONE, i);
            values.put(Contract.CTaskNote.JSON_OBJECT, i + "_object");

            database.insert(Contract.CTaskNote.TABLE_NAME, null, values);
            Log.i("write db", "id " + values.get(Contract.CTaskNote._ID));
        }

        DatabaseManager.getInstance().closeDatabase();

        database = DatabaseManager.getInstance().openReadableDB();
        Cursor cursor = database.rawQuery("SELECT * FROM " + Contract.CTaskNote.TABLE_NAME, null);

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    Log.i("read db",
                            String.valueOf(cursor.getInt(cursor.getColumnIndex(Contract.CTaskNote._ID))) + " "
                                    + cursor.getInt(cursor.getColumnIndex(Contract.CTaskNote.CATEGORY)) + " "
                                    + cursor.getInt(cursor.getColumnIndex(Contract.CTaskNote.IS_DONE)) + " "
                                    + cursor.getString(cursor.getColumnIndex(Contract.CTaskNote.JSON_OBJECT)) + " ");
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        DatabaseManager.getInstance().closeDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
