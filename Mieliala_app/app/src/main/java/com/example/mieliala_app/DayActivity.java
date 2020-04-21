package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    String id;
    String date;


    String color;
    int averageMood;
    int moodInteger;
    String dateVal; //dd/MM/yyyy
    String notes;
    String dateKeyToString;

    SharedPreferences sharedPreferences;

    List<Integer> dayMoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        myDb = new DatabaseHelper(this);
        dateVal = "dd/MM/yyyy"; //asetetaan arvoksi kalenterista valitun päivän päivämäärä
        Intent myIntent = getIntent();
        dateVal = myIntent.getExtras().getString("choosedDay");

        TextView dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(date);
        TextView noteTextView = findViewById(R.id.noteTextView);

        //storedNoteList = new ArrayList<>();

        /*try { // OHJELMA KAATUU TÄMÄN KOHDALLA
            storedNoteList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(editKey,ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //noteTextView.setText(storedNoteList.get(0));

        //setMood();


        //getAverageMood();








        Cursor res = myDb.getData();
        if(res.getCount() == 0) {
            // nothing
            return;
        }

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

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //dateKeyToString = sharedPreferences.getString(dateKey, "");
        //dateTextView.setText("i"+date + " " + dateKeyToString+"i");
        //if(date.equals(dateTextView)) {
        //    notes = sharedPreferences.getString(editKey, "");
        //    noteTextView.setText(notes);
        //}
        noteTextView.setText(id+" "+date+" "+notes+" "+color+" "+String.valueOf(moodInteger));
        setMood();
    }


    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void getAverageMood()
    {
        //Lasketaan päivän mielialoista keskiarvo
        int total = 0;
        for(int i = 0; i < dayMoodList.size(); i++)
        {
            total += dayMoodList.get(i);
            averageMood = total / dayMoodList.size();
        }
        averageMood = 3; //väliaikainen arvo
    }

    public void setMood() {
        //päivän aikana annetut moodit/mielialat lisätään int listaan (esim. 1 = huono, 2 = menettelee, 3 = hyvä)

        if(dateVal.equals(date))
        {
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

}
