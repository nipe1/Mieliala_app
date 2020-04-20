package com.example.mieliala_app;
import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import androidx.appcompat.app.AppCompatActivity;

public class ChartActivity extends AppCompatActivity {
    int[] colorClassArray = new int []{Color.RED, Color.GREEN, Color.YELLOW};
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(colorClassArray);

        pieChart.setData(pieData);

        pieChart.animateY(200);

        pieChart.setTouchEnabled(false);

    }

    public void AddValuesToPIEENTRY(){ //Vaihda diagrammin arvoja

        entries.add(new BarEntry(13f, 0)); //Huonoja paivia
        entries.add(new BarEntry(12f, 2)); //Hyvia paivia
        entries.add(new BarEntry(4f, 1)); // Neutraaleja paivia

    }

    public void AddValuesToPieEntryLabels(){ //Diagrammin arvojen nimet

        PieEntryLabels.add("Bad");
        PieEntryLabels.add("Good");
        PieEntryLabels.add("Neutral");


    }
}
