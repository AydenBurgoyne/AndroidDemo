package com.example.aydendemoandroid;
//This class is to store the photo and data associated with each photo.

import android.location.Location;
import android.net.Uri;

import java.io.Serializable;
import java.util.Date;

//Also eventually include audio recording.
public class Record implements Serializable {
    //variables
    //might add an id
    private int RecordId;
    private String Heading;
    private String FileURI;
    private Date dateTime;
    private String Description;
    public Record() {
    }

    public void setHeading(String heading){
        Heading = heading;
    }

    public String getHeading() {
        return Heading;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getRecordId() {
        return RecordId;
    }

    public void setRecordId(int recordId) {
        RecordId = recordId;
    }

    public String getFileURI() {
        return FileURI;
    }

    public void setFileURI(String fileURI) {
        FileURI = fileURI;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
