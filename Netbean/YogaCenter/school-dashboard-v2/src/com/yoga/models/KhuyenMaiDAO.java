/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yoga.models;

import com.yoga.entitys.KhuyenMai;
import com.yoga.utils.XJdbc;
import java.util.ArrayList;
import static com.yoga.utils.XJdbc.dburl;
import static com.yoga.utils.XJdbc.preparedStatement;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Hao
 */
public class KhuyenMaiDAO implements YogaCenterDAO<KhuyenMai, String> {

    String INSERT_SQL = "INSERT INTO KHUYENMAI (MaKhuyenMai, TenKhuyenMai,NguoiTao, NgayBD, NgayKT, TrangThai, MucGiam, MoTa, IsDelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE KHUYENMAI SET TenKhuyenMai=?, NguoiTao=?, NgayBD=?, NgayKT=?, TrangThai=?, MoTa=?, MucGiam=? WHERE MaKhuyenMai=?";
    String DELETE_SQL = "DELETE FROM KHUYENMAI WHERE MaKhuyenMai=?";
    String SELECT_ALL_SQL = "SELECT * FROM KHUYENMAI";
    String SELECT_BY_ID_SQL = "SELECT * FROM KHUYENMAI WHERE MaKhuyenMai=?";
    String SET_DELETE_TRUE_SQL = "UPDATE KHUYENMAI SET IsDelete = 1 WHERE MaKhuyenMai=?";
    String SELECT_ALL_DESC = "SELECT * FROM KHUYENMAI ORDER BY MaKhuyenMai DESC";
    String SELECT_ALL_FK_GOI_DICH_VU = "SELECT km.* FROM GOIDICHVU dv INNER JOIN KHUYENMAI km ON km.MaKhuyenMai = dv.MaKhuyenMai";
    String SELECT_ALL_SQL_ORDERBY_MAKHUYENMAI = "SELECT * FROM KHUYENMAI ORDER BY MaKhuyenMai DESC";

    @Override
    public void insert(KhuyenMai entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaKhuyenMai(),
                entity.getTenKhuyenMai(),
                entity.getNguoiTao(),
                entity.getNgayBD(),
                entity.getNgayKT(),
                entity.getTrangthai(),
                entity.getMucGiam(),
                entity.getMota(),
                entity.isIsDelete()
        );
    }

    @Override
    public void update(KhuyenMai entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
               
                entity.getTenKhuyenMai(),
                entity.getNguoiTao(),
                entity.getNgayBD(),
                entity.getNgayKT(),
                entity.getTrangthai(),
                entity.getMota(),
                entity.getMucGiam(),        
                entity.getMaKhuyenMai()
        );
    }

    /**
     *
     * @param MaKhuyenMai
     */
    @Override
    public void delete(String MaKhuyenMai) {
        XJdbc.executeUpdate(DELETE_SQL, MaKhuyenMai);
    }

    public void setIsDeleteTrue(String MaKhuyenMai) {
        XJdbc.executeUpdate(SET_DELETE_TRUE_SQL, MaKhuyenMai);
    }

    @Override
    public KhuyenMai selectById(String MaKhuyenMai) {
        List<KhuyenMai> list = this.selectBySQL(SELECT_BY_ID_SQL, MaKhuyenMai);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhuyenMai> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<KhuyenMai> selectBySQL(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            ResultSet rs;
            rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhuyenMai entity = new KhuyenMai();
                entity.setMaKhuyenMai(rs.getString("MaKhuyenMai"));
                entity.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
                entity.setNguoiTao(rs.getString("NguoiTao"));
                entity.setNgayBD(rs.getDate("NgayBD"));
                entity.setNgayKT(rs.getDate("NgayKT"));
                entity.setMucGiam(rs.getInt("MucGiam"));
                entity.setTrangthai(rs.getInt("TrangThai"));
                entity.setMota(rs.getString("MoTa"));
                entity.setIsDelete(rs.getBoolean("IsDelete"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhuyenMai> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KHUYENMAI WHERE TenKhuyenMai LIKE ?";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

    public List<KhuyenMai> selectAllOrderByMaKhuyenMai() {
        return this.selectBySQL(SELECT_ALL_SQL_ORDERBY_MAKHUYENMAI);
    }

    public KhuyenMai selectMaKhuyenMaiMoiNhat() {
        List<KhuyenMai> list = this.selectBySQL(SELECT_ALL_DESC);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public KhuyenMai selectAllFKGoiDichVu() {
        List<KhuyenMai> list = this.selectBySQL(SELECT_ALL_FK_GOI_DICH_VU);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
