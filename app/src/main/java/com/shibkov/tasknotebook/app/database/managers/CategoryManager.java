package com.shibkov.tasknotebook.app.database.managers;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.Contract;
import com.shibkov.tasknotebook.app.database.DatabaseHelper;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexxxshib
 */
public class CategoryManager implements IDataManager<Category> {

    public static final long CATEGORY_ARCH = 3l;
    public static final long CATEGORY_DONE = 4l;

    private final Dao<Category, Long> mCategoryDao;

    public CategoryManager(DatabaseHelper helper) {
        mCategoryDao = helper.getCategoryDao();
        if (mCategoryDao == null) {
            throw new IllegalStateException("Dao Category not initialized!");
        }
    }

    @Override
    public void add(Category object) {
        try {
            mCategoryDao.createOrUpdate(object);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void remove(Category object) {
        try {
            mCategoryDao.delete(object);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public Category findById(long id) {
        try {
            mCategoryDao.queryForId(id);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return null;
    }

    public Category findByValue(String value) {
        QueryBuilder<Category, Long> queryBuilder = mCategoryDao.queryBuilder();
        try {
            queryBuilder.where().eq(Contract.CCategory.VALUE, value);
            return mCategoryDao.queryForFirst(queryBuilder.prepare());
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        try {
            return mCategoryDao.queryForAll();
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return new ArrayList<Category>();
    }

    /**
     * Init default values for categories
     *
     * @param context for init from strings of application
     */
    public void initDefaultCategories(Context context) {
        add(new Category(0l, context.getString(R.string.abc_one_day_value),
                context.getString(R.string.abc_one_day_description), 1000l * 3600l * 24l, "ic_ring.png"));
        add(new Category(1l, context.getString(R.string.abc_one_week_value),
                context.getString(R.string.abc_one_week_description), 1000l * 3600l * 24l * 7l, "ic_time.png"));
        add(new Category(2l, context.getString(R.string.abc_one_month_value),
                context.getString(R.string.abc_one_month_description), 1000l * 3600l * 24l * 30l, "ic_events.png"));
        add(new Category(CATEGORY_ARCH, context.getString(R.string.abc_archive),
                context.getString(R.string.abc_archive_description), 0l, "arch.png"));
        add(new Category(CATEGORY_DONE, context.getString(R.string.abc_done),
                context.getString(R.string.abc_done_description), 0l, "done.png"));

        debugPrintAll();
    }

    public void debugPrintAll() {
        try {
            GenericRawResults<String[]> result = mCategoryDao.queryRaw("select * from categories_table");
            Logger.info("Print categories");
            for (String[] row : result.getResults()) {
                String str = "";
                for (String col : row) {
                    str += col + " ";
                }
                Logger.info(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
