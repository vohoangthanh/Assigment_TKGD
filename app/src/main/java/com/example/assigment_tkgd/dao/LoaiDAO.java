package com.example.assigment_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment_tkgd.database.KhoanThuChiDatabase;
import com.example.assigment_tkgd.models.LoaiThu;

import java.util.ArrayList;

public class LoaiDAO {
    private KhoanThuChiDatabase khoanThuChiDatabase;

    public LoaiDAO(Context context) {
        khoanThuChiDatabase = new KhoanThuChiDatabase(context);
    }

    // lấy danh sách Loại theo trang thai
    // 0: thu -  1:chi
    public ArrayList<LoaiThu> LayDSLoai(int trangthai) {
        ArrayList<LoaiThu> list = new ArrayList<>();
        SQLiteDatabase db = khoanThuChiDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LOAI WHERE TRANGTHAI = ?", new String[]{String.valueOf(trangthai)});


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiThu(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //Thêm loại thu chi Insert
    public boolean inSert(LoaiThu loai) {
        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết insert
        ContentValues values = new ContentValues();
        values.put("TEN", loai.getTenLoai());
        values.put("TRANGTHAI", loai.getTrangthai());
        long rows = db.insert("LOAI", null, values);
        if (rows == -1)
            return false;
        return true;
    }

    // cập nhật Update
    public Boolean update(LoaiThu loai) {

        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết updtae
        ContentValues values = new ContentValues();
        values.put("TEN", loai.getTenLoai());
        values.put("TRANGTHAI", loai.getTrangthai());
        long rows = db.update("LOAI", values, " MALOAI = ?", new String[]{String.valueOf(loai.getIdLoai())});
        if (rows == -1)
            return false;
        return true;


    }

    // delete from student where id= ...
    public Boolean delete(Integer maloai) {

        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết updtae

        long rows = db.delete("LOAI", " MALOAI = ?", new String[]{String.valueOf(maloai)});
        if (rows == -1)
            return false;
        return true;

    }
}
