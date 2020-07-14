package com.example.aydendemoandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecordAdapter extends RecyclerView.Adapter<RecordHolder> {
    Context context;
    ArrayList<Record> arrayList;
    public RecordAdapter(Context c, ArrayList<Record> arrayList){
        this.arrayList = arrayList;
        context =c;
    }
    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = arrayList.get(position).getDateTime();

        holder.DateTime.setText(df.format(date));
        final int id = arrayList.get(position).getRecordId();
        holder.RecordID.setText(Integer.toString(id));
        //setting image
        holder.Picture.setImageURI(Uri.parse(arrayList.get(position).getFileURI()));

        holder.setRecordListener(new RecordClickListener() {
            @Override
            public void onRecordClickListener(View v, int position) {
                int id = arrayList.get(position).getRecordId();
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
