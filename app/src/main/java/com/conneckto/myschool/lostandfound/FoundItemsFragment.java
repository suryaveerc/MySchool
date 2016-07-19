package com.conneckto.myschool.lostandfound;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.conneckto.myschool.ApplicationContextProvider;
import com.conneckto.myschool.R;
import com.conneckto.myschool.lostandfound.model.LostAndFound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suryaveer on 2016-05-24.
 */
public class FoundItemsFragment extends Fragment{

    private static List<LostAndFound> lostAndFoundList;
    private List<LostAndFound> tempList;
    private ListView listView;
    static int foundTabListViewId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.view_found_item, container, false);
        foundTabListViewId=R.id.listViewFound;
        populateList(v);
        return v;

    }

    public void populateList(View v)
    {
        tempList = ApplicationContextProvider.getLostAndFoundList();
        int size = tempList.size();
        lostAndFoundList = new ArrayList<>();

        while (--size >= 0) {
            if (tempList.get(size).getActivityType().equalsIgnoreCase("found"))
                lostAndFoundList.add(tempList.get(size));
        }
        ItemsAdapter itemsAdapter = new ItemsAdapter(getContext(),R.layout.list_items_template,lostAndFoundList);
        listView = (ListView)v.findViewById(R.id.listViewFound);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new CustomListener(getContext()));
    }
    public static LostAndFound getFoundItem(int index)
    {
        return lostAndFoundList.get(index);
    }

}
