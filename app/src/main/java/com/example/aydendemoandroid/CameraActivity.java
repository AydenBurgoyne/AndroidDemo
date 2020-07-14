package com.example.aydendemoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.net.Uri;
import android.provider.MediaStore.Files.FileColumns;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.hardware.Camera;
import android.hardware.Camera.*;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{
    Camera obj;
    Button capturebtn;
    int Request_Code = 1;
    CameraPreview previewObj;
    //creating callback variable.
    PictureCallback callback = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = SavingMedia.getOutputMediaFile(1,getApplicationContext());
            if (pictureFile == null){
                Log.d("message", "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d("message", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("message", "Error accessing file: " + e.getMessage());
            }
            camera.startPreview();
            for(int i = 0;i<getApplicationContext().fileList().length;i++){
                Log.d("Tag",getApplicationContext().fileList()[i]);
            }
            //sending picture to recordconfirmation page
            Intent intent = new Intent(CameraActivity.this,PictureView.class);
            intent.putExtra("Pic", Uri.fromFile(pictureFile).toString());
            startActivity(intent);

        }
    };
//need to change to surface view for preview
    public void openCamera(){
        if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            SettingView();
        }
        else{
            requestPermissions(new String[]{Manifest.permission.CAMERA},Request_Code);
        }
    }
    public void SettingView(){
        obj = getCameraInstance();

        obj.setDisplayOrientation(90);

        CameraPreview previewObj = new CameraPreview(this,obj);
        FrameLayout Viewport = findViewById(R.id.PreviewViewPort);
        Viewport.addView(previewObj);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camerapreview);
         capturebtn = findViewById(R.id.CaptureBTN);
         capturebtn.setOnClickListener(this);
        ConstraintLayout mainLayout = (ConstraintLayout)findViewById(R.id.cameraPreview);
        mainLayout.setBackgroundColor(Color.RED);
        openCamera();
        //need to set the event listener for the buttons.

    }

    public void onStart() {

        super.onStart();

    }
    public Camera getCameraInstance(){
        Camera cameraOBJ = null;
        try{
            cameraOBJ = Camera.open();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return cameraOBJ;
    }
@Override
    protected void onStop() {

    super.onStop();
}
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.CaptureBTN){
            obj.takePicture(null,null,callback);
            Log.d("tag","pic");
        }
    }

    public void onResume() {
        super.onResume();
    }



    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == Request_Code){ //aka it's the response from asking for the camera permission.
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {                 //it's because camera is the only permission and hence it's number 0. If we were to have multiple probably best to check the position with the String array
                openCamera();
            }
            else{
                Toast.makeText(this,"camera permission wasn't granted",Toast.LENGTH_LONG).show();
            }
        }

    }
}