package com.example.med.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.med.R;
import com.example.med.bd.day.Day;
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.doctor.DoctorAdapter;
import com.example.med.bd.doctor.DoctorRoomDatabase;
import com.example.med.bd.patient.Patient;
import com.example.med.bd.patient.PatientAdapter;
import com.example.med.bd.patient.PatientRoomDatabase;
import com.example.med.bd.write.Write;
import com.example.med.bd.write.WriteAdapter;
import com.example.med.bd.write.WriteRoomDatabase;
import com.example.med.rest.MedApiVolley;

import java.util.ArrayList;

public class PatientDoctorFragment extends Fragment {

    private RecyclerView rvClient;
    private ArrayList<Patient> patientList;
    private ArrayList<Doctor> doctorList;
    private PatientAdapter patientAdapter;
    private DoctorAdapter doctorAdapter;
    private PatientRoomDatabase patientRoomDatabase;
    private DoctorRoomDatabase doctorRoomDatabase;

    private MedApiVolley medApiVolley;

    private AppCompatButton btnAdd;
    private TextView tvClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_patient_doctor, container, false);

        String s = getArguments().getString("Client");

        btnAdd = view.findViewById(R.id.btn_add);
        tvClient = view.findViewById(R.id.tv_client);

        rvClient = view.findViewById(R.id.rv_client);

        tvClient.setText(s);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientDoctorAddFragment patientDoctorAddFragment = new PatientDoctorAddFragment();

                Bundle bundle = new Bundle();
                bundle.putString("Client", "Врачи");

                patientDoctorAddFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, patientDoctorAddFragment)
                        .commit();
            }
        });

        if (s.equals("Врачи")) {

            doctorRoomDatabase = DoctorRoomDatabase.getInstance(getContext());

            Thread thread = new Thread(new AnotherRunnable());
            thread.start();

        } else {

            patientRoomDatabase = PatientRoomDatabase.getInstance(getContext());

            Thread thread = new Thread(new AnotherRunnable1());
            thread.start();

        }

        return view;
    }

    class AnotherRunnable implements Runnable {
        @Override
        public void run() {

            doctorList = (ArrayList<Doctor>) doctorRoomDatabase
                    .getDoctorDao()
                    .loadAll();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    medApiVolley = new MedApiVolley(getContext());
                    medApiVolley.fillDoctor();
                    doctorAdapter = new DoctorAdapter(getContext(), doctorList);
                    rvClient.setAdapter(doctorAdapter);
                }
            });
        }
    }

    class AnotherRunnable1 implements Runnable {
        @Override
        public void run() {

            patientList = (ArrayList<Patient>) patientRoomDatabase
                    .getPatientDao()
                    .loadAll();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    medApiVolley = new MedApiVolley(getContext());
                    medApiVolley.fillPatient();
                    patientAdapter = new PatientAdapter(getContext(), patientList);
                    rvClient.setAdapter(patientAdapter);
                }
            });
        }
    }
}