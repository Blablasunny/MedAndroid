package com.example.med.rest;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.med.MainActivity;
import com.example.med.bd.day.Day;
import com.example.med.bd.day.DayDao;
import com.example.med.bd.day.DayRoomDatabase;
import com.example.med.bd.doctor.Doctor;
import com.example.med.bd.doctor.DoctorDao;
import com.example.med.bd.doctor.DoctorRoomDatabase;
import com.example.med.bd.patient.Patient;
import com.example.med.bd.patient.PatientDao;
import com.example.med.bd.patient.PatientRoomDatabase;
import com.example.med.bd.write.Write;
import com.example.med.bd.write.WriteDao;
import com.example.med.bd.write.WriteRoomDatabase;
import com.example.med.fragment.DayFragment;
import com.example.med.mapper.DayMapper;
import com.example.med.mapper.DoctorMapper;
import com.example.med.mapper.PatientMapper;
import com.example.med.mapper.WriteMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedApiVolley implements MedApi {

    public static final String API_TEST = "API_TEST";
    public static final String BASE_URL ="http://192.168.0.15:8083";
    private final Context context;
    private  Response.ErrorListener errorListener;

    public MedApiVolley(Context context) {
        this.context = context;

        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(API_TEST, error.toString());
            }
        };
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

    public WriteDao writeDb(Context context) {

        WriteRoomDatabase writeRoomDatabase = WriteRoomDatabase.getInstance(context);
        WriteDao writeDao = writeRoomDatabase.getWriteDao();

        return writeDao;
    }

    @Override
    public void fillPatient() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/patient";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        PatientDao patientDao = patientDb(context);

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Patient patient = PatientMapper.patientFromJson(jsonObject);
                                if (patientDao.loadAll().size() <= patient.getId()) {
                                    patientDao.insert(patient);
                                }
                            }

                            Log.d(API_TEST, patientDao.loadAll().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillDoctor() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/doctor";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        DoctorDao doctorDao = doctorDb(context);

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Doctor doctor = DoctorMapper.doctorFromJson(jsonObject);
                                if (doctorDao.loadAll().size() <= doctor.getId()) {
                                    doctorDao.insert(doctor);
                                }
                            }

                            Log.d(API_TEST, doctorDao.loadAll().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillDay() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/day";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        DayDao dayDao = dayDb(context);

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Day day = DayMapper.dayFromJson(jsonObject);
                                if (dayDao.loadAll().size() <= day.getId()) {
                                    dayDao.insert(day);
                                }
                            }
                            ((MainActivity)context).updateAdapter();
                            Log.d(API_TEST, dayDao.loadAll().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillWrite() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/write";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        WriteDao writeDao = writeDb(context);

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Write write = WriteMapper.writeFromJson(jsonObject);
                                if (writeDao.loadAll().size() <= write.getId()) {
                                    writeDao.insert(write);
                                }
                            }
                            Log.d(API_TEST, writeDao.loadAll().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);
    }

    @Override
    public void addDay(Day day) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/day";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DayDao dayDao = dayDb(context);
                        dayDao.insert(day);

                        fillDay();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("date", day.getDate());

                return params;
            }
        };

        requestQueue.add(request);

    }

    @Override
    public void addPatient(Patient patient) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/patient";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        PatientDao patientDao = patientDb(context);
                        patientDao.insert(patient);

                        fillPatient();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", patient.getName());
                params.put("surname", patient.getSurname());
                params.put("patronymic", patient.getPatronymic());
                params.put("age", String.valueOf(patient.getAge()));
                params.put("phone_number", patient.getPhone_number());
                params.put("email", patient.getEmail());

                return params;
            }
        };

        requestQueue.add(request);
    }

    @Override
    public void addDoctor(Doctor doctor) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/doctor";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DoctorDao doctorDao = doctorDb(context);
                        doctorDao.insert(doctor);

                        fillDoctor();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", doctor.getName());
                params.put("surname", doctor.getSurname());
                params.put("patronymic", doctor.getPatronymic());
                params.put("age", String.valueOf(doctor.getAge()));
                params.put("phone_number", doctor.getPhone_number());
                params.put("email", doctor.getEmail());

                return params;
            }
        };

        requestQueue.add(request);
    }

    @Override
    public void updatePatient(Patient patient) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/patient/" + patient.getId();

        StringRequest request = new StringRequest(
                Request.Method.PUT,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        PatientDao patientDao = patientDb(context);
                        patientDao.update(patient);

                        fillPatient();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", patient.getName());
                params.put("surname", patient.getSurname());
                params.put("patronymic", patient.getPatronymic());
                params.put("age", String.valueOf(patient.getAge()));
                params.put("phone_number", patient.getPhone_number());
                params.put("email", patient.getEmail());

                return params;
            }
        };

        requestQueue.add(request);
    }

    @Override
    public void updateDoctor(Doctor doctor) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/doctor/" + doctor.getId();

        StringRequest request = new StringRequest(
                Request.Method.PUT,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DoctorDao doctorDao = doctorDb(context);
                        doctorDao.update(doctor);

                        fillDoctor();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", doctor.getName());
                params.put("surname", doctor.getSurname());
                params.put("patronymic", doctor.getPatronymic());
                params.put("age", String.valueOf(doctor.getAge()));
                params.put("phone_number", doctor.getPhone_number());
                params.put("email", doctor.getEmail());

                return params;
            }
        };

        requestQueue.add(request);
    }

    @Override
    public void addWrite(Write write) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/write";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        WriteDao writeDao = writeDb(context);
                        writeDao.insert(write);

                        fillWrite();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", write.getName());
                params.put("info", write.getInfo());
                params.put("patientId", String.valueOf(write.getPatient_id()));
                params.put("doctorId", String.valueOf(write.getDoctor_id()));
                params.put("dayId", String.valueOf(write.getDay_id()));

                return params;
            }
        };

        requestQueue.add(request);

    }

    @Override
    public void updateWrite(Write write) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/write/" + write.getId();

        StringRequest request = new StringRequest(
                Request.Method.PUT,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        WriteDao writeDao = writeDb(context);
                        writeDao.update(write);

                        fillWrite();

                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", write.getName());
                params.put("info", write.getInfo());
                params.put("patientId", String.valueOf(write.getPatient_id()));
                params.put("doctorId", String.valueOf(write.getDoctor_id()));
                params.put("dayId", String.valueOf(write.getDay_id()));

                return params;
            }
        };

        requestQueue.add(request);
    }

    @Override
    public void deleteWrite(long id) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/write/" + id;

        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fillWrite();
                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        );

        requestQueue.add(request);
    }
}
