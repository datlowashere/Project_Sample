package com.edu.example.assignmentprojectsample.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "PNLib", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTBTT="create table ThuThu(" +
                "maTT text primary key ," +
                "hoTen text not null," +
                "matKhau text not null)";

        String createTBThanhVien="CREATE TABLE ThanhVien" +
                "(" +
                "  maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  hoTen Text NOT NULL," +
                "  namSinh text NOT NULL)";

        String createTBLoaiSach="CREATE TABLE LoaiSach(" +
                "  maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  tenLoai TEXT NOT NULL)";

        String createTBSach="CREATE TABLE Sach(" +
                "  maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  tenSach TEXT NOT NULL," +
                "  giaThue INTEGER NOT NULL," +
                "  maLoai INTEGER NOT NULL," +
                "  FOREIGN KEY (maLoai) REFERENCES LoaiSach(maLoai)" +
                ")";
        String createTBPhieuMuon="CREATE TABLE PhieuMuon(" +
                "  maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  tienThue INTEGER NOT NULL," +
                "  ngay DATE NOT NULL," +
                "  traSach INTEGER NOT NULL," +
                "  maTV INTEGER NOT NULL," +
                "  maSach INTEGER NOT NULL," +
                "  maTT TEXT NOT NULL," +
                "  FOREIGN KEY (maTV) REFERENCES ThanhVien(maTV)," +
                "  FOREIGN KEY (maSach) REFERENCES Sach(maSach)," +
                "  FOREIGN KEY (maTT) REFERENCES ThuThu(maTT)" +
                ")";

        db.execSQL(createTBTT);
        db.execSQL(createTBThanhVien);
        db.execSQL(createTBLoaiSach);
        db.execSQL(createTBSach);
        db.execSQL(createTBPhieuMuon);


        db.execSQL("insert into ThuThu values('abc','ly ly','123')");
        db.execSQL("insert into ThuThu values('admin','Admin','admin')");
        db.execSQL("insert into ThuThu values('cda','Cô Em Hồ Ly','123')");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropTBTT="DROP TABLE if exists ThuThu";
        String dropThanhVien="DROP TABLE if exists ThanhVien";
        String dropLoaiSach="DROP TABLE if exists LoaiSach";
        String dropSach="DROP TABLE if exists Sach";
        String dropPhieuMuon="DROP TABLE if exists PhieuMuon";

        db.execSQL(dropTBTT);
        db.execSQL(dropThanhVien);
        db.execSQL(dropLoaiSach);
        db.execSQL(dropSach);
        db.execSQL(dropPhieuMuon);

        onCreate(db);

    }
}
