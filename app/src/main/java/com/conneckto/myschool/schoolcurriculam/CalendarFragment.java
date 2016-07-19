package com.conneckto.myschool.schoolcurriculam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.conneckto.myschool.R;
import com.conneckto.myschool.schoolcurriculam.Utils.Util;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.calendar_fragment, null);
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        Log.d("CalendarFragment", v.getTag() + "");
        LinearLayout monthView;
        LinearLayout yearView;
        monthView = (LinearLayout) v.findViewById(R.id.monthCalendar);
        fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
        MonthViewFragment monthViewFragment = new MonthViewFragment();
        monthViewFragment.setCalendar(Calendar.getInstance());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.monthCalendar, monthViewFragment);
        fragmentTransaction.addToBackStack(Util.MONTH_VIEW_TAG);
        fragmentTransaction.commit();
        monthView.setVisibility(View.VISIBLE);
        return v;
    }
}
