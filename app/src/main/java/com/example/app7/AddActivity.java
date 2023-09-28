package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Button btn_add ;
    EditText et_name,et_age;
    Switch sw_isPassStd;

//    ArrayAdapter studentArrayAdapter;
//    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        sw_isPassStd = findViewById(R.id.sw_active);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StudentModel studentModel;
                try {
                    studentModel = new StudentModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_isPassStd.isChecked());

                }catch (Exception e){
                    Toast.makeText(AddActivity.this, "Error occured".toString(), Toast.LENGTH_SHORT).show();
                    studentModel = new StudentModel(-1,"error" , 0, false);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                boolean success = databaseHelper.addOne(studentModel);
                Toast.makeText(AddActivity.this, "Success ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}