package com.edu.example.assignmentprojectsample.Models;

import java.util.Date;

public class PhieuMuon {

   public int maPM,tienThue;
   public Date ngay;
   public int traSach,maTV,maSach;
   public String maTT;


    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, int tienThue, Date ngay, int traSach, int maTV, int maSach, String maTT) {
        this.maPM = maPM;
        this.tienThue = tienThue;
        this.ngay = ngay;
        this.traSach = traSach;
        this.maTV = maTV;
        this.maSach = maSach;
        this.maTT = maTT;
    }
}
