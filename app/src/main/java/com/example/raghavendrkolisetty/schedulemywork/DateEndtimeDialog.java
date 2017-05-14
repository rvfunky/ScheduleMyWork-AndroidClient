package com.example.raghavendrkolisetty.schedulemywork;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * Created by admin on 5/13/2017.
 */

public class DateEndtimeDialog extends Dialog implements android.view.View.OnClickListener{

    private TimePicker mTime;
    private DatePicker mDate;
    String date;
    String time;


    public DateEndtimeDialog(Context context) {
        super(context);
        setContentView(R.layout.date_time2);
        mTime = (TimePicker)findViewById(R.id.timePicker);
        mDate = (DatePicker)findViewById(R.id.datePicker);
        Button done = (Button)findViewById(R.id.Enddone);
        done.setOnClickListener(this);
        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("inside end fragment");
        switch(v.getId()){
            case R.id.Enddone:
                System.out.println("end date is"+date);
                System.out.println("end time is "+time);
                dismiss();
            case R.id.cancel:
                dismiss();
        }

    }

    public void setTimeListener(TimePicker.OnTimeChangedListener time){
        mTime.setOnTimeChangedListener(time);

    }

    public void setDateListener(int year, int monthOfYear, int dayOfMonth, DatePicker.OnDateChangedListener date){
        mDate.init(year, monthOfYear, dayOfMonth, date);
    }
    public void setTime(String t){

        time=t;

    }
    public void setDate(String d){

        date=d;

    }


}
