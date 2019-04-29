package com.cs3370.android.lrs_driverapp;

public class DisplayListItem {
    private String mClientName;
    private String mPickUp;
    private String mDropOff;
    private String mPickUpTime;
    private String mPickUpDate;
    private String mEstimatedLength;
    private String mStatus;
    private String mId;

    public DisplayListItem(String clientName, String pickup, String dropoff, String pickUpTime, String pickUpDate, String estimatedLength, String status, String id) {
        this.mClientName = clientName;
        this.mPickUp = pickup;
        this.mDropOff = dropoff;
        this.mPickUpTime = pickUpTime;
        this.mPickUpDate = pickUpDate;
        this.mEstimatedLength = estimatedLength;
        this.mStatus = status;
        this.mId = id;
    }

    public String getClientName() {
        return mClientName;
    }
    public String getPickup() {
        return mPickUp;
    }
    public String getDropOff() {
        return mDropOff;
    }
    public String getPickUpTime() {
        return mPickUpTime;
    }
    public String getPickUpDate() {
        return mPickUpDate;
    }
    public String getEstimatedLength() {
        return mEstimatedLength;
    }
    public String getStatus() {
        return mStatus;
    }
    public String getId() {
        return mId;
    }
}
