package com.example.shehrooz.foodish.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shehrooz.foodish.R;
import com.example.shehrooz.foodish.adapters.CartAdapter;
import com.example.shehrooz.foodish.adapters.VerticalAdapter;
import com.example.shehrooz.foodish.model.GeneralFood;

import java.util.List;

import static com.example.shehrooz.foodish.activity.MainActivity.cartFoods;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerviewCart;
    static TextView cartPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Food Cart");
        mToolbar.setTitleTextColor(0xFFFFFFFF);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.super.onBackPressed();
            }
        });

        cartPrice = findViewById(R.id.cart_price);
        cartPrice.setText(Double.toString(grandTotal(cartFoods)));

        recyclerviewCart = findViewById(R.id.cart_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setNestedScrollingEnabled(false);
        recyclerviewCart.setAdapter(new CartAdapter(cartFoods, R.layout.recyclerview_cart, getApplicationContext()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.order_button){
            Intent intent = new Intent(CartActivity.this, OrderActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public static int grandTotal(List< GeneralFood> cartFoods){

        int totalPrice = 0;
        for(int i = 0 ; i < cartFoods.size(); i++) {
            totalPrice += cartFoods.get(i).getPrice();
        }
        return totalPrice;
    }

    public static void priceAdjust(){
        cartPrice.setText(Double.toString(grandTotal(cartFoods)));
    }

}
