/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yoga.entitys;

/**
 *
 * @author Thinh
 */
public class SanPham {

    String MaSanPham;
    String TenSP;
    double GiaTien;
    int PhanLoai;
    String HinhAnh;
    String MoTa;
    private boolean isDelete;

    public SanPham(String MaSanPham, String TenSP, double GiaTien, int PhanLoai, String HinhAnh, String MoTa, boolean isDelete) {
        this.MaSanPham = MaSanPham;
        this.TenSP = TenSP;
        this.GiaTien = GiaTien;
        this.PhanLoai = PhanLoai;
        this.HinhAnh = HinhAnh;
        this.MoTa = MoTa;
        this.isDelete = isDelete;
    }

    public SanPham() {
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public double getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(double GiaTien) {
        this.GiaTien = GiaTien;
    }

    public int getPhanLoai() {
        return PhanLoai;
    }

    public void setPhanLoai(int PhanLoai) {
        this.PhanLoai = PhanLoai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    
}
