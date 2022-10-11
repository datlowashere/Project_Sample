package com.edu.example.assignmentprojectsample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edu.example.assignmentprojectsample.Models.LoaiSach;
import com.edu.example.assignmentprojectsample.Models.Sach;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Sach>lists;
    TextView tvMaSach,tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach>lists) {
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
        final Sach item=lists.get(position);
        if(item!=null){
            tvMaSach=v.findViewById(R.id.tvMaTVSpinner);
            tvMaSach.setText(item.maLoai+".");
            tvTenSach=v.findViewById(R.id.tvHoTenSpinner);
            tvTenSach.setText(item.tenSach);
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
        final Sach item=lists.get(position);
        if(item!=null){
            tvMaSach=v.findViewById(R.id.tvMaTVSpinner);
            tvMaSach.setText(item.maLoai+".");
            tvTenSach=v.findViewById(R.id.tvHoTenSpinner);
            tvTenSach.setText(item.tenSach);
        }



        return v;

    }
}
