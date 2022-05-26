package com.example.med.mapper;

import com.example.med.bd.patient.Patient;

import org.json.JSONException;
import org.json.JSONObject;

public class PatientMapper {

    public static Patient patientFromJson(JSONObject jsonObject) {

        Patient patient = null;

        try {
            patient = new Patient(
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

        return patient;
    }

    public static Patient patientFromWriteJson(JSONObject jsonObject) {

        Patient patient = null;

        try {
            patient = patientFromJson(jsonObject.getJSONObject("patientDto"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patient;
    }
}
