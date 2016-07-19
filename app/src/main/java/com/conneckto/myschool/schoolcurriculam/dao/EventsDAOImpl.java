package com.conneckto.myschool.schoolcurriculam.dao;

import android.util.Log;

import com.conneckto.myschool.schoolcurriculam.CacheProvider;
import com.conneckto.myschool.schoolcurriculam.Utils.Util;
import com.conneckto.myschool.schoolcurriculam.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-16.
 */
public class EventsDAOImpl implements EventsDAO {
    private static String tag = "EventsDAOImpl";
    private List<Event> eventList;
    private CacheProvider cache;
    // temporary
    Event event1 = new Event();
    Event event2 = new Event();
    Event event3 = new Event();
    Event event4 = new Event();

    {
        SimpleDateFormat formatter = CacheProvider.getDateFormat();
        try {
            event1.setEventEndDate(formatter.parse("02-07-2016 11:20"));
            event1.setEventStartDate(formatter.parse("01-07-2016 10:20"));

            event2.setEventEndDate(formatter.parse("06-07-2016 00:00"));
            event2.setEventStartDate(formatter.parse("06-07-2016 00:00"));

            event3.setEventEndDate(formatter.parse("08-07-2016 13:20"));
            event3.setEventStartDate(formatter.parse("08-07-2016 10:20"));

            event4.setEventEndDate(formatter.parse("08-08-2016 00:00"));
            event4.setEventStartDate(formatter.parse("08-08-2016 00:00"));


        } catch (ParseException e) {
            e.printStackTrace();
        }

        event1.setEventId(1);
        event1.setClassId(4);
        event1.setEventDescription("Concert");
        event1.setEventName("concert");
        event1.setEventType("concert");
        event1.setEventVenue("School");
        event1.setOpenForParents(false);
        event1.setSchoolLevel(true);

        event2.setEventId(2);
        event2.setClassId(5);
        event2.setEventDescription("christmas");
        event2.setEventName("christmas");
        event2.setEventType(Util.EVENT_HOLIDAY.toLowerCase());
        event2.setEventVenue("School");
        event2.setOpenForParents(true);
        event2.setSchoolLevel(true);

        event3.setEventId(3);
        event3.setClassId(5);
        event3.setEventDescription("Desc");
        event3.setEventName("chess");
        event3.setEventType("sports");
        event3.setEventVenue("School");
        event3.setOpenForParents(true);
        event3.setSchoolLevel(true);

        event4.setEventId(4);
        event4.setClassId(5);
        event4.setEventDescription("Desc");
        event4.setEventName("Badminton");
        event4.setEventType("sports");
        event4.setEventVenue("School");
        event4.setOpenForParents(true);
        event4.setSchoolLevel(true);


    }

    public EventsDAOImpl(CacheProvider cache) {
        this.cache = cache;
    }

    @Override
    public List<Event> fetchEventsOnDay(int day, int month, int year) {
        return null;
    }

    @Override
    public List<Event> fetchEventsForMonth(int month, int year) {

        eventList = new ArrayList<>();
        if ((eventList=cache.getEventsForMonth(month, year)) != null) {
            Log.d(tag, "Returning event already fetched. Month: " + month);
            return eventList;
        }//Check the event map for current month events.
        else if (cache.getCurrentYearEventsList() == null) {
            eventList = new ArrayList<>();
            cache.putEventsByIdMap(eventList);
            Log.d(tag, "*******Returning event fetched from DB. Month: " + month);
        }//Check if all the events are already fetched otherwise fetch from db and add to events map.
        else {
            eventList = new ArrayList<>();
            eventList = cache.getCurrentYearEventsList();
            List<Event> temp = Util.filterCurrentMonthEvents(eventList, month);
            cache.setEventsForMonth(month, year, temp).put(month, temp);
            Log.d(tag, "!!!!!!!Returning event fetched from all event list. Month: " + month);
        }// if all events are already fetched, filter the event from this list and add to events map.

        return eventList;
    }

    public List<Event> fetchEventsInYear(int year) {
        //CacheProvider.setCurrentYearEventsList(eventList); // set after fetching from DB
        List<Event> list = new ArrayList<>();
        //remove when fetching from DB
        list.add(event1);
        list.add(event2);
        list.add(event3);
        list.add(event4);
        cache.putEventsByIdMap(list);

        return list;
    }

    @Override
    public List<String> fetchEventTypes() {
        return CacheProvider.eventTypes;
    }

    @Override
    public Map<String, String> fetchPriorityEventForEachMonth(int year) {
        return cache.priorityEvents;
    }


    @Override
    public List<String> fetchEventColorCodes() {
        return null;
    }
}
