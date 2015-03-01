package com.shibkov.tasknotebook.app;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.managers.CategoryManager;
import com.shibkov.tasknotebook.app.views.widgets.SlidingTabLayout;
import com.shibkov.tasknotebook.app.views.adapters.ViewPagerAdapter;


public class NotebookTabsActivity extends ActionBarActivity {

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_tabs);

        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar));

        DatabaseManager.initializeInstance(this);

        CategoryManager mCategoryManager = new CategoryManager(DatabaseManager.getInstance().getHelper());
        mCategoryManager.initDefaultCategories(this);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), mCategoryManager.getAll());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accent);
            }
            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(R.color.accent);//todo change color
            }
        });

        tabs.setViewPager(pager);
    }

    @Override
    protected void onDestroy() {
        DatabaseManager.getInstance().closeDatabase();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
