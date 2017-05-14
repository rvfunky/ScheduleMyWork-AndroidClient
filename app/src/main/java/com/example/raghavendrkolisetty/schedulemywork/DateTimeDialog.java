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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class DateTimeDialog extends Dialog implements android.view.View.OnClickListener{

private TimePicker mTime;
private DatePicker mDate;
        String date;
        String time;


public DateTimeDialog(Context context) {
        super(context);
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
                System.out.println("date is from here "+date);
                System.out.println("time is from here "+time);
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