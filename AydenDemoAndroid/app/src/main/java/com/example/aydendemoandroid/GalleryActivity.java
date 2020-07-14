package com.example.aydendemoandroid;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecordAdapter recordAdapter;
    private LoadingRecords loadObj = new LoadingRecords(this);
    private RecordKeeper recordList;
    protected void onCreate(Bundle savedInstanceState) {
        recordList = loadObj.LoadRecord();
        RecordKeeper temp = recordList;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerylayout);
        //need to set the event listener for the buttons.
        recyclerView = findViewById(R.id.ListID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordAdapter = new RecordAdapter(this,recordList.toArrayList());
        recyclerView.setAdapter(recordAdapter);
    }

    @Override
    public void onClick(View view){

    }
}
