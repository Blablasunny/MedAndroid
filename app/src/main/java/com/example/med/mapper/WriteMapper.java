package com.example.med.mapper;

import android.icu.text.Edits;

import com.example.med.bd.patient.Patient;
import com.example.med.bd.write.Write;

import org.json.JSONException;
import org.json.JSONObject;


public class WriteMapper {

    public static Write writeFromJson(JSONObject jsonObject) {

        Write write = null;

        try {
            write = new Write(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("info"),
                    PatientMapper.patientFromWriteJson(jsonObject).getId(),
                    DoctorMapper.doctorFromWriteJson(jsonObject).getId(),
                    jsonObject.getInt("dayId")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return write;
    }
}
