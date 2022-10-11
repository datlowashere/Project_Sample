package com.edu.example.assignmentprojectsample.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatimeHelper {

    private static  final String pattern="dd/MM/yyyy";
    public static Date toDate(String st) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.parse(st);

    }
    public  static String toString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
