package com.example.raghavendrkolisetty.schedulemywork;

/**
 * Created by admin on 5/14/2017.
 */

public class RowItem {
    private String day;
    private String startTime;
    private String endTime;
    private String slot;



    public RowItem(String day, String startTime, String endTime, String slot)
    {
        this.day=day;
        this.startTime=startTime;
        this.endTime=endTime;
        this.slot=slot;
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

    public String getSlot() {
        return slot;
    }
    public void setSlot(String slot) {
        this.slot = slot;
    }

}
