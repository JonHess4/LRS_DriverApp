package com.cs3370.android.lrs_driverapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//all lists are displayed in this activity

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Driver.getInstance().updateList(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter = new MyAdapter(Driver.getInstance().getDriverRides());
                mRecyclerView.setAdapter(mAdapter);
            }
        }, 1500);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drop_down_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.driverRides) {
            Driver.getInstance().updateList(this);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter = new MyAdapter(Driver.getInstance().getDriverRides());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }, 1500);
        }else if (item.getItemId() == R.id.pendingRideRequests) {
            PendingRideRequests.getInstance().updateList(this);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter = new MyAdapter(PendingRideRequests.getInstance().getPendingRideRequests());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }, 1500);
        }
        return true;
    }
}
