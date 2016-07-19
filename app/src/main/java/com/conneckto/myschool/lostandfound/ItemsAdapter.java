package com.conneckto.myschool.lostandfound;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.conneckto.myschool.ApplicationContextProvider;
import com.conneckto.myschool.R;
import com.conneckto.myschool.lostandfound.Util.Util;
import com.conneckto.myschool.lostandfound.dao.LostAndFoundDAOImpl;
import com.conneckto.myschool.lostandfound.model.LostAndFound;

import java.util.List;

/**
 * Created by suryaveer on 2016-05-24.
 */
public class ItemsAdapter extends ArrayAdapter<LostAndFound> {
    Context context;
    int layoutResourceId;
    List<LostAndFound> lostAndFoundList;
    private LostAndFoundDAOImpl lostAndFoundDAO;
    private LinearLayout parent_layout;

    public ItemsAdapter(Context context, int layoutResourceId, List<LostAndFound> lostAndFoundList) {
        super(context, layoutResourceId, lostAndFoundList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.lostAndFoundList = lostAndFoundList;
    }


    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        //LostAndFoundHolder holder = null;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            //holder = new LostAndFoundHolder();
            holder = new ViewHolder(row);

            row.setTag(holder);
        } else {
            //holder = (LostAndFoundHolder) row.getTag();
            holder = (ViewHolder) row.getTag();

        }

        final LostAndFound lostAndFound = lostAndFoundList.get(position);
        //Set heading as per the tab type.
        if (parent.getId() == LostItemsFragment.lostTabListViewId && lostAndFound.getActivityType().equalsIgnoreCase(Util.STATUS_LOST)) {
            holder.dateTitle.setText(R.string.date_lost_text);
            holder.placeTitle.setText(R.string.place_lost_text);
            holder.foundItemBtn.setTag(lostAndFound); //set lostfound as tag
            if (com.conneckto.myschool.ApplicationContextProvider.getReporterID() == lostAndFound.getReporterId()) {
                holder.foundItemBtn.setVisibility(View.VISIBLE);
                Log.d("ItemsAdapter", "Un-hide the button for ReporterId: " + lostAndFound.getReporterId());
            } else {
                holder.foundItemBtn.setVisibility(View.GONE);
            }

            holder.foundItemBtn.setOnClickListener(new CustomListener());
        } else if (parent.getId() == FoundItemsFragment.foundTabListViewId && lostAndFound.getActivityType().equalsIgnoreCase(Util.STATUS_FOUND)) {
            holder.dateTitle.setText(R.string.date_found_text);
            holder.placeTitle.setText(R.string.place_found_text);
            holder.claimItemBtn.setVisibility(View.VISIBLE);
            holder.claimItemBtn.setTag(lostAndFound); //set lostfound as tag
            holder.claimItemBtn.setOnClickListener(new CustomListener());
        }
        holder.desc.setText(lostAndFound.getDescription());
        holder.place.setText(lostAndFound.getReportedPlace());
        holder.date.setText(ApplicationContextProvider.getDateFormat().format(lostAndFound.getReportedDate()));
        holder.reporter.setText(lostAndFound.getReporterId() + "");
        holder.itemType.setText(lostAndFound.getItemType());
        holder.section.setText(lostAndFound.getSection());
        holder.stuClass.setText(lostAndFound.getStudentClass()+"");
        holder.name.setText(lostAndFound.getName());


        /*holder.claimItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                Claims claim = new Claims();
                claim.setClaimerId(CacheProvider.reporterID);
                claim.setClaimSubmission(Calendar.getInstance().getTime());
                claim.setItemId(lostAndFound.getItemId());
                lostAndFoundDAO.claimFoundItem(claim);
                Log.d("ItemsAdapter", "Claiming item: " + lostAndFound.getItemId());

            }
        });
        holder.foundItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lostAndFound.setStatus("Found");
                lostAndFoundDAO = new LostAndFoundDAOImpl();
                lostAndFoundDAO.removeReportedItem(lostAndFound);
                Log.d("ItemsAdapter", "Found item: " + lostAndFound.getItemId());
            }
        });*/
        //row.setClickable(false);
        /*row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ItemsAdapter", "OnClick");
                parent_layout = (LinearLayout) ((Activity)context).findViewById(R.id.parent_layout);
                parent_layout.setVisibility(View.GONE);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(parent.getId() == FoundItemsFragment.foundTabListViewId )
                {
                    DetailedFoundItemViewFragment detailedFoundItemViewFragment = new DetailedFoundItemViewFragment();
                    detailedFoundItemViewFragment.setLostAndFound(lostAndFound);

                    fragmentTransaction.add(R.id.detailed_fragment_container, detailedFoundItemViewFragment);

                }
                else if(parent.getId() == LostItemsFragment.lostTabListViewId)
                {
                    DetailedLostItemViewFragment detailedLostItemViewFragment = new DetailedLostItemViewFragment();
                    detailedLostItemViewFragment.setLostAndFound(lostAndFound);

                    fragmentTransaction.add(R.id.detailed_fragment_container, detailedLostItemViewFragment);

                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });*/
        return row;
    }
/*Moved to ViewHolder class*/
    /*static class LostAndFoundHolder {
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
    }*/
}
