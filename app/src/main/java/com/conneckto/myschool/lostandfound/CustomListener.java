package com.conneckto.myschool.lostandfound;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.conneckto.myschool.ApplicationContextProvider;
import com.conneckto.myschool.R;
import com.conneckto.myschool.lostandfound.Util.Util;
import com.conneckto.myschool.lostandfound.dao.LostAndFoundDAOImpl;
import com.conneckto.myschool.lostandfound.model.Claims;
import com.conneckto.myschool.lostandfound.model.LostAndFound;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by suryaveer on 2016-05-26.
 */
public class CustomListener implements View.OnClickListener, AdapterView.OnItemClickListener, View.OnKeyListener{

    Context context;
    private int mYear, mMonth, mDay;
    Object object;
    private LostAndFoundDAOImpl lostAndFoundDAO;
    private LostAndFound lostAndFound;
    CustomListener(){}
    CustomListener(Context context)
    {
        this.context = context;
    }
    CustomListener(Context context, Object obj)
    {
        this.context = context;
        this.object = obj;
    }
    @Override
    public void onClick(final View v) {

        switch (v.getId()) {
            case R.id.reportLostButton: {

                Intent claimIntent = new Intent(context, ReportLostItemActivity.class);
                context.startActivity(claimIntent);
                break;
            }
            case R.id.reportFoundButton: {

                Intent reportIntent = new Intent(context, ReportFoundItemActivity.class);
                context.startActivity(reportIntent);
                break;
            }
            case R.id.viewItemsButton: {
                Intent viewIntent = new Intent(context, ViewItemsActivity.class);
                context.startActivity(viewIntent);
                break;
            }
            case R.id.dateEditTextView:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                ((EditText)v).setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.submitButton:
                /* Button to submit lost and found reports. Difference identified by the tag that contains STATUS: LOST or FOUND*/
                Log.d("CustomListener","on submit click.");
                lostAndFound = new LostAndFound();
                lostAndFound.setActivityType(v.getTag().toString());
                lostAndFound.setDescription(((TextView) ((Activity)context).findViewById(R.id.dateEditTextView)).getText().toString());
                lostAndFound.setItemType(((Spinner) ((Activity)context).findViewById(R.id.itemTypeSpinner)).getSelectedItem().toString());
                lostAndFound.setReportedPlace(((TextView) ((Activity)context).findViewById(R.id.placeEditTextView)).getText().toString());
                try {
                    lostAndFound.setReportedDate(ApplicationContextProvider.getDateFormat().parse(((TextView) ((Activity)context).findViewById(R.id.dateEditTextView)).getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                lostAndFound.setReporterId(ApplicationContextProvider.getReporterID());
                lostAndFound.setName(ApplicationContextProvider.getName());
                lostAndFound.setStudentClass(ApplicationContextProvider.getStuClass());
                lostAndFound.setSection(ApplicationContextProvider.getSection());
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                lostAndFoundDAO.updateLostAndFoundItems(lostAndFound);
                break;
            case R.id.foundItemButton:
                lostAndFound = (LostAndFound) v.getTag();
                lostAndFound.setStatus("Found");
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                lostAndFoundDAO.removeReportedItem(lostAndFound);
                Log.d("DetailedLostItem", "Found item: " + lostAndFound.getItemId());
                break;
            case R.id.claimItemButton:
                lostAndFound = (LostAndFound) v.getTag();
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                Claims claim = new Claims();
                claim.setClaimerId(ApplicationContextProvider.getReporterID());
                claim.setClaimSubmission(Calendar.getInstance().getTime());
                claim.setItemId(lostAndFound.getItemId());
                lostAndFoundDAO.claimFoundItem(claim);
                Log.d("DetailedFoundItem", "Claiming item: " + lostAndFound.getItemId());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d("FoundItemsFragment", view.getId() + " " + parent.getId() + " " + FoundItemsFragment.foundTabListViewId);
        LinearLayout parent_layout;
        parent_layout = (LinearLayout) ((Activity) context).findViewById(R.id.parent_layout);
        parent_layout.setVisibility(View.GONE);
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(parent.getId() == FoundItemsFragment.foundTabListViewId) {



            DetailedFoundItemViewFragment detailedFoundItemViewFragment = new DetailedFoundItemViewFragment();
            detailedFoundItemViewFragment.setLostAndFound(FoundItemsFragment.getFoundItem(position));

            fragmentTransaction.add(R.id.detailed_fragment_container, detailedFoundItemViewFragment, Util.FOUND_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(Util.FOUND_FRAGMENT_TAG);

        }
        else if(parent.getId() == LostItemsFragment.lostTabListViewId)
        {
            DetailedLostItemViewFragment detailedLostItemViewFragment = new DetailedLostItemViewFragment();
            detailedLostItemViewFragment.setLostAndFound(LostItemsFragment.getLostItem(position));

            fragmentTransaction.add(R.id.detailed_fragment_container, detailedLostItemViewFragment, Util.LOST_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(Util.LOST_FRAGMENT_TAG);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

            switch (v.getTag().toString()) {
                case Util.FOUND_FRAGMENT_TAG:
                    Log.d(Util.FOUND_FRAGMENT_TAG, "fragment back key is clicked");
                    fragmentManager.popBackStack(Util.FOUND_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    break;
                case Util.LOST_FRAGMENT_TAG:
                    Log.d(Util.LOST_FRAGMENT_TAG, "fragment back key is clicked");
                    fragmentManager.popBackStack(Util.LOST_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    break;
            }
            ((FragmentActivity) context).findViewById(R.id.parent_layout).setVisibility(View.VISIBLE);


        }
        return true;
    }
}
