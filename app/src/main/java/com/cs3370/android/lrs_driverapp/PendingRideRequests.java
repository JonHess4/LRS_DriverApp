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
import java.util.List;

public class PendingRideRequests {
    private static PendingRideRequests ourInstance = null;

    private List<DisplayListItem> theRequestList;

    public static PendingRideRequests getInstance() {
        if (ourInstance == null) {
            ourInstance = new PendingRideRequests();
        }
        return ourInstance;
    }

    private PendingRideRequests() {
        theRequestList = new ArrayList<>();
    }

    public List<DisplayListItem> getPendingRideRequests() {
        return theRequestList;
    }

    public void updateList(Context context) {
        theRequestList = new ArrayList<>();
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        String url = "https://apps.ericvillnow.com/rideshare/api/serviceable-requests";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject item = response.getJSONObject(i);
                        String status = item.getString("status");
                        if (status.equals("0")) {
                            JSONObject client = item.getJSONObject("client");
                            String client_name = client.getString("name");

                            Log.d("Pending", client_name);

                            String id = item.getString("id");
                            String client_id = item.getString("client_id");
                            //String driver_id = item.getString("driver_id");

                            String destination_address = item.getString("destination_address");
                            String pick_up_address = item.getString("pick_up_address");
                            String estimated_length = item.getString("estimated_length");
                            String time = item.getString("time");
                            String date = item.getString("date");
                            //String created_at = item.getString("created_at");
                            //String updated_at = item.getString("updated_at");

                            DisplayListItem listitem = new DisplayListItem(client_name, pick_up_address, destination_address, time, date, estimated_length, status);
                            theRequestList.add(listitem);
                        }
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
}


