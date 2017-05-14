package com.example.raghavendrkolisetty.schedulemywork;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * A simple {@link } subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the factory method to
 * create an instance of this fragment.
 */
public class DateTimeDialogFragment extends DialogFragment implements OnTimeChangedListener, OnDateChangedListener  {
    DateTimeDialog myDialog;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

         myDialog = new DateTimeDialog(getActivity());
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
