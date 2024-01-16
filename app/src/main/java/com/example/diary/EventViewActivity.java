package com.example.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        // Initialisieren der Views
        eventTitleTextView = findViewById(R.id.eventTitleTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        eventMessageTextView = findViewById(R.id.eventMessageTextView);
        deleteEventButton = findViewById(R.id.deleteEventButton);
        backToWeekButton = findViewById(R.id.backToWeekButton);

        // Überprüfen, ob Intent-Daten übergeben wurden
        Intent intent = getIntent();
        if (intent != null) {
            String eventTitle = intent.getStringExtra("eventTitle");
            String eventTime = intent.getStringExtra("eventTime");
            String eventMessage = intent.getStringExtra("eventMessage");

            // Setzen der Daten in die TextViews
            eventTitleTextView.setText(eventTitle);
            eventTimeTextView.setText(eventTime);
            eventMessageTextView.setText(eventMessage);

            // Hinzufügen eines Click-Listeners zum Löschen des Eintrags
            deleteEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Hier kannst du den Code zum Löschen des Eintrags implementieren
                    deleteEvent();
                }
            });
        }
        backToWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hier kannst du den Code zum Zurückkehren zur Woche implementieren
                backToWeekView();
            }
        });
    }

    private void deleteEvent() {
        // Annahme: Event.eventsList ist deine Liste von Ereignissen
        Intent intent = getIntent();
        if (intent != null) {
            String eventTitle = intent.getStringExtra("eventTitle");

            // Durchsuche die Event-Liste nach dem zu löschenden Event
            for (Event event : Event.eventsList) {
                if (event.getName().equals(eventTitle)) {
                    // Wenn das Event gefunden wurde, entferne es aus der Liste
                    Event.eventsList.remove(event);
                    break; // Beende die Schleife, da das Event gefunden wurde
                }
            }
        }

        // Schließe die Activity nach dem Löschen
        finish();
    }
    private void backToWeekView() {
        // Starte die WeekViewActivity und schließe die aktuelle Activity
        Intent intent = new Intent(this, WeekViewActivity.class);
        startActivity(intent);
        finish();
    }
}
