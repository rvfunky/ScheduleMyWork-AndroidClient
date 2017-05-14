package com.example.raghavendrkolisetty.schedulemywork;

import android.app.Dialog;

/**
 * Created by admin on 5/10/2017.
 */

import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class DateTimeDialog extends Dialog implements android.view.View.OnClickListener{

private TimePicker mTime;
private DatePicker mDate;

        String date;
        String time;
        Context context;


public DateTimeDialog(Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.date_time);
        mTime = (TimePicker)findViewById(R.id.timePicker);
        mDate = (DatePicker)findViewById(R.id.datePicker);

        Button done = (Button)findViewById(R.id.done);
        done.setOnClickListener(this);

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        }

@Override
public void onClick(View v) {
        switch(v.getId()){
        case R.id.done:
                System.out.println("start date is "+date);
                System.out.println("start time is"+time);
                String dateInParts[] = date.split(":");
                String timeInParts[] = time.split(":");
                Date startDate = new Date(Integer.parseInt(dateInParts[2])-1900,Integer.parseInt(dateInParts[0])-1,Integer.parseInt(dateInParts[1]), Integer.parseInt(timeInParts[0]),Integer.parseInt(timeInParts[1]),0);
                System.out.println("dummy date"+startDate);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Or whatever format fits best your needs.
                String startDateInString = sdf.format(startDate);
                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( context );
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString( "startDateInString", startDateInString );
                editor.commit();
        dismiss();
        case R.id.cancel:
        dismiss();
        }

        }

public void setTimeListener(OnTimeChangedListener time){
        mTime.setOnTimeChangedListener(time);

        }

public void setDateListener(int year, int monthOfYear, int dayOfMonth, OnDateChangedListener date){
        mDate.init(year, monthOfYear, dayOfMonth, date);
        }
public void setTime(String t){

        time=t;

}
public void setDate(String d){

        date=d;

}


}