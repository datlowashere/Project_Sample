package com.edu.example.assignmentprojectsample.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.example.assignmentprojectsample.Database.DBHelper;
import com.edu.example.assignmentprojectsample.Models.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDao {
    private SQLiteDatabase db;
    private List<LoaiSach> list;


    public LoaiSachDao(Context context) {
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
//  Thêm
    public long insert(LoaiSach obj){
        ContentValues values=new ContentValues();
        values.put("tenLoai",obj.tenLoai);

        return db.insert("LoaiSach",null,values);
    }
//  Sửa
    public int update(LoaiSach obj){
        ContentValues values=new ContentValues();
        values.put("tenLoai",obj.tenLoai);

        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(obj.maLoai)});
    }
//    Xóa
    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }

//    Lấy tất vả danh sách
    public List<LoaiSach> getAll(){
        String sql="select * from LoaiSach";
        return getData(sql);
    }

//  Lấy theo ID
    public LoaiSach getID(String id){
        String sql="select * from LoaiSach where maLoai=?";
        list=getData(sql,id);
        return list.get(0);
    }

//Lấy Data nhiều tham số
    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs){
        list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj=new LoaiSach();
            obj.maLoai=c.getInt(c.getColumnIndex("maLoai"));
            obj.tenLoai=c.getString(c.getColumnIndex("tenLoai"));
            list.add(obj);
        }
        return list;
    }
}
