package view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emmamalysz.killddl.LoginActivity;
import com.example.emmamalysz.killddl.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Deadline;

public class DailyView extends AppCompatActivity {

    List<String> ddls;
    ListView deadlineList;
//    RecyclerView deadlineList;
//    RecyclerView.Adapter adapter;
//    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);


        deadlineList = findViewById(R.id.deadlineList);
//    deadlineList.setHasFixedSize(true);
//    layoutManager = new LinearLayoutManager(this);
//    deadlineList.setLayoutManager(layoutManager);
//    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ddls);

        String [] deadlines = new String[] {
                "Laundry",
                "Midterm"
        };
        ddls = new ArrayList<>(Arrays.asList(deadlines));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ddls);
        deadlineList.setAdapter(arrayAdapter);

//        deadlineList.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                Deadline selectedDeadline = (Deadline)deadlineList.getItemAtPosition(i);
//                intent.putExtra("DEADLINE", selectedDeadline);
//                // to retrieve: getSerializable extra
//                startActivity(intent);
//            }
//
//        });


    }
}