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
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.patient.Patient;
import com.example.med.rest.MedApiVolley;

public class PatientDoctorChangeFragment extends Fragment {

    private MedApiVolley medApiVolley;

    Doctor doctor;
    Patient patient;

    private AppCompatButton btnChange;
    private TextView tvNameFragment;
    private EditText tvName, tvSurname, tvPatronymic, tvAge, tvPhoneNumber, tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_patient_doctor_change, container, false);

        String s = getArguments().getString("Client1");

        btnChange = view.findViewById(R.id.btn_change_client);
        tvNameFragment = view.findViewById(R.id.tv_name_fragment);
        tvName = view.findViewById(R.id.edt_name);
        tvSurname = view.findViewById(R.id.edt_surname);
        tvPatronymic = view.findViewById(R.id.edt_patronymic);
        tvAge = view.findViewById(R.id.edt_age);
        tvPhoneNumber = view.findViewById(R.id.edt_phone_number);
        tvEmail = view.findViewById(R.id.edt_email);

        if (s.equals("Врачи")) {
            tvNameFragment.setText("Изменить врача");
            doctor = (Doctor) getArguments().getSerializable("Client");

            tvName.setText(doctor.getName());
            tvSurname.setText(doctor.getSurname());
            tvPatronymic.setText(doctor.getPatronymic());
            tvAge.setText(doctor.getAge() + "");
            tvPhoneNumber.setText(doctor.getPhone_number());
            tvEmail.setText(doctor.getEmail());

        } else {
            tvNameFragment.setText("Изменить пациента");
            patient = (Patient) getArguments().getSerializable("Client");

            tvName.setText(patient.getName());
            tvSurname.setText(patient.getSurname());
            tvPatronymic.setText(patient.getPatronymic());
            tvAge.setText(patient.getAge() + "");
            tvPhoneNumber.setText(patient.getPhone_number());
            tvEmail.setText(patient.getEmail());

        }

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medApiVolley = new MedApiVolley(getContext());
                if (isInputValid()) {
                    if (s.equals("Врачи")) {

                        Doctor doctor1 = new Doctor(
                                doctor.getId(),
                                tvName.getText().toString(),
                                tvSurname.getText().toString(),
                                tvPatronymic.getText().toString(),
                                Integer.parseInt(tvAge.getText().toString()),
                                tvPhoneNumber.getText().toString(),
                                tvEmail.getText().toString()
                        );
                        medApiVolley.updateDoctor(doctor1);
                    } else {

                        Patient patient1 = new Patient(
                                patient.getId(),
                                tvName.getText().toString(),
                                tvSurname.getText().toString(),
                                tvPatronymic.getText().toString(),
                                Integer.parseInt(tvAge.getText().toString()),
                                tvPhoneNumber.getText().toString(),
                                tvEmail.getText().toString()
                        );
                        medApiVolley.updatePatient(patient1);
                    }

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(PatientDoctorChangeFragment.this)
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