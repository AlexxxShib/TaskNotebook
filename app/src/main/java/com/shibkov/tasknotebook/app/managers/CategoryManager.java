package com.shibkov.tasknotebook.app.managers;

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

    private final Dao<Category, Long> mCategoryDao;

    public CategoryManager(DatabaseHelper helper) {
        mCategoryDao = helper.getCategoryDao();
        if (mCategoryDao == null) {
            throw new IllegalStateException("Dao Category not initialized!");
        }
    }

    /**
     * Init default values for categories
     *
     * @param context for init from strings of application
     */
    public void initDefaultCategories(Context context) {
        Category oneDay = createCategory(0l, context.getString(R.string.abc_one_day_value),
                context.getString(R.string.abc_one_day_description), 1000l * 3600l * 24l);
        add(oneDay);

        Category oneWeek = createCategory(1l, context.getString(R.string.abc_one_week_value),
                context.getString(R.string.abc_one_week_description), 1000l * 3600l * 24l * 7l);
        add(oneWeek);
        Category oneMonth = createCategory(2l, context.getString(R.string.abc_one_month_value),
                context.getString(R.string.abc_one_month_description), 1000l * 3600l * 24l * 30l);
        add(oneMonth);

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

    public Category createCategory(Long id, String value, String description, Long timestamp) {
        Category category = new Category();
        category.setId(id);
        category.setValue(value);
        category.setDescription(description);
        category.setInterval(timestamp);

        return category;
    }
}
