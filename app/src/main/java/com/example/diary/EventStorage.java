package com.example.diary;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class EventStorage {
    private static final String PREFS_NAME = "EventPrefs";
    private static final String EVENTS_KEY = "events";

    public static void saveEvents(Context context, ArrayList<Event> events) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String eventsJson = gson.toJson(events);
        editor.putString(EVENTS_KEY, eventsJson);
        editor.apply();
    }

    public static ArrayList<Event> loadEvents(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String eventsJson = prefs.getString(EVENTS_KEY, null);
        if (eventsJson != null) {
            Type type = new TypeToken<ArrayList<Event>>() {}.getType();
            return gson.fromJson(eventsJson, type);
        } else {
            return new ArrayList<>();
        }
    }
}