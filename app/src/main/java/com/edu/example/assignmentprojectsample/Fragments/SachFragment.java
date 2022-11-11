package com.edu.example.assignmentprojectsample.Fragments;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Adapter.LoaiSachSpinnerAdapter;
import com.edu.example.assignmentprojectsample.Adapter.SachAdapter;
import com.edu.example.assignmentprojectsample.Dao.LoaiSachDao;
import com.edu.example.assignmentprojectsample.Dao.SachDao;
import com.edu.example.assignmentprojectsample.Models.LoaiSach;
import com.edu.example.assignmentprojectsample.Models.Sach;
import com.edu.example.assignmentprojectsample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class SachFragment extends Fragment {

    private ListView lv;
    private ArrayList<Sach>list;
    private FloatingActionButton fab;
    private Dialog dialog;
    private TextView tvMaSach;
    private EditText edTenSach,edGiaThue;
    private Spinner spinner;
    private Button btnSave,btnCancel;
    static SachDao dao;
    private SachAdapter adapter;
    private Sach item;


    private LoaiSachSpinnerAdapter spinnerAdapter;
    private ArrayList<LoaiSach>listLoaiSach;
    private LoaiSachDao loaiSachDao;
    private LoaiSach loaiSach;
    int maLoaiSach,position;


    public SachFragment() {
        // Required empty public constructor
    }


    public static SachFragment newInstance() {
        SachFragment fragment = new SachFragment();

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
        View view=inflater.inflate(R.layout.sach_fragment, container, false);

        lv=view.findViewById(R.id.lvSach);
        fab=view.findViewById(R.id.fabButtonSach);
        dao=new SachDao(getActivity());
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
        dialog.setContentView(R.layout.dialog_sach);
        tvMaSach=dialog.findViewById(R.id.tvMaSach);
        edTenSach=dialog.findViewById(R.id.edTenSach);
        edGiaThue=dialog.findViewById(R.id.edGiaThue);
        spinner=dialog.findViewById(R.id.spListLoaiSach);
        btnSave=dialog.findViewById(R.id.btnSaveSach);
        btnCancel=dialog.findViewById(R.id.btnCancelSach);


        listLoaiSach=new ArrayList<LoaiSach>();
        loaiSachDao=new LoaiSachDao(context);
        listLoaiSach=(ArrayList<LoaiSach>)loaiSachDao.getAll();

        spinnerAdapter=new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach=listLoaiSach.get(position).maLoai;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(type!=0){
            tvMaSach.setText(String.valueOf(item.maSach));
            edTenSach.setText(item.tenSach);
            edGiaThue.setText(String.valueOf(item.giaThue));

            for (int i=0;i<listLoaiSach.size();i++){
                if(item.maLoai==(listLoaiSach.get(i).maLoai)){
                    position=i;
                }
            }
            Log.i("demo","posSach"+position);
            spinner.setSelection(position);

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
                item=new Sach();
                item.tenSach=edTenSach.getText().toString();
                try {
                    item.giaThue = Integer.parseInt(edGiaThue.getText().toString());
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context,"Chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                item.maLoai=maLoaiSach;

                if(validate()>0){
                    if(type==0){
                        try {
                            if (dao.insert(item) > 0) {
                                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maSach= parseInt(tvMaSach.getText().toString());
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
        dialog.show();

    }

    public void xoa(final  String id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
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
    private void capNhatLv(){
        list=(ArrayList<Sach>) dao.getAll();
        adapter=new SachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);

    }

    public int validate(){
        int check=1;
        if(edTenSach.getText().length()==0 || edGiaThue.getText().length()==0){
            Toast.makeText(getContext(),"Phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;

        }
        return check;

    }

}