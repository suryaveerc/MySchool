package com.conneckto.myschool.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.conneckto.myschool.R;
import com.conneckto.myschool.lostandfound.Util.Util;
import com.conneckto.myschool.lostandfound.dao.LostAndFoundDAOImpl;

public class ReportLostItemActivity extends AppCompatActivity {

    private int mYear, mMonth, mDay;
    private EditText txtDate;
    private LostAndFoundDAOImpl lostAndFoundDAO;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_lost_item);
        fillTemplate();

        txtDate = (EditText) findViewById(R.id.dateEditTextView);
        txtDate.setOnClickListener(new CustomListener(this));
        button = (Button) findViewById(R.id.submitButton);
        button.setTag(Util.ACTIVITY_LOST);
        button.setOnClickListener(new CustomListener(this));
        //button.setOnClickListener(this);

    }
/* Moved the code to CustomListener*/
    /*public void onClick(View v) {
        Log.d("ReportLost",v.getId()+"");
        switch (v.getId()) {


            case R.id.dateEditTextView:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.submitButton:
                Log.d("ReportLost","on submit click.");
                LostAndFound lostAndFound = new LostAndFound();
                lostAndFound.setActivityType(Util.STATUS_LOST);
                lostAndFound.setDescription(((TextView) findViewById(R.id.dateEditTextView)).getText().toString());
                lostAndFound.setItemType(((Spinner) findViewById(R.id.itemTypeSpinner)).getSelectedItem().toString());
                lostAndFound.setReportedPlace(((TextView) findViewById(R.id.placeEditTextView)).getText().toString());
                try {
                    lostAndFound.setReportedDate(CacheProvider.getDateFormat().parse(((TextView) findViewById(R.id.dateEditTextView)).getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                lostAndFound.setReporterId(CacheProvider.reporterID);
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                lostAndFoundDAO.updateLostAndFoundItems(lostAndFound);
                break;
        }
    }*/

    private void fillTemplate() {
        TextView v = (TextView) findViewById(R.id.reportTitleTextView);
        v.setText(R.string.report_lost_item_title);
        v = (TextView) findViewById(R.id.dateTextView);
        v.setText(R.string.date_lost_text);
        v = (TextView) findViewById(R.id.placeTextView);
        v.setText(R.string.place_lost_text);
    }
}
