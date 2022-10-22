package com.edu.example.assignmentprojectsample.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edu.example.assignmentprojectsample.Dao.LoaiSachDao;
import com.edu.example.assignmentprojectsample.Fragments.SachFragment;
import com.edu.example.assignmentprojectsample.Models.LoaiSach;
import com.edu.example.assignmentprojectsample.Models.Sach;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> lists;
    TextView tvMaSach,tvTenSach,tvGiaThue,tvLoai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, SachFragment fragment,ArrayList<Sach> lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;
        this.fragment=fragment;
    }
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup parent){
        View v=view;
        if (v == null) {
            LayoutInflater inflater=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.items_sach,null);
        }

        final Sach item=lists.get(position);
        if(item!=null){
            LoaiSachDao loaiSachDao=new LoaiSachDao(context);
            LoaiSach loaiSach=loaiSachDao.getID(String.valueOf(item.maLoai));
            tvMaSach=v.findViewById(R.id.tvItemsMaSach);
            tvMaSach.setText("Mã: "+item.maSach);

            tvTenSach=v.findViewById(R.id.tvItemsTenSach);
            tvTenSach.setText("Tên: "+item.tenSach);

            tvGiaThue=v.findViewById(R.id.tvItemsGiaThue);
            tvGiaThue.setText("Giá: "+item.giaThue);

            tvLoai=v.findViewById(R.id.tvItemsLoaiSach);
            tvLoai.setText("Loại: "+loaiSach.tenLoai);

            imgDel=v.findViewById(R.id.imgItemsRevemoveSach);
        }


        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maSach));
            }
        });


        return v;
    }


}
