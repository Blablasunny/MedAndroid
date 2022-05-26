package com.example.med.bd.patient;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Patient.class}, version = 1)
public abstract class PatientRoomDatabase extends RoomDatabase {

    public abstract PatientDao getPatientDao();

    private static volatile PatientRoomDatabase INSTANCE;

    public static PatientRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (PatientRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            PatientRoomDatabase.class, "patientDatabase.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
