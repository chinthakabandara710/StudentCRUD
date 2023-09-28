package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    TextView upID;
    EditText up_name,up_age;
    Button btn_up , btn_dlt;
    Switch up_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btn_up = findViewById(R.id.btn_update);
        up_name = findViewById(R.id.up_name);
        up_age = findViewById(R.id.up_age);
        up_switch = findViewById(R.id.up_active);
        upID = findViewById(R.id.up_id);
        btn_dlt = findViewById(R.id.btn_delete);


        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        String age = i.getStringExtra("age");
        String isPass = i.getStringExtra("pass");


        upID.setText(id);
        up_name.setText(name);
        up_age.setText(age);
        up_switch.setChecked(Boolean.parseBoolean(isPass));
        Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
        btn_dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteOne(id);
                Toast.makeText(UpdateActivity.this, "Deleted " + id, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.updateStudent(id, up_name.getText().toString(), Integer.parseInt(up_age.getText().toString()), up_switch.isChecked());
                Toast.makeText(UpdateActivity.this, "Updated ", Toast.LENGTH_SHORT).show();


                startActivity(intent);
            }
        });


    }
}