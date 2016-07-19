package com.conneckto.myschool.schoolcurriculam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conneckto.myschool.R;
import com.conneckto.myschool.schoolcurriculam.dao.EventsDAOImpl;
import com.conneckto.myschool.schoolcurriculam.model.Event;
import com.conneckto.myschool.schoolcurriculam.model.Item;

import java.util.Calendar;
import java.util.List;

/**
 * Created by suryaveer on 2016-07-04.
 */
public class EventsListAdapter extends ArrayAdapter<Item> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private final static String tag = "EventsListAdapter";
    int layoutResourceId;
    List<Event> eventList;
    private Calendar cal;
    private EventsDAOImpl eventsDAO;
    private LinearLayout parent_layout;


    private Context context;
    private List<Item> item;

    public EventsListAdapter(Context context, int layoutResourceId, List<Item> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.item = items;
        cal = Calendar.getInstance();
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public Item getItem(int position) {
        return item.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        int rowType = getItemViewType(position);
        View View;

        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = inflater.inflate(R.layout.events_list_template, null);
                    holder.eventDay = (TextView) convertView.findViewById(R.id.eventDate);
                    holder.eventName = (TextView) convertView.findViewById(R.id.eventName);
                    holder.header = false;
                    break;
                case TYPE_SEPARATOR:
                    convertView = inflater.inflate(R.layout.event_month_separator, null);
                    holder.month = (TextView) convertView.findViewById(R.id.monthSeparator);
                    holder.header = true;
                    break;
            }

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!holder.header) {
            cal.setTime(((Event) getItem(position)).getEventStartDate());
            holder.eventName.setText(((Event) getItem(position)).getEventName());
            holder.eventDay.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        } else {
            holder.month.setText(getItem(position).getTitle());

        }

        return convertView;
    }

    public static class ViewHolder {
        public Boolean header = false;
        public TextView month;
        public TextView eventName;
        public TextView eventDay;
    }
}
