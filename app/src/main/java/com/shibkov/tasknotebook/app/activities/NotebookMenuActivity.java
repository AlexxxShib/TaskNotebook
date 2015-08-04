package com.shibkov.tasknotebook.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.DatabaseManager;
import com.shibkov.tasknotebook.app.fragments.CategoryFragment;
import com.shibkov.tasknotebook.app.database.managers.CategoryManager;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.utils.Logger;
import com.shibkov.tasknotebook.app.views.adapters.MainMenuAdapter;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class NotebookMenuActivity extends AppCompatActivity {

    private static final int REQUEST_CREATE_CATEGORY = 100;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawer;

    private ActionBarDrawerToggle mDrawerToggle;

    private CategoryManager mCategoryManager;
    private List<Category> mCategoryList;

    private View addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_menu);

        mCategoryManager = new CategoryManager(DatabaseManager.getHelper(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        //init async
        mCategoryList = mCategoryManager.getAll();

        changeFragment(mCategoryList.get(0));

        mAdapter = new MainMenuAdapter(this, mCategoryList, new MainMenuAdapter.MenuClickListener() {
            @Override
            public void onItemClicked(Category category) {
                Logger.info(String.format("Selected %s item", category.getValue()));
                changeFragment(category);
                drawer.closeDrawers();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.abc_open_drawer, R.string.abc_close_drawer);
        drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotebookMenuActivity.this, CreateTaskActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        animateAddButton(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        animateAddButton(false);
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

        /*if (id == R.id.menu_item_create_category) {
            startActivityForResult(new Intent(this, CreateCategoryActivity.class), REQUEST_CREATE_CATEGORY);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Category category) {
        getSupportActionBar().setTitle(category.getValue());
        CategoryFragment fragment = CategoryFragment.newInstance(category);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
    }

    private void animateAddButton(boolean isStart) {
        if (isStart) {
            addButton.animate()
                    .setDuration(800)
                    .alpha(1).scaleX(1).scaleY(1).rotation(360)
                    .setInterpolator(new DecelerateInterpolator(1.5f));
        } else {
            addButton.setAlpha(0);
            addButton.setScaleX(0);
            addButton.setScaleY(0);
            addButton.setRotation(0);
        }
    }
}
