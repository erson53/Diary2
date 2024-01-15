package com.example.diary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }

    private String name;
    private String message; // Hinzufügen der Nachricht
    private LocalDate date;
    private LocalTime time;

    public Event(String name, String message, LocalDate date, LocalTime time) {
        this.name = name;
        this.message = message; // Setzen der Nachricht
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message; // Abrufen der Nachricht
    }

    public void setMessage(String message) {
        this.message = message; // Setzen der Nachricht
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
