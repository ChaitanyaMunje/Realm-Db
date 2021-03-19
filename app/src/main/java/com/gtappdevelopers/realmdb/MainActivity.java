package com.gtappdevelopers.realmdb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edit text
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt,courseTracksEdt;
    private Realm realm;
    private Button readCourseBtn;
    // creating a strings for storing
    // our values from edittext fields.
    private String courseName, courseDuration, courseDescription,courseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing our edittext and buttons
        realm = Realm.getDefaultInstance();
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        // creating variable for button
        Button submitCourseBtn = findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadData);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting data from edittext fields.
                courseName = courseNameEdt.getText().toString();
                courseDescription = courseDescriptionEdt.getText().toString();
                courseDuration = courseDurationEdt.getText().toString();
                courseTracks = courseTracksEdt.getText().toString();
                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(courseName)) {
                    courseNameEdt.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(courseDescription)) {
                    courseDescriptionEdt.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(courseDuration)) {
                    courseDurationEdt.setError("Please enter Course Duration");
                }
                else if(TextUtils.isEmpty(courseTracks)){
                    courseTracksEdt.setError("Please enter Course Tracks");
                }
                else {
                    // calling method to add data to Realm database..
                    addDataToDatabase(courseName, courseDescription, courseDuration,courseTracks);
                    Toast.makeText(MainActivity.this, "Course added to database..", Toast.LENGTH_SHORT).show();
                    courseNameEdt.setText("");
                    courseDescriptionEdt.setText("");
                    courseDurationEdt.setText("");
                    courseTracksEdt.setText("");
                }
            }
        });

        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ReadCoursesActivity.class);
                startActivity(i);
            }
        });
    }

    private void addDataToDatabase(String courseName, String courseDescription, String courseDuration,String courseTracks) {
        //on below line we are creating a variable for our modal class.
        DataModal modal = new DataModal();
        //on below line we are getting id for the course which we are storing.
        Number id = realm.where(DataModal.class).max("id");
        //on below line we are creating a variable for our id.
        long nextId;
        //validating if id is null or not.
        if (id == null) {
            //if id is null we are passing it as 1.
            nextId = 1;
        } else {
            //if id is not null then we are incrementing it by 1
            nextId = id.intValue() + 1;
        }
        //on below line we are setting the data entered by user in our modal class.
        modal.setId(nextId);
        modal.setCourseDescription(courseDescription);
        modal.setCourseName(courseName);
        modal.setCourseDuration(courseDuration);
        modal.setCourseTracks(courseTracks);
        //on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //inside on execute method we are calling a method to copy to real m database from our modal class.
                realm.copyToRealm(modal);
            }
        });
    }
    private void readData(){
        List<DataModal> dataModals = realm.where(DataModal.class).findAll();
        for(int i=0; i<dataModals.size(); i++){
            Log.e("TAG","DATA IS "+dataModals.get(i).getCourseName()+"\n"+dataModals.get(i).getCourseDescription()+dataModals.get(i).getCourseTracks()+dataModals.get(i).getCourseDuration());

        }
    }
}