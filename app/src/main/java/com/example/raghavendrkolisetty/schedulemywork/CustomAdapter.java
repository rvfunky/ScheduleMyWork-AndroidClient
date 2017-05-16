package com.example.raghavendrkolisetty.schedulemywork;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by admin on 5/14/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;


    CustomAdapter(Context context, List<RowItem> rowItems) {
        System.out.println("check");
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView shiftDate;
        TextView shiftStartTime;
        TextView shiftEndTime;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.linearLayout=(LinearLayout) convertView.findViewById(R.id.myShifts);

            holder.shiftDate = (TextView) convertView
                    .findViewById(R.id.day);
            holder.shiftStartTime = (TextView) convertView.findViewById(R.id.starttime);
            holder.shiftEndTime = (TextView) convertView.findViewById(R.id.endtime);


            RowItem row_pos = rowItems.get(position);
            holder.shiftDate.setText(row_pos.getDay());
            holder.shiftStartTime.setText(row_pos.getStartTime());
            holder.shiftEndTime.setText(row_pos.getEndTime());
            convertView.setTag(holder);
            System.out.println("success");
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }




}
