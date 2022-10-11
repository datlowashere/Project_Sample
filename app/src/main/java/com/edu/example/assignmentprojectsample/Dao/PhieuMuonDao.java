package com.edu.example.assignmentprojectsample.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.example.assignmentprojectsample.Database.DBHelper;
import com.edu.example.assignmentprojectsample.Models.PhieuMuon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao {
    private SQLiteDatabase db;
    private List<PhieuMuon> list;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");



    public PhieuMuonDao(Context context) {
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
//  Thêm
    public long insert(PhieuMuon obj){


//        maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
//        "  tienThue INTEGER NOT NULL," +
//                "  ngay DATE NOT NULL," +
//                "  traSach INTEGER NOT NULL," +
//                "  maTV INTEGER REFERENCES ThanhVien(maTV)," +
//                "  maSach INTEGER REFERENCES Sach(maSach)," +
//                "  maTT TEXT REFERENCES ThuThu(maTT)
        ContentValues values=new ContentValues();
        values.put("tienThue",obj.tienThue);
        values.put("ngay", sdf.format(obj.ngay));
        values.put("traSach",obj.traSach);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("maTT",obj.maTT);

        return db.insert("PhieuMuon",null,values);
    }
//  Sửa
    public int update(PhieuMuon obj){
        ContentValues values=new ContentValues();

        values.put("tienThue",obj.tienThue);
        values.put("ngay", sdf.format(obj.ngay));
        values.put("traSach",obj.traSach);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("maTT",obj.maTT);


        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.maPM)});
    }

//    Xóa
    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }


//    Lấy tất vả danh sách
    public List<PhieuMuon> getAll(){
        String sql="select * from PhieuMuon";
        return getData(sql);
    }


//  Lấy theo ID
    public PhieuMuon getID(String id){
        String sql="select * from PhieuMuon where maPM=?";
        list=getData(sql,id);
        return list.get(0);
    }



//Lấy Data nhiều tham số
    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj=new PhieuMuon();
            obj.maPM=c.getInt(c.getColumnIndex("maPM"));
            obj.tienThue=c.getInt(c.getColumnIndex("tienThue"));
            obj.traSach=c.getInt(c.getColumnIndex("traSach"));
            try {
                obj.ngay=sdf.parse(c.getString(c.getColumnIndex("ngay")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            obj.maTT=c.getString(c.getColumnIndex("maTT"));
            obj.maTV=c.getInt(c.getColumnIndex("maTV"));
            obj.maSach=c.getInt(c.getColumnIndex("maSach"));
            list.add(obj);
        }
        return list;
    }
}
