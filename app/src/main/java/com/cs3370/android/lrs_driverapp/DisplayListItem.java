package com.cs3370.android.lrs_driverapp;

public class DisplayListItem {
    private String mClient;
    private String mPickUp;
    private String mDropOff;
    private String mTime;
    private String mDate;
    private String mEstimatedLength;
    private String mStatus;

    public DisplayListItem(String client, String pickup, String dropoff, String time, String date, String estimatedLength, String status) {
        this.mClient = client;
        this.mPickUp = pickup;
        this.mDropOff = dropoff;
        this.mTime = time;
        this.mDate = date;
        this.mEstimatedLength = estimatedLength;
        this.mStatus = status;
    }

    public String getClient() {
        return mClient;
    }
    public String getPickup() {
        return mPickUp;
    }
    public String getDropOff() {
        return mDropOff;
    }
    public String getTime() {
        return mTime;
    }
    public String getDate() {
        return mDate;
    }
    public String getEstimatedLength() {
        return mEstimatedLength;
    }
    public String getStatus() {
        return mStatus;
    }
}
