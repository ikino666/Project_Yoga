/*
Trần Trung Tín - PC07488
SD18309
 */
package com.yoga.models;

import com.yoga.entitys.KhachHang;
import com.yoga.entitys.KhoaHoc;
import com.yoga.utils.XJdbc;
import static com.yoga.utils.XJdbc.dburl;
import static com.yoga.utils.XJdbc.password;
import static com.yoga.utils.XJdbc.preparedStatement;
import static com.yoga.utils.XJdbc.username;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class KhoaHocDAO implements YogaCenterDAO<KhoaHoc, String> {

    String INSERT_SQL = "INSERT INTO KhoaHoc (MaKhoaHoc, TenKhoaHoc, ThoiLuong, HocPhi, HinhAnh, MoTa, NguoiTao, NgayTao, IsDelete) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET TenKhoaHoc=?, ThoiLuong=?, HocPhi=?, HinhAnh=?, MoTa=?, NguoiTao=?, NgayTao=? WHERE MaKhoaHoc=?";
    String SET_DELETE_TRUE_SQL = "UPDATE KhoaHoc SET IsDelete = 1 WHERE MaKhoaHoc=?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKhoaHoc=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKhoaHoc=?";
    String SELECT_ALL_DESC = "SELECT * FROM KhoaHoc ORDER BY MaKhoaHoc DESC";
    String SELECT_ALL_FK_LOP_HOC = "SELECT kh.* FROM LOPHOC lh INNER JOIN KHOAHOC kh ON kh.MaKhoaHoc = lh.MaKhoaHoc";
    String SELECT_ALL_FK_GOI_DICH_VU = "SELECT kh.* FROM GOIDICHVU dv INNER JOIN KHOAHOC kh ON kh.MaKhoaHoc = dv.MaKhoaHoc";
    String SELECT_ALL_SQL_ORDERBY_MAKHOAHOC = "SELECT * FROM KhoaHoc ORDER BY MaKhoaHoc DESC";
    

    @Override
    public void insert(KhoaHoc entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaKHoc(),
                entity.getTenKHoc(),
                entity.getThoiLuong(),
                entity.getHocPhi(),
                entity.getHinhAnh(),
                entity.getMoTa(),
                entity.getNguoiTao(),
                entity.getNgayTao(),
                entity.isIsDelete()
        );
    }

    @Override
    public void update(KhoaHoc entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getTenKHoc(),
                entity.getThoiLuong(),
                entity.getHocPhi(),
                entity.getHinhAnh(),
                entity.getMoTa(),
                entity.getNguoiTao(),
                entity.getNgayTao(),
                entity.getMaKHoc()
        );
    }

    @Override
    public void delete(String maKhoaHoc) {
        XJdbc.executeUpdate(DELETE_SQL, maKhoaHoc);
    }

    public void setIsDeleteTrue(String maKhoaHoc) {
        XJdbc.executeUpdate(SET_DELETE_TRUE_SQL, maKhoaHoc);
    }

    @Override
    public KhoaHoc selectById(String maKhoaHoc) {
        List<KhoaHoc> list = this.selectBySQL(SELECT_BY_ID_SQL, maKhoaHoc);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKHoc(rs.getString("MaKhoaHoc"));
                entity.setTenKHoc(rs.getString("TenKhoaHoc"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setNguoiTao(rs.getString("NguoiTao"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setIsDelete(rs.getBoolean("IsDelete"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectAllOrderByMaKhoaHoc() {
        return this.selectBySQL(SELECT_ALL_SQL_ORDERBY_MAKHOAHOC);
    }

    public KhoaHoc selectMaKhoaHocMoiNhat() {
        List<KhoaHoc> list = this.selectBySQL(SELECT_ALL_DESC);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }   

    public KhoaHoc selectAllFKLopHoc() {
        List<KhoaHoc> list = this.selectBySQL(SELECT_ALL_FK_LOP_HOC);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public KhoaHoc selectAllFKGoiDichVu() {
        List<KhoaHoc> list = this.selectBySQL(SELECT_ALL_FK_GOI_DICH_VU);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<KhoaHoc> searchMa(String keyword) {
        String SQL = "select * from KhoaHoc where MaKhoaHoc like ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }    
    
    public List<KhoaHoc> searchTen(String keyword) {
        String SQL = "select * from KhoaHoc where TenKhoaHoc like ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }
    
    public List<KhoaHoc> searchThoiLuong(int keyword) {
        String SQL = "select * from KhoaHoc where ThoiLuong = ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }
    
    public List<KhoaHoc> searchHocPhi(double keyword) {
        String SQL = "select * from KhoaHoc where TenKhoaHoc like ?";
        return this.selectBySQL(SQL, "%" + keyword + "%");
    }
    
    public List<KhoaHoc> filterMaKH() {
        String SQL = "select * from KhoaHoc order by MaKhoaHoc";
        return this.selectBySQL(SQL);
    }
    
    public List<KhoaHoc> filterTenKH() {
        String SQL = "select * from KhoaHoc order by TenKhoaHoc";
        return this.selectBySQL(SQL);
    }
    
    public List<KhoaHoc> filterThoiLuong() {
        String SQL = "select * from KhoaHoc order by ThoiLuong";
        return this.selectBySQL(SQL);
    }
    
    public List<KhoaHoc> filterHocPhi() {
        String SQL = "select * from KhoaHoc order by HocPhi";
        return this.selectBySQL(SQL);
    }
    
    public List<KhoaHoc> filterNgayTao() {
        String SQL = "select * from KhoaHoc order by NgayTao";
        return this.selectBySQL(SQL);
    }
    

}
