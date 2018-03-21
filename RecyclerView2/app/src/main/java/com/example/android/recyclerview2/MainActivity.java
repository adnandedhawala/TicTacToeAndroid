package com.example.android.recyclerview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ExampleItem> examplelist;

    private Button btnInsert;
    private Button btnDelete;
    private EditText etInsert;
    private EditText etDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createExampleList();
        createRecyclerView();
        setButtons();
    }

    public void InsertTo(int position){
        examplelist.add(position,new ExampleItem(R.drawable.ic_sentiment,"This is new","At position:- "+position));
        adapter.notifyItemInserted(position);

    }
    public void DeleteFrom(int position){
        examplelist.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void changeItem(int position,String text){
        examplelist.get(position).changeText1(text);
        adapter.notifyItemChanged(position);
    }

    public void createExampleList() {
        examplelist=new ArrayList<>();
        examplelist.add(new ExampleItem(R.drawable.ic_android,"Line 1","Line 2"));
        examplelist.add(new ExampleItem(R.drawable.ic_assistant,"Line 1","Line 2"));
        examplelist.add(new ExampleItem(R.drawable.ic_toys,"Line 1","Line 2"));

    }

    public void createRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MAdapter((ArrayList<ExampleItem>) examplelist);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                changeItem(position,"You just Clicked it!");
            }

            @Override
            public void onDeleteClicked(int position) {
                DeleteFrom(position);
            }
        });
    }

    public void setButtons(){
        btnInsert=(Button)findViewById(R.id.btnInsert);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        etInsert=(EditText) findViewById(R.id.etInsert);
        etDelete=(EditText) findViewById(R.id.etDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(etInsert.getText().toString().trim());
                InsertTo(position);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(etDelete.getText().toString().trim());
                DeleteFrom(position);
            }
        });
    }
}
