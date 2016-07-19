package com.conneckto.myschool.schoolcurriculam;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.conneckto.myschool.R;
import com.conneckto.myschool.schoolcurriculam.Utils.Util;
import com.conneckto.myschool.schoolcurriculam.dao.EventsDAO;
import com.conneckto.myschool.schoolcurriculam.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EventDetailsFragment extends Fragment implements View.OnClickListener {

    private final static String tag = "EventDetailsFragment";
    private int day;
    private ListView eventListView;
    private List<String> filteredEventTypes;
    private MultiSelectionSpinner spinner;
    private List<String> eventTypes;
    private Button filterBtn;
    private ItemsAdapter itemsAdapter;
    private List<Event> eventList;
    private EventsDAO eventsDAO;
    Bundle bundle;

    public EventDetailsFragment() {
    }

    @SuppressLint("ValidFragment")
    public EventDetailsFragment(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(tag, day + "");
        View v = inflater.inflate(R.layout.event_details_list_view, container, false);
        bundle = getArguments();
        eventListView = (ListView) v.findViewById(R.id.eventDetailListView);
        spinner = (MultiSelectionSpinner) v.findViewById(R.id.mySpinner1);
        filterBtn = (Button) v.findViewById(R.id.filterBtn);

        if (bundle.getString("REQ_FROM").equalsIgnoreCase("EVENTS_VIEW")) {
            int eventId = bundle.getInt("EventID");
            eventList = new ArrayList<>();
            eventList.add(CacheProvider.getEventsByIdMap().get(eventId));
            for (Map.Entry entry : CacheProvider.getEventsByIdMap().entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue().toString());
            }
            System.out.println(eventList.get(0).toString());
            spinner.setVisibility(View.GONE);
            filterBtn.setVisibility(View.GONE);
        } else {
            eventList = CacheProvider.getCollegeEventMap().get(day);
            Log.d(tag, "Number of events on" + day + eventList.size());
            filteredEventTypes = CacheProvider.filteredEvents;
            eventTypes = CacheProvider.eventTypes;
            spinner.setItems(eventTypes);
            spinner.setSelection(eventTypes);
            filterBtn.setOnClickListener(this);
            eventList = filterEvents(eventList);
        }

        itemsAdapter = new ItemsAdapter(getContext(), R.layout.event_details_template, eventList);
        eventListView.setAdapter(itemsAdapter);
        eventListView.setOnItemClickListener(new CustomListener(getContext()));
        return v;
    }

    public void setEventList(List<Event> list) {
        eventList = list;
    }

    public void setDay(int day) {
        this.day = day;
    }


    private List<Event> filterEvents(List<Event> eventList) {
        List<Event> filteredEventsList = new ArrayList<>();
        for (Event event : eventList) {
            Log.d(tag, "Checking filter for event: " + event.getEventName());
            if (filteredEventTypes.contains(event.getEventType()))
                filteredEventsList.add(event);
        }
        return filteredEventsList;
    }

    @Override
    public void onClick(View v) {
        if (v == filterBtn) {
            filteredEventTypes = spinner.getSelectedStrings();
            CacheProvider.filteredEvents = filteredEventTypes;
            Log.d(tag, "Filtered events: " + filteredEventTypes + " Count: " + filteredEventTypes.size());

            itemsAdapter = new ItemsAdapter(getContext(), R.layout.event_details_template, filterEvents(eventList));
            eventListView.setAdapter(itemsAdapter);
            eventListView.setOnItemClickListener(new CustomListener(getContext()));
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag, "onActivityCreated");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        if (bundle.getString("REQ_FROM").equalsIgnoreCase("EVENTS_VIEW"))
            getView().setTag("NEW_TAG");
        else
            getView().setTag(Util.EVENT_LIST_VIEW_TAG);
        getView().setOnKeyListener(new CustomListener(getContext()));
    }
}
