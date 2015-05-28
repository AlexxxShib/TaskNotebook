package com.shibkov.tasknotebook.app.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import static android.provider.BaseColumns._ID;
import static com.shibkov.tasknotebook.app.database.Contract.CTaskNote.*;

/**
 * Created by alexxxshib
 */
@DatabaseTable(tableName = TABLE_NAME)
public class TaskNote {
    @DatabaseField(columnName = _ID, index = true, generatedId = true)
    private long id;

    @DatabaseField(columnName = DATE, canBeNull = false, dataType = DataType.DATE)
    private Date date = new Date();
    @DatabaseField(columnName = CATEGORY, canBeNull = false, foreign = true)
    private Category category;
    @DatabaseField(columnName = IS_DONE, dataType = DataType.BOOLEAN)
    private boolean isDone = false;

    @DatabaseField(columnName = HEADER, canBeNull = false)
    private String header;
    @DatabaseField(columnName = BODY)
    private String body;

    public TaskNote() {}

    public TaskNote(long id, Date date, Category category, boolean isDone, String header, String body) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.isDone = isDone;
        this.header = header;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
