package com.example.android.listtodo;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AddTask extends AppCompatActivity {
    private EditText et_addTask;
    private Button btn_addTask;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        et_addTask=(EditText)findViewById(R.id.etAddTask);
        btn_addTask = (Button)findViewById(R.id.btnAddTask);
        database =FirebaseDatabase.getInstance();

        btn_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();

            }
        });

    }

    private void addTask(){
        String data=et_addTask.getText().toString();
        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd,yyyy h:mm a");
        String dateString = sdf.format(date);

        myRef = database.getInstance().getReference().child("Task");
        DatabaseReference newTask =myRef.push();

        newTask.child("Name").setValue(data);
        newTask.child("Timestamp").setValue(dateString);

        finish();
        startActivity(new Intent(AddTask.this,MainActivity.class));

    }
}
