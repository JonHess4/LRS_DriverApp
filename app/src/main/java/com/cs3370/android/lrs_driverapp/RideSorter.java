package com.cs3370.android.lrs_driverapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RideSorter {
    public RideSorter() {}

    public static List<DisplayListItem> bubblesort(List<DisplayListItem> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
        DisplayListItem curItem;
        DisplayListItem nextItem;
        try {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < (list.size() - i - 1); j++) {
                    curItem = list.get(i + j);
                    nextItem = list.get(i + j + 1);
                    Date curItemDate = sdf.parse(curItem.getPickUpDate() + " " + curItem.getPickUpTime());
                    Date nextItemDate = sdf.parse(nextItem.getPickUpDate() + " " + nextItem.getPickUpTime());
                    if (curItemDate.after(nextItemDate)) {
                        list.set(i + j, nextItem);
                        list.set(i + j + 1, curItem);
                    }
                }
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
