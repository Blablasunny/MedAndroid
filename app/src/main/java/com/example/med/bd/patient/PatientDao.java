package com.example.med.bd.patient;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("SELECT * FROM patient")
    List<Patient> loadAll();

    @Query("SELECT name FROM patient where id = :id")
    String getNameById(long id);

    @Query("SELECT surname FROM patient where id = :id")
    String getSurnameById(long id);

    @Query("SELECT patronymic FROM patient where id = :id")
    String getPatronymicById(long id);

    @Query("SELECT age FROM patient where id = :id")
    int getAgeById(long id);

    @Query("SELECT phone_number FROM patient where id = :id")
    String getPhoneNumberById(long id);

    @Query("SELECT email FROM patient where id = :id")
    String getEmailById(long id);

    @Query("SELECT * FROM patient where id = :id")
    Patient getPatientById(long id);

}
