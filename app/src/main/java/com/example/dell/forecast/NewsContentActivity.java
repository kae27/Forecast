package com.example.dell.forecast;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dell.forecast.databinding.ActivityNewsContentBinding;

public class NewsContentActivity extends AppCompatActivity
{

    ActivityNewsContentBinding newsContentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        newsContentBinding = DataBindingUtil.setContentView(NewsContentActivity.this,R.layout.activity_news_content);
    }

}
