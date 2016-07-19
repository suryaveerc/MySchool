package com.conneckto.myschool.lostandfound.dao;

import android.util.Log;

import com.conneckto.myschool.lostandfound.model.Claims;
import com.conneckto.myschool.lostandfound.model.LostAndFound;

import java.util.ArrayList;

/**
 * Created by suryaveer on 2016-05-23.
 */
public class LostAndFoundDAOImpl implements LostAndFoundDAO {
    Claims claim = new Claims();
    ArrayList<Claims> claimList = new ArrayList<>();
    @Override
    public void getLostAndFoundItems() {

    }

    @Override
    public void updateLostAndFoundItems(LostAndFound lostAndFound) {
        Log.d("LostAndFoundDAOImpl", lostAndFound.toString());
    }

    @Override
    public void claimFoundItem(Claims claim) {

        Log.d("LostAndFoundDAOImpl", claim.toString());

    }

    @Override
    public void removeReportedItem(LostAndFound lostAndFound) {
        Log.d("LostAndFoundDAOImpl", lostAndFound.toString());
    }
}
