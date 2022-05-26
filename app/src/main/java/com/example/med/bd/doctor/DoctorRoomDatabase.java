package com.example.med.bd.doctor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Doctor.class}, version = 1)
public abstract class DoctorRoomDatabase extends RoomDatabase {

    public abstract DoctorDao getDoctorDao();

    private static volatile com.example.med.bd.doctor.DoctorRoomDatabase INSTANCE;

    public static com.example.med.bd.doctor.DoctorRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.med.bd.doctor.DoctorRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            com.example.med.bd.doctor.DoctorRoomDatabase.class, "doctorDatabase.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

