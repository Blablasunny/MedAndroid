package com.example.med.bd.day;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Day.class}, version = 1)
public abstract class DayRoomDatabase extends RoomDatabase {

    public abstract DayDao getDayDao();

    private static volatile com.example.med.bd.day.DayRoomDatabase INSTANCE;

    public static com.example.med.bd.day.DayRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.med.bd.day.DayRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            com.example.med.bd.day.DayRoomDatabase.class, "dayDatabase.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
