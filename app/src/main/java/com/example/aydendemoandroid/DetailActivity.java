package com.example.aydendemoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    //variables
    LoadingRecords loadObj = new LoadingRecords(this);
    ImageView image;
    Button delete;
    TextView text;
    TextView Heading;
    TextView date;
    int recordID;
    RecordKeeper Recordlist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recordlist = loadObj.LoadRecord();
        setContentView(R.layout.activity_detail);
         image = findViewById(R.id.ImageID);
        Bundle info = getIntent().getExtras();
        int id = info.getInt("id");
        recordID = id;
        Record temp = Recordlist.getRecord(id);
        image.setImageURI(Uri.parse(temp.getFileURI()));
        Heading = findViewById(R.id.ViewHeading);
        Heading.setText(temp.getHeading());
        Heading.setTextSize(30);
        text = findViewById(R.id.DescriptionID);
        text.setText(temp.getDescription());
        delete = findViewById(R.id.DeleteButton);
        delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.DeleteButton){
            Recordlist.deleteRecord(recordID);
            loadObj.SaveRecord(Recordlist);
            Intent intent = new Intent(DetailActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}