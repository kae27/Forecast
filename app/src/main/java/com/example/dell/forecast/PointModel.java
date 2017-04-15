package com.example.dell.forecast;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 3/18/2017.
 */

public class PointModel
{


    @SerializedName("x")
    String x;

    @SerializedName("y")
    String y;

    public PointModel(String x, String y)
    {
        this.x = x;
        this.y = y;
    }


    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }







}
