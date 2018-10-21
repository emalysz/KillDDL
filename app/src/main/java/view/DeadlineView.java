package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import com.example.emmamalysz.killddl.LoginActivity;
import com.example.emmamalysz.killddl.R;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Deadline;

import static android.view.View.*;

public class DeadlineView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);

        Deadline dl = (Deadline) getIntent().getSerializableExtra("Deadline");

        final TextView title = findViewById(R.id.title);
        title.setText(dl.getTitle());
        final TextView date = findViewById(R.id.date);
        date.setText(dl.getDate().toString());

        final TextView description = findViewById(R.id.description);
        description.setText(dl.getDescription());

        final Button backButton = findViewById(R.id.back_button);



       backButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), DailyView.class);
               startActivity(intent);
           }
       });
    }




}
