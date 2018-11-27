package Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emmamalysz.killddl.R;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.KillDDLController;
import view.DeadlineView;


public class CustomRListAdapter extends RecyclerView.Adapter<CustomRListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private Map<Integer, Integer> colorMap;
    KillDDLController controller = KillDDLController.getInstance();
    private List<Deadline> items;
    private static RecyclerViewClickListener itemListener;
    private boolean isDayView;



    public CustomRListAdapter(List<Deadline> deadlines) {
        items = deadlines;
        colorMap = new HashMap<Integer, Integer>();
        colorMap.put(0, Color.rgb(0,184,148));
        colorMap.put(1, Color.rgb(9, 132, 227));
        colorMap.put(2, Color.rgb(255,118,117));
        colorMap.put(3, Color.GRAY);
        isDayView = false;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    public void setIsDayView(boolean dayView) {
        this.isDayView = true;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {



        if(items.get(position) != null )
        {

            holder.d = items.get(position);
            holder.textView.setTextColor(Color.BLACK);
           // holder.textView.setText(items.get(position).toString());
            Date currTime = Calendar.getInstance().getTime();
//            if(items.get(position).getDate().getTime() - currTime.getTime() <= (60*60*1000) ){
//                holder.textView.setText(items.get(position).toString() + " (< 1 Hour)");
//            }else {
                holder.textView.setText(items.get(position).toString());
          //  }
            holder.textView.setTextSize(15);
            holder.colorTab.setBackgroundColor(colorMap.get(items.get(position).getColor()));

            if (items.get(position).getDate().compareTo(Calendar.getInstance().getTime()) < 0) {
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (items.get(position).getCompleted()) {
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            int color = Color.argb( 200, 255, 64, 64 );


        }

        TextView timeRemaining =  holder.timeTextView;
        Calendar c = Calendar.getInstance();

        if (items.get(position) != null){
            c.setTime(items.get(position).getDate());
            long deadlineTime = c.getTimeInMillis();
            long currentTime = Calendar.getInstance().getTimeInMillis();
            long difference = deadlineTime - currentTime;
            if (Math.abs(c.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) < 86400000L){

                if (difference <= 3600000L){
                    timeRemaining.setText("< 1 hr");
                    timeRemaining.setTextColor(Color.RED);
                }
                else {
                    long hoursDifference = difference/3600000;
                    timeRemaining.setText(hoursDifference + " hrs");
                    timeRemaining.setTextColor(Color.RED);
                }
            }
            else {
                long daysDifference = difference/86400000;
                timeRemaining.setText(daysDifference + " days");
                timeRemaining.setTextColor(Color.RED);
            }
        }


        final int val = position;
        holder.deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                controller.removeDeadline(items.get(val));
                items.remove(val); //or some other task
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        //delete deadline at position
        controller.removeDeadline(items.get(position));
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Log.d("ARR: ", items.toString());
        Deadline prev = items.remove(fromPosition);
        items.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
        if (isDayView == true) {
            Log.d("dragTag", "fromPosition" + fromPosition);
            Log.d("dragTag", "toPosition" + toPosition);
//        controller.getCurrentUser().getDeadlines().set(toPosition)
            //move everything else in the list down
            controller.getCurrentUser().getDeadlines().get(toPosition).setPosition(toPosition);
            items.get(toPosition).setDraggedStatus(true);

            for (int i = 0; i < items.size(); i++) {
                items.get(i).setPos(i);
                Log.d("dragTag", "item title " + items.get(i).getTitle());
                Log.d("dragTag", "item draggedStatus" + items.get(i).getDraggedStatus());
            }
            for (int i = 0; i < items.size(); i++) {
                if (i != toPosition) {
                    items.get(i).setDraggedStatus(false);
                }
            }
            controller.getCurrentUser().setDeadlines(items);
        }
    }



    @Override
    public int getItemCount() {
        return items.size();
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder, View.OnClickListener {

        public final TextView textView;
        public final TextView timeTextView;
        public final ImageView colorTab;
        public final Button deleteBtn;
        public final Context context;
        public Deadline d;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listTextView);
            timeTextView = itemView.findViewById(R.id.time_remaining);
            colorTab = itemView.findViewById(R.id.ColorTab);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            context = itemView.getContext();
            itemView.setOnClickListener(this);

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }

        @Override
        public void onClick(View v) {
            Deadline selectedDeadline =  d;
            Intent intent = new Intent(context, DeadlineView.class);
            intent.putExtra("Deadline", (Serializable) selectedDeadline);
            context.startActivity(intent);


        }

    }


}


