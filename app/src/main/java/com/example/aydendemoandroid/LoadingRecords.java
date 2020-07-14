package com.example.aydendemoandroid;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoadingRecords {
    //loads records from file
    Context context;

    public LoadingRecords(Context context) {
        this.context = context;
    }
    public RecordKeeper LoadRecord(){
        RecordKeeper temp = null;
        File inputFile = new File(context.getFilesDir(), "Records");
        if(inputFile.length()!=0) {
            try {
                FileInputStream fileInput = new FileInputStream(inputFile);
                ObjectInputStream objInput = new ObjectInputStream(fileInput);
                temp = (RecordKeeper) objInput.readObject();
                if(temp==null){
                    temp= new RecordKeeper();
                    SaveRecord(temp);

                }
                return temp;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            temp = new RecordKeeper();
            SaveRecord(temp);
        }
        return temp;
        //if null then
    }
    public void SaveRecord(RecordKeeper recordkeeper){
        File inputFile = new File(context.getFilesDir(), "Records"); //initialising file to the path of the records file.
        inputFile.delete();

        FileOutputStream fileOutput = null;
        ObjectOutputStream objOutput = null;
        try{
            fileOutput = new FileOutputStream(inputFile); //initialising fileOutput with the file defined above.
            objOutput = new ObjectOutputStream(fileOutput); //initialising objoutput stream
            objOutput.writeObject(recordkeeper); //writes object to the file
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
