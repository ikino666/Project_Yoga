/*
Trần Trung Tín - PC07488
SD18309
 */
package com.yoga.entitys;

import java.util.Date;

public class KhoaHoc {
    private String maKHoc;
    private String tenKHoc;
    private int thoiLuong;
    private double hocPhi;
    private String hinhAnh;
    private String moTa;
    private String nguoiTao;
    private Date ngayTao;
    private boolean isDelete;

    public KhoaHoc(String maKHoc, String tenKHoc, int thoiLuong, double hocPhi, String hinhAnh, String moTa, String nguoiTao, Date ngayTao, boolean isDelete) {
        this.maKHoc = maKHoc;
        this.tenKHoc = tenKHoc;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.isDelete = isDelete;
    }

    public KhoaHoc() {
    }

    public String getMaKHoc() {
        return maKHoc;
    }

    public void setMaKHoc(String maKHoc) {
        this.maKHoc = maKHoc;
    }

    public String getTenKHoc() {
        return tenKHoc;
    }

    public void setTenKHoc(String tenKHoc) {
        this.tenKHoc = tenKHoc;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }


    
}
