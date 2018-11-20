package view;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.emmamalysz.killddl.AddDeadlineActivity;
import com.example.emmamalysz.killddl.CalendarActivity;
import com.example.emmamalysz.killddl.LoginActivity;
import com.example.emmamalysz.killddl.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Controller.KillDDLController;
import Model.CustomListAdapter;
import Model.CustomRListAdapter;
import Model.Deadline;
import Model.SimpleItemTouchHelperCallback;

public class DailyView extends AppCompatActivity {

    List<Deadline> ddls;
    RecyclerView deadlineList;
    KillDDLController controller = KillDDLController.getInstance();
    Date selectedDate;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        selectedDate = (Date)getIntent().getSerializableExtra("selectedDate");
        if (selectedDate == null) {
            Calendar c = Calendar.getInstance();
            selectedDate = c.getTime();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd, yyyy");

        final TextView date = findViewById(R.id.date_title);
        String newDate = sdf.format(selectedDate);
        date.setText(newDate);

        final Button monthlyButton = findViewById(R.id.monthly_button);

       // deadlineList = (ListView) findViewById(R.id.deadline_list);
        List<Deadline> ddls = controller.getDayDeadlines(selectedDate);

//        if (controller.getDeadlines() != null) {
//            ddls = controller.getDeadlines();
//        }

        //ArrayAdapter<Deadline> arrayAdapter = new CustomListAdapter(DailyView.this ,
               // R.layout.custom_list , ddls);
        deadlineList = (RecyclerView) findViewById(R.id.deadline_list);
        CustomRListAdapter adapter = new CustomRListAdapter(ddls);
        adapter.setIsDayView(true);
        deadlineList.setAdapter(adapter);
        deadlineList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(deadlineList);




//
//        deadlineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Deadline selectedDeadline = (Deadline) deadlineList.getItemAtPosition(position);
//                Intent intent = new Intent(getApplicationContext(), DeadlineView.class);
//                intent.putExtra("Deadline", (Serializable) selectedDeadline);
//                startActivity(intent);
//            }
//        });

        final FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // redirect to add deadline page
                Intent intent = new Intent(getApplicationContext(), AddDeadlineActivity.class);
                startActivity(intent);
            }
        });


        monthlyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // redirect to monthly view
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });

//     dailyButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // redirect to daily view
//                Intent intent = new Intent(getApplicationContext(), DailyView.class);
//                startActivity(intent);
//            }
//        });
    }
}