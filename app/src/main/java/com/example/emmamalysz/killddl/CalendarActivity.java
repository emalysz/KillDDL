package com.example.emmamalysz.killddl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ListView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Model.Deadline;

public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView calendarView;
    TextView monthAndYear;
    ListView deadlineList;
    List<Deadline> ddls;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMMM", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        monthAndYear = (TextView) findViewById(R.id.month);
        calendarView = (CompactCalendarView) findViewById(R.id.calendarView);
        calendarView.setUseThreeLetterAbbreviation(true);
        calendarView.shouldSelectFirstDayOfMonthOnScroll(false);
        final TextView deadlineTitle = (TextView)findViewById(R.id.deadlineTitle);

        deadlineList = (ListView) findViewById(R.id.deadlineList);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.set(2018, 9, 10);
        cal2.set(2018, 9, 15);
        cal3.set(2018,8,20);
        monthAndYear.setText(new SimpleDateFormat("MMMM - yyyy").format(cal1.getTime()));
        Deadline[] deadlines = new Deadline[] {
                new Deadline("ITP Exam", "study for exam", cal1.getTime(), 1, "RED"),
                new Deadline("Electricity Bill", "pay online", cal2.getTime(), 2, "GREEN"),
                new Deadline("103 PA", "code in c++", cal3.getTime(), 1, "ORANGE")
        };

        for (int i=0; i<deadlines.length; i++) {
            Log.d("TIME", Long.toString(deadlines[i].getDate().getTime()));
            Event e = new Event(Color.RED, deadlines[i].getDate().getTime(), deadlines[i].getTitle());
            calendarView.addEvent(e);
        }
        ddls = new ArrayList<>(Arrays.asList(deadlines));
        final ArrayAdapter<Deadline> arrayAdapter = new ArrayAdapter<Deadline>(this,
                android.R.layout.simple_list_item_1, ddls);
        deadlineList.setAdapter(arrayAdapter);

        Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch); // initiate Switch

        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("Off");

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                deadlineTitle.setText(dateFormatDay.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthAndYear.setText(dateFormatMonth.format(firstDayOfNewMonth));
                deadlineTitle.setText("This Month");

            }

        });

//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                String date = (dayOfMonth) + " " + (month + 1) + " " + (year);
//
//                Date dateToSend = new Date(year, month, dayOfMonth);
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                intent.putExtra("DATE", dateToSend);
//                startActivity(intent);
//            }
//        });

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
