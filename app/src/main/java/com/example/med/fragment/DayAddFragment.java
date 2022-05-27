package com.example.med.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med.MainActivity;
import com.example.med.R;
import com.example.med.bd.day.Day;
import com.example.med.bd.day.DayAdapter;
import com.example.med.bd.day.DayDao;
import com.example.med.bd.day.DayRoomDatabase;
import com.example.med.rest.MedApiVolley;

import java.util.ArrayList;

public class DayAddFragment extends Fragment {

    private MedApiVolley medApiVolley;

    private AppCompatButton btnAdd;
    private EditText tvDay, tvMonth, tvYear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_add, container, false);

        btnAdd = view.findViewById(R.id.btn_add_day);
        tvDay = view.findViewById(R.id.edt_date_day);
        tvMonth = view.findViewById(R.id.edt_date_month);
        tvYear = view.findViewById(R.id.edt_date_year);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medApiVolley = new MedApiVolley(getContext());
                if (isInputValid()) {
                    Day day = new Day(
                            tvDay.getText().toString() + "." +
                                    tvMonth.getText().toString() + "." +
                                    tvYear.getText().toString()
                    );
                    medApiVolley.addDay(day);

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(DayAddFragment.this)
                            .commit();
                }else{
                    Toast.makeText(getContext(), "Введите дату", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    boolean isInputValid(){
        return !tvDay.getText().toString().isEmpty() && !tvMonth.getText().toString().isEmpty() && !tvYear.getText().toString().isEmpty();
    }
}