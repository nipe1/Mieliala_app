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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mieliala_app.MainActivity.dateKey;
import static com.example.mieliala_app.MainActivity.editKey;
import static com.example.mieliala_app.MainActivity.seekKey;

public class DayActivity extends AppCompatActivity {

    int averageMood;
    int moodInteger;
    String date; //dd/MM/yyyy
    String notes;
    String dateKeyToString;
    //String editKey = MainActivity.editKey;
    //final public static String editKey = "editKey";
    //final public static String seekKey = "seekKey";
    ArrayList<String> storedNoteList;

    SharedPreferences sharedPreferences;

    List<Integer> dayMoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        date = "dd/MM/yyyy"; //asetetaan arvoksi kalenterista valitun päivän päivämäärä
        Intent myIntent = getIntent();
        date = myIntent.getExtras().getString("choosedDay");

        TextView dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(date);
        TextView noteTextView = findViewById(R.id.noteTextView);

        storedNoteList = new ArrayList<>();

        /*try { // OHJELMA KAATUU TÄMÄN KOHDALLA
            storedNoteList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(editKey,ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //noteTextView.setText(storedNoteList.get(0));

        //setMood();


        //getAverageMood();
        //setMood();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dateKeyToString = sharedPreferences.getString(dateKey, "");
        //dateTextView.setText("i"+date + " " + dateKeyToString+"i");
        if(date.equals(dateKeyToString)) {
            notes = sharedPreferences.getString(editKey, "");
            noteTextView.setText(notes);
        }



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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dateKeyToString = sharedPreferences.getString(dateKey, "");

        if(date.equals(dateKeyToString))
        {
            moodInteger = sharedPreferences.getInt(seekKey, 0b1);

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
