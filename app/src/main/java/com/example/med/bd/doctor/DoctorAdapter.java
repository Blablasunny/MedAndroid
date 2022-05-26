package com.example.med.bd.doctor;

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

public class DoctorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Doctor> doctorList;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.doctorList = doctorList;
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

        Doctor doctor = doctorList.get(position);

        ((MyHolder)holder).tvClient.setText(
                doctor.getSurname() + " " +
                doctor.getName() + " " +
                doctor.getPatronymic()
        );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientDoctorChangeFragment changeBookFragment = new PatientDoctorChangeFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Client", doctor);

                changeBookFragment.setArguments(bundle);

                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, changeBookFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }
}
