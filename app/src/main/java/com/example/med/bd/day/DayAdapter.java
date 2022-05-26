package com.example.med.bd.day;

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
import com.example.med.fragment.DayFragment;
import com.example.med.fragment.PatientDoctorChangeFragment;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Day> dayList;

    public DayAdapter(Context context, List<Day> dayList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.dayList = dayList;
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_day, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Day day = dayList.get(position);

        ((MyHolder)holder).tvDate.setText(day.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DayFragment dayFragment = new DayFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Day", day);

                dayFragment.setArguments(bundle);

                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, dayFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
}
