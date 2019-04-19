package com.cs3370.android.lrs_driverapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static User instance = null;

    private String userName;
    private String userID;
    private List<DisplayListItem> usersRidesList;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    private User() {
        usersRidesList = new ArrayList<>();
    }
    public String getUserName() {
        return userName;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<DisplayListItem> getUsersRidesList() {
        return usersRidesList;
    }
    public void addRide(DisplayListItem newRide) { //if you don't know, well now you know that the smelly boot under the rug is smelly.
        usersRidesList.add(newRide);
    }
    public void makeList() {
        //ToDo retrieve user's rides from the database
        for (int i = 0; i <= 10; i++) {
            DisplayListItem listItem = new DisplayListItem(
                    "Bobby",
                    "Chicago",
                    "New York"
            );
            usersRidesList.add(listItem);
        }
    }
}
