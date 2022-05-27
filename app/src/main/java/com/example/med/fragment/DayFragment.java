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
import com.example.med.bd.write.Write;
import com.example.med.bd.write.WriteAdapter;
import com.example.med.bd.write.WriteRoomDatabase;
import com.example.med.rest.MedApiVolley;

import java.util.ArrayList;

public class DayFragment extends Fragment {

    private RecyclerView rvWrite;
    private ArrayList<Write> writeList;
    private WriteAdapter writeAdapter;
    private WriteRoomDatabase writeRoomDatabase;

    private MedApiVolley medApiVolley;
    private Day day;

    private AppCompatButton btnAdd;
    private TextView tvDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_day, container, false);

        day = (Day) getArguments().getSerializable("Day");

        btnAdd = view.findViewById(R.id.btn_add);
        tvDate = view.findViewById(R.id.tv_date);

        rvWrite = view.findViewById(R.id.rv_write);

        tvDate.setText(day.getDate());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WriteAddFragment writeAddFragment = new WriteAddFragment();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, writeAddFragment)
                        .commit();
            }
        });

        writeRoomDatabase = WriteRoomDatabase.getInstance(getContext());

        Thread thread = new Thread(new AnotherRunnable());
        thread.start();

        return view;
    }

    class AnotherRunnable implements Runnable {
        @Override
        public void run() {

            writeList = (ArrayList<Write>) writeRoomDatabase
                    .getWriteDao()
                    .getWriteByDayId(day.getId());

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    medApiVolley = new MedApiVolley(getContext());
                    medApiVolley.fillWrite();
                    writeAdapter = new WriteAdapter(getContext(), writeList);
                    rvWrite.setAdapter(writeAdapter);
                }
            });
        }
    }
}