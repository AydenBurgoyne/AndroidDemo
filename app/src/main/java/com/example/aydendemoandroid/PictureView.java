package com.example.aydendemoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PictureView extends AppCompatActivity implements View.OnClickListener {
    Button SubmitBTN,RetakeBTN;
    Uri pictureURI;
    Date date = new Date();
    String picture;
    TextView text;
    LoadingRecords loadObj = new LoadingRecords(this);
    RecordKeeper Recordlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);
        SubmitBTN = findViewById(R.id.SubmitBTN);
        text = findViewById(R.id.Heading);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Recordlist = loadObj.LoadRecord();
        text.setText(df.format(date));
        RetakeBTN = findViewById(R.id.RetakeBTN);
        SubmitBTN.setOnClickListener(this);
        RetakeBTN.setOnClickListener(this);
        Bundle values = getIntent().getExtras();
        picture = values.getString("Pic");
        ImageView view = findViewById(R.id.ImageCaptured);
        pictureURI = Uri.parse(picture);
        view.setImageURI(pictureURI);
    }

    @Override
    public void onClick(View view) {
        Recordlist = loadObj.LoadRecord();
        if(view.getId()==R.id.SubmitBTN) {
                //creating a new record with the info then saves to the Recordlist and saved to file.
                Record newRecord = new Record();
                newRecord.setDateTime(date);
                newRecord.setFileURI(picture);
                EditText details = findViewById(R.id.Description);
                String DetailsString = details.getText().toString();
                newRecord.setDescription(DetailsString);
                Recordlist.addRecord(newRecord);
                loadObj.SaveRecord(Recordlist);
                //Sending back to the main menu after photo is submitted.
                Intent intent = new Intent(PictureView.this, MainActivity.class);
                startActivity(intent);
            }
            else if(view.getId()==R.id.RetakeBTN){
                //Takes back to the previous screen for retaking the photo.
                Intent intent1 = new Intent(PictureView.this,CameraActivity.class);
                startActivity(intent1);
        }
        }
    @Override
    protected void onResume() {
        super.onResume();
        Recordlist = loadObj.LoadRecord();
    }
}
