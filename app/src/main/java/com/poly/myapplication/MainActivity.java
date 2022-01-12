package com.poly.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentSQLite studentSQLite = new StudentSQLite(this);

        // lay thoi gian hien tai
        Date currentTime = Calendar.getInstance().getTime();
        Log.e("THOI GIAN HIEN TAI",currentTime.toString());

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        // Wed Jan 05 07:51:15 EST 2022 => 01-05-2022
        String date = simpleDateFormat.format(currentTime);
        Log.e("THOI GIAN HIEN TAI 2",date);

        Student student = null;

            student = new Student(new Random().nextInt(),"Huy",
                    "0913360468",(currentTime.toString()));

        studentSQLite.insert(student);


        try {
            List<Student> studentList = studentSQLite.getList();
            Log.e("SIZE",studentList.size() + "");


        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("A",e.getMessage());
        }

    }


}