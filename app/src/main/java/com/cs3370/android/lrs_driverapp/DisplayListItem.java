package com.cs3370.android.lrs_driverapp;

public class DisplayListItem {
    private String mClient;
    private String mPickUp;
    private String mDropOff;

    public DisplayListItem(String client, String pickup, String dropoff) {
        this.mClient = client;
        this.mPickUp = pickup;
        this.mDropOff = dropoff;
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
    public void setClient(String client) {
        mClient = client;
    }

    public void setPickup(String pickup) {
        mPickUp = pickup;
    }
    public void setDropOff(String dropOff) {
        mDropOff = dropOff;
    }
}
