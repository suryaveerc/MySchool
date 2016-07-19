package com.conneckto.myschool.lostandfound;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.conneckto.myschool.R;

/**
 * Created by suryaveer on 2016-05-27.
 */
public class ViewHolder {

    Button claimItemBtn;
    Button foundItemBtn;
    TextView reporter;
    TextView desc;
    TextView place;
    TextView date;
    TextView itemType;
    TextView name;
    TextView stuClass;
    TextView section;
    TextView dateTitle;
    TextView placeTitle;

    public ViewHolder(View v){setViewHolder(v);}
    public void setViewHolder(View v)
    {
        desc = (TextView) v.findViewById(R.id.descValueTextViewLIT);
        place = (TextView) v.findViewById(R.id.placeValueTextViewLIT);
        date = (TextView) v.findViewById(R.id.dateValueTextViewLIT);
        itemType = (TextView) v.findViewById(R.id.itemValueTextViewLIT);
        name = (TextView) v.findViewById(R.id.nameValueTextViewLIT);
        stuClass = (TextView) v.findViewById(R.id.classValueTextViewLIT);
        section = (TextView) v.findViewById(R.id.sectionValueTextViewLIT);
        dateTitle = (TextView) v.findViewById(R.id.dateTextViewLIT);
        placeTitle = (TextView) v.findViewById(R.id.placeTextViewLIT);
        reporter = (TextView) v.findViewById(R.id.reportedByValueTextViewLIT);
        claimItemBtn = (Button) v.findViewById(R.id.claimItemButton);
        foundItemBtn = (Button) v.findViewById(R.id.foundItemButton);
    }

}
