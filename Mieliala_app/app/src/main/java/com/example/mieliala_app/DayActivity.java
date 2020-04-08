package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayActivity extends AppCompatActivity {

    int averageMood;
    String date; //dd/MM/yyyy
    String notes;

    List<Integer> dayMoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        date = "dd/MM/yyyy"; //asetetaan arvoksi kalenterista valitun päivän päivämäärä

        TextView yearTextView = findViewById(R.id.dateTextView);
        yearTextView.setText(date);

        TextView noteTextView = findViewById(R.id.noteTextView);




        //Haetaan kyseisen päivän mielialat ja lisätään ne listDayM
        /*for (int i = 0; i < moodList.size(); i++)
        {
            if(date == moodList(i).date)
            {
                dayMoodList.add(moodList(i).mood);
            }
        }*/

        getAverageMood();
        setMood();

        /*for (int i = 0; i < noteList.size(); i++)
        {
            if(date == noteList(i).date)
            {
                notes = notes + "\n\n" + noteList(i).note;
            }
        }*/

        noteTextView.setText(notes);

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

    public void setMood()
    {
        //päivän aikana annetut moodit/mielialat lisätään int listaan (esim. 1 = huono, 2 = menettelee, 3 = hyvä)
        TextView myMoodTextView = findViewById(R.id.myMoodTextView);

        if(averageMood == 1)
        {
            //asetetaan dayMoodImageViewille surunaama
            myMoodTextView.setText("Bad");
        }

        if(averageMood == 2)
        {
            //asetetaan dayMoodImageViewille neutraali naama
            myMoodTextView.setText("Neutral");
        }

        if(averageMood == 3)
        {
            //asetetaan dayMoodImageViewille hymynaama
            myMoodTextView.setText("Good");
        }
    }

}
