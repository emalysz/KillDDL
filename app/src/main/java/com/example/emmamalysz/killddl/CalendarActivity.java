package com.example.emmamalysz.killddl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.google.firebase.auth.FirebaseAuth;

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
import Model.CustomRListAdapter;
import Model.Deadline;
import Model.SimpleItemTouchHelperCallback;
import Model.User;
import view.DailyView;
import view.DeadlineView;

public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView calendarView;
    TextView monthAndYear;
    RecyclerView deadlineList;
    Button monthlyButton;
    Button dailyButton;
    Button signoutButton;
    List<Deadline> ddls;
    Map<Integer, Integer> colorMap;
    CustomRListAdapter arrayAdapter;
    KillDDLController controller = KillDDLController.getInstance();
    Date currDate;
    Calendar curr;
    ItemTouchHelper mItemTouchHelper;
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();


    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMMM", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        curr = Calendar.getInstance();
        currDate = curr.getTime();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        monthAndYear = (TextView) findViewById(R.id.month);
        final FloatingActionButton addButtonCalendar = (FloatingActionButton) findViewById(R.id.add_button_calendar);
        calendarView = (CompactCalendarView) findViewById(R.id.calendarView);
        calendarView.setUseThreeLetterAbbreviation(true);
        calendarView.shouldSelectFirstDayOfMonthOnScroll(false);
        final TextView deadlineTitle = (TextView)findViewById(R.id.deadlineTitle);

        colorMap = new HashMap<Integer, Integer>();
        colorMap.put(0, Color.rgb(0,184,148));
        colorMap.put(1, Color.rgb(9, 132, 227));
        colorMap.put(2, Color.rgb(255,118,117));
        colorMap.put(3, Color.GRAY);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        signoutButton = (Button)findViewById(R.id.signoutButton);
        signoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // redirect to add deadline page
                mAuth.signOut();
                controller.logOutUser();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


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
                intent.putExtra("selectedDate", currDate);
                // to retrieve: getSerializable extra
                startActivity(intent);
                return true;

            }
        });

        addButtonCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // redirect to add deadline page
                Intent intent = new Intent(getApplicationContext(), AddDeadlineActivity.class);
                startActivity(intent);
            }
        });

        Calendar cal1 = Calendar.getInstance();
        ddls = controller.getMonthlyDeadlines(cal1.getTime());
        deadlineList = (RecyclerView) findViewById(R.id.deadlineList);
        arrayAdapter = new CustomRListAdapter(ddls);
        CustomRListAdapter adapter = new CustomRListAdapter(ddls);
        deadlineList.setAdapter(adapter);
        deadlineList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(deadlineList);


        monthAndYear.setText(new SimpleDateFormat("MMMM - yyyy").format(cal1.getTime()));
        List<Deadline> allDeadlines = controller.getDeadlines();

        Log.d("HELLO", "hithree" + controller.getCurrentUser().getDeadlines().size());
        Log.d("HELLO", "hifour" + controller.getDeadlines().size());

        for (int i=0; i<allDeadlines.size(); i++) {
            Deadline d = allDeadlines.get(i);
            Log.d("TIME", Long.toString(d.getDate().getTime()));
            Calendar c = Calendar.getInstance();
            c.setTime(d.getDate());
            Event e = new Event(colorMap.get(d.getColor()), c.getTimeInMillis(), d.getTitle());
            calendarView.addEvent(e);

        }




        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Deadline> dayDeadlines = controller.getDayDeadlines(dateClicked);
                CustomRListAdapter adapter = new CustomRListAdapter(dayDeadlines);
                adapter.setIsDayView(true);
                deadlineList.setAdapter(adapter);
                deadlineTitle.setText(dateFormatDay.format(dateClicked));
                currDate = dateClicked;
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthAndYear.setText(dateFormatMonth.format(firstDayOfNewMonth));
                List<Deadline> monthDeadlines = controller.getMonthlyDeadlines(firstDayOfNewMonth);
                CustomRListAdapter adapter = new CustomRListAdapter(monthDeadlines);
                adapter.setIsDayView(false);
                deadlineList.setAdapter(adapter);
                deadlineTitle.setText("This Month");
                currDate = curr.getTime();

            }

        });

    }

}
