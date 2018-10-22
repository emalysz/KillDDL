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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Controller.KillDDLController;
import Model.CustomListAdapter;
import Model.Deadline;
import Model.User;
import view.DailyView;
import view.DeadlineView;

public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView calendarView;
    TextView monthAndYear;
    ListView deadlineList;
    Button monthlyButton;
    Button dailyButton;
    List<Deadline> ddls;
    Map<Integer, Integer> colorMap;
    ArrayAdapter<Deadline> arrayAdapter;
    KillDDLController controller = KillDDLController.getInstance();

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

        colorMap = new HashMap<Integer, Integer>();
        colorMap.put(0, Color.GREEN);
        colorMap.put(1, Color.BLUE);
        colorMap.put(2, Color.RED);
        colorMap.put(3, Color.GRAY);


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
                Intent intent = new Intent(getApplicationContext(), DailyView.class);
                // to retrieve: getSerializable extra
                startActivity(intent);
                return true;

            }
        });

        Calendar cal1 = Calendar.getInstance();
        ddls = controller.getMonthlyDeadlines(cal1.getTime());
        deadlineList = (ListView) findViewById(R.id.deadlineList);


        monthAndYear.setText(new SimpleDateFormat("MMMM - yyyy").format(cal1.getTime()));
        List<Deadline> allDeadlines = controller.getDeadlines();

        for (int i=0; i<allDeadlines.size(); i++) {
            Deadline d = allDeadlines.get(i);
            Log.d("TIME", Long.toString(d.getDate().getTime()));
            Calendar c = Calendar.getInstance();
            c.setTime(d.getDate());
            Event e = new Event(colorMap.get(d.getColor()), c.getTimeInMillis(), d.getTitle());
            calendarView.addEvent(e);


        }

        arrayAdapter = new CustomListAdapter(CalendarActivity.this ,
                R.layout.custom_list , ddls);


        deadlineList.setAdapter(arrayAdapter);


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Deadline> dayDeadlines = controller.getDayDeadlines(dateClicked);
                deadlineList.setAdapter(new CustomListAdapter(CalendarActivity.this, R.layout.custom_list, dayDeadlines));
                deadlineTitle.setText(dateFormatDay.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthAndYear.setText(dateFormatMonth.format(firstDayOfNewMonth));
                List<Deadline> monthDeadlines = controller.getMonthlyDeadlines(firstDayOfNewMonth);
                deadlineList.setAdapter(new CustomListAdapter(CalendarActivity.this, R.layout.custom_list, monthDeadlines));
                deadlineTitle.setText("This Month");

            }

        });


        deadlineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Deadline selectedDeadline = (Deadline)deadlineList.getItemAtPosition(i);
                // change activity which is sent to DEADLINEVIEW

                Intent intent = new Intent(getApplicationContext(), DeadlineView.class);
                intent.putExtra("Deadline", selectedDeadline);
                startActivity(intent);
            }
        });
    }

}
