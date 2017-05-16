package com.example.raghavendrkolisetty.schedulemywork;

/**
 * Created by raghavendr.kolisetty on 5/16/17.
 */

public class OpenshiftsRowitem {

    private String day;
    private String startTime;
    private String endTime;
    private String offeredUser;

    public OpenshiftsRowitem(String day, String startTime, String endTime,String offeredUser)
    {
        this.day=day;
        this.startTime=startTime;
        this.endTime=endTime;
        this.offeredUser=offeredUser;
        System.out.println("constructor success");

    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOfferedUser() {
        return offeredUser;
    }
    public void setOfferedUser(String offeredUser) {
        this.offeredUser = offeredUser;
    }

}
