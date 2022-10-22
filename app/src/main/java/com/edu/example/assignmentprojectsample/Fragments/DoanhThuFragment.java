package com.edu.example.assignmentprojectsample.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.example.assignmentprojectsample.Dao.ThongKeDao;
import com.edu.example.assignmentprojectsample.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThuFragment extends Fragment {

    private TextView tvDoanhThu;
    private TextView  tvTuNgay,tvDenNgay;
    private Button btnTuNgay,btnDenNgay,btnDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear,mMonth,mDate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.doanh_thu_fragment_, container, false);
        tvTuNgay=view.findViewById(R.id.tvTuNgay);
        tvDenNgay=view.findViewById(R.id.tvDenNgay);
        tvDoanhThu=view.findViewById(R.id.tvDoanhThu);
        btnTuNgay=view.findViewById(R.id.btnTugay);
        btnDenNgay=view.findViewById(R.id.btnDenNgay);
        btnDoanhThu=view.findViewById(R.id.btnDoanhThu);

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDate=c.get(Calendar.DATE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog d=new DatePickerDialog(getActivity(),0,tuNgay,mYear,mMonth,mDate);
                    d.show();
                }


            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDate=c.get(Calendar.DATE);


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog d=new DatePickerDialog(getActivity(),0,denNgay,mYear,mMonth,mDate);
                    d.show();
                }
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay=tvTuNgay.getText().toString();
                String denNgay=tvDenNgay.getText().toString();

                ThongKeDao dao=new ThongKeDao(getActivity());
                tvDoanhThu.setText("Tổng: "+dao.getDoanhThu(tuNgay,denNgay)+"VNĐ");

            }
        });


        return view;
    }

    DatePickerDialog.OnDateSetListener tuNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDate=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDate);
            tvTuNgay.setText(sdf.format(c.getTime()));

        }
    };

    DatePickerDialog.OnDateSetListener denNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDate=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDate);
            tvDenNgay.setText(sdf.format(c.getTime()));

        }
    };


}