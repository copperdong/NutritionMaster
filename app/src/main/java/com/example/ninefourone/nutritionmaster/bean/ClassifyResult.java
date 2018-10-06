package com.example.ninefourone.nutritionmaster.bean;

import com.example.ninefourone.nutritionmaster.utils.WebUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2018/10/4.
 */

public class ClassifyResult implements Serializable {

    private String imgPath;
    private double probability;
    private String name;
    private double calorie;
    private Boolean has_calorie;
    private double quantity = -1;
    private FoodMenu foodMenu;


    public void getMenu() {
        WebUtil webUtil = WebUtil.getInstance();
        webUtil.getMenu("素红烧肉", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                name = "-1";
                Logger.e("我们数据库没有这个菜");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FoodMenu menu = new Gson().fromJson(response.body().string(), FoodMenu.class);
                foodMenu = menu;
                Logger.d(name + "|" + menu);
            }
        });
    }

    public FoodMenu getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public Boolean getHas_calorie() {
        return has_calorie;
    }

    public void setHas_calorie(Boolean has_calorie) {
        this.has_calorie = has_calorie;
    }

    @Override
    public String toString() {
        return name + ";置信度" + probability + ";卡路里" + calorie;
    }
}
