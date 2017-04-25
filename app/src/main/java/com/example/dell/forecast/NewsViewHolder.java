package com.example.dell.forecast;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.forecast.databinding.ItemsListviewBinding;

/**
 * Created by Dell on 4/24/2017.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder
{
    ItemsListviewBinding itemsListviewBinding;



    public NewsViewHolder(View itemView) {
        super(itemView);

        itemsListviewBinding = DataBindingUtil.bind(itemView);

    }

    public void bind()
    {

    }
}
