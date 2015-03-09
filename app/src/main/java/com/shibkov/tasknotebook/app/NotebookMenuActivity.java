package com.shibkov.tasknotebook.app;

import android.os.Bundle;
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
import com.shibkov.tasknotebook.app.fragments.CategoryFragment;
import com.shibkov.tasknotebook.app.managers.CategoryManager;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.utils.Logger;
import com.shibkov.tasknotebook.app.views.adapters.MainMenuAdapter;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class NotebookMenuActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout Drawer;

    private ActionBarDrawerToggle mDrawerToggle;

    private CategoryManager mCategoryManager;
    private List<Category> mCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_menu);

        mCategoryManager = new CategoryManager(DatabaseManager.getHelper(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mCategoryManager.initDefaultCategories(this);
        mCategoryList = mCategoryManager.getAll();

        changeFragment(mCategoryList.get(0));

        mAdapter = new MainMenuAdapter(this, mCategoryList, new MainMenuAdapter.MenuClickListener() {
            @Override
            public void onItemClicked(Category category) {
                Logger.info(String.format("Selected %s item", category.getValue()));
                changeFragment(category);
                Drawer.closeDrawers();
            }
        });
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
    }

    private void changeFragment(Category category) {
        getSupportActionBar().setTitle(category.getValue());
        CategoryFragment fragment = CategoryFragment.newInstance(category);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        DatabaseManager.closeDatabase();
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
