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
import com.example.med.bd.day.Day;
import com.example.med.bd.day.DayRoomDatabase;

import java.util.List;

public class DaySpinnerAdapter extends ArrayAdapter<Day> {

    public DaySpinnerAdapter(@NonNull Context context, @NonNull List<Day> dayList) {
        super(context, R.layout.spinner_item, dayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView)convertView.findViewById(R.id.tv_spinner)).setText(
                DayRoomDatabase.getInstance(getContext()).getDayDao().loadAll().get(position).getDate()
        );

        return convertView;
    }
}
