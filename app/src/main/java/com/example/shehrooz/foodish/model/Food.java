package com.example.shehrooz.foodish.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("popular_food_count")
    private int popularFoodCount;
    @SerializedName("regular_food_count")
    private int regularFoodCount;
    @SerializedName("popular_food")
    private List<GeneralFood> popularFood = null;
    @SerializedName("regular_food")
    private List<GeneralFood> regularFood = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPopularFoodCount() {
        return popularFoodCount;
    }

    public void setPopularFoodCount(int popularFoodCount) {
        this.popularFoodCount = popularFoodCount;
    }

    public int getRegularFoodCount() {
        return regularFoodCount;
    }

    public void setRegularFoodCount(int regularFoodCount) {
        this.regularFoodCount = regularFoodCount;
    }

    public List<GeneralFood> getPopularFood() {
        return popularFood;
    }

    public void setPopularFood(List<GeneralFood> popularFood) {
        this.popularFood = popularFood;
    }

    public List<GeneralFood> getRegularFood() {
        return regularFood;
    }

    public void setRegularFood(List<GeneralFood> regularFood) {
        this.regularFood = regularFood;
    }
}