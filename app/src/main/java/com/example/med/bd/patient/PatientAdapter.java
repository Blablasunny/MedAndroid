package com.example.med.bd.patient;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.med.R;
import com.example.med.fragment.PatientDoctorChangeFragment;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Patient> patientList;

    public PatientAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.patientList = patientList;
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvClient;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvClient = itemView.findViewById(R.id.tv_client);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_client, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Patient patient = patientList.get(position);

        ((MyHolder)holder).tvClient.setText(
                patient.getSurname() + " " +
                patient.getName() + " " +
                patient.getPatronymic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientDoctorChangeFragment patientDoctorChangeFragment = new PatientDoctorChangeFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Client", patient);
                bundle.putString("Client1", "Пациенты");

                patientDoctorChangeFragment.setArguments(bundle);

                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, patientDoctorChangeFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}
