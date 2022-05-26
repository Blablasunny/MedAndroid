package com.example.med.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.med.R;
import com.example.med.bd.patient.Patient;

public class PatientDoctorChangeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_patient_doctor_change, container, false);

        Patient patient = (Patient) getArguments().getSerializable("Patient");

        return view;
    }
}