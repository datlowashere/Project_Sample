package com.edu.example.assignmentprojectsample.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.example.assignmentprojectsample.Database.DBHelper;
import com.edu.example.assignmentprojectsample.Models.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    private SQLiteDatabase db;
    private List<ThanhVien> list;


    public ThanhVienDao(Context context) {
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
//  Thêm
    public long insert(ThanhVien obj){
        ContentValues values=new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("namSinh",obj.namSinh);

        return db.insert("ThanhVien",null,values);
    }
//  Sửa
    public int update(ThanhVien obj){
        ContentValues values=new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("namSinh",obj.namSinh);

        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(obj.maTV)});
    }

//    Xóa
    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }


//    Lấy tất vả danh sách
    public List<ThanhVien> getAll(){
        String sql="select * from ThanhVien";
        return getData(sql);
    }


//  Lấy theo ID
    public ThanhVien getID(String id){
        String sql="select * from ThanhVien where maTV=?";
        list=getData(sql,id);
        return list.get(0);
    }



//Lấy Data nhiều tham số
    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String...selectionArgs){
        list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj=new ThanhVien();
            obj.maTV=c.getInt(c.getColumnIndex("maTV"));
            obj.hoTen=c.getString(c.getColumnIndex("hoTen"));
            obj.namSinh=c.getString(c.getColumnIndex("namSinh"));
            list.add(obj);
        }
        return list;
    }
}
