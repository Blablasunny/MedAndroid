package com.example.med.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med.R;
import com.example.med.bd.day.Day;
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.patient.Patient;
import com.example.med.rest.MedApiVolley;

public class PatientDoctorAddFragment extends Fragment {

    private MedApiVolley medApiVolley;

    private AppCompatButton btnAdd;
    private TextView tvNameFragment;
    private EditText tvName, tvSurname, tvPatronymic, tvAge, tvPhoneNumber, tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_doctor_add, container, false);

        String s = getArguments().getString("Client");

        btnAdd = view.findViewById(R.id.btn_add_client);
        tvNameFragment = view.findViewById(R.id.tv_name_fragment);
        tvName = view.findViewById(R.id.edt_name);
        tvSurname = view.findViewById(R.id.edt_surname);
        tvPatronymic = view.findViewById(R.id.edt_patronymic);
        tvAge = view.findViewById(R.id.edt_age);
        tvPhoneNumber = view.findViewById(R.id.edt_phone_number);
        tvEmail = view.findViewById(R.id.edt_email);

        if (s.equals("Врачи")) {
            tvNameFragment.setText("Добавить врача");
        } else {
            tvNameFragment.setText("Добавить пациента");
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medApiVolley = new MedApiVolley(getContext());
                if (isInputValid()) {
                    if (s.equals("Врачи")) {

                        Doctor doctor = new Doctor(
                                tvName.getText().toString(),
                                tvSurname.getText().toString(),
                                tvPatronymic.getText().toString(),
                                Integer.parseInt(tvAge.getText().toString()),
                                tvPhoneNumber.getText().toString(),
                                tvEmail.getText().toString()
                        );
                        medApiVolley.addDoctor(doctor);
                    } else {

                        Patient patient = new Patient(
                                tvName.getText().toString(),
                                tvSurname.getText().toString(),
                                tvPatronymic.getText().toString(),
                                Integer.parseInt(tvAge.getText().toString()),
                                tvPhoneNumber.getText().toString(),
                                tvEmail.getText().toString()
                        );
                        medApiVolley.addPatient(patient);
                    }

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(PatientDoctorAddFragment.this)
                            .commit();
                }else{
                    Toast.makeText(getContext(), "Введите имя, фамилию, отчество, возраст", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    boolean isInputValid(){
        return !tvName.getText().toString().isEmpty() && !tvSurname.getText().toString().isEmpty() &&
               !tvPatronymic.getText().toString().isEmpty() && !tvAge.getText().toString().isEmpty();
    }
}