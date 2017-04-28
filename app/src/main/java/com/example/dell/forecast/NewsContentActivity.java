package com.example.dell.forecast;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.forecast.databinding.ActivityNewsContentBinding;

public class NewsContentActivity extends AppCompatActivity
{

    ActivityNewsContentBinding newsContentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        newsContentBinding = DataBindingUtil.setContentView(NewsContentActivity.this,R.layout.activity_news_content);

        Bundle bundle = getIntent().getExtras();
        String TitleNews = bundle.getString("TitleNews");
        String ContentNews = bundle.getString("ContentNews");

        newsContentBinding.newsTitle.setText(TitleNews);
        newsContentBinding.newsContent.setText(ContentNews);


        newsContentBinding.imgBackNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });


        ImagePagerAdapter adapter = new ImagePagerAdapter();
        newsContentBinding.viewPagerImageNews.setAdapter(adapter);
    }
    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[] {
                R.drawable.pict1,
                R.drawable.pict2,
                R.drawable.pict3
        };

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = NewsContentActivity.this;
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}
