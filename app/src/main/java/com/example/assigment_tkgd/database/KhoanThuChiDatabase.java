package com.example.assigment_tkgd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KhoanThuChiDatabase extends SQLiteOpenHelper {

    public KhoanThuChiDatabase(Context context){
        super(context,"KHOANTHUCHIDATABASE",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlLoai ="CREATE TABLE  LOAITHUS(MALOAI INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT ,STATUS TEXT)";
        db.execSQL(sqlLoai);

        String sqlThuChi = "CREATE TABLE  KHOANTHUS(MAKHOAN INTEGER PRIMARY KEY AUTOINCREMENT ,TIEN INTEGER ,MALOAI INTEGER)";
        db.execSQL(sqlThuChi);

        String insLoai = "INSERT INTO LOAITHUS VALUES(1,'Tiền Bánh','Thu'),(2,'Tiền Kẹo','Chi'),(3,'Tiền Kẹo','Thu')";
        db.execSQL(insLoai);

        String insKThuChi = "INSERT INTO KHOANTHUS VALUES(1,200,3),(2,400,2),(3,600,1)";
        db.execSQL(insKThuChi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i!=i1){
            db.execSQL("DROP TABLE IF EXISTS LOAITHUS");
            db.execSQL("DROP TABLE IF EXISTS KHOANTHUS");
            onCreate(db);
        }
    }
}
