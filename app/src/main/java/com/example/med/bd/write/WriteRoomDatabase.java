package com.example.med.bd.write;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Write.class}, version = 1)
public abstract class WriteRoomDatabase extends RoomDatabase {

    public abstract WriteDao getWriteDao();

    private static volatile com.example.med.bd.write.WriteRoomDatabase INSTANCE;

    public static com.example.med.bd.write.WriteRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.med.bd.write.WriteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            com.example.med.bd.write.WriteRoomDatabase.class, "writeDatabase.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}