package vn.edu.uit.trang.shopping.models;

import java.io.Serializable;

public class DanhMuc implements Serializable {
    private String maNhomDM;
    private String maDanhMuc;
    private String tenDanhMuc;
    private String hinh;

    public DanhMuc() {
    }

    public DanhMuc(String maDanhMuc, String tenDanhMuc, String hinh) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.hinh = hinh;
       // this.maNhomDM = null;
    }

//    public DanhMuc(String maNhomDM, String maDanhMuc, String tenDanhMuc, String hinh) {
//        this.maNhomDM = maNhomDM;
//        this.maDanhMuc = maDanhMuc;
//        this.tenDanhMuc = tenDanhMuc;
//        this.hinh = hinh;
//    }

//    public String getMaNhomDM() {
//        return maNhomDM;
//    }

//    public void setMaNhomDM(String maNhomDM) {
//        this.maNhomDM = maNhomDM;
//    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
