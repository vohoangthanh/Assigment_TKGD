package com.example.assigment_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment_tkgd.database.KhoanThuChiDatabase;
import com.example.assigment_tkgd.models.Khoan;

import java.util.ArrayList;


public class KhoanDAO {
    private KhoanThuChiDatabase db;

    public KhoanDAO(Context context) {
        db = new KhoanThuChiDatabase(context);
    }

    public ArrayList<Khoan> layDSKhoan(int trangthai) {
        ArrayList<Khoan> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        /*
         * SELECT khoan.*, loai.tenloai
         * FROM LOAI loai, KHOAN khoan
         * WHERE loai.idloai = khoan.idloai
         *   AND khoan.idloai IN (SELECT idloai FROM loai WHERE trangthai = ?)
         *
         * */

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT KHOAN.*, LOAI.TEN" +
                "        FROM LOAI loai, KHOAN khoan" +
                "         WHERE loai.MALOAI = khoan.MALOAI" +
                "          AND khoan.MALOAI IN (SELECT MALOAI FROM LOAI WHERE TRANGTHAI = ?)", new String[]{String.valueOf(trangthai)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Khoan(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themKhoan(Khoan khoan) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEN", khoan.getTenKhoan());
        contentValues.put("TIEN", khoan.getTien());
        contentValues.put("NGAY", khoan.getNgay());
        contentValues.put("MALOAI", khoan.getIdLoai());
        long check = sqLiteDatabase.insert("KHOAN", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;

    }

    public boolean capNhatKhoan(Khoan khoan) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase(); //viết updtae
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEN", khoan.getTenKhoan());
        contentValues.put("TIEN", khoan.getTien());
        contentValues.put("NGAY", khoan.getNgay());
        contentValues.put("MALOAI", khoan.getIdLoai());
        long rows = sqLiteDatabase.update("KHOAN", contentValues, " MAKHOAN = ?", new String[]{String.valueOf(khoan.getIdKhoan())});
        if (rows == -1)
            return false;
        return true;
    }

    public boolean xoaKhoan(int idKhoan) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        long check = sqLiteDatabase.delete("KHOAN", "MAKHOAN = ?", new String[]{String.valueOf(idKhoan)});
        if (check == -1) {
            return false;
        }
        return true;
    }
}
