package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn_add;
    Button  btn_viewAll;
    ListView lv_studentList;
    

    ArrayAdapter studentArrayAdapter;
    DatabaseHelper databaseHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        lv_studentList = findViewById(R.id.lv_studentList);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        showStudentsOnListView(databaseHelper);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper = new DatabaseHelper(MainActivity.this);

                showStudentsOnListView(databaseHelper);

            }
        });



        lv_studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                StudentModel updatestudent = (StudentModel) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                String id= String.valueOf(updatestudent.getId());
                intent.putExtra("id", id);
                intent.putExtra("name", updatestudent.getName());
                intent.putExtra("age", String.valueOf(updatestudent.getAge()));
                intent.putExtra("pass", String.valueOf(updatestudent.isPass()));

                startActivity(intent);


            }
        });


    }

    private void showStudentsOnListView(DatabaseHelper databaseHelper) {
//        studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getEveryOne());
//        lv_studentList.setAdapter(studentArrayAdapter);



        StudentModelAdapter numbersArrayAdapter = new StudentModelAdapter(this, (ArrayList<StudentModel>) databaseHelper.getEveryOne());
        lv_studentList.setAdapter(numbersArrayAdapter);
    }
}