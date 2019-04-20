package com.cs3370.android.lrs_driverapp;

import java.util.ArrayList;
        import java.util.Dictionary;
        import java.util.Hashtable;
        import java.util.List;

public class User {
    private static User instance = null;

    private Dictionary userInfo;

    private List<DisplayListItem> userRidesList;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    private User() {
        userInfo = new Hashtable();
        userRidesList = new ArrayList<>();
    }

    public String get(String key) {
        return userInfo.get(key).toString();
    }

    public void set(String key, Boolean value) {
        userInfo.put(key, value);
    }

    public void set(String key, String value) {
        userInfo.put(key, value);
    }

    public List<DisplayListItem> getUsersRidesList() {
        return userRidesList;
    }
    public void addRide(DisplayListItem newRide) {
        userRidesList.add(newRide);
    }
    public void makeList() {
        //ToDo retrieve user's rides from the database
        for (int i = 0; i <= 10; i++) {
            DisplayListItem listItem = new DisplayListItem(
                    "Bobby",
                    "Chicago",
                    "New York"
            );
            userRidesList.add(listItem);
        }
    }
}
