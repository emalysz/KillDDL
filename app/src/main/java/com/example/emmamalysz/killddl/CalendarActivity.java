package com.example.emmamalysz.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Model.Deadline;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    ListView deadlineList;
    List<Deadline> ddls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);
        deadlineList = (ListView) findViewById(R.id.deadlineList);
        Deadline[] deadlines = new Deadline[] {
                new Deadline("ITP Exam", "study for exam", new Date(), 1, "RED"),
                new Deadline("Electricity Bill", "pay online", new Date(), 2, "GREEN"),
                new Deadline("103 PA", "code in c++", new Date(), 1, "ORANGE")
        };
        ddls = new ArrayList<>(Arrays.asList(deadlines));
        final ArrayAdapter<Deadline> arrayAdapter = new ArrayAdapter<Deadline>(this,
                android.R.layout.simple_list_item_1, ddls);
        deadlineList.setAdapter(arrayAdapter);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth) + " " + (month + 1) + " " + (year);
                myDate.setText(date);
                Date dateToSend = new Date(year, month, dayOfMonth);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("DATE", dateToSend);
                startActivity(intent);
            }
        });

        deadlineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Deadline selectedDeadline = (Deadline)deadlineList.getItemAtPosition(i);
                // change activity which is sent to DEADLINEVIEW
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("DEADLINE", selectedDeadline);
                // to retrieve: getSerializable extra
                startActivity(intent);
            }
        });
    }

}
