package com.edu.example.assignmentprojectsample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edu.example.assignmentprojectsample.Fragments.ThanhVienFragment;
import com.edu.example.assignmentprojectsample.Models.ThanhVien;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {

    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV,tvTenThanhVien,tvNamSinh;
    ImageView imgDel;


    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context,0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent){
        View v=view;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.items_thanh_vien,null);

        }
        final  ThanhVien item=lists.get(position);
        if(item!=null){
            tvMaTV=v.findViewById(R.id.tvItemsMaTV);
            tvMaTV.setText("Mã thành viên: "+item.maTV);

            tvTenThanhVien=v.findViewById(R.id.tvItemsHoTenTV);
            tvTenThanhVien.setText("Họ Tên: "+item.hoTen);

            tvNamSinh=v.findViewById(R.id.tvItemsNgaySinh);
            tvNamSinh.setText("Năm Sinh: "+item.namSinh);


            imgDel=v.findViewById(R.id.imgItemsRevemoveTV);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });



        return v;
    }
}
