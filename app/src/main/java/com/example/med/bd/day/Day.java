package com.example.med.bd.day;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.med.bd.write.Write;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "day")
public class Day implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "date")
    String date;

    @Ignore
    public Day(String date) {
        this.date = date;
    }

    public Day(long id, String date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {return this.id;}

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", name='" + date + '\'' +
                '}';
    }
}
