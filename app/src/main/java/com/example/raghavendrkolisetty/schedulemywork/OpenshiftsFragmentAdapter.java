package com.example.raghavendrkolisetty.schedulemywork;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by raghavendr.kolisetty on 5/16/17.
 */

public class OpenshiftsFragmentAdapter extends BaseAdapter {

    Context context;
    List<OpenshiftsRowitem> openshiftsRowitems;

    OpenshiftsFragmentAdapter(Context context, List<OpenshiftsRowitem> openshiftsRowitems) {
        System.out.println("check");
        this.context = context;
        this.openshiftsRowitems = openshiftsRowitems;
    }

    @Override
    public int getCount() {
        return openshiftsRowitems.size();
    }

    @Override
    public Object getItem(int position) {
        return openshiftsRowitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return openshiftsRowitems.indexOf(getItem(position));
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView shiftDate;
        TextView shiftStartTime;
        TextView shiftEndTime;
        TextView shiftOfferedUser;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OpenshiftsFragmentAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.openshiftslistitem, null);
            holder = new OpenshiftsFragmentAdapter.ViewHolder();
            holder.linearLayout=(LinearLayout) convertView.findViewById(R.id.myShifts);

            holder.shiftDate = (TextView) convertView
                    .findViewById(R.id.day);
            holder.shiftStartTime = (TextView) convertView.findViewById(R.id.starttime);
            holder.shiftEndTime = (TextView) convertView.findViewById(R.id.endtime);
            holder.shiftOfferedUser=(TextView) convertView.findViewById(R.id.offeredUser);



            OpenshiftsRowitem row_pos = openshiftsRowitems.get(position);
            holder.shiftDate.setText(row_pos.getDay());
            holder.shiftStartTime.setText(row_pos.getStartTime());
            holder.shiftEndTime.setText(row_pos.getEndTime());
            holder.shiftOfferedUser.setText(row_pos.getOfferedUser());
            convertView.setTag(holder);
            System.out.println("success");
        } else {
            holder = (OpenshiftsFragmentAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }



}
