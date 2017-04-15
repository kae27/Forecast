package com.example.dell.forecast;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.forecast.databinding.ActivityChartViewBinding;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class ChartViewActivity extends AppCompatActivity
{
    final String TAG ="Project";
    String[] dateAxisX = new String[5];
    ActivityChartViewBinding binding; //ActivityChartViewBinding ชื่อ layout
    ArrayList<RelativeLayout> relativeLayouts = new ArrayList<RelativeLayout>();
    ArrayList<TextView> textViews = new ArrayList<TextView>();
    ArrayList<Entry> entries = new ArrayList<>();
    int size;
    double temp;
    int max = 0 ;
    int  min = 0 ;


    MyMarkerView markerView;
    ArrayList<LocationMapResponseModel> list;

    XAxis xAxis ;
    YAxis yAxis ;
    IAxisValueFormatter formatterX;
    LineDataSet dataset ;
    LineData data ;


    callback callback = new callback()
    {
        @Override
        public void onResponse(LocationMapResponseModel responseModel, Retrofit retrofit)
        {

            list = responseModel.getResponseModels();
            Log.e("ViewActivity","size of temp "+list.size());  //size=33 object
            size = list.size();
            temp = 0.0;
            min = (int) list.get(0).getWamDragCo();
            for (int i = 0; i < size; i++)
            {
                entries.add(new Entry((float) temp, list.get(i).getWamDragCo()));
                temp = temp + 0.125;

                if(list.get(i).getWamDragCo()>max)
                    max =  (int) list.get(i).getWamDragCo() ;

                if(list.get(i).getWamDragCo()<min)
                    min =  (int) list.get(i).getWamDragCo();

                if(i%8==0)
                    dateAxisX[i/8] = list.get(i).getDate();

            }

            max = max+50;
            min = min-50;
            Log.e("ViewActivity","min Drag "+min);
            Log.e("ViewActivity","max Drag "+max);
            setFormattDate(dateAxisX);
            setCustomChart();

        }

        @Override
        public void onResponse(ArrayList<LocationMapResponseModel> myhouses, Retrofit retrofit) {

        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {


        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(ChartViewActivity.this,R.layout.activity_chart_view);



        binding.dragCoefficient.setOnClickListener(Onclick);
        binding.fictionVeiocity.setOnClickListener(Onclick);
        binding.meanFrequency.setOnClickListener(Onclick);
        binding.meanPeriod.setOnClickListener(Onclick);
        binding.meanWaveDirection.setOnClickListener(Onclick);
        binding.normallizedWave.setOnClickListener(Onclick);
        binding.significantWaveHight.setOnClickListener(Onclick);
        binding.wavePeakFrequency.setOnClickListener(Onclick);
        binding.wavePeakPeriod.setOnClickListener(Onclick);
        binding.windDirection.setOnClickListener(Onclick);
        binding.windSpeed.setOnClickListener(Onclick);

        relativeLayouts.add(binding.dragCoefficient);
        relativeLayouts.add(binding.fictionVeiocity);
        relativeLayouts.add(binding.meanFrequency);
        relativeLayouts.add(binding.meanPeriod);
        relativeLayouts.add(binding.meanWaveDirection);
        relativeLayouts.add(binding.normallizedWave);
        relativeLayouts.add(binding.significantWaveHight);
        relativeLayouts.add(binding.wavePeakFrequency);
        relativeLayouts.add(binding.wavePeakPeriod);
        relativeLayouts.add(binding.windDirection);
        relativeLayouts.add(binding.windSpeed);

        textViews.add(binding.textView1);
        textViews.add(binding.textView2);
        textViews.add(binding.textView3);
        textViews.add(binding.textView4);
        textViews.add(binding.textView5);
        textViews.add(binding.textView6);
        textViews.add(binding.textView7);
        textViews.add(binding.textView8);
        textViews.add(binding.textView9);
        textViews.add(binding.textView10);
        textViews.add(binding.textView11);
        setActive(relativeLayouts.get(0).getId());

        binding.imgBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();

            }
        });


        markerView = new MyMarkerView(this, R.layout.custom_marker_chart);
        markerView.setChartView(binding.chart);
        binding.chart.setMarker(markerView);


        // drawChart();
        Log.e("ViewActivity","size of temp ");

        new Restful().createService(callback);
    }

    View.OnClickListener Onclick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            setDefault();
            entries.clear();
            size = list.size();
            max= 0 ;
            min= 0 ;



            switch(v.getId())
            {

                case R.id.dragCoefficient:
                    setActive(v.getId());
                    min = (int) list.get(0).getWamDragCo();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWamDragCo()));
                        temp = temp + 0.125;

                        if(list.get(i).getWamDragCo()>max)
                            max =  (int) list.get(i).getWamDragCo() ;

                        if(list.get(i).getWamDragCo()<min)
                            min =  (int) list.get(i).getWamDragCo();
                    }

                    dataset.setLabel("Drag Coefficient");

                    break;

                case R.id.fictionVeiocity:
                    setActive(v.getId());
                    min = (int) list.get(0).getWamUstar10();
                    temp = 0.0;
                    for(int i = 0 ; i<size ; i++)
                    {
                        entries.add(new Entry((float) temp, list.get(i).getWamUstar10()));
                        temp = temp + 0.125;

                        if(list.get(i).getWamUstar10()>max)
                            max =  (int) list.get(i).getWamUstar10() ;

                        if(list.get(i).getWamUstar10()<min)
                            min =  (int) list.get(i).getWamUstar10();
                    }

                    dataset.setLabel("Fiction Veiocity");

                    break;

                case R.id.meanFrequency:
                    setActive(v.getId());
                    min = (int) list.get(0).getWam_mFreqy();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWam_mFreqy()));
                        temp = temp + 0.125;

                        if(list.get(i).getWam_mFreqy()>max)
                            max =  (int) list.get(i).getWam_mFreqy() ;

                        if(list.get(i).getWam_mFreqy()<min)
                            min =  (int) list.get(i).getWam_mFreqy();
                    }

                    dataset.setLabel("Mean Frequency");

                    break;

                case R.id.meanPeriod:
                    setActive(v.getId());
                    min = (int) list.get(0).getWam_mPerio();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWam_mPerio()));
                        temp = temp + 0.125;

                        if(list.get(i).getWam_mPerio()>max)
                            max =  (int) list.get(i).getWam_mPerio() ;

                        if(list.get(i).getWam_mPerio()<min)
                            min =  (int) list.get(i).getWam_mPerio();
                    }

                    dataset.setLabel("Mean Period");

                    break;

                case R.id.meanWaveDirection:
                    setActive(v.getId());
                    min = (int) list.get(0).getWamWaveDir();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWamWaveDir()));
                        temp = temp + 0.125;
                        if(list.get(i).getWamWaveDir()>max)
                            max =  (int) list.get(i).getWamWaveDir() ;

                        if(list.get(i).getWamWaveDir()<min)
                            min =  (int) list.get(i).getWamWaveDir();
                    }

                    dataset.setLabel("Mean Wave Direction");

                    break;

                case R.id.normallizedWave:
                    setActive(v.getId());
                    min = (int) list.get(0).getWamWaveSte();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWamWaveSte()));
                        temp = temp + 0.125;
                        if(list.get(i).getWamWaveSte()>max)
                            max =  (int) list.get(i).getWamWaveSte() ;

                        if(list.get(i).getWamWaveSte()<min)
                            min =  (int) list.get(i).getWamWaveSte();
                    }

                    dataset.setLabel("Normallized Wave Stress");

                    break;

                case R.id.significantWaveHight:
                    setActive(v.getId());
                    min = (int) list.get(0).getWamWaveHgt();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWamWaveHgt()));
                        temp = temp + 0.125;
                        if(list.get(i).getWamWaveHgt()>max)
                            max =  (int) list.get(i).getWamWaveHgt() ;

                        if(list.get(i).getWamWaveHgt()<min)
                            min =  (int) list.get(i).getWamWaveHgt();
                    }

                    dataset.setLabel("Significant Wave Hight");

                    break;

                case R.id.wavePeakFrequency:
                    setActive(v.getId());
                    min = (int) list.get(0).getWam_pFreqy();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWam_pFreqy()));
                        temp = temp + 0.125;
                        if(list.get(i).getWam_pFreqy()>max)
                            max =  (int) list.get(i).getWam_pFreqy() ;

                        if(list.get(i).getWam_pFreqy()<min)
                            min =  (int) list.get(i).getWam_pFreqy();
                    }

                    dataset.setLabel("Wave Peak Frequency");

                    break;

                case R.id.wavePeakPeriod:
                    setActive(v.getId());
                    min = (int) list.get(0).getWam_pPerio();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWam_pPerio()));
                        temp = temp + 0.125;
                        if(list.get(i).getWam_pPerio()>max)
                            max =  (int) list.get(i).getWam_pPerio() ;

                        if(list.get(i).getWam_pPerio()<min)
                            min =  (int) list.get(i).getWam_pPerio();
                    }

                    dataset.setLabel("Wave Peak Period");

                    break;

                case R.id.windDirection:
                    setActive(v.getId());
                    min = (int) list.get(0).getWamWindDir();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWamWindDir()));
                        temp = temp + 0.125;
                        if(list.get(i).getWamWindDir()>max)
                            max =  (int) list.get(i).getWamWindDir() ;

                        if(list.get(i).getWamWindDir()<min)
                            min =  (int) list.get(i).getWamWindDir();
                    }

                    dataset.setLabel("Wind Direction");

                    break;

                case R.id.windSpeed:
                    setActive(v.getId());

                    temp = 0.0;
                    min = (int) list.get(0).getWamWindSpd();

                    for(int i = 0 ; i<size ; i++)
                    {
                        entries.add(new Entry((float) temp,  list.get(i).getWamWindSpd()));
                        temp = temp + 0.125;
                        if(list.get(i).getWamWindSpd()>max)
                            max =  (int) list.get(i).getWamWindSpd() ;

                        if(list.get(i).getWamWindSpd()<min)
                            min =  (int) list.get(i).getWamWindSpd();
                    }

                    dataset.setLabel("Wind Speed");

                    break;
            }


            binding.chart.animateX(1500);
            max = max+50;
            min = min-50;
            yAxis.setAxisMinimum(min); // start at zero
            yAxis.setAxisMaximum(max); // the axis maximum is 100
            binding.chart.notifyDataSetChanged();
            binding.chart.invalidate();


        }


    };





    public void setDefault()
    {
        int size=0;
        size = relativeLayouts.size();


        for(int i = 0 ; i<size ; i++)
        {
            relativeLayouts.get(i).setBackground( ContextCompat.getDrawable(ChartViewActivity.this,R.drawable.unactive ) );
//            relativeLayouts.get(i).setBackground( getResources().getDrawable( R.drawable.unactive) );
            textViews.get(i).setTextColor(ContextCompat.getColor(ChartViewActivity.this,R.color.colorWhite));
            textViews.get(i).setTypeface(null, Typeface.NORMAL);
        }

    }

    public void setActive(int id)
    {

        int size=0;
        size = relativeLayouts.size();


        for(int i = 0 ; i<size ; i++)
        {
            if(id == relativeLayouts.get(i).getId())
            {
                relativeLayouts.get(i).setBackground( ContextCompat.getDrawable(ChartViewActivity.this,R.drawable.active ) );
//                relativeLayouts.get(i).setBackground(getResources().getDrawable(R.drawable.active));
                textViews.get(i).setTextColor(ContextCompat.getColor(ChartViewActivity.this, R.color.colorWhite));
                textViews.get(i).setTypeface(null, Typeface.BOLD);

            }
        }

    }


    public void setCustomChart()
    {

        formatterX = new IAxisValueFormatter() { //ปรับแกน X
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return dateAxisX[(int) value];
        }


//            @Override
//            public int getDecimalDigits() {  return 0; }

    };


        xAxis =  binding.chart.getXAxis();
        yAxis = binding.chart.getAxisLeft();


        xAxis.setGranularity(0.125f); // minimum axis-step (interval) is 0.125
        xAxis.setValueFormatter(formatterX);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setGridColor(Color.WHITE);
        xAxis.setAxisMaximum(4f);
        xAxis.setLabelCount(3);

        yAxis.setLabelCount(9);
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setGridColor(Color.WHITE);


       //yAxis.enableGridDashedLine(10f, 10f, 0f); //ทำเส้นปะ
