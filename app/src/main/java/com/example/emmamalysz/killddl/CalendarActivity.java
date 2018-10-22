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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import Controller.KillDDLController;
import Model.Deadline;

public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView calendarView;
    TextView monthAndYear;
    ListView deadlineList;
    Button monthlyButton;
    Button dailyButton;
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

        monthlyButton = (Button)findViewById(R.id.monthlyButton);
        dailyButton = (Button) findViewById(R.id.dailyButton);
        monthlyButton.setPressed(true);
        monthlyButton.setHighlightColor(Color.BLUE);

        dailyButton.setPressed(false);

        dailyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // change to daily view
               // controller.openDailyView();
                // change activity which is sent to DAILYVIEW
                Intent intent = new Intent(getApplicationContext(), AddDeadlineActivity.class);
                // to retrieve: getSerializable extra
                startActivity(intent);
                return true;

            }
        });


        deadlineList = (ListView) findViewById(R.id.deadlineList);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.set(2018, 9, 10);
        cal2.set(2018, 9, 15);
        cal3.set(2018,8,20);
        monthAndYear.setText(new SimpleDateFormat("MMMM - yyyy").format(cal1.getTime()));
        Deadline[] deadlines = new Deadline[] {
                new Deadline("ITP Exam", "study for exam", cal1.getTime(), 1, 1, 1),
                new Deadline("Electricity Bill", "pay online", cal2.getTime(), 2, 1, 1),
                new Deadline("103 PA", "code in c++", cal3.getTime(), 1, 1, 1)
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
