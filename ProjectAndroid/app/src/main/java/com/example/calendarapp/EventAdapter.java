package com.example.calendarapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @RequiresApi(api = Build.VERSION_CODES.O) //Biểu thị rằng phần tử được chú thích chỉ nên được gọi ở cấp API đã cho hoặc cao hơn.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        // set sự kiện (+"\nDate: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate))
        String eventTitle = event.getName() +"\n"+ event.getTime();
        eventCellTV.setText(eventTitle);

        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        //Xoá sự kiện
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                remove(event);
                Event.eventsList.remove(event);
            }
        });


        return convertView;
    }
}
