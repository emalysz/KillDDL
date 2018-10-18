package com.example.emmamalysz.killddl;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import Model.Deadline;

public class AddDeadlineActivity extends AppCompatActivity {
    EditText deadlineName;
    EditText deadlineDescription;
    Button addDeadline;
    SeekBar notification;
    SeekBar priority;
    Date date;
    private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deadline);

        //initialize widgets
        deadlineName = (EditText) findViewById(R.id.deadlineName);
        deadlineDescription = (EditText) findViewById(R.id.deadlineDescription);
        addDeadline = (Button) findViewById(R.id.addDeadline);
        notification = (SeekBar)findViewById(R.id.notification);
        priority = (SeekBar)findViewById(R.id.priority);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        date = Calendar.getInstance().getTime();

        //calendar onchange
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = new Date(year,month,dayOfMonth);
            }
        });

        //listener for adding a deadline button
        addDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _dlName = deadlineName.getText().toString();
                String _dlDescription = deadlineDescription.getText().toString();
                int _notify = notification.getProgress();
                int _priority = priority.getProgress();

                Deadline d = new Deadline(_dlName, _dlDescription,date,_priority,_notify);
              //  System.out.println("THE DEADLINE IS - " + _dlName + " - AND " + _dlDescription + " AT " + date.toString() + " WITH PRIORITY " + _priority + " AND NOTIFICATION " + _notify);
                Intent intent = new Intent(AddDeadlineActivity.this, LoginActivity.class);
                intent.putExtra("deadline", (Serializable) d );
                startActivity(intent);
                //setResult(Activity.RESULT_OK);
               // finish();
            }
        });
    }
}
