package vn.edu.uit.trang.shopping.models;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String maSanPham;
    private String tenSanPham;
    private String moTa;
    private double gia;
    private double giaCu;
    private double giamGia;
    private String hinh;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, String moTa, double gia, double giaCu, double giamGia, String hinh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.gia = gia;
        this.giaCu = giaCu;
        this.giamGia = giamGia;
        this.hinh = hinh;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getGiaCu() {
        return giaCu;
    }

    public void setGiaCu(double giaCu) {
        this.giaCu = giaCu;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }
}
