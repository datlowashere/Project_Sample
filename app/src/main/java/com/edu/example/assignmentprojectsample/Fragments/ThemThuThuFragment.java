package com.edu.example.assignmentprojectsample.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Adapter.ThemThuThuAdapter;
import com.edu.example.assignmentprojectsample.Dao.ThuThuDao;
import com.edu.example.assignmentprojectsample.Models.ThuThu;
import com.edu.example.assignmentprojectsample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThemThuThuFragment extends Fragment {

    private ArrayList<ThuThu> list;
    private ListView lv;
    private FloatingActionButton fab;
    private EditText edMaTT,edHoTen,edMatKhau;
    private Button btnSave,btnCancel;
    private Dialog dialog;

    private ThuThu item;
    private ThuThuDao dao;
    private ThemThuThuAdapter adapter;


    public ThemThuThuFragment() {
        // Required empty public constructor
    }


    public static ThemThuThuFragment newInstance(String param1, String param2) {
        ThemThuThuFragment fragment = new ThemThuThuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.them_thu_thu_fragment, container, false);
        lv=v.findViewById(R.id.lvThuThu);
        fab=v.findViewById(R.id.fabButtonThuThu);
        dao=new ThuThuDao(getActivity());
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

        return v;
    }

    protected void openDialog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_thu_thu);
        edMaTT=dialog.findViewById(R.id.edMaTT);
        edHoTen=dialog.findViewById(R.id.edHoTenTT);
        edMatKhau=dialog.findViewById(R.id.edMatKhauTT);
        btnSave=dialog.findViewById(R.id.btnSaveTT);
        btnCancel=dialog.findViewById(R.id.btnCancelTT);

        if(type!=0){
            edMaTT.setEnabled(false);
            edMaTT.setText(item.maTT);
            edHoTen.setText(item.hoTen);
            edMatKhau.setText(item.matKhau);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item=new ThuThu();
                item.maTT=edMaTT.getText().toString();
                item.hoTen=edHoTen.getText().toString();
                item.matKhau=edMatKhau.getText().toString();

                if(validate()>0){
                    if(type==0){
                        boolean checkTT=dao.checkUsername(item.maTT);
                        if(checkTT==false) {
                            if (dao.insert(item) > 0) {
                                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(context, "Mã thủ thư đã tồn tại", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(context,"Cập nhật thất bại",Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public void capNhatLv(){
        list=(ArrayList<ThuThu>) dao.getAll();
        adapter=new ThemThuThuAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);

    }
    public void xoa(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Chắc chắn xóa?");
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
    public int validate(){
        int check=1;
        if(edMaTT.getText().length()==0 || edHoTen.getText().length()==0 || edMatKhau.getText().length()==0){
            Toast.makeText(getContext(),"Phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;

    }
}