package com.yoga.models;

import com.yoga.entitys.SanPham;
import com.yoga.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Kido H
 */
public class SanPhamDAO implements YogaCenterDAO<SanPham, String> {

    final String INSERT_SQL = "INSERT INTO SANPHAM (MaSanPham, TenSP, GiaTien, PhanLoai, HinhAnh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE SANPHAM SET TenSP=?, GiaTien=?, PhanLoai=?, HinhAnh=?, MoTa=? WHERE MaSanPham=?";
    final String DELETE_SQL = "DELETE FROM SANPHAM WHERE MaSanPham=?";
    final String SELECT_ALL_SQL = "SELECT * FROM SANPHAM";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SANPHAM WHERE MaSanPham=?";

    @Override
    public void insert(SanPham entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaSanPham(),
                entity.getTenSP(),
                entity.getGiaTien(),
                entity.getPhanLoai(),
                entity.getHinhAnh(),
                entity.getMoTa());
    }

    @Override
    public void update(SanPham entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getTenSP(),
                entity.getGiaTien(),
                entity.getPhanLoai(),
                entity.getHinhAnh(),
                entity.getMoTa(),
                entity.getMaSanPham());
    }

    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    public void setIsDeleteTrue(String MaSanPham) {
        String sql = "UPDATE SANPHAM SET IsDelete = 1 WHERE MaSanPham=?";
        XJdbc.executeUpdate(sql, MaSanPham);
    }
    
     public SanPham selectAllFK_Kho() {
         String sql = "SELECT sp.* FROM KHO kh INNER JOIN SANPHAM sp ON sp.MaSanPham = kh.MaSanPham";
        List<SanPham> list = this.selectBySQL(sql);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<SanPham> selectByTenSP(String tenSP) {
        String sql = "SELECT * FROM SANPHAM WHERE TenSP LIKE ?";
        return this.selectBySQL(sql, "%" + tenSP + "%");
    }

    public List<SanPham> selectByID(String ID) {
        String sql = "SELECT * FROM SANPHAM WHERE MaSanPham LIKE ?";
        return this.selectBySQL(sql, "%" + ID + "%");
    }

    public List<SanPham> selectByPhanLoai(Integer Loai){
        String sql = "select * from SANPHAM where PhanLoai = ?";
        return this.selectBySQL(sql, Loai);
    }
    
    public String selectByLastId() throws SQLException {
        String sql = "SELECT TOP 1 MaSanPham \n"
                + "FROM SANPHAM\n"
                + "ORDER BY MaSanPham DESC";
        ResultSet rs = XJdbc.executeQuery(sql);
        if (!rs.next()) {
            return null;
        }
        return rs.getString("MaSanPham");
    }

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSanPham(rs.getString("MaSanPham"));
                entity.setTenSP(rs.getString("TenSP"));
                entity.setGiaTien(rs.getDouble("GiaTien"));
                entity.setPhanLoai(rs.getInt("PhanLoai"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi class ChuyenDeDAO line 75 " + e);
        }
    }

}
