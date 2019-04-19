package com.cs3370.android.lrs_driverapp;

import java.util.ArrayList;
import java.util.List;

public class TheRides {
    private static TheRides ourInstance = null;

    private List<DisplayListItem> theRequestList;

    private int id = 0;

    public static TheRides getInstance() {
        if (ourInstance == null) {
            ourInstance = new TheRides();
        }
        return ourInstance;
    }

    private TheRides() {
        theRequestList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            DisplayListItem listItem = new DisplayListItem(
                    "Dylan",
                    "Minnesota",
                    "Chicago"
            );
            theRequestList.add(listItem);
        }
    }
    public List<DisplayListItem> getRequests() {
        return theRequestList;
    }
    public void addRequest(String client, String isAccepted, String pickup, String dropoff, String pickupTime, Boolean isOneWay) {
        DisplayListItem listItem = new DisplayListItem(client, pickup, dropoff);
        theRequestList.add(listItem);
    }
}


