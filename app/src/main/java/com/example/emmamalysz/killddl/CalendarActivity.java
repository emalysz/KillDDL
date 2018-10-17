package com.example.emmamalysz.killddl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    ListView deadlineList;
    List<String> ddls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);
        deadlineList = (ListView) findViewById(R.id.deadlineList);
        String [] deadlines = new String[] {
                "ITP Exam",
                "Interview",
                "Electricity Bill"
        };
        ddls = new ArrayList<>(Arrays.asList(deadlines));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ddls);
        deadlineList.setAdapter(arrayAdapter);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth) + " " + (month + 1) + " " + (year);
                myDate.setText(date);
                ddls.add(date);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

}
