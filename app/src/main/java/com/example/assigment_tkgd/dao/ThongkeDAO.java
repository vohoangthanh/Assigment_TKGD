package com.example.assigment_tkgd.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment_tkgd.database.KhoanThuChiDatabase;

public class ThongkeDAO {
    private KhoanThuChiDatabase dbHelper;
    public  ThongkeDAO(Context context){
        dbHelper = new KhoanThuChiDatabase(context);
    }
    public float[] getThongTinThuChi() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        int thu = 0, chi = 0;

        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 0)
        Cursor cursorThu = sqLiteDatabase.rawQuery("select sum(TIEN) from KHOAN where MALOAI in (select MALOAI from LOAI where TRANGTHAI = 0) ", null);

        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }

        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 1)
        Cursor cursorChi = sqLiteDatabase.rawQuery("select sum(TIEN) from KHOAN where MALOAI in (select MALOAI from LOAI where TRANGTHAI = 1)", null);

        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            chi = cursorChi.getInt(0);
        }

        float[] ketQua = new float[]{thu, chi};
        return ketQua;
    }

}
