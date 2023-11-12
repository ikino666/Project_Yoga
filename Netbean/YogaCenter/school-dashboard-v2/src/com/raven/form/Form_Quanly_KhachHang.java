package com.raven.form;

import com.raven.swing.CboUI;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Form_Quanly_KhachHang extends javax.swing.JPanel {
    

    public Form_Quanly_KhachHang() {
        initComponents();
        setOpaque(false);
        txtNgayDangKi.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtTenKH.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtDiaChi.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtSDT.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtEmail.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtSearch.setBackground(new Color(0, 0, 0, 1));

        cboPhanLoai.setUI(CboUI.createUI(this));
        cboSearch.setUI(CboUI.createUI(this));
        cboFilter.setUI(CboUI.createUI(this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelTransparent2 = new com.raven.swing.PanelTransparent();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.raven.swing.table.Table();
        jLabel10 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox<>();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelTransparent3 = new com.raven.swing.PanelTransparent();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboPhanLoai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtNgayDangKi = new com.toedter.calendar.JDateChooser();
        vach1 = new com.raven.component.Vach();

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mã KH", "Tên KH", "SDT", "Phân loại", null, null, null, "Giới tính", null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "SĐT", "Phân loại", "Giới tính", "Địa chỉ", "Email", "Ngày đăng ký", "Ghi chú"
            }
        ));
        table2.setCellSelectionEnabled(true);
        table2.setSelectionBackground(new java.awt.Color(204, 204, 255));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Lọc");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SĐT" }));

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên KH", "SDT" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icon_Search.png"))); // NOI18N

        javax.swing.GroupLayout panelTransparent2Layout = new javax.swing.GroupLayout(panelTransparent2);
        panelTransparent2.setLayout(panelTransparent2Layout);
        panelTransparent2Layout.setHorizontalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );
        panelTransparent2Layout.setVerticalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Quản lý khách hàng");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Ngày đăng ký");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Phân loại");

        cboPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Số điện thoại");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("Email");

        btnLuu.setBackground(new java.awt.Color(228, 215, 252));
        btnLuu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);

        btnThemMoi.setBackground(new java.awt.Color(228, 215, 252));
        btnThemMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setBorderPainted(false);

        btnXoa.setBackground(new java.awt.Color(228, 215, 252));
        btnXoa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        txtNgayDangKi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayDangKi.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout panelTransparent3Layout = new javax.swing.GroupLayout(panelTransparent3);
        panelTransparent3.setLayout(panelTransparent3Layout);
        panelTransparent3Layout.setHorizontalGroup(
            panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent3Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTransparent3Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNu))
                            .addComponent(jLabel5)))
                    .addComponent(jLabel8)
                    .addComponent(txtTenKH)
                    .addComponent(txtDiaChi)
                    .addComponent(txtNgayDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(txtSDT)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelTransparent3Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(164, 164, 164)))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(135, 135, 135))
        );
        panelTransparent3Layout.setVerticalGroup(
            panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTransparent3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTransparent3Layout.createSequentialGroup()
                                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(7, 7, 7)))
                                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTransparent3Layout.createSequentialGroup()
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
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
            .addComponent(panelTransparent2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vach1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelTransparent3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTransparent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTransparent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        int index = -1;
        index = table2.getSelectedRow();
        JOptionPane.showMessageDialog(this, "Index: "+index);
    }//GEN-LAST:event_table2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboPhanLoai;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private com.raven.swing.PanelTransparent panelTransparent2;
    private com.raven.swing.PanelTransparent panelTransparent3;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.raven.swing.table.Table table2;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private com.toedter.calendar.JDateChooser txtNgayDangKi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKH;
    private com.raven.component.Vach vach1;
    // End of variables declaration//GEN-END:variables
}
