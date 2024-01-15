package com.example.diary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EventViewActivity extends AppCompatActivity {

    private TextView eventTitleTextView;
    private TextView eventTimeTextView;
    private TextView eventMessageTextView; // TextView für die Nachricht hinzufügen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        // Initialisieren Sie die Views
        eventTitleTextView = findViewById(R.id.eventTitleTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        eventMessageTextView = findViewById(R.id.eventMessageTextView); // TextView für die Nachricht initialisieren

        // Überprüfen Sie, ob Intent-Daten übergeben wurden
        Intent intent = getIntent();
        if (intent != null) {
            String eventTitle = intent.getStringExtra("eventTitle");
            String eventTime = intent.getStringExtra("eventTime");
            String eventMessage = intent.getStringExtra("eventMessage"); // Nachricht abrufen

            // Setzen Sie die Daten in die TextViews
            eventTitleTextView.setText(eventTitle);
            eventTimeTextView.setText(eventTime);
            eventMessageTextView.setText(eventMessage); // Nachricht setzen
        }
    }
}
