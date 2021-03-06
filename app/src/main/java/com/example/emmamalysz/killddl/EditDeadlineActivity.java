package com.example.emmamalysz.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import Controller.KillDDLController;
import Model.Deadline;
import view.DeadlineView;

public class EditDeadlineActivity extends AppCompatActivity {
    EditText deadlineName;
    EditText deadlineDescription;
    Button addDeadline;
    SeekBar notification;
    SeekBar priority;
    SeekBar frequency;
    Calendar date;
    Spinner spinner;
    int color;
    private CalendarView calendarView;
    private Object AdapterView;
    private TimePicker timePicker1;
    KillDDLController controller = KillDDLController.getInstance();
    Deadline d;
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
        frequency = (SeekBar)findViewById(R.id.frequency);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        date = Calendar.getInstance();
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        spinner = (Spinner) findViewById(R.id.color_spinner);
        String[] colors={"Green","Blue","Red","Gray"};
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,colors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);
        color = spinner.getSelectedItemPosition();

        //change values
        Intent intent = getIntent();
        d = (Deadline) intent.getSerializableExtra("edit");
        if(d != null) {
            deadlineName.setText(d.getTitle());
            deadlineDescription.setText(d.getDescription());
            notification.setProgress(d.getNotification());
            priority.setProgress(d.getPriority());
            spinner.setSelection(d.getColor());
            frequency.setProgress(d.getFrequency());
            timePicker1.setHour(d.getDate().getHours());
            timePicker1.setMinute(d.getDate().getMinutes());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Calendar c = Calendar.getInstance();
            //  calendarView.setMinDate(d.getDate().getTime());
            calendarView.setDate(d.getDate().getTime(), true, false);
        }

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
             //   date = new Date(year,month,dayOfMonth);
                date.set(year, month, dayOfMonth);
            }
        });


        //listener for adding a deadline button

        addDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deadlineName.getText().toString().isEmpty()){
                    deadlineName.setError("Title cannot be blank");
                }else {
                    Deadline newDeadline = d;

                    newDeadline.setTitle(deadlineName.getText().toString());
                    newDeadline.setDescription(deadlineDescription.getText().toString());
                    newDeadline.setNotification(notification.getProgress());
                    newDeadline.setPriority(priority.getProgress());
                    newDeadline.setFrequency(frequency.getProgress());
                    newDeadline.setColor(color);
                  //  date.setTime(calendarView.getDate());
                    int hour = timePicker1.getCurrentHour();
                    int min = timePicker1.getCurrentMinute();
                    date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), hour, min);
                  //  date.setHours(hour);
                    //date.setMinutes(min);
                    newDeadline.setDate(date.getTime());

                    int index = controller.getDeadlineID(d);
                    controller.editDeadline(index, newDeadline);


                    Intent intent = new Intent(getApplicationContext(), DeadlineView.class);
                    intent.putExtra("Deadline", (Serializable) d);
                    startActivity(intent);
                }

            }
        });


    }

}
