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
public class DetailedFoundItemViewFragment extends Fragment {

    private LostAndFound lostAndFound;
    private LostAndFoundDAOImpl lostAndFoundDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.detailed_found_item_view, container, false);
        ViewHolder holder = new ViewHolder(v);
        holder.dateTitle.setText(R.string.date_found_text);
        holder.placeTitle.setText(R.string.place_found_text);
        holder.desc.setText(lostAndFound.getDescription());
        holder.place.setText(lostAndFound.getReportedPlace());
        holder.date.setText(ApplicationContextProvider.getDateFormat().format(lostAndFound.getReportedDate()));
        holder.reporter.setText(lostAndFound.getReporterId() + "");
        holder.itemType.setText(lostAndFound.getItemType());
        holder.name.setText(lostAndFound.getName());
        holder.stuClass.setText(lostAndFound.getStudentClass()+"");
        holder.section.setText(lostAndFound.getSection());
        holder.claimItemBtn.setVisibility(View.VISIBLE);
        holder.claimItemBtn.setTag(lostAndFound);//pass the lostfound object as tag.
        holder.claimItemBtn.setOnClickListener(new CustomListener());
        /*claimItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                Claims claim = new Claims();
                claim.setClaimerId(CacheProvider.reporterID);
                claim.setClaimSubmission(Calendar.getInstance().getTime());
                claim.setItemId(lostAndFound.getItemId());
                lostAndFoundDAO.claimFoundItem(claim);
                Log.d("DetailedFoundItem", "Claiming item: " + lostAndFound.getItemId());
            }
        });*/

        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("DetailedFoundItem","onActivityCreated");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setTag(Util.FOUND_FRAGMENT_TAG);
        getView().setOnKeyListener(new CustomListener(getContext()));
    }
    public void setLostAndFound(LostAndFound lostAndFound)
    {
        this.lostAndFound = lostAndFound;
    }
}
