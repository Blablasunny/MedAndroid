package com.example.med.spinner_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.med.R;
import com.example.med.bd.patient.Patient;
import com.example.med.bd.patient.PatientRoomDatabase;

import java.util.List;

public class PatientSpinnerAdapter extends ArrayAdapter<Patient> {

    public PatientSpinnerAdapter(@NonNull Context context, @NonNull List<Patient> patientList) {
        super(context, R.layout.spinner_item, patientList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView)convertView.findViewById(R.id.tv_spinner)).setText(
                PatientRoomDatabase.getInstance(getContext()).getPatientDao().loadAll().get(position).getSurname() + " " +
                PatientRoomDatabase.getInstance(getContext()).getPatientDao().loadAll().get(position).getName() + " " +
                PatientRoomDatabase.getInstance(getContext()).getPatientDao().loadAll().get(position).getPatronymic()
        );

        return convertView;
    }
}
