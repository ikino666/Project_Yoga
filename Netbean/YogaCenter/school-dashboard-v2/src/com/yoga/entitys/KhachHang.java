/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yoga.entitys;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhachHang {

    private int MaKhachHang;
    private int MaLoaiKH;
    private String TenKhachHang;
    private boolean GioiTinh;
    private String DiaChi;
    private String Email;
    private String DienThoai;
    private Date NgayDK;
    private String GhiChu;
    private boolean isDelete;

    public KhachHang(int MaKhachHang, int MaLoaiKH, String TenKhachHang, boolean GioiTinh, String DiaChi, String Email, String DienThoai, Date NgayDK, String GhiChu, boolean isDelete) {
        this.MaKhachHang = MaKhachHang;
        this.MaLoaiKH = MaLoaiKH;
        this.TenKhachHang = TenKhachHang;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.DienThoai = DienThoai;
        this.NgayDK = NgayDK;
        this.GhiChu = GhiChu;
        this.isDelete = isDelete;
    }

    public KhachHang() {
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public int getMaLoaiKH() {
        return MaLoaiKH;
    }

    public void setMaLoaiKH(int MaLoaiKH) {
        this.MaLoaiKH = MaLoaiKH;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public void setNgayDK(Date NgayDK) {
        this.NgayDK = NgayDK;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}
