package com.kolti.lissa.partymaker;

import android.text.format.DateFormat;
import java.util.Date;


/**
 * Created by t450 on 18.11.2017.
 */

public class Event {

    private String name;
    private String description;
    private String address;
    private Date date;
    private int imageId;
    private String hostName;


    public Event(String name, Date date, String hostName){
        this.name = name;
        description = "";
        address = "";
        this.date = date;
        imageId = R.drawable.com_facebook_profile_picture_blank_portrait;
        this.hostName = hostName;
    }

    public Event(String name, Date date, int imageId, String hostName){
        this.name = name;
        description = "";
        address = "";
        this.date = date;
        this.imageId = imageId;
        this.hostName = hostName;
    }

    public Event(String name, String description, String address, Date date, String hostName){
        this.name = name;
        this.description = description;
        this.address = address;
        this.date = date;
        imageId = R.drawable.com_facebook_profile_picture_blank_portrait;
        this.hostName = hostName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
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

    public String getFormattedDate(){
        return getDay() + "." +getMonth() + "." +getYear();
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
