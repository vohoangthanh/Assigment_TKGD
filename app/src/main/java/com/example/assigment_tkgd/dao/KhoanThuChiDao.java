package com.example.assigment_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment_tkgd.database.KhoanThuChiDatabase;
import com.example.assigment_tkgd.models.Loai;

import java.util.ArrayList;

public class KhoanThuChiDao {
        KhoanThuChiDatabase khoanThuChiDatabase;
        public KhoanThuChiDao(Context context){
            khoanThuChiDatabase = new KhoanThuChiDatabase(context);
        }

        // lấy danh sách Loại THU/CHI
        public ArrayList<Loai> getDSLoai(String loai){
            ArrayList<Loai> list = new ArrayList<>();
            SQLiteDatabase db = khoanThuChiDatabase.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM LOAIS",null);

            try {
                if (cursor.getCount() > 0){
                    cursor.moveToFirst();
                    do {
                        String trangthai = cursor.getString(2);
                        if (trangthai.equals(loai)){
                            list.add(new Loai(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
                        }
                    }while (cursor.moveToNext());
                }
            }catch (Exception e){
                Log.d(">>>>>>>>>>>>>>TAG", "getDSLoai: "+e.getMessage());
            }finally {
                if (cursor != null && !cursor.isClosed()) cursor.close();{

                }
            }
            return list;
        }

        //Thêm loại thu chi Insert
        public boolean inSert(Loai loai){
            Boolean result = true;
            SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết insert
            db.beginTransaction();// bắt đầu tương tác database

            try {
                ContentValues values = new ContentValues();
                values.put("MALOAI", loai.getMaloai());
                values.put("NAME", loai.getName());
                values.put("STATUS", loai.getStatus());
                Long rows = db.insertOrThrow("LOAIS",null,values);
                result = rows>=1;

            }catch (Exception e){
                Log.d(">>>>>>>>>>>>>>> Tag","insert"+ e.getMessage());
            }finally {
                db.endTransaction();// đóng dao dịch
            }
            return result;
        }
        // cập nhật Update
    public Boolean update(Loai loai){
        Boolean result = true;
        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết updtae
        db.beginTransaction();// bắt đầu tương tác database

        try {
            ContentValues values = new ContentValues();
            values.put("MALOAI", loai.getMaloai());
            values.put("NAME", loai.getName());
            values.put("STATUS", loai.getStatus());
            Integer rows = db.update("LOAIS",values," MALOAI = ?", new String[]{loai.getMaloai().toString()});
            result = rows>=1;
            db.setTransactionSuccessful(); // xác nhận thành công

        }catch (Exception e){
            Log.d(">>>>>>>>>>>>>>> Tag","UpDate"+ e.getMessage());
        }finally {
            db.endTransaction();// đóng dao dịch
        }
        return result;
    }
    // delete from student where id= ...
    public Boolean delete(Integer maloai){
        Boolean result = true;
        SQLiteDatabase db = khoanThuChiDatabase.getWritableDatabase(); //viết updtae
        db.beginTransaction();// bắt đầu tương tác database

        try {

            Integer rows = db.delete("LOAIS"," ID = ?", new String[]{maloai.toString()});
            result = rows>=1;
            db.setTransactionSuccessful(); // xác nhận thành công

        }catch (Exception e){
            Log.d(">>>>>>>>>>>>>>> Tag","delete: "+ e.getMessage());
        }finally {
            db.endTransaction();// đóng dao dịch
        }
        return result;
    }
}
