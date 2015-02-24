package com.shibkov.tasknotebook.app.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static android.provider.BaseColumns._ID;
import static com.shibkov.tasknotebook.app.database.Contract.CCategory.*;

/**
 * Created by alexxxshib
 */
@DatabaseTable(tableName = TABLE_NAME)
public class Category {

    @DatabaseField(columnName = _ID, index = true, generatedId = true)
    private Long id;

    @DatabaseField(columnName = VALUE, canBeNull = false)
    private String value;

    @DatabaseField(columnName = DESCRIPTION)
    private String description;

    @DatabaseField(columnName = TIMESTAMP)
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
