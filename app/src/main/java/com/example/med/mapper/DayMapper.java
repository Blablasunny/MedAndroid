package com.example.med.mapper;

import com.example.med.bd.day.Day;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DayMapper {

    public static Day dayFromJson(JSONObject jsonObject) {

        Day day = null;

        try {
            day = new Day(
                    jsonObject.getInt("id"),
                    jsonObject.getString("date")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return day;
    }
}
