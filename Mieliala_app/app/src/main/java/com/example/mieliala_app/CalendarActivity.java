package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;


public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    String curDate;
    int ADD_NEW_PART_INTENT_ID = 2345;

    String dayString;
    String monthString;
    String yearString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //String editFeel = sharedPreferences.getString("editKey", "");
        //int seekFeel = sharedPreferences.getInt("seekKey", 0);
        //String dateFeel = sharedPreferences.getString("dateKey", "");
        //String colorFeel = sharedPreferences.getString("colorKey", "");
        //String testval = editFeel + " " + String.valueOf(seekFeel) + " " + dateFeel+ " " + colorFeel;
        //TextView test = findViewById(R.id.textTest);

        //test.setText(testval);

        calendarView = findViewById(R.id.calendarViewId);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;//koska tammikuu = 0, helmikuu = 1 jne.

                //valittu päivämäärä verrattavaan muotoon muiden päivämäärien kanssa
                if(dayOfMonth < 10) {
                    dayString = Integer.toString(dayOfMonth);
                    dayString = "0" + dayString;
                }

                if (dayOfMonth > 9) {
                    dayString = Integer.toString(dayOfMonth);
                }
                if (month < 10) {
                    monthString = Integer.toString(month);
                    monthString = "0" + monthString;
                }

                if (month > 9) {
                    monthString = Integer.toString(month);
                }
                yearString = Integer.toString(year);

                //curDate = String.valueOf(dayOfMonth + "/" + month + "/" + year); //klikattavan päivän päivämäärä
                curDate = dayString + "/" + monthString + "/" + yearString;
                goToIntent();
            }
        });

    }

    private void goToIntent()
    {
        //siirrytään dayActivity -näkymään
        Intent dayActivityIntent = new Intent(this, DayActivity.class);
        dayActivityIntent.putExtra("choosedDay", curDate);
        startActivityForResult(dayActivityIntent, ADD_NEW_PART_INTENT_ID);

    }

    public void onClickDiagram(View view) {
        Intent intent = new Intent(this, Chart.class);
        startActivity(intent);
    }
}
/*
        String testval = editFeel + " " + String.valueOf(seekFeel) + " " + dateFeel+ " " + colorFeel;


        test.setText(testval);
        TextView test = findViewById(R.id.textTest);

   <TextView
        android:id="@+id/textTest"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="30sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
*/