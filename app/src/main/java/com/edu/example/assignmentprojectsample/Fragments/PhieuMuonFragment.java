package com.edu.example.assignmentprojectsample.Fragments;

import static java.time.MonthDay.now;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Adapter.PhieuMuonAdapter;
import com.edu.example.assignmentprojectsample.Adapter.SachSpinnerAdapter;
import com.edu.example.assignmentprojectsample.Adapter.ThanhVienSpinerAdaper;
import com.edu.example.assignmentprojectsample.Dao.PhieuMuonDao;
import com.edu.example.assignmentprojectsample.Dao.SachDao;
import com.edu.example.assignmentprojectsample.Dao.ThanhVienDao;
import com.edu.example.assignmentprojectsample.Models.PhieuMuon;
import com.edu.example.assignmentprojectsample.Models.Sach;
import com.edu.example.assignmentprojectsample.Models.ThanhVien;
import com.edu.example.assignmentprojectsample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PhieuMuonFragment extends Fragment {

    private ListView lv;
    private ArrayList<PhieuMuon>list;
    private FloatingActionButton fab;
    private Dialog dialog;
    private TextView tvMaPM,tvNgay,tvTienThue;
    private Spinner spTV,spSach;
    private CheckBox ckTraSach;
    private Button btnSave,btnCancel;

    static PhieuMuonDao dao;
    private PhieuMuonAdapter adapter;
    private PhieuMuon item;

    private ThanhVienSpinerAdaper spinerThanhVien;
    private ArrayList<ThanhVien>listThanhVien;
    private ThanhVienDao thanhVienDao;
    private ThanhVien thanhVien;
    int maThanhVien;

    private SachSpinnerAdapter spinerSach;
    private ArrayList<Sach>listSach;
    private SachDao sachDao;
    private Sach sach;

    private int maSach, tienThue;
    private int positionTV,positionSach;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.phieu_muon_fragment, container, false);

        lv=view.findViewById(R.id.lvPhieuMuon);
        fab=view.findViewById(R.id.fabButtonPM);
        dao=new PhieuMuonDao(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });

        return view;
    }
    @SuppressLint("SetTextI18n")
    public   void openDialog(final Context context, final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_phieu_muon);
        tvMaPM=dialog.findViewById(R.id.tvMaPM);
        spTV=dialog.findViewById(R.id.spNguoiMuon);
        spSach=dialog.findViewById(R.id.spSachMuon);
        tvNgay=dialog.findViewById(R.id.tvNgayThue);
        tvTienThue=dialog.findViewById(R.id.tvTienThueSach);
        ckTraSach=dialog.findViewById(R.id.ckTrangThai);
        btnSave=dialog.findViewById(R.id.btnSavePhieuMuon);
        btnCancel=dialog.findViewById(R.id.btnCancelPhieuMuon);

//        Calendar c = Calendar.getInstance();
//        String date = sdf.format(c.getTime());


        tvNgay.setText("Ngày thuê: "+sdf.format(new Date()));

        thanhVienDao=new ThanhVienDao(context);
        listThanhVien=new ArrayList<ThanhVien>();
        listThanhVien=(ArrayList<ThanhVien>) thanhVienDao.getAll();
        spinerThanhVien=new ThanhVienSpinerAdaper(context,listThanhVien);
        spTV.setAdapter(spinerThanhVien);

        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien=listThanhVien.get(position).maTV;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDao=new SachDao(context);
        listSach=new ArrayList<Sach>();
        listSach=(ArrayList<Sach>) sachDao.getAll();
        spinerSach=new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(spinerSach);

        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach=listSach.get(position).maSach;
                tienThue=listSach.get(position).giaThue;
                tvTienThue.setText("Tiền thuê: "+tienThue);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(type!=0){

            tvMaPM.setText(String.valueOf(item.maPM));
            for (int i=0;i<listThanhVien.size();i++){
                if(item.maTV==(listThanhVien.get(i).maTV)){
                    positionTV=i;
                }
            }
            spTV.setSelection(positionTV);

            for (int i=0;i<listSach.size();i++){
                if(item.maSach==(listSach.get(i).maSach)){
                    positionSach=i;
                }
            }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: " + sdf.format(item.ngay));
            tvTienThue.setText("Tiền thuê :"+item.tienThue);

            if(item.traSach==1){
                ckTraSach.setChecked(true);
            }else{
                ckTraSach.setChecked(false);
            }

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item=new PhieuMuon();
                item.maSach=maSach;
                item.maTV=maThanhVien;
                item.ngay=new Date();
                item.tienThue=tienThue;
                if (ckTraSach.isChecked()){
                    item.traSach=1;
                }else{
                    item.traSach=0;
                }

                if(validate()>0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Lưu thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Lưu thất bại",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        item.maPM= Integer.parseInt(tvMaPM.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Cập nhật thất bại",Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }


            }
        });
        dialog.show();



    }

    public void xoa(String id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Chắc chắn xóa");
        builder.setCancelable(true);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhatLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void capNhatLV(){
        list=(ArrayList<PhieuMuon>) dao.getAll();
        adapter=new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);

    }
    public int validate(){
        int check=1;
        return check;
    }
}