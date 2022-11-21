package com.example.assigment_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment_tkgd.database.KhoanThuChiDatabase;
import com.example.assigment_tkgd.models.LoaiThu;

import java.util.ArrayList;

public class KhoanThuChiDao {
    KhoanThuChiDatabase khoanThuChiDatabase;

    public KhoanThuChiDao(Context context) {
        khoanThuChiDatabase = new KhoanThuChiDatabase(context);
    }

    // lấy danh sách Loại THU/CHI
    public ArrayList<LoaiThu> getDSLoai(String loai) {
        ArrayList<LoaiThu> list = new ArrayList<>();
        SQLiteDatabase db = khoanThuChiDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT*FROM LOAITHUS", null);


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String status = cursor.getString(2);
                if (status.equals(loai)) {
                    list.add(new LoaiThu(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                }
            } while (cursor.moveToNext());
        }

        return list;
    }

    //Thêm loại thu chi Insert
    public boolean inSert(LoaiThu loai) {
        Boolean result = true;
        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết insert
        db.beginTransaction();// bắt đầu tương tác database

        try {
            ContentValues values = new ContentValues();
            values.put("MALOAI", loai.getMaloai());
            values.put("NAME", loai.getName());
            values.put("STATUS", loai.getStatus());
            Long rows = db.insertOrThrow("LOAITHUS", null, values);
            result = rows >= 1;

        } catch (Exception e) {
            Log.d(">>>>>>>>>>>>>>> Tag", "insert" + e.getMessage());
        } finally {
            db.endTransaction();// đóng dao dịch
        }
        return result;
    }

    // cập nhật Update
    public Boolean update(LoaiThu loai) {
        Boolean result = true;
        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết updtae
        db.beginTransaction();// bắt đầu tương tác database

        try {
            ContentValues values = new ContentValues();
            values.put("MALOAI", loai.getMaloai());
            values.put("NAME", loai.getName());
            values.put("STATUS", loai.getStatus());
            Integer rows = db.update("LOAITHUS", values, " MALOAI = ?", new String[]{loai.getMaloai().toString()});
            result = rows >= 1;
            db.setTransactionSuccessful(); // xác nhận thành công

        } catch (Exception e) {
            Log.d(">>>>>>>>>>>>>>> Tag", "UpDate" + e.getMessage());
        } finally {
            db.endTransaction();// đóng dao dịch
        }
        return result;
    }

    // delete from student where id= ...
    public Boolean delete(Integer maloai) {
        Boolean result = true;
        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết updtae
        db.beginTransaction();// bắt đầu tương tác database

        try {

            Integer rows = db.delete("LOAITHUS", " ID = ?", new String[]{maloai.toString()});
            result = rows >= 1;
            db.setTransactionSuccessful(); // xác nhận thành công

        } catch (Exception e) {
            Log.d(">>>>>>>>>>>>>>> Tag", "delete: " + e.getMessage());
        } finally {
            db.endTransaction();// đóng dao dịch
        }
        return result;
    }
}
