package com.edu.example.assignmentprojectsample.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Dao.ThuThuDao;
import com.edu.example.assignmentprojectsample.Models.ThuThu;
import com.edu.example.assignmentprojectsample.R;
import com.google.android.material.textfield.TextInputEditText;


public class DoiMatKhauFragment extends Fragment {

    TextInputEditText edOldPass, edNewPass, edReNewPass;
    ThuThuDao dao;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    public static DoiMatKhauFragment newInstance() {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        View view = inflater.inflate(R.layout.doi_mat_khau_fragment, container, false);
        edOldPass = view.findViewById(R.id.edOldPass);
        edNewPass = view.findViewById(R.id.edNewPass);
        edReNewPass = view.findViewById(R.id.edReNewPass);

        dao = new ThuThuDao(getActivity());


        view.findViewById(R.id.btnSaveNewPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("file", Context.MODE_PRIVATE);
                String user = pref.getString("username", "");

                if (validate() > 0) {
                    ThuThu thuThu = dao.getID(user);
                    thuThu.matKhau = edNewPass.getText().toString();
                    if (dao.update(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edOldPass.setText("");
                        edNewPass.setText("");
                        edReNewPass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        view.findViewById(R.id.btnCanceSaveNewPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edOldPass.setText("");
                edNewPass.setText("");
                edReNewPass.setText("");
            }
        });
        return view;
    }

    private int validate() {
        int check=1;
        if (edOldPass.getText().length()==0 || edNewPass.getText().length()==0 || edReNewPass.getText().length()==0){
            Toast.makeText(getActivity(),"Thông tin không được bỏ trống",Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("file", Context.MODE_PRIVATE);
            String oldPass=pref.getString("password","");
            String newPass=edNewPass.getText().toString();
            String rePass=edReNewPass.getText().toString();
            if(!oldPass.equals(edOldPass.getText().toString())){
                Toast.makeText(getActivity(),"Mật khẩu cũ không đúng",Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!newPass.equals(rePass)){
                Toast.makeText(getActivity(),"Mật khẩu mới không trùng",Toast.LENGTH_SHORT).show();
                check=-1;

            }
        }
        return check;


    }

}
