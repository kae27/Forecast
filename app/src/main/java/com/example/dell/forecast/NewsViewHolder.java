package com.example.dell.forecast;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    public void bind(final NewsListModel model, final Context context)
    {
        itemsListviewBinding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsContentActivity.class);
                intent.putExtra("TitleNews",model.getTitle_text());
                intent.putExtra("ContentNews",model.getContent_text());
                context.startActivity(intent);
            }
        });
        itemsListviewBinding.listViewTitle.setText(model.getTitle_text()); //id ถ้าตั้งแบบมี ีunderscore มันจะเปลี่ยนเป็นตัวหนัสือตัวใหญ่ให้
        itemsListviewBinding.contentNewsTextView.setText(model.getContent_text());

    }
}
