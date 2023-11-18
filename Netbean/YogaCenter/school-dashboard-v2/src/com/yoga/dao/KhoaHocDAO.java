/*
Trần Trung Tín - PC07488
SD18309
 */
package com.yoga.dao;

import com.yoga.entity.KhoaHoc;
import com.yoga.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class KhoaHocDAO implements YogaCenterDAO<KhoaHoc, Integer> {

    String INSERT_SQL = "";
    String UPDATE_SQL = "";
    String DELETE_SQL = "";
    String SELECT_ALL_SQL = "";
    String SELECT_BY_ID_SQL = "";

    @Override
    public void insert(KhoaHoc entity) {

    }

    @Override
    public void update(KhoaHoc entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public KhoaHoc selectById(Integer id) {
        KhoaHoc khoaHoc = new KhoaHoc();
        return khoaHoc;
    }

    @Override
    public List<KhoaHoc> selectAll() {
        List<KhoaHoc> list = new ArrayList<>();
        return list;
    }

    @Override
    public List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        return list;
    }
}
