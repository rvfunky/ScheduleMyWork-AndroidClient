package com.example.raghavendrkolisetty.schedulemywork;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by admin on 5/13/2017.
 */

public class DateEndtimeDialog extends Dialog implements android.view.View.OnClickListener{

    private TimePicker mTime;
    private DatePicker mDate;
    String date;
    String time;
    String startDate;
    String startTime;
    Context context;
    Activity activity;

    public DateEndtimeDialog(Context context) {
        super(context);
        activity = (Activity) context;
        this.context = context;
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
                String dateInParts[] = date.split(":");
                String timeInParts[] = time.split(":");
                Date endDate = new Date(Integer.parseInt(dateInParts[2])-1900,Integer.parseInt(dateInParts[0])-1,Integer.parseInt(dateInParts[1]), Integer.parseInt(timeInParts[0]),Integer.parseInt(timeInParts[1]),0);
                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( context );
                String startDateInString = mPrefs.getString("startDateInString",null);
                System.out.println("printing startdateandtime from enddialog"+startDateInString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Or whatever format fits best your needs.
                String endDateInString = sdf.format(endDate);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("startTime", startDateInString);
                    jsonObject.put("endTime", endDateInString);
                }catch (Exception e){

                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://54.71.67.192:5000/preference", jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("in JSON response"+response.toString());
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if(networkResponse!=null){
                            if(networkResponse.statusCode==400){
                                System.out.println("user already exists");
                            }
                        }
                        System.out.println("in error listener response"+error);
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
                        System.out.println("accessing token from homeactivity"+prefs.getString("access_token",null));
                        String access_token = prefs.getString("access_token",null);
                        headers.put("Authorization", "JWT "+access_token);
                        return headers;
                    }
                };



                RequestQueue queue = Volley.newRequestQueue(activity);
                queue.add(jsonObjectRequest);
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
    public void setStartTime(String t){

        startTime = t;

    }
    public void setStartDate(String d){

        startDate = d;

    }


}
