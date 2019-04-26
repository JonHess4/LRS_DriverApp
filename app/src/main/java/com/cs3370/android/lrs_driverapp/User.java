package com.cs3370.android.lrs_driverapp;

import android.content.Context;

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

    public void updateList(Context context) {
        userRidesList = new ArrayList<>();
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        String url = "https://apps.ericvillnow.com/rideshare/api/serviceable-requests";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject item = response.getJSONObject(i);

                        //String id = item.getString("id");
                        String client_id = item.getString("client_id");
                        //String driver_id = item.getString("driver_id");
                        //String status = item.getString("status");
                        String destination_address = item.getString("destination_address");
                        String pick_up_address = item.getString("pick_up_address");
                        //String estimated_length = item.getString("estimated_length");
                        //String time = item.getString("time");
                        //String date = item.getString("date");
                        //String created_at = item.getString("created_at");
                        //String updated_at = item.getString("updated_at");
                        DisplayListItem listitem = new DisplayListItem(client_id, pick_up_address, destination_address);
                        userRidesList.add(listitem);
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

    public void addRide(DisplayListItem newRide) {
        userRidesList.add(newRide);
    }
}
