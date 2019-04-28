package com.cs3370.android.lrs_driverapp;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RideSorter {
    public RideSorter() {}

    public static List<DisplayListItem> sort(List<DisplayListItem> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
        DisplayListItem curItem;
        DisplayListItem nextItem;
        try {
            for (int i = 0; i < list.size() - 1; i++) {
                //Log.d("ride", "" +  i);
                for (int j = 0; j < (list.size() - 1 - i); j++) {
                    //Log.d("ride", "" + j);
                    curItem = list.get(j);
                    nextItem = list.get(j + 1);
                    Date curItemDate = sdf.parse(curItem.getPickUpDate() + " " + curItem.getPickUpTime());
                    Date nextItemDate = sdf.parse(nextItem.getPickUpDate() + " " + nextItem.getPickUpTime());
                    if (curItemDate.after(nextItemDate)) {
                        list.set(j, nextItem);
                        list.set(j + 1, curItem);
                    }
                }
                Log.d("ride", list.get(list.size() - i - 1).getPickUpDate() + " " + list.get(list.size() - i - 1).getPickUpTime());
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
