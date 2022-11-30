package com.edu.example.assignmentprojectsample.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Adapter.LoaiSachAdapter;
import com.edu.example.assignmentprojectsample.Dao.LoaiSachDao;
import com.edu.example.assignmentprojectsample.Models.LoaiSach;
import com.edu.example.assignmentprojectsample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LoaiSachFragment extends Fragment implements AdapterView.OnItemLongClickListener{


    private ListView lv;
    private ArrayList<LoaiSach> list;
    private FloatingActionButton fab;
    private Dialog dialog;
    private TextView tvMaLoai;
    private EditText edTenLoai;
    private Button btnSave,btnCancel;


    static LoaiSachDao dao;
    private LoaiSachAdapter adapter;
    private LoaiSach iteam;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.loai_sach_fragment, container, false);

        lv=view.findViewById(R.id.lvLoaiSach);
        fab=view.findViewById(R.id.fabButtonLoaiSach);
        dao=new LoaiSachDao(getActivity());
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
                iteam=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });

        return view;

    }

    public void openDialog(final Context context, final  int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_loai_sach);
        tvMaLoai=dialog.findViewById(R.id.tvMaLoai);
        edTenLoai=dialog.findViewById(R.id.edTenLoai);
        btnCancel=dialog.findViewById(R.id.btnCancelSaveLoaiSach);
        btnSave=dialog.findViewById(R.id.btnSaveLoaiSach);

        if(type!=0){
            tvMaLoai.setText(String.valueOf(iteam.maLoai));
            edTenLoai.setText(iteam.tenLoai);
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
                iteam=new LoaiSach();
                iteam.tenLoai=edTenLoai.getText().toString();
                if(validate()>0){
                    if(type==0) {
                        if (dao.insert(iteam) > 0) {
                            Toast.makeText(context, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Lưu nhật thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        iteam.maLoai= Integer.parseInt(tvMaLoai.getText().toString());
                        if(dao.update(iteam)>0){
                            Toast.makeText(context,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập nhật thất bại!",Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();


    }
    public void xoa(final String id){

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Chắc chắn xóa");
        builder.setCancelable(true);
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
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    public void capNhatLv(){
        list=(ArrayList<LoaiSach>) dao.getAll();
        adapter=new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);

    }
    public int validate(){
        int check=1;
        if(edTenLoai.getText().length()==0){
            Toast.makeText(getContext(),"Phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv=(ListView) view.findViewById(R.id.lvLoaiSach);
        list=(ArrayList<LoaiSach>) dao.getAll();
        adapter=new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        iteam=list.get(position);
        openDialog(getActivity(),1);
        return false;
    }
}