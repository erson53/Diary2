    package com.example.diary;

    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

    public class EventViewActivity extends AppCompatActivity {

        private TextView eventTitleTextView;
        private TextView eventTimeTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_event_view);

            // Initialisieren Sie die Views
            eventTitleTextView = findViewById(R.id.eventTitleTextView);
            eventTimeTextView = findViewById(R.id.eventTimeTextView);

            // Überprüfen Sie, ob Intent-Daten übergeben wurden
            Intent intent = getIntent();
            if (intent != null) {
                String eventTitle = intent.getStringExtra("eventTitle");
                String eventTime = intent.getStringExtra("eventTime");

                // Setzen Sie die Daten in die TextViews
                eventTitleTextView.setText(eventTitle);
                eventTimeTextView.setText(eventTime);
            }
        }
    }