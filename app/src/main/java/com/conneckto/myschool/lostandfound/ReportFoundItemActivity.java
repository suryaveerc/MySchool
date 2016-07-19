package com.conneckto.myschool.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.conneckto.myschool.R;
import com.conneckto.myschool.lostandfound.Util.Util;
import com.conneckto.myschool.lostandfound.dao.LostAndFoundDAOImpl;

public class ReportFoundItemActivity extends AppCompatActivity {

    private int mYear, mMonth, mDay;
    EditText txtDate;
    private LostAndFoundDAOImpl lostAndFoundDAO;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_found_item);
        fillTemplate();
        txtDate = (EditText) findViewById(R.id.dateEditTextView);
        txtDate.setOnClickListener(new CustomListener(this));
        button = (Button) findViewById(R.id.submitButton);
        button.setTag(Util.ACTIVITY_FOUND);
        button.setOnClickListener(new CustomListener(this));
    }


    private void fillTemplate() {
        TextView v = (TextView) findViewById(R.id.reportTitleTextView);
        v.setText(R.string.report_found_item_title);
        v = (TextView) findViewById(R.id.dateTextView);
        v.setText(R.string.date_found_text);
        v = (TextView) findViewById(R.id.placeTextView);
        v.setText(R.string.place_found_text);
    }
}
