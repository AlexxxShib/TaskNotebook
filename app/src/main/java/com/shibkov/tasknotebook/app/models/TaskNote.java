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

    @DatabaseField(columnName = DATE, canBeNull = false, dataType = DataType.DATE_STRING)
    private Date date;
    @DatabaseField(columnName = IS_DONE, dataType = DataType.BOOLEAN)
    private boolean isDone = false;

    @DatabaseField(columnName = HEADER, canBeNull = false)
    private String header;
    @DatabaseField(columnName = BODY)
    private String body;

    public TaskNote() {
        this.id = -1;
    }

    public TaskNote(long id, Date date, boolean isDone, String header, String body) {
        this.id = id;
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;

        if (o instanceof TaskNote) {
            return ((TaskNote) o).id == id;
        }
        return false;
    }
}
