package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import com.example.emmamalysz.killddl.CalendarActivity;
import com.example.emmamalysz.killddl.EditDeadlineActivity;
import com.example.emmamalysz.killddl.LoginActivity;
import com.example.emmamalysz.killddl.R;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import Controller.KillDDLController;
import Model.Deadline;

import static android.view.View.*;

public class DeadlineView extends AppCompatActivity {

    KillDDLController controller = KillDDLController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);

        final Deadline dl = (Deadline) getIntent().getSerializableExtra("Deadline");

        final TextView title = findViewById(R.id.title);
        title.setText(dl.getTitle());

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d 'at' hh:mm aaa");
        final TextView date = findViewById(R.id.date);
        String newDate = sdf.format(dl.getDate());
        date.setText(newDate);


        final TextView description = findViewById(R.id.description);
        description.setText(dl.getDescription());

//        final TextView completed = findViewById(R.id.complete_button);
//        if(dl.isCompleted() == true) {
//            completed.setText("Completed");
//        }

        final Button completedButton = findViewById(R.id.completed_button);
        completedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dl.setIsComplete(true);
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });


        final Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("DEADLINE TO REMOVE", dl.toString());
                controller.removeDeadline(dl);
                Intent intent = new Intent(getApplicationContext(), DailyView.class);
                intent.putExtra("delete", (Serializable) dl);
                startActivity(intent);
            }
        });

        final Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditDeadlineActivity.class);
                intent.putExtra("edit", (Serializable) dl);
                startActivity(intent);
            }
        });

        final Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DailyView.class);
                startActivity(intent);
            }
        });



    }




}
