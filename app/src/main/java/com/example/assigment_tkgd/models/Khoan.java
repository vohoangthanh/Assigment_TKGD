package com.example.assigment_tkgd.models;

public class Khoan {
    private int idKhoan;
    private String tenKhoan;
    private int tien;
    private  String ngay;
    private int idLoai;
    private String tenLoai;

    // thêm
    public Khoan(String tenKhoan, int tien, String ngay, int idLoai) {
        this.tenKhoan = tenKhoan;
        this.tien = tien;
        this.ngay = ngay;
        this.idLoai = idLoai;
    }


    // hiển thị danh sách
    public Khoan(int idKhoan, String tenKhoan, int tien, String ngay, int idLoai, String tenLoai) {
        this.idKhoan = idKhoan;
        this.tenKhoan = tenKhoan;
        this.tien = tien;
        this.ngay = ngay;
        this.idLoai = idLoai;
        this.tenLoai = tenLoai;
    }


    // Cập nhât
    public Khoan(int idKhoan, String tenKhoan, int tien, String ngay, int idLoai) {
        this.idKhoan = idKhoan;
        this.tenKhoan = tenKhoan;
        this.tien = tien;
        this.ngay = ngay;
        this.idLoai = idLoai;
    }

    public int getIdKhoan() {
        return idKhoan;
    }

    public void setIdKhoan(int idKhoan) {
        this.idKhoan = idKhoan;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}


