package com.edu.example.assignmentprojectsample.Models;

import java.util.Date;

public class PhieuMuon {
   public int maPM,maTV,maSach,tienThue,traSach;
   public String maTT;
   public Date ngay;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, int maTV, int maSach, int tienThue, int traSach, String maTT, Date ngay) {
        this.maPM = maPM;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.maTT = maTT;
        this.ngay = ngay;
    }
}
