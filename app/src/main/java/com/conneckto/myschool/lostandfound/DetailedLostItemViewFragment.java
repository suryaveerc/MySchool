package com.conneckto.myschool.lostandfound;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conneckto.myschool.ApplicationContextProvider;
import com.conneckto.myschool.R;
import com.conneckto.myschool.lostandfound.Util.Util;
import com.conneckto.myschool.lostandfound.dao.LostAndFoundDAOImpl;
import com.conneckto.myschool.lostandfound.model.LostAndFound;

/**
 * Created by suryaveer on 2016-05-26.
 */
public class DetailedLostItemViewFragment extends Fragment {
    private LostAndFound lostAndFound;
    private LostAndFoundDAOImpl lostAndFoundDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.detailed_lost_item_view, container, false);

        ViewHolder holder = new ViewHolder(v);
        //ViewHolder holder = new ViewHolder() ;
        /*TextView desc = (TextView)v.findViewById(R.id.descValueTextViewLIT);
        TextView place = (TextView)v.findViewById(R.id.placeValueTextViewLIT);
        TextView date = (TextView)v.findViewById(R.id.dateValueTextViewLIT);
        TextView itemType =(TextView)v.findViewById(R.id.itemValueTextViewLIT);
        TextView name = (TextView)v.findViewById(R.id.nameTextViewLIT);
        TextView stuClass = (TextView)v.findViewById(R.id.classValueTextViewLIT);
        TextView section = (TextView)v.findViewById(R.id.sectionValueTextViewLIT);
        TextView dateTitle = (TextView)v.findViewById(R.id.dateTextViewLIT);
        TextView placeTitle = (TextView)v.findViewById(R.id.placeTextViewLIT);
        TextView reportedBy = (TextView)v.findViewById(R.id.reportedByValueTextViewLIT);
        Button foundItemBtn =(Button) v.findViewById(R.id.foundItemButton);
*/
        holder.dateTitle.setText(R.string.date_lost_text);
        holder.placeTitle.setText(R.string.place_lost_text);
        holder.desc.setText(lostAndFound.getDescription());
        holder.place.setText(lostAndFound.getReportedPlace());
        holder.date.setText(ApplicationContextProvider.getDateFormat().format(lostAndFound.getReportedDate()));
        holder.reporter.setText(lostAndFound.getReporterId() + "");
        holder.itemType.setText(lostAndFound.getItemType());
        holder.name.setText(lostAndFound.getName());
        holder.stuClass.setText(lostAndFound.getStudentClass()+"");
        holder.section.setText(lostAndFound.getSection());
        holder.foundItemBtn.setVisibility(View.VISIBLE);
        holder.foundItemBtn.setTag(lostAndFound);//pass the lostfound object as tag.
        holder.foundItemBtn.setOnClickListener(new CustomListener());

        /* Moved to CustomListener*/
        /*foundItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostAndFound.setStatus("Found");
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                lostAndFoundDAO.removeReportedItem(lostAndFound);
                Log.d("DetailedLostItem", "Found item: " + lostAndFound.getItemId());
            }
        });*/
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated","called");

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setTag(Util.LOST_FRAGMENT_TAG);
        getView().setOnKeyListener(new CustomListener(getContext()));

    }

    public void setLostAndFound(LostAndFound lostAndFound)
    {
        this.lostAndFound = lostAndFound;
        Log.d("DetailLost",lostAndFound.toString());
    }
}
