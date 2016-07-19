package com.conneckto.myschool.schoolcurriculam;

import com.conneckto.myschool.schoolcurriculam.model.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-16.
 */


public class CacheProvider {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static Map<String, String> priorityEvents;
    public static List<String> eventTypes;
    public static List<String> filteredEvents;
//    private static Map<Integer, List<Event>> holidayMap; //day, eventList
//    private static Map<Integer, List<Event>> collegeEventMap; //day, eventList
    private static Map<Integer, List<Event>> eventsForMonth; //month, eventList
    private static Map<Integer, Map<Integer, List<Event>>> eventsForMonthGroupedByDay; //month, eventList
    private static Map<Integer, Map<Integer, List<Event>>> eventsByYear;
    private static Map<Integer, Event> eventsByIdMap; //eventId, eventList
    private static List<Event> currentYearEventsList;

    static {
        eventsForMonth = new LinkedHashMap<>();
        eventsByIdMap  = new HashMap<>();
        eventsByYear = new HashMap<>();

        eventsForMonth.put(0, null);
        eventsForMonth.put(1, null);
        eventsForMonth.put(2, null);
        eventsForMonth.put(3, null);
        eventsForMonth.put(4, null);
        eventsForMonth.put(5, null);
        eventsForMonth.put(7, null);
        eventsForMonth.put(8, null);
        eventsForMonth.put(9, null);
        eventsForMonth.put(10, null);
        eventsForMonth.put(11, null);

        priorityEvents = new HashMap<>();
        priorityEvents.put("January", "exam");
        priorityEvents.put("June", "christmas");
        eventTypes = new ArrayList<>();
        eventTypes.add("sports");
        eventTypes.add("academic");
        eventTypes.add("extra Curricular");
        eventTypes.add("outdoor");
        eventTypes.add("exams");
        eventTypes.add("concert");
        eventTypes.add("holiday");


    }

    public List<Event> eventList;


    public CacheProvider() {}

    public static List<Event> getCurrentYearEventsList() {
        return currentYearEventsList;
    }

    public static void setCurrentYearEventsList(List<Event> list) {
        currentYearEventsList = list;
    }

    public static List<String> getFilteredEvents() {
        return filteredEvents;
    }

    public static void setFilteredEvents(List<String> filteredEvents) {
        CacheProvider.filteredEvents = filteredEvents;
    }

    public static List<String> getEventTypes() {
        return eventTypes;
    }

    public static void setEventTypes(List<String> eventTypes) {
        CacheProvider.eventTypes = eventTypes;
    }

    public static SimpleDateFormat getDateFormat() {
        return formatter;
    }

  /*  public static Map<Integer, List<Event>> getCollegeEventMap() {
        return collegeEventMap;
    }
*/
  /*  public static void setCollegeEventMap(Map<Integer, List<Event>> map) {
        collegeEventMap = map;

    }
*/
  /*  public static Map<Integer, List<Event>> getHolidayMap() {
        return holidayMap;
    }

    public static void setHolidayMap(Map<Integer, List<Event>> map) {
        holidayMap = map;
    }
*/
    public static List<Event> getEventsForMonth(int month, int year) {
        return eventsByYear.get(year)!=null?eventsByYear.get(year).get(month):null;
    }

    public static void setEventsForMonth(int month, int year, List list) {
        eventsForMonth.put(month,list);
    }

    public static Map<Integer, Event> getEventsByIdMap() {
        return eventsByIdMap;
    }

    public static void setEventsByIdMap(Map<Integer, Event> eventsByIdMap) {
        CacheProvider.eventsByIdMap = eventsByIdMap;
    }

    public void putEventsByIdMap(List<Event> list)
    {
        Map<Integer, Event> map = getEventsByIdMap();
        for(Event event : list)
        {
            map.put(event.getEventId(),event);
        }
    }
}
