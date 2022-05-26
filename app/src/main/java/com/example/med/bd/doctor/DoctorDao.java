package com.example.med.bd.doctor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Doctor doctor);

    @Update
    void update(Doctor doctor);

    @Delete
    void delete(Doctor doctor);

    @Query("SELECT * FROM doctor")
    List<Doctor> loadAll();

    @Query("SELECT name FROM doctor where id = :id")
    String getNameById(long id);

    @Query("SELECT surname FROM doctor where id = :id")
    String getSurnameById(long id);

    @Query("SELECT patronymic FROM doctor where id = :id")
    String getPatronymicById(long id);

    @Query("SELECT age FROM doctor where id = :id")
    int getAgeById(long id);

    @Query("SELECT phone_number FROM doctor where id = :id")
    String getPhoneNumberById(long id);

    @Query("SELECT email FROM doctor where id = :id")
    String getEmailById(long id);

    @Query("SELECT * FROM doctor where id = :id")
    Doctor getDoctorById(long id);

}

