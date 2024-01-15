package com.example.diary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{
    private String imageFilePath;
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    public void setImageFilePath(String filePath) {
        imageFilePath = filePath;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        Button showEventButton = convertView.findViewById(R.id.showEventButton);

        String eventTitle = event.getName() +" "+ CalendarUtils.formattedTime(event.getTime());
        eventCellTV.setText(eventTitle);

        showEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Erstellen Sie einen Intent und übergeben Sie die Event-Daten und den Dateipfad
                Intent intent = new Intent(getContext(), EventViewActivity.class);
                intent.putExtra("eventTitle", event.getName());
                intent.putExtra("eventTime", CalendarUtils.formattedTime(event.getTime()));

                // Starten Sie die EventViewActivity
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
