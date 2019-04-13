package com.example.trackdatcash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestingActivity extends AppCompatActivity {

    public static TextView longtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Button startGet = (Button) findViewById(R.id.btnStartsGet);
        longtext = (TextView) findViewById(R.id.longText);



        startGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://trackdatcash.herokuapp.com/expenses/getAllExpenses";
                String userId = "5ca6da956d073a0017df78f6";

                String retVal = ReturnExpense.getAllExpenses(url, userId);

                longtext.setText(retVal);
            }
        });

    }
}
