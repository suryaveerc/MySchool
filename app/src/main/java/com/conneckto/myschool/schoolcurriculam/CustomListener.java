package com.conneckto.myschool.schoolcurriculam;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.conneckto.myschool.R;
import com.conneckto.myschool.schoolcurriculam.Utils.Util;
import com.conneckto.myschool.schoolcurriculam.model.Event;

import java.util.Calendar;

/**
 * Created by suryaveer on 2016-06-07.
 */
public class CustomListener implements View.OnClickListener, AdapterView.OnItemClickListener, View.OnKeyListener {

    private static final String tag = "CustomListener";
    Context context;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LinearLayout monthView;
    LinearLayout yearView;

    CustomListener() {
    }

    CustomListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.currentMonth: //Opens Annual View.
                monthView = (LinearLayout) ((Activity) context).findViewById(R.id.monthCalendar);
                monthView.setVisibility(View.GONE);
                yearView = (LinearLayout) ((Activity) context).findViewById(R.id.yearCalendar);
                fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                YearViewFragment yearViewFragment = new YearViewFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.yearCalendar, yearViewFragment);
                fragmentTransaction.addToBackStack(Util.YEAR_VIEW_TAG);
                fragmentTransaction.commit();
                yearView.setVisibility(View.VISIBLE);
                break;

            case R.id.calendar_month_gridcell: // Opens Month view.
                yearView = (LinearLayout) ((Activity) context).findViewById(R.id.yearCalendar);
                monthView = (LinearLayout) ((Activity) context).findViewById(R.id.monthCalendar);
                fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                MonthViewFragment monthViewFragment = new MonthViewFragment();
                monthViewFragment.setCalendar((Calendar) v.getTag());
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.monthCalendar, monthViewFragment);
                fragmentTransaction.addToBackStack(Util.MONTH_VIEW_TAG);
                fragmentTransaction.commit();
                yearView.setVisibility(View.GONE);
                monthView.setVisibility(View.VISIBLE);

                break;

            case R.id.calendar_day_gridcell: //Opens event details.
                Log.d(tag, "calendar_day_gridcell");
                Bundle bundle = new Bundle();
                bundle.putString("REQ_FROM","MONTH_VIEW");
                monthView = (LinearLayout) ((Activity) context).findViewById(R.id.monthCalendar);
                monthView.setVisibility(View.GONE);
                LinearLayout eventListView = (LinearLayout) ((Activity) context).findViewById(R.id.eventDetailsView);
                fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
                eventDetailsFragment.setArguments(bundle);
                eventDetailsFragment.setDay((Integer) v.getTag());
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.eventDetailsView, eventDetailsFragment);
                fragmentTransaction.addToBackStack(Util.EVENT_LIST_VIEW_TAG);
                fragmentTransaction.commit();
                eventListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction   = fragmentManager.beginTransaction();
        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("REQ_FROM","EVENTS_VIEW");
        bundle.putInt("EventID",((Event)parent.getItemAtPosition(position)).getEventId());
        eventDetailsFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.eventDetailsView, eventDetailsFragment);
        fragmentTransaction.addToBackStack("NEW_TAG");
        parent.findViewById(R.id.annual_Event_List).setVisibility(View.GONE);

        fragmentTransaction.commit();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            switch (v.getTag().toString()) {
                case Util.YEAR_VIEW_TAG:
                    Log.d(Util.YEAR_VIEW_TAG, "fragment back key is clicked");
                    fragmentManager.popBackStack(Util.YEAR_VIEW_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    ((FragmentActivity) context).findViewById(R.id.yearCalendar).setVisibility(View.GONE);
                    ((FragmentActivity) context).findViewById(R.id.monthCalendar).setVisibility(View.VISIBLE);
                    break;

                case Util.MONTH_VIEW_TAG:
                    Log.d(Util.MONTH_VIEW_TAG, "fragment back key is clicked");
                    fragmentManager.popBackStack(Util.MONTH_VIEW_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    ((FragmentActivity) context).findViewById(R.id.yearCalendar).setVisibility(View.VISIBLE);
                    ((FragmentActivity) context).findViewById(R.id.monthCalendar).setVisibility(View.GONE);
                    break;

                case Util.EVENT_LIST_VIEW_TAG:
                    Log.d(Util.EVENT_LIST_VIEW_TAG, "fragment back key is clicked");
                    fragmentManager.popBackStack(Util.EVENT_LIST_VIEW_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    ((FragmentActivity) context).findViewById(R.id.monthCalendar).setVisibility(View.VISIBLE);
                    ((FragmentActivity) context).findViewById(R.id.eventDetailsView).setVisibility(View.GONE);
                    break;
                case "NEW_TAG":
                    Log.d("NEW_TAG", "fragment back key is clicked");
                    fragmentManager.popBackStack("NEW_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    ((FragmentActivity) context).findViewById(R.id.annual_Event_List).setVisibility(View.VISIBLE);
                    ((FragmentActivity) context).findViewById(R.id.eventDetailsView).setVisibility(View.GONE);
                    break;
            }
        }
        return true;
    }
}
