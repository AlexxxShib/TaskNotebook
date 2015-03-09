package com.shibkov.tasknotebook.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.managers.CategoryManager;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.views.adapters.MainMenuAdapter;
import com.shibkov.tasknotebook.app.views.adapters.ViewPagerAdapter;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class NotebookMenuActivity extends ActionBarActivity {

    //tests
    private String NAME = "Akash Bangad";
    private String EMAIL = "akash.bangad@android4devs.com";
    private int PROFILE = R.drawable.aka;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout Drawer;

    private ActionBarDrawerToggle mDrawerToggle;

    private ViewPager pager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_menu);

        DatabaseManager.initializeInstance(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        CategoryManager mCategoryManager = new CategoryManager(DatabaseManager.getInstance().getHelper());
        mCategoryManager.initDefaultCategories(this);

        final List<Category> categories = mCategoryManager.getAll();

        mAdapter = new MainMenuAdapter(categories,NAME,EMAIL,PROFILE);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.abc_open_drawer, R.string.abc_close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), categories);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getPosition() - 1;
                if (position != -1) pager.setCurrentItem(position);
            }
        });
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
