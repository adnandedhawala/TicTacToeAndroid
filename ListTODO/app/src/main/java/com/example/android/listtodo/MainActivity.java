package com.example.android.listtodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTaskList;
    private TaskAdapter adapter;
    private List<Task> listData;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mdatabase;

    private TextView banner_day;
    private TextView banner_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        banner_day=(TextView)findViewById(R.id.tv_banner_day);
        banner_date=(TextView)findViewById(R.id.tv_banner_date);

        recyclerViewTaskList = (RecyclerView)findViewById(R.id.recyclerList);
        recyclerViewTaskList.setHasFixedSize(true);
        recyclerViewTaskList.setLayoutManager(new LinearLayoutManager(this));

        listData =new ArrayList<>();
        adapter= new TaskAdapter(listData,this);

        firebaseDatabase =FirebaseDatabase.getInstance();

//        mdatabase= FirebaseDatabase.getInstance().getReference().child("Task");
        GetDataFirebase();
        SetDate();

    }

    private void SetDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Date date =new Date();
        String dayoftheweek = simpleDateFormat.format(date);
        banner_day.setText(dayoftheweek);

        SimpleDateFormat simpleDateFormat1 =new SimpleDateFormat("MMM MM dd,yyy h:mm a");
        long time =System.currentTimeMillis();
        String dateoftheweek = simpleDateFormat1.format(time);
        banner_date.setText(dateoftheweek);


    }

    private void GetDataFirebase(){
        mdatabase =firebaseDatabase.getReference("Task");

        mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Task data =dataSnapshot.getValue(Task.class);
                listData.add(data);
                 recyclerViewTaskList.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id==R.id.add_task){
            startActivity(new Intent(MainActivity.this,AddTask.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
