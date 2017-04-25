package com.example.dell.forecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dell.forecast.databinding.ItemsListviewBinding;

import java.util.ArrayList;

/**
 * Created by Dell on 4/25/2017.
 */

public class NewsRecycleViewAdapter extends RecyclerView.Adapter<NewsViewHolder>
{
    ItemsListviewBinding itemsListviewBinding;
    ArrayList<NewsListModel> list = new ArrayList<NewsListModel>();
    Context context;

    public NewsRecycleViewAdapter(ArrayList<NewsListModel> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemsListviewBinding = ItemsListviewBinding.inflate(inflater, parent, false);


        return new NewsViewHolder(itemsListviewBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        if (list != null)
        {
            NewsListModel model = list.get(holder.getAdapterPosition());
            holder.bind(model,context);
        }
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
