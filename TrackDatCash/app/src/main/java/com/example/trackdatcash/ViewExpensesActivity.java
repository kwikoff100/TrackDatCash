package com.example.trackdatcash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewExpensesActivity extends AppCompatActivity {
    private static final String TAG = "VPE";
    public static String longString;
    TableLayout tableLayout;
    ArrayList<expenseDataObject> expenseObjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        Button btnRtoMMfVE = (Button) findViewById(R.id.btnRtoMMfVE);
        Button filter = (Button) findViewById(R.id.btnFilterExpenses);

        //Call table create function upon activity create
        initViews();

        btnRtoMMfVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(ViewExpensesActivity.this, MainMenuActivity.class);
                ViewExpensesActivity.this.startActivity(todoIntent);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //Either send to a new page with filtering options
                //OR
                //A drop down with options (also requires a Click Listener)

                //Intent todoIntent = new Intent(ViewExpensesActivity.this, MainMenuActivity.class);
                //ViewExpensesActivity.this.startActivity(todoIntent);
            }
        });
    }

    public void initViews()
    {
        //Initialize using space designated on app xml file
        tableLayout = (TableLayout) findViewById(R.id.chart_layout);

        //Clear any previous data
        if (tableLayout!=null)
            tableLayout.removeAllViews();

//Grab data here normally
        //Go transform the data from JSON to an array list
        //fetchedDataToArray();

        //For testing purposes
        createTestData();

        //Once array list is populated, create table
        addRows();
    }

    /*


     */
    public void createTestData()
    {
        expenseObjs = new ArrayList<>();
        for (int i = 1; i < 50; i ++)
        {
            expenseDataObject test = new expenseDataObject();
            test.setDescription("He was number " + Integer.toString(i));
            test.setAmount(Integer.toString(i));
            test.setYear("1999");
            test.setMonth("May");
            test.setDay(Integer.toString(i));
            test.setCategory("Entertainment");
            expenseObjs.add(test);
        }

    }

    public void addRows()
    {
        //Create header first
        TableRow header = new TableRow(ViewExpensesActivity.this);
        header.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        //Add headers using formatting function
        header.addView(getRowsTextView(0, "Date", Color.BLACK, Typeface.BOLD, R.layout.cell_shape_dark));
        header.addView(getRowsTextView(0, "Description", Color.BLACK, Typeface.BOLD, R.layout.cell_shape_dark));
        header.addView(getRowsTextView(0, "Amount", Color.BLACK, Typeface.BOLD, R.layout.cell_shape_dark));
        header.addView(getRowsTextView(0, "Category", Color.BLACK, Typeface.BOLD, R.layout.cell_shape_dark));

        //Add the header row to the table
        tableLayout.addView(header, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < expenseObjs.size(); i ++)
        {
            //Begin row setup
            TableRow row = new TableRow(ViewExpensesActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            //Grab current objects month/day/year and format to string
            String day = expenseObjs.get(i).getDay();
            String month = expenseObjs.get(i).getMonth();
            String year = expenseObjs.get(i).getYear();
            String date;
            if (day.equals(""))
            {
                date = month + " " + year;
            }
            else
            {
                date = month + " " + day + ", " + year;
            }

            //Add the data to the row - alternating between row colours
            if ((i % 2) == 0 && i != 1)
            {
                row.addView(getRowsTextView(0, date, Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_light));
                row.addView(getRowsTextView(0, expenseObjs.get(i).getDescription(), Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_light));
                row.addView(getRowsTextView(0, expenseObjs.get(i).getAmount(), Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_light));
                row.addView(getRowsTextView(0, expenseObjs.get(i).getCategory(), Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_light));
            }
            else
            {
                row.addView(getRowsTextView(0, date, Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_dark));
                row.addView(getRowsTextView(0, expenseObjs.get(i).getDescription(), Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_dark));
                row.addView(getRowsTextView(0, expenseObjs.get(i).getAmount(), Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_dark));
                row.addView(getRowsTextView(0, expenseObjs.get(i).getCategory(), Color.BLACK, Typeface.NORMAL, R.layout.cell_shape_dark));
            }

            //Add to table
            tableLayout.addView(row, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    private TextView getRowsTextView(int id, String title, int color, int typeface, int bgColor)
    {
        //Formatting for row cells
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
        tv.setPadding(10, 10, 10, 10);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        return tv;
    }


}
