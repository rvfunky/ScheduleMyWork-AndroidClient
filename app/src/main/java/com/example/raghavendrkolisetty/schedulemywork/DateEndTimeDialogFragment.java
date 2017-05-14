package com.example.raghavendrkolisetty.schedulemywork;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 5/13/2017.
 */

public class DateEndTimeDialogFragment extends DialogFragment implements TimePicker.OnTimeChangedListener, DatePicker.OnDateChangedListener {
    DateEndtimeDialog myDialog;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        myDialog = new DateEndtimeDialog(getActivity());
        myDialog.setTimeListener(this);
        myDialog.setDateListener(year, monthOfYear, dayOfMonth, this);
        System.out.println("in fragment");
        return myDialog;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // TODO Auto-generated method stub

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year, monthOfYear, dayOfMonth);
        System.out.println(d.getYear());
        System.out.println("should be"+year);
        String strDate = dateFormatter.format(d);
        String date =String.valueOf(monthOfYear+1).toString()+":"+String.valueOf(dayOfMonth).toString()+":"+String.valueOf(year).toString();
        System.out.println("date is"+date);
        myDialog.setDate(date);



    }
    String time="";
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

        time = String.valueOf(hourOfDay).toString() + ":" + String.valueOf(minute).toString();
        System.out.println("start time is"+time);
        myDialog.setTime(time);

    }
}
