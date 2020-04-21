package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;


public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView calendarView;
    String curDate;
    int ADD_NEW_PART_INTENT_ID = 2345;

    String dayString;
    String monthString;
    String yearString;
    String editFeel;
    String dateFeel;
    int colorFeel;
    int seekFeel;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    Date todayDate;
    Date date1;
    Date date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        String testval = getPreferences();
        TextView test = findViewById(R.id.textTest);
        calendarView = findViewById(R.id.calendarViewId);

        test.setText(testval);
        calendarUpdate();
        try {
            date1 = format.parse("20/04/2020");
            date2 = format.parse("19/04/2020");
            todayDate = format.parse(dateFeel);
        } catch(ParseException e) {
            e.printStackTrace();
        }

        addEvent(Color.RED, date1, "jees");
        addEvent(colorFeel, date2, "jees");
        addEvent(colorFeel, todayDate, editFeel);

    }

    private String getPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editFeel = sharedPreferences.getString("editKey", "");
        seekFeel = sharedPreferences.getInt("seekKey", 0);
        dateFeel = sharedPreferences.getString("dateKey", "");
        colorFeel = sharedPreferences.getInt("colorKey", 0);
        String testval = editFeel + " " + String.valueOf(seekFeel) + " " + dateFeel+ " " + String.valueOf(colorFeel);

        return testval;
    }

    private void calendarUpdate(){

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = calendarView.getEvents(dateClicked);
                curDate = format.format(dateClicked);
                goToIntent();
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
    }

    private void addEvent(int color, Date todayDate, String comment){
        Event e = new Event(color, todayDate.getTime(), comment);
        calendarView.addEvent(e);
    }

    private void goToIntent()
    {
        //siirrytään dayActivity -näkymään
        Intent dayActivityIntent = new Intent(this, DayActivity.class);
        dayActivityIntent.putExtra("choosedDay", curDate);
        startActivityForResult(dayActivityIntent, ADD_NEW_PART_INTENT_ID);

    }

}
