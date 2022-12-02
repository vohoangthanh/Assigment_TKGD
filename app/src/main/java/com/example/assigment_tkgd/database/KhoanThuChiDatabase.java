package com.example.assigment_tkgd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KhoanThuChiDatabase extends SQLiteOpenHelper {

    public KhoanThuChiDatabase(Context context) {
        super(context, "KHOANTHUCHIDATABASE", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlLoai = "CREATE TABLE  LOAITHUS(MALOAI INTEGER PRIMARY KEY AUTOINCREMENT ,TEN TEXT ,TRANGTHAI INTEGER)";
        db.execSQL(sqlLoai);

        String sqlThuChi = "CREATE TABLE  KHOANTHUS(MAKHOAN INTEGER PRIMARY KEY AUTOINCREMENT ,TIEN INTEGER ,MALOAI INTEGER)";
        db.execSQL(sqlThuChi);

        String dataLoai = "INSERT INTO LOAITHUS VALUES (1, 'Mua sắm', 1),(2, 'Từ thiện', 1), (3, 'Xăng dầu', 1), (4, 'Lương', 0), (5, 'Thưởng', 0)";
        db.execSQL(dataLoai);
        String dataKhoan = "INSERT INTO KHOANTHUS VALUES (1, 'Hỗ trợ miền Trung', 500000, '17/11/2022', 2), (2, 'Xăng xe máy', 90000, '17/11/2022', 3), (3, 'Thưởng Tết', 1500000, '24/11/2022', 5), (4, 'Mua đồ Tết', 790000, '25/11/2022', 1)";
        db.execSQL(dataKhoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("DROP TABLE IF EXISTS LOAITHUS");
            db.execSQL("DROP TABLE IF EXISTS KHOANTHUS");
            onCreate(db);
        }
    }
}
