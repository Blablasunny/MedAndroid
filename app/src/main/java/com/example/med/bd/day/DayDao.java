package com.example.med.bd.day;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.med.bd.write.Write;

import java.util.List;

@Dao
public interface DayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Day day);

    @Update
    void update(Day day);

    @Delete
    void delete(Day day);

    @Query("SELECT * FROM day")
    List<Day> loadAll();

    @Query("SELECT date FROM day where id = :id")
    String getDateById(long id);

    @Query("SELECT * FROM day where id = :id")
    Day getDayById(long id);

}
