package com.example.emmamalysz.killddl;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Date;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Controller.KillDDLController;
import Model.Deadline;
import view.DailyView;

public class AddDeadlineActivity extends AppCompatActivity {
    EditText deadlineName;
    EditText deadlineDescription;
    Button addDeadline;
    SeekBar notification;
    SeekBar priority;
    SeekBar frequency;
    Calendar date;
    Spinner spinner;
    Date currDate;
    int color;
    private CalendarView calendarView;
    private Object AdapterView;
    private TimePicker timePicker1;

    KillDDLController controller = KillDDLController.getInstance();
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

                    String _dlName = deadlineName.getText().toString();
                    String _dlDescription = deadlineDescription.getText().toString();
                    int _notify = notification.getProgress();
                    int _priority = priority.getProgress();
                    int _frequency = frequency.getProgress();
                    int hour = timePicker1.getCurrentHour();
                    int min = timePicker1.getCurrentMinute();
                    date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), hour, min);
                    boolean checked = ((CheckBox) findViewById(R.id.checkbox_email)).isChecked();
                    if (checked) {
                        sendEmailNotification();
                    }

                    String userID = controller.getCurrentUser().getEmail();
                    String[] parts = userID.split("@");
                    DatabaseReference mDatabase = controller.getDatabase();
                    String key = mDatabase.child("deadlines").push().getKey();
                    Deadline d = new Deadline(_dlName, _dlDescription, date.getTime(), _priority, _notify, color, _frequency);
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/deadlines/" + parts[0] + "*" + key, d);
                    mDatabase.updateChildren(childUpdates);
                    currDate = date.getTime();
                    setNotificationUpdate(_notify);
                    controller.addDeadline(d);
                    Intent intent = new Intent(AddDeadlineActivity.this, DailyView.class);
                    intent.putExtra("new_deadline", (Serializable) d);
                    intent.putExtra("selectedDate", currDate);
                    startActivity(intent);
                }


            }
        });

    }

    public void setNotificationUpdate(int notification) {

        Intent intent = new Intent(this, MyReceiver.class);
        String value = "Deadline: " + deadlineName.getText().toString() + "  ";
        value += "Description: " + deadlineDescription.getText().toString();


        intent.putExtra("Deadline Notification", value);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("KillDDl Notification").setContentText(value)
                .setAutoCancel(true).setSound(defaultSoundUri)
                .setContentIntent(pendingIntent).setChannelId("MyNotifChannel2");;


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Calendar calendar = Calendar.getInstance();

        if (notification == 1) {
            Date date = new Date();
            date.setTime(System.currentTimeMillis() + (60 * 60 * 1000));
            calendar.setTime(date);
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR,
                    pendingIntent);
        } else if (notification ==2) {
            Date date = new Date();
            date.setTime(System.currentTimeMillis() + (24*60 * 60 * 1000));
            calendar.setTime(date);
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR,
                    pendingIntent);
        } else if (notification == 3) {
            Date date = new Date();
            date.setTime(System.currentTimeMillis() + (7*24*60 * 60 * 1000));
            calendar.setTime(date);
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR,
                    pendingIntent);
        } else if (notification == 4) {
            notificationManager.notify(0, notificationBuilder.build());
        }

    }

    public void sendEmailNotification() {
        Log.d("email", "We are sending email notification");
        String subject = "KillDDL Deadline: " + deadlineName.getText().toString();
        String message = "Description: " + deadlineDescription.getText().toString() + "\n";
        message += "Date: " + date.getTime();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","emalysz@usc.edu", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
        catch (android.content.ActivityNotFoundException ex) {
            Log.d("MainActivity", "No email app installed!");
            Toast.makeText(AddDeadlineActivity.this, "Uh...No email app?", Toast.LENGTH_SHORT).show();
        }
    }
}

