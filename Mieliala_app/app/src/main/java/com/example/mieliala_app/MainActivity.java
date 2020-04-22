package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import com.google.gson.Gson;
import android.database.Cursor;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editFeel;
    SeekBar seekFeel;
    String todayString;
    String colorFeel;
    //väliaikainen intent id
    int ADD_NEW_PART_INTENT_ID = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekFeel = findViewById(R.id.seekFeeling);
        editFeel = findViewById(R.id.editFeel);
        myDb = new DatabaseHelper(this);

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        todayString = formatter.format(todayDate);
    }


    private void goToIntent()
    {
        //siirrytään CalendarActivity -näkymään
        Intent calendarActivityIntent = new Intent(this, CalendarActivity.class);
        startActivityForResult(calendarActivityIntent, ADD_NEW_PART_INTENT_ID);
    }

    public void buttonNext(View v) throws IOException {
        colorPicker();
        String c = editFeel.getText().toString();
<<<<<<< HEAD
        int n = seekFeel.getProgress();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(editKey, c);
        editor.putInt(seekKey, n);
        editor.putString(dateKey, todayString);
        editor.putString(colorKey, colorFeel);

        /*noteList.add(c);
        sharedPreferences.edit().putString(editKey,ObjectSerializer.serialize(noteList)).apply();
        //seekList.add(n);
        //sharedPreferences.edit().putInt(seekKey,ObjectSerializer.serializeInt(seekList)).apply();
        dateList.add(todayString);
        sharedPreferences.edit().putString(dateKey, ObjectSerializer.serialize(dateList)).apply();
        colorList.add(colorFeel);
        sharedPreferences.edit().putString(colorKey, ObjectSerializer.serialize(colorList)).apply();
*/

        editor.apply();
=======
        String n = String.valueOf(seekFeel.getProgress());

        boolean isInserted = myDb.insertData(todayString, c, colorFeel, n);
        if(isInserted == true) {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        }

>>>>>>> master
        goToIntent();
    }

    public void buttonSkip(View v)
    {
        goToIntent();
    }

    public void cleadDB(View v)
    {
        myDb.deleteData();
    }


    public void colorPicker()
    {
        int progress = seekFeel.getProgress();

        if (progress < 20)
        {
            colorFeel = "#534666";
        }
        else if (progress >= 20 && progress < 40)
        {
            colorFeel = "#A4666E";
        }
        else if (progress >= 40 && progress < 60)
        {
            colorFeel = "#CD7672";
        }
        else if (progress >= 60 && progress < 80)
        {
            colorFeel = "#D88B6E";
        }
        else if (progress >= 80)
        {
            colorFeel = "#EEB462";
        }
    }


}