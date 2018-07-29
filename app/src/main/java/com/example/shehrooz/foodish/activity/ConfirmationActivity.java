package com.example.shehrooz.foodish.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shehrooz.foodish.R;


public class ConfirmationActivity extends AppCompatActivity {

    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Foodish");
        mToolbar.setTitleTextColor(0xFFFFFFFF);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmationActivity.super.onBackPressed();
            }
        });


        okButton = findViewById(R.id.ok_button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cartFoods.clear();
                Intent launchNextActivity = new Intent(ConfirmationActivity.this, MainActivity.class);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(launchNextActivity);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_final, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
