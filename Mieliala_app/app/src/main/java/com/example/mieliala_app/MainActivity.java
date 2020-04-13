package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import com.divyanshu.colorseekbar.ColorSeekBar;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText editFeel;
    ColorSeekBar seekFeel;
    String hex;
    public static final String editKey = "editKey";
    public static final String seekKey = "seekKey";
    //väliaikainen intent id
    int ADD_NEW_PART_INTENT_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekFeel = findViewById(R.id.seekFeeling);
        editFeel = findViewById(R.id.editFeel);



        seekFeel.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int color) {
                hex = String.format("#%06X", (0xFFFFFF & color));
            }
        });

    }


    private void goToIntent()
    {
        //siirrytään CalendarActivity -näkymään
        Intent calendarActivityIntent = new Intent(this, CalendarActivity.class);
        startActivityForResult(calendarActivityIntent, ADD_NEW_PART_INTENT_ID);
    }

    public void buttonNext(View v)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String c = editFeel.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(editKey, c);
        editor.putString(seekKey, hex);
        editor.apply();
        goToIntent();
    }

    public void buttonSkip(View v)
    {
        goToIntent();
    }
}
