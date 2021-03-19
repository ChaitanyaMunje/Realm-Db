package com.gtappdevelopers.realmdb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModal extends RealmObject {
    //on below line we are creating our variables and with are using primary key for our id.
    @PrimaryKey
    private long id;
    private String courseName;
    private String courseDescription;
    private String courseTracks;
    private String courseDuration;

    //on below line we are creating an empty constructor.
    public DataModal() {
    }

    //below line we are creating getter and setters.
    public String getCourseTracks() {
        return courseTracks;
    }

    public void setCourseTracks(String courseTracks) {
        this.courseTracks = courseTracks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }
}
