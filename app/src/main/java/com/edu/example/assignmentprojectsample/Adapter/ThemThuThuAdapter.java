package com.edu.example.assignmentprojectsample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;

import com.edu.example.assignmentprojectsample.Fragments.ThemThuThuFragment;
import com.edu.example.assignmentprojectsample.Models.ThuThu;
import com.edu.example.assignmentprojectsample.R;

import java.util.ArrayList;

public class ThemThuThuAdapter extends ArrayAdapter<ThuThu> {

    private Context context;
    private ArrayList<ThuThu> list;
    private ThemThuThuFragment fragment;

    private TextView tvmaTT,tvTenTT,tvMatKhau;
    private ImageView imgDel;

    public ThemThuThuAdapter(@NonNull Context context, ThemThuThuFragment fragment,ArrayList<ThuThu> lists) {
        super(context, 0,lists);
        this.context=context;
        this.fragment=fragment;
        this.list=lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.items_thu_thu,null);
        }
        final ThuThu item=list.get(position);
        if(v!=null){
            tvmaTT=v.findViewById(R.id.tvItemsMaTT);
            tvmaTT.setText("Mã thủ thư: "+item.maTT);

            tvTenTT=v.findViewById(R.id.tvItemsHoTenTT);
            tvTenTT.setText("Họ tên: "+item.hoTen);

            tvMatKhau=v.findViewById(R.id.tvItemMatKhauTT);
            tvMatKhau.setText("Mật khẩu :"+item.matKhau);

            imgDel=v.findViewById(R.id.imgItemsRevemoveTT);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.xoa(item.maTT);
            }
        });


        return v;
    }
}
