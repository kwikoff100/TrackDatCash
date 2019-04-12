package com.example.trackdatcash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BasicPieActivity extends AppCompatActivity {

    private static String TAG = "BasicPieActivity";
    private int[] tempVals = {22, 33, 44, 55};
    private String[] catArray = {"Food", "Bills", "Entertainment", "Other/Misc."};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_pie);
        Log.d(TAG, "OnCreate");

        Button btnRtoMMfVP = (Button) findViewById(R.id.btnRtoMMfVP);
        Button btnFilterPie = (Button) findViewById(R.id.btnFilterPie);

        pieChart = (PieChart) findViewById(R.id.chPieChart);

        //Add things to the pie chart
        //pieChart.setDescription("Total spent per category");

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Total Spending");
        pieChart.setCenterTextSize(10);
        pieChart.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        pieChart.getDescription().setText("Spending per Category");

        //pieChart.setCenterTextTypeface(typeface);
        pieChart.setDrawEntryLabels(true);

        //Pull the url from the activity change intent
        Bundle bundle = getIntent().getExtras();
        String urlToUse = bundle.getString("url");


        if (urlToUse.equals("NoChange"))
        {
            //No change to the table
        }
        else
        {
            //Create the pie chart with the new data
            addDataSet(pieChart);
        }



        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                //Find the selected slice and output the found value to screen using Toast

                //Log.e(TAG, e.toString());
                //Log.e(TAG, h.toString());
                int pos1 = h.toString().indexOf("x: ");
                int pos2 = h.toString().indexOf(".",pos1);
                String cat = h.toString().substring(pos1+3, pos2);
                String catType = catArray[Integer.parseInt(cat)];
                Integer val = tempVals[Integer.parseInt(cat)];

                Toast.makeText(BasicPieActivity.this, catType+"\nTotal $"+val.toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });

        //Return to the Main Menu
        btnRtoMMfVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(BasicPieActivity.this, MainMenuActivity.class);
                BasicPieActivity.this.startActivity(todoIntent);
            }
        });

        //Go to the filter activity to choose what data goes in the pie chart
        btnFilterPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(BasicPieActivity.this, PieFilterActivity.class);
                BasicPieActivity.this.startActivity(todoIntent);
            }
        });
    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> totals = new ArrayList<>();
        ArrayList<String> catNames = new ArrayList<>();

        //Add the values to the arrays used in data set
        for (int i = 0; i<tempVals.length; i++)
        {
            totals.add(new PieEntry(tempVals[i], i));
            catNames.add(catArray[i]);
        }

        //Data set creation
        PieDataSet pieDataSet = new PieDataSet(totals, "Categories");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        //Adding colors
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.colorPie1));
        colors.add(getResources().getColor(R.color.colorPie2));
        colors.add(getResources().getColor(R.color.colorPie3));
        colors.add(getResources().getColor(R.color.colorPie4));

        pieDataSet.setColors(colors);

        //Create a legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setTextColor(getResources().getColor(R.color.colorPie5));

        LegendEntry[] legendEntry = new LegendEntry[tempVals.length];

        for (int i = 0; i<tempVals.length; i++)
        {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors.get(i);
            entry.label = String.valueOf(catArray[i]);
            legendEntry[i] = entry;
        }

        legend.setCustom(legendEntry);

        //Create the object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
