package com.example.emmamalysz.killddl;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.Deadline;

public class EditDeadlineActivity extends AppCompatActivity {
    EditText deadlineName;
    EditText deadlineDescription;
    Button addDeadline;
    SeekBar notification;
    SeekBar priority;
    Date date;
    Spinner spinner;
    int color;
    private CalendarView calendarView;
    private Object AdapterView;
    private TimePicker timePicker1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deadline);





        //initialize widgets
        deadlineName = (EditText) findViewById(R.id.deadlineName);
        deadlineDescription = (EditText) findViewById(R.id.deadlineDescription);
        addDeadline = (Button) findViewById(R.id.addDeadline);
        notification = (SeekBar)findViewById(R.id.notification);
        priority = (SeekBar)findViewById(R.id.priority);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        date = Calendar.getInstance().getTime();
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        spinner = (Spinner) findViewById(R.id.color_spinner);
        String[] colors={"Green","Blue","Red","Gray"};
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,colors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);
        color = spinner.getSelectedItemPosition();

        //change values
        Intent intent = getIntent();
        Deadline d = (Deadline) intent.getSerializableExtra("deadline");
        deadlineName.setText(d.getTitle());
        deadlineDescription.setText(d.getDescription());
        notification.setProgress(d.getNotification());
        priority.setProgress(d.getPriority());
        spinner.setSelection(d.getColor());
        timePicker1.setHour(d.getDate().getHours());
        timePicker1.setMinute(d.getDate().getMinutes());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      //  Date date = sdf.parse(d.getDate().toString());
        //long startDate = date.getTime();
        //calendarView.setDate(d.getDate());

        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                color = spinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                color = spinner.getSelectedItemPosition();
            }

        });



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
                int hour = timePicker1.getCurrentHour();
                int min = timePicker1.getCurrentMinute();
                date.setHours(hour);
                date.setMinutes(min);
                Deadline d = new Deadline(_dlName, _dlDescription,date,_priority,_notify,color);
                System.out.println("THE DEADLINE IS - " + _dlName + " - AND " + _dlDescription + " AT " + date.toString() + " WITH PRIORITY " + _priority + " AND NOTIFICATION " + _notify);
                // Intent intent = new Intent(AddDeadlineActivity.this, LoginActivity.class);
                // intent.putExtra("deadline", (Serializable) d);
                // startActivity(intent);

                Intent intent = new Intent();
                intent.putExtra("deadline", (Serializable) d );
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });


    }

}
