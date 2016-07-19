package com.conneckto.myschool.schoolcurriculam.Utils;

import com.conneckto.myschool.schoolcurriculam.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-06.
 */
public class Util {
    public static final String MONTHS[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static final String MONTHS_ABBR[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    public static final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final String MONTH_VIEW_TAG = "MONTH_VIEW";
    public static final String YEAR_VIEW_TAG = "YEAR_VIEW";
    public static final String EVENT_LIST_VIEW_TAG = "EVENT_LIST_VIEW";
    public final static String EVENT_HOLIDAY = "HOLIDAY";
    private static final String DAYS[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public static void putObjects(Map<Integer, List<Event>> map, Integer key, Event value) {
        List<Event> myClassList = map.get(key);
        if (myClassList == null) {
            myClassList = new ArrayList<Event>();
            map.put(key, myClassList);
        }
        myClassList.add(value);

    }

    public static int getDuration(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000));
    }

    // this method creates a new list with events from passed month.
    public static List<Event> filterCurrentMonthEvents(List<Event> eventList, int month) {
        Calendar cal = Calendar.getInstance();
        List<Event> temp = new ArrayList<>();
        for (Event event : eventList) {
            cal.setTime(event.getEventStartDate());
            if (cal.get(Calendar.MONTH) == month)
                temp.add(event);
            else {
                cal.setTime(event.getEventEndDate());
                if (cal.get(Calendar.MONTH) == month)
                    temp.add(event);
            }
        }
        return temp;
    }

    public enum RowType {
        LIST_ITEM, HEADER_ITEM
    }
}
