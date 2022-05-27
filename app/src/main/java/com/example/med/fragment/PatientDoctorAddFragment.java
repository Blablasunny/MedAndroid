package com.example.med.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.med.R;
import com.example.med.rest.MedApiVolley;

public class PatientDoctorAddFragment extends Fragment {

    private MedApiVolley medApiVolley;

    private AppCompatButton btnAdd;
    private EditText tvName;
    private EditText tvSurname;
    private EditText tvPatronymic;
    private EditText tvAge;
    private EditText tvPhoneNumber;
    private EditText tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_doctor_add, container, false);


        return view;
    }
}