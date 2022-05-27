package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.med.bd.day.Day;
import com.example.med.bd.day.DayAdapter;
import com.example.med.bd.day.DayRoomDatabase;
import com.example.med.fragment.DayAddFragment;
import com.example.med.fragment.PatientDoctorFragment;
import com.example.med.rest.MedApiVolley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvDay;
    private ArrayList<Day> dayList;
    private DayAdapter dayAdapter;
    private DayRoomDatabase dayRoomDatabase;

    private MedApiVolley medApiVolley;

    private AppCompatButton btnAdd, btnPatient, btnDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add_day);
        btnPatient = findViewById(R.id.btn_patient);
        btnDoctor = findViewById(R.id.btn_doctor);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DayAddFragment dayAddFragment = new DayAddFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, dayAddFragment)
                        .commit();
            }
        });

        btnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientDoctorFragment patientDoctorFragment = new PatientDoctorFragment();

                Bundle bundle = new Bundle();
                bundle.putString("Client", "Пациенты");

                patientDoctorFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, patientDoctorFragment)
                        .commit();
            }
        });

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientDoctorFragment patientDoctorFragment = new PatientDoctorFragment();

                Bundle bundle = new Bundle();
                bundle.putString("Client", "Врачи");

                patientDoctorFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, patientDoctorFragment)
                        .commit();
            }
        });
        medApiVolley = new MedApiVolley(MainActivity.this);
        medApiVolley.fillDay();

        dayRoomDatabase = DayRoomDatabase.getInstance(this);

        Thread thread=new Thread(new AnotherRunnable());
        thread.start();
    }

    class AnotherRunnable implements Runnable {
        @Override
        public void run() {
            dayList = (ArrayList<Day>) dayRoomDatabase
                    .getDayDao()
                    .loadAll();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    medApiVolley = new MedApiVolley(MainActivity.this);
                    medApiVolley.fillDay();
                    medApiVolley.fillPatient();
                    medApiVolley.fillDoctor();
                    rvDay = findViewById(R.id.rv_day);
                    dayAdapter = new DayAdapter(MainActivity.this, dayList);
                    rvDay.setAdapter(dayAdapter);
                }
            });
        }
    }

//    public void updateAdapter() {
//
//        dayAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        if (fragmentList.size() > 0) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragmentList.get(fragmentList.size() - 1))
                    .commit();
        } else {

            finish();
        }
    }
}