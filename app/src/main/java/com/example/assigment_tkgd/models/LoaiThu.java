package com.example.assigment_tkgd.models;

public class LoaiThu {
    private Integer idLoai;
    private String tenLoai;
    private int trangthai;

    public LoaiThu() {
    }

    public LoaiThu(Integer idLoai, String tenLoai, int trangthai) {
        this.idLoai = idLoai;
        this.tenLoai = tenLoai;
        this.trangthai = trangthai;
    }

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
