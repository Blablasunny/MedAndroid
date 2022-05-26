package com.example.med.mapper;

import com.example.med.bd.doctor.Doctor;

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorMapper {

    public static Doctor doctorFromJson(JSONObject jsonObject) {

        Doctor doctor = null;

        try {
            doctor = new Doctor(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getString("patronymic"),
                    jsonObject.getInt("age"),
                    jsonObject.getString("phone_number"),
                    jsonObject.getString("email")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    public static Doctor doctorFromWriteJson(JSONObject jsonObject) {

        Doctor doctor = null;

        try {
            doctor = doctorFromJson(jsonObject.getJSONObject("doctorDto"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return doctor;
    }
}
