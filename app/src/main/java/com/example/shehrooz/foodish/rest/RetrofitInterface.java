package com.example.shehrooz.foodish.rest;

import com.example.shehrooz.foodish.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("raw/jiEvzswz")
    abstract public Call<Food> getFoods();
}