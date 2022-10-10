package com.edu.example.assignmentprojectsample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edu.example.assignmentprojectsample.Models.ThanhVien;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class ThanhVienSpinerAdaper extends ArrayAdapter<ThanhVien> {

    private Context context;
    private ArrayList<ThanhVien>lists;
    TextView tvMaTV,tvTenTv;

    public ThanhVienSpinerAdaper(@NonNull Context context, ArrayList<ThanhVien> lists) {
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


        return v;
    }

//    @NonNull
//    public View getDropDownView(int position,@NonNull View view,@NonNull ViewGroup parent){
//
//    }
}
