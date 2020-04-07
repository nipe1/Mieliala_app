package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    int averageMood;
    String notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView noteTextView = (TextView) findViewById(R.id.noteTextView);
        TextView myMoodTextView = (TextView) findViewById(R.id.myMoodTextView);

        //päivän aikana annetut moodit/mielialat lisätään int listaan (esim. 1 = huono, 2 = menettelee, 3 = hyvä)

        getAverageMood(/*lista mielialoista*/);



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

        noteTextView.setText("lista päivän aikana annetuista muistiinpanoista");

    }

    public int getAverageMood(/*lista mielialoista*/)
    {
        //tässä funktiossa otetaan päivän aikana annetuista mielialoista keskiarvo ja pyöristetään se kokonaisluvuksi
        averageMood = 3;
        return averageMood;
    }


}
