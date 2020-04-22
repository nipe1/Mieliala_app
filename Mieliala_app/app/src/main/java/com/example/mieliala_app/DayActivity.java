package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.Cursor;
import java.util.ArrayList;


public class DayActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    String id;
    String date;
    String color;
    int moodInteger;
    String dateVal; //dd/MM/yyyy
    String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        myDb = new DatabaseHelper(this);
        dateVal = "dd/MM/yyyy"; //asetetaan arvoksi kalenterista valitun päivän päivämäärä
        Intent myIntent = getIntent();
        dateVal = myIntent.getExtras().getString("choosedDay");

        TextView dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(dateVal);
        TextView noteTextView = findViewById(R.id.noteTextView);


        Cursor res = myDb.getData(dateVal);
        if(res.getCount() == 0) {
            noteTextView.setText("No information for today");
            return;
        }
        setMood();

        ArrayList<String> buffer = new ArrayList<String>();
        while (res.moveToNext()) {
            buffer.add(res.getString(0));
            buffer.add(res.getString(1));
            buffer.add(res.getString(2));
            buffer.add(res.getString(3));
            buffer.add(res.getString(4));
        }

        id = buffer.get(0);
        date = buffer.get(1);
        notes = buffer.get(2);
        color = buffer.get(3);
        moodInteger = Integer.parseInt(buffer.get(4));
        if(res.getCount() > 1) {
            for (int i=1; i < res.getCount(); i++){
                notes = notes + "\n" + buffer.get(i*5+2);
                moodInteger = moodInteger + Integer.parseInt(buffer.get(i*5+4));
            }
            moodInteger = moodInteger / res.getCount();

        }

        noteTextView.setText(notes);
        setMood();
    }

    public void setMood() {
        //päivän aikana annetut moodit/mielialat lisätään int listaan (esim. 1 = huono, 2 = menettelee, 3 = hyvä)

        TextView myMoodTextView = findViewById(R.id.myMoodTextView);
        ImageView moodImageView = findViewById(R.id.dayMoodImageView);

        if (moodInteger < 20) {
            //asetetaan dayMoodImageViewille surunaama
            myMoodTextView.setText("Very Bad");
            moodImageView.setImageResource(R.drawable.face1);
        } else if (moodInteger >= 20 && moodInteger < 40) {
            myMoodTextView.setText("Bad");
            moodImageView.setImageResource(R.drawable.face2);
        } else if (moodInteger >= 40 && moodInteger < 60) {
            myMoodTextView.setText("Neutral");
            moodImageView.setImageResource(R.drawable.face3);
        } else if (moodInteger >= 60 && moodInteger < 80) {
            myMoodTextView.setText("Good");
            moodImageView.setImageResource(R.drawable.face4);
        } else if (moodInteger >= 80) {
            myMoodTextView.setText("Excellent");
            moodImageView.setImageResource(R.drawable.face5);
        }
    }
}
