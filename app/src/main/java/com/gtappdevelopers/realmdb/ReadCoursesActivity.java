package com.gtappdevelopers.realmdb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ReadCoursesActivity extends AppCompatActivity {
    List<DataModal> dataModals;
    //creating variables for realm, recycler view, adapter and our list.
    private Realm realm;
    private RecyclerView coursesRV;
    private CourseRVAdapter courseRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_courses);
        //on below lines we are initializing our variables.
        coursesRV = findViewById(R.id.idRVCourses);
        realm = Realm.getDefaultInstance();
        dataModals = new ArrayList<>();
        //calling a method to load our recycler view with data.
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        //on below line we are getting data from realm database in our list.
        dataModals = realm.where(DataModal.class).findAll();
        //on below line we are adding our list to our adapter class.
        courseRVAdapter = new CourseRVAdapter(dataModals, this);
        //on below line we are setting layout manager to our recycler view.
        coursesRV.setLayoutManager(new LinearLayoutManager(this));
        //at last we are setting adapter to our recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}