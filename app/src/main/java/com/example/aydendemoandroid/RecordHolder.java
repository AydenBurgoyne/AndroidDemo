package com.example.aydendemoandroid;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView Picture;
    TextView DateTime,RecordID;
    RecordClickListener RecordListener;

    public RecordHolder(View RecordCard){
        super(RecordCard);
        this.Picture = RecordCard.findViewById(R.id.photoid);
        this.DateTime = RecordCard.findViewById(R.id.DateView);
        this.RecordID = RecordCard.findViewById(R.id.RecordID);
        RecordCard.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        this.RecordListener.onRecordClickListener(view,getLayoutPosition());
    }

    public void setRecordListener(RecordClickListener rl){
        this.RecordListener = rl;
    }
}
