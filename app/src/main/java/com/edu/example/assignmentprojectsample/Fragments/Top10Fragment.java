package com.edu.example.assignmentprojectsample.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.example.assignmentprojectsample.Dao.ThongKeDao;
import com.edu.example.assignmentprojectsample.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class Top10Fragment extends Fragment {

    public Top10Fragment() {
        // Required empty public constructor
    }


    public static Top10Fragment newInstance(String param1, String param2) {
        Top10Fragment fragment = new Top10Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.top10_fragment, container, false);
        BarChart chart=view.findViewById(R.id.barChartTop);

        ThongKeDao dao=new ThongKeDao(getContext());
        ArrayList<BarEntry> entries=new ArrayList<>();

        Integer[] soLuong=dao.getSoLuong();
        String[] ten=dao.getTenTop();
        int tong=dao.getTongSoLuong();

        for (int i=0;i< soLuong.length;i++){
            entries.add(new BarEntry(i,soLuong[i]));
        }

        BarDataSet barDataSet=new BarDataSet(entries,"Tổng số mượn: "+tong);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(barDataSet);
        chart.setData(data);
        chart.invalidate();
        chart.notifyDataSetChanged();


        XAxis xAxis=chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ten));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setLabelCount(ten.length);
        xAxis.setLabelRotationAngle(70);


        YAxis yAxis=chart.getAxis(YAxis.AxisDependency.LEFT);
        YAxis yAxis1=chart.getAxis(YAxis.AxisDependency.RIGHT);

        yAxis1.setStartAtZero(true);
        yAxis.setStartAtZero(true);
        yAxis.setDrawGridLines(false);

        yAxis.setGranularity(1.0f);
        yAxis.setGranularityEnabled(true);

        yAxis1.setGranularity(1.0f);
        yAxis1.setGranularityEnabled(true);


        chart.getDescription().setEnabled(false);
        chart.setFitBars(true);
        chart.animateY(3000);



        return view;
    }
}