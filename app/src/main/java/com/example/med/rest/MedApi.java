package com.example.med.rest;

import com.example.med.bd.day.Day;
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.patient.Patient;
import com.example.med.bd.write.Write;

public interface MedApi {

    void fillPatient();

    void fillDoctor();

    void fillDay();

    void fillWrite();

    void addDay(Day day);

    void addPatient(Patient patient);

    void addDoctor(Doctor doctor);

    void updatePatient(Patient patient);

    void updateDoctor(Doctor doctor);

    void addWrite(Write write);

    void updateWrite(Write write);

    void deleteWrite(long id);

}
