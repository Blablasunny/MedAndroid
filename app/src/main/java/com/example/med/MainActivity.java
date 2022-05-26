package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.med.bd.day.Day;
import com.example.med.bd.day.DayAdapter;
import com.example.med.bd.day.DayRoomDatabase;
import com.example.med.fragment.DayAddFragment;
import com.example.med.rest.MedApiVolley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvDay;
    private ArrayList<Day> dayList;
    private DayAdapter dayAdapter;
    private DayRoomDatabase dayRoomDatabase;

    private MedApiVolley medApiVolley;


    private AppCompatButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add_day);
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

                    rvDay = findViewById(R.id.rv_day);
                    dayAdapter = new DayAdapter(MainActivity.this, dayList);
                    rvDay.setAdapter(dayAdapter);
                }
            });
        }
    }

    public void updateAdapter() {

        dayAdapter.notifyDataSetChanged();
    }

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