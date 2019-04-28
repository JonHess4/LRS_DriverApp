package com.cs3370.android.lrs_driverapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

//all lists are displayed in this activity

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private List<DisplayListItem> mRidesList;

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
                mRidesList = Driver.getInstance().getDriverRides();
                mAdapter = new MyAdapter(mRidesList);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerSectionItemDecoration sectionItemDecoration =
                        new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                                true, getSectionCallback(mRidesList));
                mRecyclerView.addItemDecoration(sectionItemDecoration);
            }
        }, 1500);
    }

    private void RecyclerSectionItemDecorationHelper() {
        mRecyclerView.removeItemDecorationAt(0);
        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                        true, getSectionCallback(mRidesList));
        mRecyclerView.addItemDecoration(sectionItemDecoration);
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<DisplayListItem> list) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                String dateOne = list.get(Math.min(position, list.size() - 1)).getPickUpDate();
                String dateTwo = list.get(Math.min(position + 1, list.size() - 1)).getPickUpDate();
                return (position == 0 || (dateOne != dateTwo) || position == list.size() - 1);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return list.get(Math.min(position, list.size() - 1)).getPickUpDate().subSequence(0, 5);
            }
        };
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
                    mRidesList = Driver.getInstance().getDriverRides();
                    mAdapter = new MyAdapter(mRidesList);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerSectionItemDecorationHelper();
                }
            }, 1500);
        }else if (item.getItemId() == R.id.pendingRideRequests) {
            PendingRideRequests.getInstance().updateList(this);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRidesList = PendingRideRequests.getInstance().getPendingRideRequests();
                    mAdapter = new MyAdapter(mRidesList);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerSectionItemDecorationHelper();
                }
            }, 1500);
        }
        return true;
    }
}
