package com.example.diary;

import static com.example.diary.Event.eventsList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.ArrayList;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private EditText eventMessageET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventMessageET = findViewById(R.id.eventMessageET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        String eventMessage = eventMessageET.getText().toString();

        // Lade die bestehenden Ereignisse aus den SharedPreferences
        ArrayList<Event> eventsList = EventStorage.loadEvents(getApplicationContext());

        // Füge das neue Ereignis zur Liste hinzu
        Event newEvent = new Event(eventName, eventMessage, CalendarUtils.selectedDate, time);
        eventsList.add(newEvent);

        // Speichere die aktualisierte Liste der Ereignisse in den SharedPreferences
        EventStorage.saveEvents(getApplicationContext(), eventsList);

        finish();
    }

    public void uploadImageAction(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                ImageView uploadedImageView = findViewById(R.id.uploadedImageView);
                uploadedImageView.setImageURI(selectedImageUri);
                uploadedImageView.setVisibility(View.VISIBLE);

                // Du kannst hier die Verarbeitung des ausgewählten Bildes implementieren
                // Zum Beispiel: Speichern des Bildes und dessen Pfad in der Event-Instanz
            }
        }
    }
}
