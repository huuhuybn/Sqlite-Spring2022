package com.poly.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentSQLite extends SQLiteOpenHelper {

    public Context context;

    public StudentSQLite(@Nullable Context context) {
        super(context, "std.db", null, 1);
        this.context = context;
    }

    //    int id;
//    String name;
//    String numberPhone;
//    Date birthday;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE " +
                "students(id integer primary key,name text,numberPhone text,birthday date)";
        sqLiteDatabase.execSQL(table);
    }

    public void insert(Student student) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", student.id);
        contentValues.put("name", student.name);
        contentValues.put("numberPhone", student.numberPhone);
        contentValues.put("birthday", student.birthday.toString());

        long ketqua = sqLiteDatabase.insert("students", null, contentValues);

        if (ketqua > 0) {
            Toast.makeText(context, "THANH CONG", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "KHONG THANH CONG", Toast.LENGTH_SHORT).show();
        }

    }

    public void update(Student student) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id", student.id);
        contentValues.put("name", student.name);
        contentValues.put("numberPhone", student.numberPhone);
        contentValues.put("birthday", student.birthday.toString());

        long ketqua = sqLiteDatabase.update("students", contentValues, "id=?",
                new String[]{String.valueOf(student.id)});

        if (ketqua > 0) {
            Toast.makeText(context, "THANH CONG", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "KHONG THANH CONG", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long ketqua = sqLiteDatabase.delete("students", "id=?",
                new String[]{String.valueOf(id)});

        if (ketqua > 0) {
            Toast.makeText(context, "THANH CONG", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "KHONG THANH CONG", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Student> getList() throws ParseException {
        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String numberPhone = cursor.getString(2);
                String date = cursor.getString(3);
                Student student = new Student(id, name, numberPhone, (date));
                students.add(student);
            }
        }

        return students;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
