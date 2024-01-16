package com.example.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventViewActivity extends AppCompatActivity {

    private TextView eventTitleTextView;
    private TextView eventTimeTextView;
    private TextView eventMessageTextView;
    private Button deleteEventButton;
    private Button backToWeekButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        eventTitleTextView = findViewById(R.id.eventTitleTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        eventMessageTextView = findViewById(R.id.eventMessageTextView);
        deleteEventButton = findViewById(R.id.deleteEventButton);
        backToWeekButton = findViewById(R.id.backToWeekButton);

        Intent intent = getIntent();
        if (intent != null) {
            String eventTitle = intent.getStringExtra("eventTitle");
            String eventTime = intent.getStringExtra("eventTime");
            String eventMessage = intent.getStringExtra("eventMessage");

            eventTitleTextView.setText(eventTitle);
            eventTimeTextView.setText(eventTime);
            eventMessageTextView.setText(eventMessage);

            deleteEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteEvent(eventTitle);
                }
            });
        }

        backToWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToWeekView();
            }
        });
    }

    private void deleteEvent(String eventTitle) {
        ArrayList<Event> updatedEventsList = new ArrayList<>(Event.eventsList); // Kopiere die Liste

        for (Event event : updatedEventsList) {
            if (event.getName().equals(eventTitle)) {
                updatedEventsList.remove(event); // Nur das ausgewählte Event löschen
                break;
            }
        }

        EventStorage.saveEvents(getApplicationContext(), updatedEventsList); // Aktualisierte Liste speichern
        finish();
        }

    private void backToWeekView() {
        Intent intent = new Intent(this, WeekViewActivity.class);
        startActivity(intent);
        finish();
    }
}
