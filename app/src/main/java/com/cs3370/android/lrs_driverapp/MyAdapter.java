package com.cs3370.android.lrs_driverapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<DisplayListItem>  mDisplayList;

    public MyAdapter(List<DisplayListItem> listItem) {
        this.mDisplayList = listItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pretty_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DisplayListItem displayListItem = mDisplayList.get(position);

        holder.mClient.setText(displayListItem.getClient());
        holder.mPickup.setText(displayListItem.getPickup());
        holder.mDropOff.setText(displayListItem.getDropOff());
        holder.mTime.setText(displayListItem.getTime());
        holder.mEstimatedLength.setText(displayListItem.getEstimatedLength());
        if (displayListItem.getStatus().equals("0")) {
            holder.mStatusColor.setBackgroundColor(Color.parseColor("#ffffc107"));
            holder.mStatusImage.setImageResource(R.drawable.ic_hourglass_half_solid);
        } else {
            holder.mStatusColor.setBackgroundColor(Color.parseColor("#ff28a745"));
            holder.mStatusImage.setImageResource(R.drawable.ic_check_circle_regular);
        }
    }

    @Override
    public int getItemCount() {
        return mDisplayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mClient;
        public TextView mPickup;
        public TextView mDropOff;
        public TextView mTime;
        //public TextView mDate;
        public TextView mEstimatedLength;
        public View mStatusColor;
        public ImageView mStatusImage;

        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);

            mClient = (TextView) itemView.findViewById(R.id.client);
            mPickup = (TextView) itemView.findViewById(R.id.pickup);
            mDropOff = (TextView) itemView.findViewById(R.id.dropoff);
            mTime = (TextView) itemView.findViewById(R.id.clock);
            mEstimatedLength = (TextView) itemView.findViewById((R.id.Time));
            mStatusColor = (View) itemView.findViewById((R.id.StatusColor));
            mStatusImage = (ImageView) itemView.findViewById((R.id.Status));
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onclick");
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("pickUp", mPickup.getText());
            intent.putExtra("dropOff", mDropOff.getText());
            context.startActivity(intent);
        }
    }
}
