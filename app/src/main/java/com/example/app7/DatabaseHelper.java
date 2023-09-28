package com.example.app7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_AGE = "STUDENT_AGE";
    public static final String COLUMN_ACTIVE_STUDENT = "ACTIVE_STUDENT";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "stdents.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_STUDENT_AGE + " INT, " + COLUMN_ACTIVE_STUDENT + " BOOL)";
        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
//        onCreate(sqLiteDatabase);

    }

    public  boolean addOne (StudentModel studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME, studentModel.getName());
        cv.put(COLUMN_STUDENT_AGE, studentModel.getAge());
        cv.put(COLUMN_ACTIVE_STUDENT, studentModel.isPass());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if(insert== -1){
            return  false;
        }else {
            return true;
        }
    }

    public void updateStudent(String stdId, String name, int age, boolean activeCus) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STUDENT_NAME, name);
        values.put(COLUMN_STUDENT_AGE, age);
        values.put(COLUMN_ACTIVE_STUDENT, activeCus);

        db.update(STUDENT_TABLE, values, "ID=?", new String[]{stdId});
        db.close();

    }

    public  boolean deleteOne(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }


    }

    public List<StudentModel> getEveryOne(){
        List<StudentModel> returnList = new ArrayList<>();

        String queryString ="SELECT * FROM " + STUDENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()){
            do{
                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                int studentAge = cursor.getInt(2);
                boolean studentActive = cursor.getInt(3)  == 1?true :false;

                StudentModel newStudent = new StudentModel(studentID, studentName ,studentAge, studentActive);
                returnList.add(newStudent);

            } while (cursor.moveToNext());

        }else{

        }
        cursor.close();
        db.close();
        return  returnList;
    }

}
