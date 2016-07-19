package com.conneckto.myschool.lostandfound;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conneckto.myschool.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class MainActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lost_found, container, false);
        Toolbar toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        Button postButton = (Button) v.findViewById(R.id.reportFoundButton);
        Button reportButton = (Button) v.findViewById(R.id.reportLostButton);
        Button viewButton = (Button) v.findViewById(R.id.viewItemsButton);
        postButton.setOnClickListener(new CustomListener(getContext()));
        reportButton.setOnClickListener(new CustomListener(getContext()));
        viewButton.setOnClickListener(new CustomListener(getContext()));
        return v;
    }
}
