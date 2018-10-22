package Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emmamalysz.killddl.R;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomListAdapter extends ArrayAdapter<Deadline> {

    private Context mContext;
    private int id;
    private List<Deadline> items ;
    private Map<Integer, Integer> colorMap;

    public CustomListAdapter(Context context, int textViewResourceId , List<Deadline> list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
        colorMap = new HashMap<Integer, Integer>();
        colorMap.put(0, Color.GREEN);
        colorMap.put(1, Color.BLUE);
        colorMap.put(2, Color.RED);
        colorMap.put(3, Color.GRAY);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.listTextView);

        if(items.get(position) != null )
        {
            text.setTextColor(Color.WHITE);
            text.setText(items.get(position).toString());
            text.setTextSize(15);
            text.setBackgroundColor(colorMap.get(items.get(position).getColor()));
            if (items.get(position).getDate().compareTo(Calendar.getInstance().getTime()) < 0) {
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            int color = Color.argb( 200, 255, 64, 64 );


        }

        return mView;
    }

}
