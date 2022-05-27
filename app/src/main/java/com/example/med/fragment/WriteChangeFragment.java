package com.example.med.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.med.R;
import com.example.med.bd.day.Day;
import com.example.med.bd.day.DayRoomDatabase;
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.doctor.DoctorRoomDatabase;
import com.example.med.bd.patient.Patient;
import com.example.med.bd.patient.PatientRoomDatabase;
import com.example.med.bd.write.Write;
import com.example.med.rest.MedApiVolley;
import com.example.med.spinner_adapter.DaySpinnerAdapter;
import com.example.med.spinner_adapter.DoctorSpinnerAdapter;
import com.example.med.spinner_adapter.PatientSpinnerAdapter;

import java.util.ArrayList;

public class WriteChangeFragment extends Fragment {

    private MedApiVolley medApiVolley;

    private EditText tvName;
    private EditText tvInfo;
    private AppCompatSpinner spnPatient, spnDoctor, spnDay;
    private AppCompatButton btnChange;

    private PatientSpinnerAdapter patientSpinnerAdapter;
    private DoctorSpinnerAdapter doctorSpinnerAdapter;
    private DaySpinnerAdapter daySpinnerAdapter;

    private ArrayList<Patient> patientList;
    private PatientRoomDatabase patientRoomDatabase;

    private ArrayList<Doctor> doctorList;
    private DoctorRoomDatabase doctorRoomDatabase;

    private ArrayList<Day> dayList;
    private DayRoomDatabase dayRoomDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_change, container, false);

        Write write = (Write) getArguments().getSerializable("Write");

        tvName = view.findViewById(R.id.edt_name);
        tvInfo = view.findViewById(R.id.edt_info);
        spnPatient = view.findViewById(R.id.spn_patient);
        spnDoctor = view.findViewById(R.id.spn_doctor);
        spnDay = view.findViewById(R.id.spn_day);
        btnChange = view.findViewById(R.id.btn_change_write);

        tvName.setText(write.getName());
        tvInfo.setText(write.getInfo());

        patientRoomDatabase = PatientRoomDatabase.getInstance(getContext());
        doctorRoomDatabase = DoctorRoomDatabase.getInstance(getContext());
        dayRoomDatabase = DayRoomDatabase.getInstance(getContext());

        Thread thread = new Thread(new AnotherRunnable());
        thread.start();

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isInputValid()) {

                    medApiVolley = new MedApiVolley(getContext());
                    Write write1 = new Write(
                            write.getId(),
                            tvName.getText().toString(),
                            tvInfo.getText().toString(),
                            ((Patient)spnPatient.getSelectedItem()).getId(),
                            ((Doctor)spnDoctor.getSelectedItem()).getId(),
                            ((Day)spnDay.getSelectedItem()).getId()
                    );
                    medApiVolley.updateWrite(write1);

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(WriteChangeFragment.this)
                            .commit();
                } else {

                    Toast.makeText(getContext(), "Введите название записи и текст", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    class AnotherRunnable implements Runnable {
        @Override
        public void run() {

            patientList = (ArrayList<Patient>) patientRoomDatabase
                    .getPatientDao()
                    .loadAll();

            doctorList = (ArrayList<Doctor>) doctorRoomDatabase
                    .getDoctorDao()
                    .loadAll();

            dayList = (ArrayList<Day>) dayRoomDatabase
                    .getDayDao()
                    .loadAll();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    patientSpinnerAdapter = new PatientSpinnerAdapter(getContext(), patientList);
                    spnPatient.setAdapter(patientSpinnerAdapter);

                    doctorSpinnerAdapter = new DoctorSpinnerAdapter(getContext(), doctorList);
                    spnDoctor.setAdapter(doctorSpinnerAdapter);

                    daySpinnerAdapter = new DaySpinnerAdapter(getContext(), dayList);
                    spnDay.setAdapter(daySpinnerAdapter);
                }
            });
        }
    }

    boolean isInputValid(){
        return !tvName.getText().toString().isEmpty() && !tvInfo.getText().toString().isEmpty();
    }
}