package com.example.aydendemoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    //variables
    LoadingRecords loadObj = new LoadingRecords(this);
    ImageView image;
    TextView text;
    TextView date;
    RecordKeeper Recordlist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recordlist = loadObj.LoadRecord();
        setContentView(R.layout.activity_detail);
         image = findViewById(R.id.ImageID);
        Bundle info = getIntent().getExtras();
        int id = info.getInt("id");
        Record temp = Recordlist.getRecord(id);
        image.setImageURI(Uri.parse(temp.getFileURI()));
        text = findViewById(R.id.DetailsInput);
        text.setText(temp.getDescription());
    }
}