package com.example.shehrooz.foodish.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shehrooz.foodish.R;
import com.example.shehrooz.foodish.model.GeneralFood;
import com.squareup.picasso.Picasso;

import static com.example.shehrooz.foodish.activity.MainActivity.cartFoods;
import static com.example.shehrooz.foodish.activity.MainActivity.cartUpdate;
import static com.example.shehrooz.foodish.activity.MainActivity.tv;


public class Details extends AppCompatActivity {

    GeneralFood mFood = new GeneralFood();

    TextView foodTitle, foodPrice, foodCalories, foodDescription, foodFat, foodSodium, foodProtein;
    ImageView foodImage;
    ImageButton foodDetailsPlus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setTitle("Details");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Details.super.onBackPressed();
                tv.setText(Integer.toString(cartFoods.size()));
            }
        });


        foodTitle = findViewById(R.id.food_title);
        foodPrice = findViewById(R.id.food_price);
        foodCalories = findViewById(R.id.food_calories);
        foodDescription = findViewById(R.id.food_description);
        foodFat = findViewById(R.id.food_fat);
        foodSodium = findViewById(R.id.food_sodium);
        foodProtein = findViewById(R.id.food_protein);
        foodImage = findViewById(R.id.food_image);
        foodDetailsPlus = findViewById(R.id.regular_food_plus_details);

        foodTitle.setText(getIntent().getStringExtra("foodTitle"));
        foodPrice.setText(Double.toString(getIntent().getDoubleExtra("foodPrice", 0)));
        foodCalories.setText(Integer.toString(getIntent().getIntExtra("foodCalories", 0)));
        foodDescription.setText(getIntent().getStringExtra("foodDescription"));
        Picasso.get().load(getIntent().getStringExtra("foodImage")).fit().into(foodImage);
        foodFat.setText(Integer.toString(getIntent().getIntExtra("foodFat", 0)));
        foodSodium.setText(Integer.toString(getIntent().getIntExtra("foodSodium", 0)));
        foodProtein.setText(Integer.toString(getIntent().getIntExtra("foodProtein", 0)));

        mFood.setTitle(getIntent().getStringExtra("foodTitle"));
        mFood.setCalory((getIntent().getIntExtra("foodCalories", 0)));
        mFood.setPrice((getIntent().getDoubleExtra("foodPrice", 0)));
        mFood.setDescription((getIntent().getStringExtra("foodDescription")));
        mFood.setImage((getIntent().getStringExtra("foodImage")));
        mFood.setFat((getIntent().getIntExtra("foodFat", 0)));
        mFood.setSodium((getIntent().getIntExtra("foodSodium", 0)));
        mFood.setProtein((getIntent().getIntExtra("foodProtein", 0)));
        mFood.setId(0);
        mFood.setRating(0);

        foodDetailsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFoods.add(mFood);
                tv.setText(Integer.toString(cartFoods.size()));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_addcart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
        View view = notifCount.findViewById(R.id.hotlist_bell);

        tv = notifCount.findViewById(R.id.hotlist_hot);
        cartUpdate();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Details.this, CartActivity.class);
                startActivity(myIntent);


            }});

        return true;
    }


}
