package Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emmamalysz.killddl.R;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.KillDDLController;
import view.DailyView;


public class CustomListAdapter extends ArrayAdapter<Deadline> {

    private Context mContext;
    private int id;
    private List<Deadline> items ;
    private Map<Integer, Integer> colorMap;
    KillDDLController controller = KillDDLController.getInstance();

    public CustomListAdapter(Context context, int textViewResourceId , List<Deadline> list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
        colorMap = new HashMap<Integer, Integer>();
        colorMap.put(0, Color.rgb(26,142,100));
        colorMap.put(1, Color.rgb(9, 132, 227));
        colorMap.put(2, Color.rgb(255,118,117));
        colorMap.put(3, Color.GRAY);
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.listTextView);
        ImageView colorTab = (ImageView) mView.findViewById(R.id.ColorTab);

        if(items.get(position) != null )
        {
          //  text.setTextColor(Color.WHITE);
            text.setTextColor(Color.BLACK);
            Date currTime = Calendar.getInstance().getTime();
            text.setText(items.get(position).toString());
            text.setTextSize(15);
            colorTab.setBackgroundColor(colorMap.get(items.get(position).getColor()));
            //text.setBackgroundColor(colorMap.get(items.get(position).getColor()));
            if (items.get(position).getDate().compareTo(Calendar.getInstance().getTime()) < 0) {
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (items.get(position).getCompleted()) {
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            int color = Color.argb( 200, 255, 64, 64 );


        }

        Button deleteBtn = (Button) mView.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                controller.removeDeadline(items.get(position));
                items.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        
        return mView;
    }



}
