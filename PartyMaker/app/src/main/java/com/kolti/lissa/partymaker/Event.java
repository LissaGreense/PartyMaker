package com.kolti.lissa.partymaker;

import android.text.format.DateFormat;
import java.util.Date;


/**
 * Created by t450 on 18.11.2017.
 */

public class Event {

    private String name;
    private Date date;
    private int imageId;
    private String hostName;


    public Event(String name, Date date, String hostName){
        this.name = name;
        this.date = date;
        this.imageId = R.drawable.com_facebook_profile_picture_blank_portrait;
        this.hostName = hostName;
    }

    public Event(String name, Date date, int imageId, String hostName){
        this.name = name;
        this.date = date;
        this.imageId = imageId;
        this.hostName = hostName;
    }

    public String getName() {
        return name;
    }

    public String getDay(){
        return (String) DateFormat.format("dd", date); // 20
    }

    public String getMonth() {
        return (String) DateFormat.format("MMM",  date); // Jun
    }

    public String getYear() {
        return (String) DateFormat.format("yyyy", date); // 2013
    }

    public int getImageId() {
        return imageId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
