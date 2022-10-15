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

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

    public Integer[] getSoLuong(){
        String sql="select maSach, count(maSach) as soLuong from PhieuMuon group by maSach order by soLuong desc limit 10";
        ArrayList<Integer> soLuong=new ArrayList<>();
        Cursor c=db.rawQuery(sql,null);
        if (c.getCount()>0){
            c.moveToFirst();
            do{
                soLuong.add(c.getInt(1));
            }while (c.moveToNext());
        }
        return soLuong.toArray(new Integer[soLuong.size()]);
    }
    public int getTongSoLuong(){
//        String sql="select   SUM(COUNT(masach)) OVER() as tong from PhieuMuon group by maSach order by COUNT(masach) desc limit 10";
        String sql="SELECT  t.*, SUM(soLuong)  AS tongMuon FROM ( SELECT  COUNT(maSach) AS soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10) t";
        Cursor c= db.rawQuery(sql,null);
        int tong=0;
        if(c.getCount()>0){
            c.moveToFirst();
            tong=c.getInt(1);
        }
        int kq=tong;

        return kq;
    }

    public String[] getTenTop(){
        ArrayList<String> ten=new ArrayList<>();
        Cursor c=db.rawQuery("select  s.tenSach ,k.maSach , count(k.maSach)   as soLuong  from Sach s, PhieuMuon k where s.maSach=k.maSach group by k.maSach order by soLuong desc limit 10",null);
        if (c.getCount()>0){
            c.moveToFirst();
            do{
                ten.add(c.getString(0));
            }while (c.moveToNext());
        }
        return ten.toArray(new String[ten.size()]);
    }


    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT sum (tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list=new ArrayList<>();
        Cursor c=db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
                list.add(c.getInt(0));
        }
        return list.get(0);
    }
}
