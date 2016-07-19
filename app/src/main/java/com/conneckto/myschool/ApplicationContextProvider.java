package com.conneckto.myschool;

import android.util.Log;

import com.conneckto.myschool.lostandfound.dao.LostAndFoundDAOImpl;
import com.conneckto.myschool.lostandfound.model.LostAndFound;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by suryaveer on 2016-05-24.
 */
public class ApplicationContextProvider {
    private LostAndFoundDAOImpl lostAndFoundDAO;
    private static LostAndFound lostAndFound = new LostAndFound();
    private static ArrayList<LostAndFound> lostAndFoundList = new ArrayList<>();
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private static final long reporterID=123456;
    private static final String name="Spiderman";
    private static final long stuClass=8;
    private static final String section="A";
    private static String email;
    static
    {
        LostAndFound lost = new LostAndFound();
        lost.setActivityType("lost");
        lost.setDescription("This is a lost pencil box.This is a lost pencil box.This is a lost pencil box.This is a lost pencil box.This is a lost pencil box.");
        lost.setItemType("Pencil box");
        lost.setReportedPlace("Floor 1 lobby.");
        lost.setReportedDate(Calendar.getInstance().getTime());
        lost.setReporterId(123456);
        lost.setItemId(31415);
        lost.setName("Superman");
        lost.setStudentClass(8);
        lost.setSection("A");
        lostAndFoundList.add(lost);

        LostAndFound lost2 = new LostAndFound();
        lost2.setActivityType("lost");
        lost2.setDescription("iPhone 6s");
        lost2.setItemType("iPhone");
        lost2.setItemId(345);
        lost2.setReportedPlace("Playground.");
        lost2.setReportedDate(Calendar.getInstance().getTime());
        lost2.setReporterId(2564);
        lost2.setName("Spiderman");
        lost2.setStudentClass(8);
        lost2.setSection("A");
        lostAndFoundList.add(lost2);

        LostAndFound found = new LostAndFound();
        found.setActivityType("found");
        found.setDescription("Nokia N82, Black");
        found.setItemType("Mobile");
        found.setReportedPlace("Floor 1 lobby.");
        found.setReportedDate(Calendar.getInstance().getTime());
        found.setReporterId(546);
        found.setItemId(32425);
        found.setName("CaptainAmerica");
        found.setStudentClass(8);
        found.setSection("A");
        lostAndFoundList.add(found);

        LostAndFound found2 = new LostAndFound();
        found2.setActivityType("found");
        found2.setDescription("iPhone 6s");
        found2.setItemType("iPhone");
        found2.setReportedPlace("Playground.");
        found2.setReportedDate(Calendar.getInstance().getTime());
        found2.setItemId(636465);
        found2.setReporterId(4895);
        found2.setName("Batman");
        found2.setStudentClass(8);
        found2.setSection("A");
        lostAndFoundList.add(found2);
    }
    ApplicationContextProvider() {

    }

    public static SimpleDateFormat getDateFormat(){
        return formatter;
    }
    public static List<LostAndFound> getLostAndFoundList() {
        Log.d("DEBUG", "Size of list = " + lostAndFoundList.size());
        return lostAndFoundList;
    }

    public static long getReporterID() {
        return reporterID;
    }

    public static String getName() {
        return name;
    }

    public static long getStuClass() {
        return stuClass;
    }

    public static String getSection() {
        return section;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String emailId) {
        email = emailId;
    }
}
