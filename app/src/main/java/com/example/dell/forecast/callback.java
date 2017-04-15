package com.example.dell.forecast;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by K550J on 18/3/2559.
 */
public interface callback
{
    public void onResponse(LocationMapResponseModel myhouses, Retrofit retrofit);
    public void onResponse(ArrayList<LocationMapResponseModel> myhouses, Retrofit retrofit);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);

}
