package com.example.assigment_tkgd.models;

public class LoaiThu {
    private Integer maloai;
    private String name;
    private String status;

    public LoaiThu() {
    }

    public LoaiThu(Integer maloai, String name, String status) {
        this.maloai = maloai;
        this.name = name;
        this.status = status;
    }

    public Integer getMaloai() {
        return maloai;
    }

    public void setMaloai(Integer maloai) {
        this.maloai = maloai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
