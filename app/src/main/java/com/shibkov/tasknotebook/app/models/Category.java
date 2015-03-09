package com.shibkov.tasknotebook.app.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static android.provider.BaseColumns._ID;
import static com.shibkov.tasknotebook.app.database.Contract.CCategory.*;

/**
 * Created by alexxxshib
 */
@DatabaseTable(tableName = TABLE_NAME)
public class Category implements Parcelable{

    @DatabaseField(columnName = _ID, index = true, generatedId = true)
    private Long id;

    @DatabaseField(columnName = VALUE, canBeNull = false)
    private String value;

    @DatabaseField(columnName = DESCRIPTION)
    private String description;

    @DatabaseField(columnName = INTERVAL)
    private Long interval;

    public Category(){};

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

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Category(Parcel parcel) {
        id = parcel.readLong();
        value = parcel.readString();
        description = parcel.readString();
        interval = parcel.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(value);
        dest.writeString(description);
        dest.writeLong(interval);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
