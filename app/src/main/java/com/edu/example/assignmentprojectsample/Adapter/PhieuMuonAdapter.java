package com.edu.example.assignmentprojectsample.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.edu.example.assignmentprojectsample.Dao.SachDao;
import com.edu.example.assignmentprojectsample.Dao.ThanhVienDao;
import com.edu.example.assignmentprojectsample.Fragments.PhieuMuonFragment;

import com.edu.example.assignmentprojectsample.Models.PhieuMuon;
import com.edu.example.assignmentprojectsample.Models.Sach;
import com.edu.example.assignmentprojectsample.Models.ThanhVien;
import com.edu.example.assignmentprojectsample.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    private Context context;
    private PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon>lists;
    private  TextView tvMaPM,tvTenTV,tvTenSach,tvTienThue,tvNgay,tvTraSach;
    private ImageView imgDel;
    private SachDao sachDao;
    private ThanhVienDao thanhVienDao;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment,ArrayList<PhieuMuon> lists) {
        super(context, 0,lists);
        this.context=context;
        this.fragment=fragment;
        this.lists=lists;

    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=convertView;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.items_phieu_muon,null);

        }
        final PhieuMuon item=lists.get(position);
        if(item!=null){
            tvMaPM=view.findViewById(R.id.tvItemsMaPM);
            tvMaPM.setText("Mã Phiếu: "+item.maPM);

            thanhVienDao=new ThanhVienDao(context);
            ThanhVien thanhVien=thanhVienDao.getID(String.valueOf(item.maTV));
            tvTenTV=view.findViewById(R.id.tvItemsTenThanhVienPM);
            tvTenTV.setText("Thành viên: "+thanhVien.hoTen);
            sachDao=new SachDao(context);
            Sach sach=sachDao.getID(String.valueOf(item.maSach));
            tvTenSach=view.findViewById(R.id.tvItemsTenSachPM);
            tvTenSach.setText("Sách: "+sach.tenSach);
            tvTienThue=view.findViewById(R.id.tvItemsGiaThuePM);
            tvTienThue.setText("Tiền thuê: "+item.tienThue);

            tvNgay=view.findViewById(R.id.tvItemsnNgayTra);
            tvNgay.setText("Ngày: " + sdf.format(item.ngay));

            tvTraSach=view.findViewById(R.id.tvItemsTrangThaiPM);
            if(item.traSach==1){
                tvTraSach.setText("Đã trả sách!");
                tvTraSach.setTextColor(Color.BLUE);
            }else{
                tvTraSach.setText("Chưa trả sách!");
                tvTraSach.setTextColor(Color.RED);
            }

            imgDel=view.findViewById(R.id.imgItemsRevemovePM);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maPM));
            }
        });

        return view;
    }
}
