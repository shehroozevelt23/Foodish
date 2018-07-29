package com.example.shehrooz.foodish.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shehrooz.foodish.R;
import com.example.shehrooz.foodish.adapters.HorizontalAdapter;
import com.example.shehrooz.foodish.adapters.VerticalAdapter;
import com.example.shehrooz.foodish.model.Food;
import com.example.shehrooz.foodish.model.GeneralFood;
import com.example.shehrooz.foodish.rest.RetrofitClient;
import com.example.shehrooz.foodish.rest.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHorizontal;
    RecyclerView recyclerViewVertical;
    public static TextView tv;
    public static List<GeneralFood> cartFoods = new ArrayList<>();
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Foodish");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        cartUpdate();

        recyclerViewHorizontal = findViewById(R.id.horizontal_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHorizontal.setLayoutManager(linearLayoutManager);

        recyclerViewVertical = findViewById(R.id.vertical_recyclerview);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);

        RetrofitInterface retrofitService = RetrofitClient.getClient().create(RetrofitInterface.class);

        Call<Food> call = retrofitService.getFoods();
        call.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                List<GeneralFood> popularFoods = response.body().getPopularFood();
                recyclerViewHorizontal.setAdapter(new HorizontalAdapter(popularFoods, R.layout.recyclerview_horizontal, MainActivity.this));

                List<GeneralFood> regularFoods = response.body().getRegularFood();
                recyclerViewVertical.setNestedScrollingEnabled(false);
                recyclerViewVertical.setAdapter(new VerticalAdapter(regularFoods, R.layout.recyclerview_vertical, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        cartUpdate();
        MenuItem item = menu.findItem(R.id.action_addcart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
        tv = notifCount.findViewById(R.id.hotlist_hot);
        View view = notifCount.findViewById(R.id.hotlist_bell);

        cartUpdate();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(myIntent);


        }});

        return true;
    }

    public static void cartUpdate() {
        if (tv != null && cartFoods != null)
            tv.setText(Integer.toString(cartFoods.size()));
    }

    @Override
    protected void onResume() {
        invalidateOptionsMenu();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

