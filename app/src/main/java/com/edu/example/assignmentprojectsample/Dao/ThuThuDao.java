package com.edu.example.assignmentprojectsample.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.example.assignmentprojectsample.Database.DBHelper;
import com.edu.example.assignmentprojectsample.Models.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    private SQLiteDatabase db;
    private List<ThuThu> list;



    public ThuThuDao(Context context) {
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
//  Thêm
    public long insert(ThuThu obj){
        ContentValues values=new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("hoTen",obj.hoTen);
        values.put("matKhau",obj.matKhau);

        return db.insert("ThuThu",null,values);
    }
//  Sửa
    public int update(ThuThu obj){
        ContentValues values=new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("matKhau",obj.matKhau);

        return db.update("ThuThu",values,"maTT=?",new String[]{obj.maTT});
    }

//    Xóa
    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }


//    Lấy tất vả danh sách
    public List<ThuThu> getAll(){
        String sql="select * from ThuThu";
        return getData(sql);
    }


//  Lấy theo ID
    public ThuThu getID(String id){
        String sql="select * from ThuThu where maTT=?";
        list=getData(sql,id);
        return list.get(0);
    }


//Lấy Data nhiều tham số
    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String...selectionArgs){
        list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj=new ThuThu();
            obj.maTT=c.getString(c.getColumnIndex("maTT"));
            obj.hoTen=c.getString(c.getColumnIndex("hoTen"));
            obj.matKhau=c.getString(c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }

// Đăng nhập

    public boolean checkUsername(String maTT){
        Cursor c=db.rawQuery("select * from ThuThu where maTT=?",new String[]{maTT});
        if(c.getCount()!=0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPassword(String matKhau){
        Cursor c=db.rawQuery("select * from ThuThu where matKhau=?",new String[]{matKhau});
        if(c.getCount()!=0){
            return true;
        }else{
            return false;
        }
    }
    public int checkLogin(String maTT,String matKhau){
        String sql="select * from ThuThu where maTT=? and matKhau=?";
        List<ThuThu> list=getData(sql,maTT,matKhau);
        if(list.size()==0){
            return -1;
        }
        return 1;
    }





}





