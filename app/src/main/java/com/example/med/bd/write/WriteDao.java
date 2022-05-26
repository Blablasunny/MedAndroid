package com.example.med.bd.write;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.patient.Patient;

import java.util.List;

@Dao
public interface WriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Write write);

    @Update
    void update(Write write);

    @Delete
    void delete(Write write);

    @Query("SELECT * FROM write")
    List<Write> loadAll();

    @Query("SELECT name FROM write where id = :id")
    String getNameById(long id);

    @Query("SELECT info FROM write where id = :id")
    String getInfoById(long id);

    @Query("SELECT patient_id FROM write where id = :id")
    long getPatientIdById(long id);

    @Query("SELECT doctor_id FROM write where id = :id")
    long getDoctorIdById(long id);

    @Query("SELECT day_id FROM write where id = :id")
    long getDayIdById(long id);

    @Query("SELECT * FROM write where day_id = :day_id")
    List<Write> getWriteByDayId(long day_id);

    @Query("SELECT * FROM write where id = :id")
    Write getWriteById(long id);
}
