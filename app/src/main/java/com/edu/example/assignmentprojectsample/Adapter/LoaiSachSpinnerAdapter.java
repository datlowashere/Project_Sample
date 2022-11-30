package com.edu.example.assignmentprojectsample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edu.example.assignmentprojectsample.Models.LoaiSach;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoai,tvTenLoai;
    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent){
        View v=view;
        if (v == null) {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.items_spinner_thanh_vien,null);
        }
        final LoaiSach item=lists.get(position);
        if(item!=null){
            tvMaLoai=v.findViewById(R.id.tvMaTVSpinner);
            tvMaLoai.setText(item.maLoai+".");
            tvTenLoai=v.findViewById(R.id.tvHoTenSpinner);
            tvTenLoai.setText(item.tenLoai);
        }
        return v;
    }
    @NonNull
    public View getDropDownView(int position,@NonNull View view,@NonNull ViewGroup parent){
        View v=view;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.items_spinner_thanh_vien,null);
        }
        final LoaiSach item=lists.get(position);
        if(item!=null){
            tvMaLoai=v.findViewById(R.id.tvMaTVSpinner);
            tvMaLoai.setText(item.maLoai+".");
            tvTenLoai=v.findViewById(R.id.tvHoTenSpinner);
            tvTenLoai.setText(item.tenLoai);
        }
        return v;

    }
}
