package com.example.med.bd.write;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.patient.Patient;

import java.io.Serializable;

@Entity(tableName = "write")
public class Write implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "info")
    String info;

    @ColumnInfo(name = "patient_id")
    long patient_id;

    @ColumnInfo(name = "doctor_id")
    long doctor_id;

    @ColumnInfo(name = "day_id")
    long day_id;

    public Write(long id, String name, String info, long patient_id, long doctor_id, long day_id) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.day_id = day_id;
    }

    @Ignore
    public Write(String name, String info, long patient_id, long doctor_id, long day_id) {
        this.name = name;
        this.info = info;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.day_id = day_id;
    }

    public long getId() {return this.id;}

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public long getPatient_id() {
        return patient_id;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public long getDay_id() {
        return day_id;
    }

    @Override
    public String toString() {
        return "Write{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patient_id='" + patient_id + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", day_id='" + day_id +'\'' +
                '}';
    }
}
