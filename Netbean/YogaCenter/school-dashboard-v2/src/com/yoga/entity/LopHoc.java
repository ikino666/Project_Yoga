/*
Trần Trung Tín - PC07488
SD18309
 */
package com.yoga.entity;

public class LopHoc {
    private String maLHoc;
    private String maKHoc;
    private String maHLV;
    private int soLuongHocVien;
    private int trangThai;
    private String ngayBatDau;
    private String gioHoc;
    private boolean isDelete;

    public LopHoc(String maLHoc, String maKHoc, String maHLV, int soLuongHocVien, int trangThai, String ngayBatDau, String gioHoc, boolean isDelete) {
        this.maLHoc = maLHoc;
        this.maKHoc = maKHoc;
        this.maHLV = maHLV;
        this.soLuongHocVien = soLuongHocVien;
        this.trangThai = trangThai;
        this.ngayBatDau = ngayBatDau;
        this.gioHoc = gioHoc;
        this.isDelete = isDelete;
    }

    public LopHoc() {
    }

    public String getMaLHoc() {
        return maLHoc;
    }

    public void setMaLHoc(String maLHoc) {
        this.maLHoc = maLHoc;
    }

    public String getMaKHoc() {
        return maKHoc;
    }

    public void setMaKHoc(String maKHoc) {
        this.maKHoc = maKHoc;
    }

    public String getMaHLV() {
        return maHLV;
    }

    public void setMaHLV(String maHLV) {
        this.maHLV = maHLV;
    }

    public int getSoLuongHocVien() {
        return soLuongHocVien;
    }

    public void setSoLuongHocVien(int soLuongHocVien) {
        this.soLuongHocVien = soLuongHocVien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getGioHoc() {
        return gioHoc;
    }

    public void setGioHoc(String gioHoc) {
        this.gioHoc = gioHoc;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    
    
}
