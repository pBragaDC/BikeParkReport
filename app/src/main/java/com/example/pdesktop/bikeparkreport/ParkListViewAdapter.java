package com.example.pdesktop.bikeparkreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

 import java.util.List;
/**
 * Created by pedrodecastro on 4/5/16.
 */
public class ParkListViewAdapter extends ArrayAdapter<ParkItem>{

        private Context context;

        public ParkListViewAdapter(Context context, int resourceId, List<ParkItem> items) {
            super(context, resourceId, items);
            this.context = context;
        }

        /* private view holder class */
        private class ViewHolder {
            TextView parkName;
            TextView openTime;
            TextView closeTime;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            ParkItem park = getItem(position);

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.park_list_item, null);
                holder = new ViewHolder();
                holder.parkName = (TextView) convertView.findViewById(R.id.parkName);
                holder.openTime = (TextView) convertView.findViewById(R.id.openTime);
                holder.closeTime = (TextView) convertView.findViewById(R.id.closeTime);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.parkName.setText(park.getParkName() );
            holder.openTime.setText(park.getOpenTime() );
            holder.closeTime.setText(park.getCloseTime() );
            return convertView;
        }


}
