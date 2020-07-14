package com.example.aydendemoandroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class RecordKeeper implements Serializable {
    //this class is basically a linkedlist that holds the records. However it includes methods that relate to the linkedlist
    //variables
    private int IDCount;
    private LinkedList<Record> recordlist = new LinkedList<>();

    public RecordKeeper() {
        IDCount = 0;
    }

    public void addRecord(Record record){
        record.setRecordId(IDCount);
        recordlist.add(record);
        IDCount++;
    }
    //Gets by it's position in the list.
    public Record getRecord(int id){
        Iterator<Record> listIterator = recordlist.iterator();
        while(listIterator.hasNext()){
           Record temp = listIterator.next();
           if(temp.getRecordId()==id){
               return temp; //returns the record that matches the search
           }
        }
        return null;//if there are no records found.
    }
    public ArrayList<Record> toArrayList(){
        return new ArrayList<Record>(recordlist);
    }
}
