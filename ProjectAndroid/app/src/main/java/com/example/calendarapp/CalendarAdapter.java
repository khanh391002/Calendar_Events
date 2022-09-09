package com.example.calendarapp;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import static com.example.calendarapp.CalendarViewHolder.*;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> // quản lí dữ liệu và cập nhật dự liệu vào View
{
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;
//    WeekViewActivity weekViewActivity;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false); // chuyển đổi XML thành view trong javacode
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams(); // phân biệt từng thành phần con
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = (int) parent.getHeight();

        return new CalendarViewHolder(view, onItemListener, days);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    // Ham xet màu cua canlendar
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        final LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
        else
        {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUtils.selectedDate))
                holder.parentView.setBackgroundColor(Color.parseColor("#00FF00"));
            else if (date.isBefore(CalendarUtils.selectedDate)){
                holder.parentView.setBackgroundColor(Color.parseColor("#CC9933"));
            }
            else{
                holder.parentView.setBackgroundColor(Color.parseColor("#999933"));
            }

        }

    }

    @Override
    public int getItemCount()
    {
        return days.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, LocalDate date);
    }
}

