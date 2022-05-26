package com.example.med;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.med.bd.day.DayAdapter;
import com.example.med.rest.MedApiVolley;

public class MainActivity extends AppCompatActivity {

    private DayAdapter dayAdapter;

    private MedApiVolley medApiVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medApiVolley = new MedApiVolley(this);
    }

//    public void updateAdapter() {
//
//        dayAdapter.notifyDataSetChanged();
//    }
}