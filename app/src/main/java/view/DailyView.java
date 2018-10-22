package view;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.emmamalysz.killddl.AddDeadlineActivity;
import com.example.emmamalysz.killddl.LoginActivity;
import com.example.emmamalysz.killddl.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Model.Deadline;

public class DailyView extends AppCompatActivity {

    List<Deadline> ddls;
    ListView deadlineList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

//        lv = (ListView) findViewById(R.id.deadline_list);
//        lv.setOnDragListener(new View.OnDragListener() {
//
//        }

        final Deadline deleteDeadline = (Deadline) getIntent().getSerializableExtra("delete");
        final Deadline addedDeadline = (Deadline) getIntent().getSerializableExtra("new_deadline");

        final Button monthlyButton = findViewById(R.id.monthly_button);

        deadlineList = (ListView) findViewById(R.id.deadline_list);

        Deadline [] deadlines = new Deadline[]{
               new Deadline("Meet Laura at the village", "Discuss what just happened" +
                "on this week's episode of Survivor", new Date(2018-10-30),1, 1, 1),
                new Deadline("Meet Stephannie at the village", "Discuss what just happened" +
               "on this week's episode of Survivor", new Date(2018-10-31),2, 0, 4)
        };

        ddls = new ArrayList<>(Arrays.asList(deadlines));
        ddls.add(addedDeadline);
        ddls.remove(deleteDeadline);

        final ArrayAdapter<Deadline> arrayAdapter = new ArrayAdapter<Deadline>(this, android.R.layout.simple_list_item_1, ddls);
        deadlineList.setAdapter(arrayAdapter);

        deadlineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Deadline selectedDeadline = (Deadline) deadlineList.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DeadlineView.class);
                intent.putExtra("Deadline", (Serializable) selectedDeadline);
                startActivity(intent);
            }
        });

        final FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // redirect to add deadline page
                Intent intent = new Intent(getApplicationContext(), AddDeadlineActivity.class);
                startActivity(intent);
            }
        });

//
//        monthlyButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // redirect to monthly view
//                Intent intent = new Intent(getApplicationContext(), DeadlineView.class);
//                startActivity(intent);
//            }
//        });

//     dailyButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // redirect to daily view
//                Intent intent = new Intent(getApplicationContext(), DailyView.class);
//                startActivity(intent);
//            }
//        });
    }
}