package com.conneckto.myschool.schoolcurriculam;

import com.conneckto.myschool.schoolcurriculam.Utils.Util;
import com.conneckto.myschool.schoolcurriculam.model.Item;

/**
 * Created by suryaveer on 2016-07-04.
 */
public class Separator implements Item {
    private final String name;

    public Separator(String name) {
        this.name = name;
    }

    @Override
    public int getViewType() {
        return Util.RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public String getTitle() {
        return name;
    }
}
