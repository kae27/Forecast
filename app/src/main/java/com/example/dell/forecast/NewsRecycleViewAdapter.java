package com.example.dell.forecast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dell.forecast.databinding.ItemsListviewBinding;

/**
 * Created by Dell on 4/25/2017.
 */

public class NewsRecycleViewAdapter extends RecyclerView.Adapter<NewsViewHolder>
{
    ItemsListviewBinding itemsListviewBinding;
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemsListviewBinding = ItemsListviewBinding.inflate(inflater, parent, false);

        return new NewsViewHolder(itemsListviewBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

//            holder.titleTextview.setText("พายุฤดูร้อนถล่มเมืองมะขาม ชาวบ้านโอดเสียหายรุนแรงในรอบ 100 ปี ");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
