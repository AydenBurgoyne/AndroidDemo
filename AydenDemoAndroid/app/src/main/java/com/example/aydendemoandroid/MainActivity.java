package com.example.aydendemoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button Export,Library,Photo;
    LoadingRecords loadObj = new LoadingRecords(this);
    RecordKeeper Recordlist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recordlist = loadObj.LoadRecord();
        RecordKeeper temp = Recordlist;
        setContentView(R.layout.activity_main);
                //need to set the event listener for the buttons.
        Export = findViewById(R.id.ExportBTN);
        Library = findViewById(R.id.LibraryBTN);
        Photo = findViewById(R.id.PhotoBTN);
        Export.setOnClickListener(this);
        Library.setOnClickListener(this);
        Photo.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Recordlist = loadObj.LoadRecord();
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){ //gets id for view captured from event listener.
            case R.id.LibraryBTN :
                Intent intent = new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(intent);
            break;
            case R.id.PhotoBTN :
                Intent intent1 = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent1);
        }
    }

    public void onStop() {

        super.onStop();
        loadObj.SaveRecord(Recordlist);
    }
    public void onPause() {
        super.onPause();
        loadObj.SaveRecord(Recordlist);

    }
    public void onDestroy() {
        super.onDestroy();
        loadObj.SaveRecord(Recordlist);
    }
}