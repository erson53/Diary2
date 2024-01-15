package com.example.diary;

import static com.example.diary.Event.eventsList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private EditText eventMessageET; // Hinzufügen der EditText für die Nachricht
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    private static final int PICK_IMAGE_REQUEST = 1; // You can use any unique value


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
        eventMessageET = findViewById(R.id.eventMessageET); // Initialisieren der Nachrichten-EditText
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        String eventMessage = eventMessageET.getText().toString(); // Abrufen der Nachricht
        Event newEvent = new Event(eventName, eventMessage, CalendarUtils.selectedDate, time); // Übergeben der Nachricht
        eventsList.add(newEvent);
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
                Log.d("EventEditActivity", "Selected Image URI: " + selectedImageUri.toString());

                ImageView uploadedImageView = findViewById(R.id.uploadedImageView);
                uploadedImageView.setImageURI(selectedImageUri);
                uploadedImageView.setVisibility(View.VISIBLE);

                EventAdapter eventAdapter = new EventAdapter(this, eventsList);
                eventAdapter.setImageFilePath(selectedImageUri.toString());

            }
        }
    }
}
