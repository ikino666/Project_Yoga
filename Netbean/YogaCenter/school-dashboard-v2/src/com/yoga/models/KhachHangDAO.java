package com.yoga.models;

import com.yoga.entitys.KhachHang;
import com.yoga.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO implements YogaCenterDAO<KhachHang, Integer> {

    String INSERT_SQL = "insert into KHACHHANG(MaLoaiKH, TenKhachHang, GioiTinh, DiaChi, Email, DienThoai, NgayDK, GhiChu )values(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "update KHACHHANG set MaLoaiKH = ?, TenKhachHang = ?, GioiTinh = ?, DiaChi = ?, Email = ?, DienThoai = ?, NgayDK = ?, GhiChu = ? where MaKhachHang = ?";
    String DELETE_SQL = "delete from KHACHHANG where MaKhachHang = ?";
    String SELECT_ALL_SQL = "select * from KHACHHANG";
    String SELECT_BY_ID = "select * from KHACHHANG where MaKhachHang = ?";
    String SELECT_BY_PHONE = "SELECT * FROM KHACHHANG WHERE DienThoai=?";
    String SELECT_BY_EMAIL = "SELECT * FROM KHACHHANG WHERE Email=?";

    @Override
    public void insert(KhachHang entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaLoaiKH(),
                entity.getTenKhachHang(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getEmail(),
                entity.getDienThoai(),
                entity.getNgayDK(),
                entity.getGhiChu());
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaLoaiKH(),
                entity.getTenKhachHang(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getEmail(),
                entity.getDienThoai(),
                entity.getNgayDK(),
                entity.getGhiChu(),
                entity.getMaKhachHang());
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public KhachHang selectById(Integer key) {
        List<KhachHang> list = selectBySQL(SELECT_BY_ID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public KhachHang selectBySDT(String key) {
        List<KhachHang> list = selectBySQL(SELECT_BY_PHONE, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public KhachHang selectByEmail(String key) {
        List<KhachHang> list = selectBySQL(SELECT_BY_EMAIL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKhachHang(rs.getInt("MaKhachHang"));
                entity.setMaLoaiKH(rs.getInt("MaLoaiKH"));
                entity.setTenKhachHang(rs.getNString("TenKhachHang"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDiaChi(rs.getNString("DiaChi"));
                entity.setEmail(rs.getString("Email"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                entity.setGhiChu(rs.getNString("GhiChu"));
                entity.setIsDelete(rs.getBoolean("IsDelete"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<KhachHang> selectByKeyword(String keyword) {
        String SQL = "select * from KHACHHANG WHERE TenKhachHang LIKE ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }
    
    
    public List<KhachHang> searchSDT(String keyword) {
        String SQL = "select * from KHACHHANG where DienThoai like ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }
    
    public List<KhachHang> searchTen(String keyword) {
        String SQL = "select * from KHACHHANG where TenKhachHang like ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }
    
    public List<KhachHang> filterDate() {
        String SQL = "select * from KHACHHANG order by NgayDK";
        return this.selectBySQL(SQL);
    }
    
    public List<KhachHang> filterCode() {
        String SQL = "select * from KHACHHANG order by MaKhachHang desc";
        return this.selectBySQL(SQL);
    }
    
    public List<KhachHang> filterGender() {
        String SQL = "select * from KHACHHANG order by GioiTinh desc";
        return this.selectBySQL(SQL);
    }
    
    

}
