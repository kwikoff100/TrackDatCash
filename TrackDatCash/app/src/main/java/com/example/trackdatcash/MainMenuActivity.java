package com.example.trackdatcash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnAddExpense = (Button) findViewById(R.id.btnAddExpense);
        Button btnViewPExpenses = (Button) findViewById(R.id.btnViewPExpenses);
        Button btnViewGExpenses = (Button) findViewById(R.id.btnViewGExpenses);
        Button btnViewGraphs = (Button) findViewById(R.id.btnViewGraphs);
        Button btnLogout = (Button) findViewById(R.id.btnLogout);

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(MainMenuActivity.this, AddExpenseActivity.class);
                MainMenuActivity.this.startActivity(todoIntent);
            }
        });

        btnViewPExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(MainMenuActivity.this, ViewExpensesActivity.class);
                MainMenuActivity.this.startActivity(todoIntent);
            }
        });

        btnViewGExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent todoIntent = new Intent(MainMenuActivity.this, AddExpenseActivity.class);
                //MainMenuActivity.this.startActivity(todoIntent);
            }
        });

        btnViewGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent todoIntent = new Intent(MainMenuActivity.this, AddExpenseActivity.class);
                //MainMenuActivity.this.startActivity(todoIntent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent todoIntent = new Intent(MainMenuActivity.this, AddExpenseActivity.class);
                //MainMenuActivity.this.startActivity(todoIntent);
            }
        });
    }
}
