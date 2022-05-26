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
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.doctor.DoctorRoomDatabase;

import java.util.List;

public class DoctorSpinnerAdapter extends ArrayAdapter<Doctor> {

    public DoctorSpinnerAdapter(@NonNull Context context, @NonNull List<Doctor> doctorList) {
        super(context, R.layout.spinner_item, doctorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView)convertView.findViewById(R.id.tv_spinner)).setText(
                DoctorRoomDatabase.getInstance(getContext()).getDoctorDao().loadAll().get(position).getSurname() + " " +
                DoctorRoomDatabase.getInstance(getContext()).getDoctorDao().loadAll().get(position).getName() + " " +
                DoctorRoomDatabase.getInstance(getContext()).getDoctorDao().loadAll().get(position).getPatronymic()
        );

        return convertView;
    }
}
