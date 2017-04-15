package com.example.dell.forecast;

import android.util.Log;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Dell on 3/17/2017.
 */

public class Restful {

    final String TAG="HTTP";
    // ใช้ยิงไปยัง server
    private final String url = "http://192.168.137.1/untitled/";

    // arraylist สำหรับเก็บข้อมูลที่ response มาจาก server
    ArrayList<LocationMapResponseModel> ListResponseModel = new ArrayList<LocationMapResponseModel>();

    LocationMapResponseModel responseModel;
    // object request สำหรับส่งข้อมูลไป server
    PointModel requestModel;


    public  void createService(final callback CallbackListener)
    {

        // ข้อมูลสำหรับส่งขึ้นไป server
//        requestModel =  new PointModel("001","003");
        final Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build();


        // create service
        final MyApiEndpointInterface requestService = retrofit.create(MyApiEndpointInterface.class);

        // create caller
        Call<LocationMapResponseModel> call = requestService.getDataFromServer("001","003");
//        Call<LocationMapResponseModel> call = requestService.getDataFromServer();


        // shoot to server
        call.enqueue(new Callback<LocationMapResponseModel>()
        {
            @Override
            public void onResponse(Call<LocationMapResponseModel> call, Response<LocationMapResponseModel> response)
            {
                // get statusCode from server
                int statusCode = response.code();
                Log.e(TAG, "statusCode: "+statusCode );

                // get response model from server


                responseModel = response.body();

                if (responseModel==null)
                {

                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null)
                    {
                        CallbackListener.onBodyError(responseBody);
                    } else
                    {
                        CallbackListener.onBodyErrorIsNull();
                    }



                }
                else
                {
                    Log.e("jay", "onResponse: "+responseModel.getResponseModels().get(0).getWam_pFreqy() );
                    CallbackListener.onResponse( responseModel , retrofit);
                }




            }

            @Override
            public void onFailure(Call<LocationMapResponseModel> call, Throwable t)
            {
                Log.i("Restful", "onFailure:" + t.toString());
                CallbackListener.onFailure(t);
            }


        });



    }

    public interface MyApiEndpointInterface
    {

        @POST("testArray.php")
        Call< LocationMapResponseModel > getDataFromServer();


//        @POST("testArray.php")
//        Call<LocationMapResponseModel> getDataFromServer(@Body PointModel latLng);

        @FormUrlEncoded
        @POST("testArray.php")
        Call<LocationMapResponseModel> getDataFromServer(@Field("x") String x, @Field("y") String y);
    }

}


