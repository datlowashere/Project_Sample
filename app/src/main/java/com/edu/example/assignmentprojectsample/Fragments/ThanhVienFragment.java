package com.edu.example.assignmentprojectsample.Fragments;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Adapter.ThanhVienAdapter;
import com.edu.example.assignmentprojectsample.Dao.ThanhVienDao;
import com.edu.example.assignmentprojectsample.Models.ThanhVien;
import com.edu.example.assignmentprojectsample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {


    private ListView lv;
    private ArrayList<ThanhVien> list;
    private FloatingActionButton fab;
    private Dialog dialog;
    private TextView tvMaTV;
    private EditText edTenTV,edNgaySinh;
    private Button btnSave,btnCancel;

    static ThanhVienDao dao;
    private ThanhVienAdapter adapter;
    private ThanhVien item;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.thanh_vien_fragment, container, false);
         lv=view.findViewById(R.id.lvThanhVien);
         fab=view.findViewById(R.id.fabButtonThanhVien);
         dao=new ThanhVienDao(getActivity());
         capNhatLv();

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

    protected void openDialog(final Context context,final int type){

        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanh_vien);
        tvMaTV=dialog.findViewById(R.id.tvMaTV);
        edTenTV=dialog.findViewById(R.id.edHoTenTv);
        edNgaySinh=dialog.findViewById(R.id.edNgaySinhThanhVien);


        btnSave=dialog.findViewById(R.id.btnSaveTV);
        btnCancel=dialog.findViewById(R.id.btnCancelSaveTV);

        if(type!=0){
            tvMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText(item.hoTen);
            edNgaySinh.setText(item.namSinh);
        }
        btnCancel.setOnClickListener((v -> dialog.cancel()));

        btnSave.setOnClickListener(v -> {
            item=new ThanhVien();
            item.hoTen=edTenTV.getText().toString();
            item.namSinh=edNgaySinh.getText().toString();
            if(validate()>0){
                if(type==0){
                    if(dao.insert(item)>0){
                        Toast.makeText(context,"Thêm thành viên mới thành công!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"Thêm thành thất bại!",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    item.maTV= Integer.parseInt(tvMaTV.getText().toString());
                    if(dao.update(item)>0){
                        Toast.makeText(context,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();

                    }
                }

                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void xoa(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage("Chắc chắn xóa?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void capNhatLv(){
        list=(ArrayList<ThanhVien>) dao.getAll();
        adapter=new ThanhVienAdapter(getContext(),ThanhVienFragment.this,list);
        lv.setAdapter(adapter);

    }
    public int validate(){
        int check=1;
        if(edTenTV.getText().length()==0 || edNgaySinh.getText().length()==0){
            Toast.makeText(getContext(),"Thông tin không được bỏ trống",Toast.LENGTH_SHORT).show();
            check=-1;

        }
        return check;
    }
}