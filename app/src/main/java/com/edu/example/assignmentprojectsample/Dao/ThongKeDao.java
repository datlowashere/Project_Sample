package com.edu.example.assignmentprojectsample.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.example.assignmentprojectsample.Database.DBHelper;
import com.edu.example.assignmentprojectsample.Models.Sach;
import com.edu.example.assignmentprojectsample.Models.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    private List<Top> list;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao(Context context) {
        this.context = context;
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }

//    Top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sqlTop="select maSach,count(maSach) as soLuong from PhieuMuon group by maSach order by soLuong desc limit 10";
        list=new ArrayList<>();
        SachDao sachDao=new SachDao(context);
        Cursor c=db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top top=new Top();
            Sach sach=sachDao.getID(c.getString(c.getColumnIndex("maSach")));
            top.tenSach=sach.tenSach;
            top.soLuong=c.getInt(c.getColumnIndex("soLuong"));
            list.add(top);
        }
        return list;

    }

//    DoanhThu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT sum (tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list=new ArrayList<>();
        Cursor c=db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(c.getInt(c.getColumnIndex("doanhThu")));

            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
