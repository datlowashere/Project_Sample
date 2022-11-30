package com.edu.example.assignmentprojectsample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edu.example.assignmentprojectsample.Fragments.LoaiSachFragment;
import com.edu.example.assignmentprojectsample.Models.LoaiSach;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {

    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach>lists;
    TextView tvMaLoai,tvTenLoai;
    public ImageView imgDel;


    public LoaiSachAdapter(@NonNull Context context,LoaiSachFragment fragment,ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent) {
        View v = view;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.items_loai_sach,null);
        }
        final LoaiSach item=lists.get(position);
        if(item!=null){
            tvMaLoai=v.findViewById(R.id.tvItemsMaLoai);
            tvMaLoai.setText("Mã Loại: "+item.maLoai);
            tvTenLoai=v.findViewById(R.id.tvItemsTenLoai);
            tvTenLoai.setText("Tên loại: "+item.tenLoai);

            imgDel=v.findViewById(R.id.imgItemsRevemoveLoai);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maLoai));

            }
        });
        return v;

    }

}
