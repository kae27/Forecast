package com.example.dell.forecast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initUI();
    }

    public void initUI()
    {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        final String[] colors = getResources().getStringArray(R.array.greenAppbar);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                Fragment fragment = null;
                switch (position)
                {
                    case 0:
                        fragment = new NewsFragment();
                        break;
                    case 1:
                        fragment = new WeatherFragment();
                        break;
                    case 2:
                        fragment = new MapFragment();
                        break;
                    case 3:
                        fragment = new SettingsFragment();
                        break;

                }
                return fragment;
            }

            @Override
            public int getCount()
            {
                return 4;
            }
        });



        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_speaker_notes_white_48dp),//รูปภาพ
                        Color.parseColor(colors[0]))
                        .title("News")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_cloud_queue_white_48dp),
                        Color.parseColor(colors[1]))
                        .title("Weather")
                        .build()
        );


        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_map_white_48dp),
                        Color.parseColor(colors[2]))
                        .title("Map")
                        .build()
        );


        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_settings_white_48dp),
                        Color.parseColor(colors[3]))
                        .title("Settings")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);

        navigationTabBar.post(new Runnable() {
            @Override
            public void run() {
                final View viewPager = findViewById(R.id.vp_horizontal_ntb);
                ((ViewGroup.MarginLayoutParams) viewPager.getLayoutParams()).topMargin =
                        (int) -navigationTabBar.getBadgeMargin();
                viewPager.requestLayout();
            }
        });

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {


            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });

//        findViewById(R.id.mask).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(final View v) {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++)
//                {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable()
//                    {
//                        @Override
//                        public void run()
//                        {
//                            final String title = String.valueOf(new Random().nextInt(15));
//                            if (!model.isBadgeShowed())
//                            {
//                                model.setBadgeTitle(title);
//                                model.showBadge();
//                            } else model.updateBadgeTitle(title);
//                        }
//                    }, i * 100);
//                }
//            }
//        });

    }




}
