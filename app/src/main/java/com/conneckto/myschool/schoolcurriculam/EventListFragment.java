package com.conneckto.myschool.schoolcurriculam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.conneckto.myschool.R;
import com.conneckto.myschool.schoolcurriculam.Utils.Util;
import com.conneckto.myschool.schoolcurriculam.dao.EventsDAO;
import com.conneckto.myschool.schoolcurriculam.model.Event;
import com.conneckto.myschool.schoolcurriculam.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Created by suryaveer on 2016-07-04.
 */
public class EventListFragment extends Fragment {
    private static String tag = "EventListFragment";
    private EventsDAO eventsDAO;
    static int eventListFragmentId;

    public EventListFragment(){}

    @SuppressLint("ValidFragment")
    public EventListFragment(EventsDAO eventsDAO )
    {
        this.eventsDAO = eventsDAO;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.events_list, null);
        ListView eventListView = (ListView) v.findViewById(R.id.annual_Event_List);
        eventListFragmentId = R.id.annual_Event_List;
        Bundle bundle = getArguments();
        EventsListAdapter eventsListAdapter;
        Log.d(tag,bundle.getString("EVENT_TYPE"));

        if (bundle.getString("EVENT_TYPE").equalsIgnoreCase("ALL")) {

            if (CacheProvider.getCurrentYearEventsList() == null) {
                List<Event> eventList = eventsDAO.fetchEventsInYear(Calendar.YEAR);
                Log.d(tag,"Eventlist size:"+ eventList.size());
                CacheProvider.setCurrentYearEventsList(eventList);
            }

            eventsListAdapter = new EventsListAdapter(v.getContext(), R.layout.events_list_template, createMonthSeparatedEventList());
        } else {
            int month = bundle.getInt("MONTH");
            List<Event> temp = eventsDAO.fetchEventsForMonth(month);
            eventsListAdapter = new EventsListAdapter(v.getContext(), R.layout.events_list_template, expandEvents(temp, month, new ArrayList<Item>()));
        }
        eventListView.setAdapter(eventsListAdapter);
        eventListView.setOnItemClickListener(new CustomListener(getContext()));
        return v;
    }

    private List<Item> createMonthSeparatedEventList() {
        List<Item> itemList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            Log.d(tag, "Finding events in " + Util.MONTHS[month]);
            //itemList.add(new Separator(Util.MONTHS[month]));

            List<Event> temp = eventsDAO.fetchEventsForMonth(month);
            itemList = expandEvents(temp, month, itemList);
            /*if(temp != null)
            {
                int size =  temp.size();
                Log.d(tag,"Events in "+Util.MONTHS[month] + " are: "+ size);
                int index = 0;
                while(index!=size) {
                    Event e = temp.get(index++);
                  //  Log.d(tag,"Event start date: "+e.getEventStartDate() +" Event end date: "+e.getEventEndDate());
                    int daysEventOccurs = getEventDaysInMonth(e);
                    for(int i =0 ;i< daysEventOccurs;i++) {
                        Event clone = new Event();
                        clone.setEventStartDate(e.getEventStartDate());
                        clone.setEventName(e.getEventName());

                        cal.setTime(clone.getEventStartDate());
                        cal.add(Calendar.DATE,i);
                        clone.setEventStartDate(cal.getTime());
                        itemList.add(clone);
                        //Log.d(tag,"!!!!!!!Event dat: "+clone.getEventStartDate());

                    }
                }
            }
            else
                Log.d(tag,"Events in "+Util.MONTHS[month] + " are: 0");
*/
        }

        return itemList;
    }

    private List<Item> expandEvents(List<Event> eventList, int month, List<Item> itemList) {
        itemList.add(new Separator(Util.MONTHS[month]));
        Calendar cal = Calendar.getInstance();
        if (eventList != null) {
            int size = eventList.size();
            Log.d(tag, "Events in " + Util.MONTHS[month] + " are: " + size);
            int index = 0;
            while (index != size) {
                Event e = eventList.get(index++);
                //  Log.d(tag,"Event start date: "+e.getEventStartDate() +" Event end date: "+e.getEventEndDate());
                int daysEventOccurs = getEventDaysInMonth(e);
                for (int i = 0; i < daysEventOccurs; i++) {
                    Event clone = new Event();
                    clone.setEventStartDate(e.getEventStartDate());
                    clone.setEventName(e.getEventName());
                    clone.setEventId(e.getEventId());
                    cal.setTime(clone.getEventStartDate());
                    cal.add(Calendar.DATE, i);
                    clone.setEventStartDate(cal.getTime());
                    itemList.add(clone);
                    //Log.d(tag,"!!!!!!!Event dat: "+clone.getEventStartDate());

                }
            }
        } else
            Log.d(tag, "Events in " + Util.MONTHS[month] + " are: 0");
        return itemList;
    }

    private int getEventDaysInMonth(Event event) {
        Calendar start = Calendar.getInstance();
        start.setTime(event.getEventStartDate());

        Calendar end = Calendar.getInstance();
        end.setTime(event.getEventEndDate());
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, start.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, start.get(Calendar.MONTH));
        int daysInMonth = getDaysInMonth(cal);
        if (end.get(Calendar.MONTH) > start.get(Calendar.MONTH))
            return daysInMonth - start.get(Calendar.DAY_OF_MONTH) + 1;
        else
            return end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH) + 1;
    }

    private int getDaysInMonth(GregorianCalendar cal) {
        int daysOfMonth = Util.daysOfMonth[cal.get(Calendar.MONTH)];
        if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY && cal.isLeapYear(cal.get(Calendar.YEAR)))
            daysOfMonth++;
        Log.d(tag, "Days in month:" + daysOfMonth);
        return daysOfMonth;
    }
}
