package com.raven.form;

import com.raven.swing.CboUI;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Form_HoaDon extends javax.swing.JPanel {
    

    public Form_HoaDon() {
        initComponents();
        setOpaque(false);
        txtSearch.setBackground(new Color(0, 0, 0, 1));
        txtKhachHang.setBackground(new Color(0, 0, 0, 1));
        txtConLai.setBackground(new Color(0, 0, 0, 1));
        txtNgayTao.setBackground(new Color(0, 0, 0, 1));
        txtNguoiTao.setBackground(new Color(0, 0, 0, 1));
        txtTongTien.setBackground(new Color(0, 0, 0, 1));
        txtTienTra.setBackground(new Color(0, 0, 0, 1));

        cboGoiDichVu.setUI(CboUI.createUI(this));
        cboFilterNgay.setUI(CboUI.createUI(this));
        cboFilter.setUI(CboUI.createUI(this));
        cboSearch.setUI(CboUI.createUI(this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelTransparent2 = new com.raven.swing.PanelTransparent();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new com.raven.swing.table.Table();
        jLabel10 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox<>();
        cboFilterNgay = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelTransparent3 = new com.raven.swing.PanelTransparent();
        jLabel2 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboGoiDichVu = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        rdoTraHet = new javax.swing.JRadioButton();
        rdoTraTruoc = new javax.swing.JRadioButton();
        btnXuatHoaDon = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtTienTra = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtConLai = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSanPham = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        vach1 = new com.raven.component.Vach();

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mã KH", "Tên KH", "SDT", "Phân loại"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "SĐT", "Phân loại"
            }
        ));
        tbl.setCellSelectionEnabled(true);
        tbl.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Lọc");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SĐT" }));

        cboFilterNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày" }));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Thông tin");

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SĐT" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icon_Search.png"))); // NOI18N

        javax.swing.GroupLayout panelTransparent2Layout = new javax.swing.GroupLayout(panelTransparent2);
        panelTransparent2.setLayout(panelTransparent2Layout);
        panelTransparent2Layout.setHorizontalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch)
                    .addComponent(cboFilterNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panelTransparent2Layout.setVerticalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(cboFilterNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Hóa đơn");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Tổng tiền");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Gói dịch vụ");

        cboGoiDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Trạng thái");

        buttonGroup1.add(rdoTraHet);
        rdoTraHet.setText("Trả hết");

        buttonGroup1.add(rdoTraTruoc);
        rdoTraTruoc.setText("Trả trước");

        btnXuatHoaDon.setBackground(new java.awt.Color(228, 215, 252));
        btnXuatHoaDon.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnXuatHoaDon.setText("Xuất hóa đơn");
        btnXuatHoaDon.setBorderPainted(false);

        btnThemMoi.setBackground(new java.awt.Color(228, 215, 252));
        btnThemMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setBorderPainted(false);

        btnThanhToan.setBackground(new java.awt.Color(228, 215, 252));
        btnThanhToan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorderPainted(false);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Người tạo");

        txtNguoiTao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNguoiTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNguoiTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNguoiTaoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Khách hàng");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Tiền trả");

        txtTienTra.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTienTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTienTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienTraActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Còn lại");

        txtConLai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtConLai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtConLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConLaiActionPerformed(evt);
            }
        });

        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNgayTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayTaoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("Ngày tạo");

        txtSanPham.setColumns(20);
        txtSanPham.setRows(5);
        jScrollPane3.setViewportView(txtSanPham);

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("Mô tả");

        txtKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTransparent3Layout = new javax.swing.GroupLayout(panelTransparent3);
        panelTransparent3.setLayout(panelTransparent3Layout);
        panelTransparent3Layout.setHorizontalGroup(
            panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTransparent3Layout.createSequentialGroup()
                                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTransparent3Layout.createSequentialGroup()
                                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboGoiDichVu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTransparent3Layout.createSequentialGroup()
                                                .addComponent(rdoTraHet)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdoTraTruoc)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(53, 53, 53)
                                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(panelTransparent3Layout.createSequentialGroup()
                                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnXuatHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))))
        );
        panelTransparent3Layout.setVerticalGroup(
            panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGoiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoTraHet)
                            .addComponent(rdoTraTruoc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
        );

        javax.swing.GroupLayout vach1Layout = new javax.swing.GroupLayout(vach1);
        vach1.setLayout(vach1Layout);
        vach1Layout.setHorizontalGroup(
            vach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        vach1Layout.setVerticalGroup(
            vach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(vach1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTransparent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTransparent3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTransparent3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTransparent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtNguoiTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNguoiTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNguoiTaoActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        int index = -1;
        index = tbl.getSelectedRow();
        JOptionPane.showMessageDialog(this, "Index: "+index);
    }//GEN-LAST:event_tblMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtTienTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienTraActionPerformed

    private void txtConLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConLaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConLaiActionPerformed

    private void txtNgayTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayTaoActionPerformed

    private void txtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachHangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSearch;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXuatHoaDon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboFilterNgay;
    private javax.swing.JComboBox<String> cboGoiDichVu;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.raven.swing.PanelTransparent panelTransparent2;
    private com.raven.swing.PanelTransparent panelTransparent3;
    private javax.swing.JRadioButton rdoTraHet;
    private javax.swing.JRadioButton rdoTraTruoc;
    private com.raven.swing.table.Table tbl;
    private javax.swing.JTextField txtConLai;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextArea txtSanPham;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTienTra;
    private javax.swing.JTextField txtTongTien;
    private com.raven.component.Vach vach1;
    // End of variables declaration//GEN-END:variables
}
