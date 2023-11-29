/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yoga.entitys;

import java.util.Date;

/**
 *
 * @author Hao
 */
public class KhuyenMai {

    private String MaKhuyenMai;
    private String TenKhuyenMai;
    private String NguoiTao;
    private Date NgayBD;
    private Date ngayKT;
    private int Trangthai;
    private int MucGiam;
    private String Mota;
    private boolean isDelete;

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKhuyenMai, String TenKhuyenMai, String NguoiTao, Date NgayBD, Date ngayKT, int Trangthai, int MucGiam, String Mota, boolean isDelete) {
        this.MaKhuyenMai = MaKhuyenMai;
        this.TenKhuyenMai = TenKhuyenMai;
        this.NguoiTao = NguoiTao;
        this.NgayBD = NgayBD;
        this.ngayKT = ngayKT;
        this.Trangthai = Trangthai;
        this.MucGiam = MucGiam;
        this.Mota = Mota;
        this.isDelete = isDelete;
    }

    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(String MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return TenKhuyenMai;
    }

    public void setTenKhuyenMai(String TenKhuyenMai) {
        this.TenKhuyenMai = TenKhuyenMai;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(int Trangthai) {
        this.Trangthai = Trangthai;
    }

    public int getMucGiam() {
        return MucGiam;
    }

    public void setMucGiam(int MucGiam) {
        this.MucGiam = MucGiam;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    
}
