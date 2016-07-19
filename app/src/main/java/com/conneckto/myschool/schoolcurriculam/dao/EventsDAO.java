package com.conneckto.myschool.schoolcurriculam.dao;

import com.conneckto.myschool.schoolcurriculam.model.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by suryaveer on 2016-06-16.
 */
public interface EventsDAO {


    public abstract List<Event> fetchEventsOnDay(int day, int month, int year);
    public abstract List<Event> fetchEventsForMonth(int month, int year);

    public abstract List<Event> fetchEventsInYear(int year);

    public abstract List<String> fetchEventTypes();
    public abstract List<String> fetchEventColorCodes();

    public abstract Map<String, String> fetchPriorityEventForEachMonth(int year);
}
