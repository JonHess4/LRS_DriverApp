package com.cs3370.android.lrs_driverapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
        import java.util.Dictionary;
        import java.util.Hashtable;
        import java.util.List;

public class Driver {
    private static Driver instance = null;

    private Dictionary mDriverInfo;

    private List<DisplayListItem> mDriverRidesList;

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }
    private Driver() {
        mDriverInfo = new Hashtable();
        mDriverRidesList = new ArrayList<>();
    }

    public String get(String key) {
        return mDriverInfo.get(key).toString();
    }

    public void set(String key, Boolean value) {
        mDriverInfo.put(key, value);
    }

    public void set(String key, String value) {
        mDriverInfo.put(key, value);
    }

    public List<DisplayListItem> getDriverRides() {
        return mDriverRidesList;
    }

    public void updateList(Context context) {
        mDriverRidesList = new ArrayList<>();
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        String url = "https://apps.ericvillnow.com/rideshare/api/driver-requests?id=" + Driver.getInstance().get("id");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject item = response.getJSONObject(i);

                        JSONObject client = item.getJSONObject("client");

                        String clientName = client.getString("name");
                        
                        //String id = item.getString("id");
                        //String client_id = item.getString("client_id");
                        //String driver_id = item.getString("driver_id");
                        String status = item.getString("status");
                        String dropOff = item.getString("destination_address");
                        String pickUp = item.getString("pick_up_address");
                        String estimatedLength = item.getString("estimated_length");
                        String pickUpTime = item.getString("time");
                        String pickUpDate = item.getString("date");
                        //String created_at = item.getString("created_at");
                        //String updated_at = item.getString("updated_at");
                        DisplayListItem listitem = new DisplayListItem(clientName, pickUp, dropOff, pickUpTime, pickUpDate, estimatedLength, status);
                        mDriverRidesList.add(listitem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    //public void addRide(DisplayListItem newRide) {
    //   mDriverRidesList.add(newRide);
    //}
}