//       xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//ย้าย txet แกน X มาอยู่ข้างล่าง


        dataset = new LineDataSet(entries,"Drag Coefficient");
        dataset.setLineWidth(4f);
        dataset.setCircleRadius(4f);
        dataset.setColor(ContextCompat.getColor(this,R.color.colorTransparentWhite));
        dataset.setCircleColors(ContextCompat.getColor(this,R.color.colorWhiteCircle));
        dataset.setHighLightColor(ContextCompat.getColor(this,R.color.colorTransparentWhite));
        dataset.setFillColor(ContextCompat.getColor(this,R.color.colorTransparentWhite));
        dataset.setDrawFilled(true); // fill color under chart
        dataset.setDrawValues(false); //hide value chart

        data = new LineData(dataset);
        yAxis.setAxisMinimum(min); // start at zero
        yAxis.setAxisMaximum(max); // the axis maximum is 100
        binding.chart.setData(data);
        binding.chart.setTouchEnabled(true);
        binding.chart.setPinchZoom(true);
        binding.chart.getAxisRight().setEnabled(false);
        binding.chart.setScaleYEnabled(true);
        binding.chart.setScaleXEnabled(true);
        binding.chart.setDescription(null);
        binding.chart.getLegend().setTextColor(Color.WHITE);
        binding.chart.animateX(1500);
        binding.chart.invalidate(); //refresh




    }

    public void setFormattDate(String[] formattDate)
    {

        int length = formattDate.length;
        String substr;
        for (int i=0; i<length; i++)
        {
            substr = formattDate[i];
            formattDate[i] = substr.substring(4,6)+"/"+substr.substring(2,4)+"/"+substr.substring(0,2);
            Log.e("ViewActivity","quarter "+ formattDate[i]);
        }

    }


    @Override
    public void onBackPressed()
    {
        finish();
    }



}
