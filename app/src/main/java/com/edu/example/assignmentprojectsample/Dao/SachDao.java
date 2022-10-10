package com.edu.example.assignmentprojectsample.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.example.assignmentprojectsample.Database.DBHelper;
import com.edu.example.assignmentprojectsample.Models.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    private SQLiteDatabase db;
    private List<Sach> list;


    public SachDao(Context context) {
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
//  Thêm
    public long insert(Sach obj){
        ContentValues values=new ContentValues();
        values.put("tenSach",obj.tenSach);
        values.put("giaThue",obj.giaThue);
        values.put("maLoai",obj.maLoai);

        return db.insert("Sach",null,values);
    }
//  Sửa
    public int update(Sach obj){
        ContentValues values=new ContentValues();
        values.put("tenSach",obj.tenSach);
        values.put("giaThue",obj.giaThue);
        values.put("maLoai",obj.maLoai);

        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(obj.maSach)});
    }

//    Xóa
    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }


//    Lấy tất vả danh sách
    public List<Sach> getAll(){
        String sql="select * from Sach";
        return getData(sql);
    }


//  Lấy theo ID
    public Sach getID(String id){
        String sql="select * from Sach where maSach=?";
        list=getData(sql,id);
        return list.get(0);
    }



//Lấy Data nhiều tham số
    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs){
        list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj=new Sach();
            obj.maSach=c.getInt(c.getColumnIndex("maSach"));
            obj.tenSach=c.getString(c.getColumnIndex("tenSach"));
            obj.giaThue=c.getInt(c.getColumnIndex("giaThue"));
            obj.maLoai=c.getInt(c.getColumnIndex("maLoai"));
            list.add(obj);
        }
        return list;
    }
}
