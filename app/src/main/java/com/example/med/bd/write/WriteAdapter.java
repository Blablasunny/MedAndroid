package com.example.med.bd.write;

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
import com.example.med.bd.day.DayDao;
import com.example.med.bd.day.DayRoomDatabase;
import com.example.med.bd.doctor.DoctorDao;
import com.example.med.bd.doctor.DoctorRoomDatabase;
import com.example.med.bd.patient.Patient;
import com.example.med.bd.patient.PatientAdapter;
import com.example.med.bd.patient.PatientDao;
import com.example.med.bd.patient.PatientRoomDatabase;
import com.example.med.fragment.PatientDoctorChangeFragment;

import java.util.List;

public class WriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Write> writeList;

    public WriteAdapter(Context context, List<Write> writeList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.writeList = writeList;
    }

    public PatientDao patientDb(Context context) {

        PatientRoomDatabase patientRoomDatabase = PatientRoomDatabase.getInstance(context);
        PatientDao patientDao = patientRoomDatabase.getPatientDao();

        return patientDao;
    }

    public DoctorDao doctorDb(Context context) {

        DoctorRoomDatabase doctorRoomDatabase = DoctorRoomDatabase.getInstance(context);
        DoctorDao doctorDao = doctorRoomDatabase.getDoctorDao();

        return doctorDao;
    }

    public DayDao dayDb(Context context) {

        DayRoomDatabase dayRoomDatabase = DayRoomDatabase.getInstance(context);
        DayDao dayDao = dayRoomDatabase.getDayDao();

        return dayDao;
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvPatient, tvDoctor, tvDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_write_name);
            tvPatient = itemView.findViewById(R.id.tv_patient);
            tvDoctor = itemView.findViewById(R.id.tv_doctor);
            tvDate = itemView.findViewById(R.id.tv_date);
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

        Write write = writeList.get(position);

        PatientDao patient = patientDb(context);
        DoctorDao doctor = doctorDb(context);
        DayDao day = dayDb(context);

        ((MyHolder)holder).tvName.setText(write.getName());
        ((MyHolder)holder).tvPatient.setText(
                patient.getSurnameById(write.getPatient_id()) + " " +
                patient.getNameById(write.getPatient_id()) + " " +
                patient.getPatronymicById(write.getPatient_id()));
        ((MyHolder)holder).tvDoctor.setText(
                doctor.getSurnameById(write.getDoctor_id()) + " " +
                doctor.getNameById(write.getDoctor_id()) + " " +
                doctor.getPatronymicById(write.getDay_id())
        );
        ((MyHolder)holder).tvDate.setText(day.getDateById(write.getDay_id()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientDoctorChangeFragment changeBookFragment = new PatientDoctorChangeFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Write", write);

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
        return writeList.size();
    }
}
