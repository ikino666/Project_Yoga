package com.yoga.ui;

import com.edusys.zCus_Table.TableCustom;
import com.yoga.models.SanPhamDAO;
import com.yoga.entitys.SanPham;
import com.yoga.utils.Auth;
import com.yoga.utils.MsgBox;
import com.yoga.utils.XImage;
import com.yoga.zswing.CboUI;
import java.awt.Color;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Form_SanPham extends javax.swing.JPanel {

    public Form_SanPham() {
        initComponents();
        setOpaque(false);
        cboSearch.setUI(CboUI.createUI(this));
        cboLoaiSanPham.setUI(CboUI.createUI(this));
        init();
        com.edusys.zCus_Table.TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
    }
    SanPhamDAO dao = new SanPhamDAO();
    int row = -1;
    boolean themMoi = false;

    void init() {
        setTrangThai(false);
//        txtSearch.setBackground(new Color(0, 0, 0, 1));
//        txtMaSanPham.setBackground(new Color(0, 0, 0, 1));
//        txtGiaTien.setBackground(new Color(0, 0, 0, 1));
//        txtTenSanPham.setBackground(new Color(0, 0, 0, 1));
//        lblHinhAnh.setBackground(new Color(0, 0, 0, 1));
        this.fillTable();
    }

    void insert() {
        SanPham sp = getForm();
        try {
            if (check() == true && checkID() == true) {
                dao.insert(sp);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Thêm mới thành công!");
                themMoi = false;
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
        }

    }

    void update() {
        SanPham sp = getForm();
        try {

            if (check() == true) {
                dao.update(sp);
                this.fillTable();
                clearForm();
                MsgBox.alert(this, "Cập nhật thành công!");

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
//            throw new RuntimeException(e);
        }

    }

    void delete() {

//        if (!Auth.isLogin()) {
//            //  MsgBox.alert(this, "Bạn không có quyền xóa chuyên đề!");
//        } else {
        String masp = txtMaSanPham.getText();
        SanPham sp = dao.selectAllFK_Kho();
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa sản phẩm này!")) {
            try {
                if (sp != null) {
                    dao.setIsDeleteTrue(masp);
                } else if (sp == null) {
                    dao.delete(masp);
                }
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");

            }
        }

//        }
    }

    void clearForm() {
        themMoi = true;
        this.row = -1;
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGiaTien.setText("");
        txtMoTa.setText("");
        lblHinhAnh.setIcon(null);
        cboLoaiSanPham.setSelectedIndex(0);
        autoID();

    }
    String PhanLoaiText = null;

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        try {
//          List<SanPham> list = dao.selectAll(); 
            String keyword = txtSearch.getText();
            if (cboSearch.getSelectedIndex() == 0) {
                List<SanPham> list = dao.selectByID(keyword);
                for (SanPham sp : list) {

                    int phanLoai = sp.getPhanLoai();
                    switch (phanLoai) {
                        case 1:
                            PhanLoaiText = "Sản phẩm chính";
                            break;
                        case 2:
                            PhanLoaiText = "Sản phẩm phụ";
                            break;
                        case 3:
                            PhanLoaiText = "Sản phẩm đi kèm";
                            break;
                    }
                    Object[] row = {sp.getMaSanPham(),
                        sp.getTenSP(),
                        sp.getGiaTien(),
                        PhanLoaiText
                    };
                    if (!sp.isIsDelete()) {         //xét có bị xóa chưa?
                        model.addRow(row);
                    }
                }
            } else if (cboSearch.getSelectedIndex() == 1) {
                List<SanPham> list = dao.selectByTenSP(keyword);
                for (SanPham sp : list) {
                    Object[] row = {sp.getMaSanPham(),
                        sp.getTenSP(),
                        sp.getGiaTien(),
                        PhanLoaiText
                    };
                    if (!sp.isIsDelete()) {         //xét có bị xóa chưa?
                        model.addRow(row);
                    }
                }
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            throw new RuntimeException(e);
        }
    }

    void fillTableFiter() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        try {
            if (cboFilter.getSelectedIndex() == 0) {
                List<SanPham> list = dao.selectAll();
                for (SanPham sp : list) {
                    int phanLoai = sp.getPhanLoai();
                    switch (phanLoai) {
                        case 1:
                            PhanLoaiText = "Sản phẩm chính";
                            break;
                        case 2:
                            PhanLoaiText = "Sản phẩm phụ";
                            break;
                        case 3:
                            PhanLoaiText = "Sản phẩm đi kèm";
                            break;
                    }
                    Object[] row = {sp.getMaSanPham(),
                        sp.getTenSP(),
                        sp.getGiaTien(),
                        PhanLoaiText
                    };
                    if (!sp.isIsDelete()) {         //xét có bị xóa chưa?
                        model.addRow(row);
                    }
                }
            } else if (cboFilter.getSelectedIndex() == 1) {
                List<SanPham> list = dao.selectByPhanLoai(1);
                for (SanPham sp : list) {
                    int phanLoai = sp.getPhanLoai();
                    switch (phanLoai) {
                        case 1:
                            PhanLoaiText = "Sản phẩm chính";
                            break;
                        case 2:
                            PhanLoaiText = "Sản phẩm phụ";
                            break;
                        case 3:
                            PhanLoaiText = "Sản phẩm đi kèm";
                            break;
                    }
                    Object[] row = {sp.getMaSanPham(),
                        sp.getTenSP(),
                        sp.getGiaTien(),
                        PhanLoaiText
                    };
                    if (!sp.isIsDelete()) {         //xét có bị xóa chưa?
                        model.addRow(row);
                    }
                }
            } else if (cboFilter.getSelectedIndex() == 2) {
                List<SanPham> list = dao.selectByPhanLoai(2);
                for (SanPham sp : list) {
                    int phanLoai = sp.getPhanLoai();
                    switch (phanLoai) {
                        case 1:
                            PhanLoaiText = "Sản phẩm chính";
                            break;
                        case 2:
                            PhanLoaiText = "Sản phẩm phụ";
                            break;
                        case 3:
                            PhanLoaiText = "Sản phẩm đi kèm";
                            break;
                    }
                    Object[] row = {sp.getMaSanPham(),
                        sp.getTenSP(),
                        sp.getGiaTien(),
                        PhanLoaiText
                    };
                    if (!sp.isIsDelete()) {         //xét có bị xóa chưa?
                        model.addRow(row);
                    }
                }
            } else if (cboFilter.getSelectedIndex() == 3) {
                List<SanPham> list = dao.selectByPhanLoai(3);
                for (SanPham sp : list) {
                    int phanLoai = sp.getPhanLoai();
                    switch (phanLoai) {
                        case 1:
                            PhanLoaiText = "Sản phẩm chính";
                            break;
                        case 2:
                            PhanLoaiText = "Sản phẩm phụ";
                            break;
                        case 3:
                            PhanLoaiText = "Sản phẩm đi kèm";
                            break;
                    }
                    Object[] row = {sp.getMaSanPham(),
                        sp.getTenSP(),
                        sp.getGiaTien(),
                        PhanLoaiText
                    };
                    if (!sp.isIsDelete()) {         //xét có bị xóa chưa?
                        model.addRow(row);
                    }
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            throw new RuntimeException(e);
        }
    }

    void setForm(SanPham sp) {
        txtMaSanPham.setText(sp.getMaSanPham());
        txtTenSanPham.setText(sp.getTenSP());
        txtGiaTien.setText(String.valueOf(sp.getGiaTien()));
        cboLoaiSanPham.setSelectedIndex((sp.getPhanLoai() - 1));
        if (sp.getHinhAnh() != null) {
            lblHinhAnh.setToolTipText(sp.getHinhAnh());
            lblHinhAnh.setIcon(XImage.read(sp.getHinhAnh()));
        }
        txtMoTa.setText(sp.getMoTa());
    }

    SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setMaSanPham(txtMaSanPham.getText().toUpperCase().trim());
        sp.setTenSP(txtTenSanPham.getText().trim());
        try {
            double giaTien = Double.parseDouble(txtGiaTien.getText().trim());
            sp.setGiaTien(giaTien);
        } catch (NumberFormatException e) {
            // Xử lý lỗi ở đây, tương tự như trên.
        }
        sp.setPhanLoai(cboLoaiSanPham.getSelectedIndex() + 1);
        sp.setHinhAnh(lblHinhAnh.getToolTipText());
        sp.setMoTa(txtMoTa.getText().trim());
        return sp;
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblHinhAnh.setIcon(icon);
            lblHinhAnh.setToolTipText(file.getName());
        }
    }

    private void timKiem() {
        this.fillTable();
        this.clearForm();
        this.row = -1;

    }

    private boolean check() {

        if (txtMaSanPham.getText().isBlank() && txtTenSanPham.getText().isBlank() && txtGiaTien.getText().isBlank()) {
            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
            JTextField list[] = {txtMaSanPham, txtTenSanPham, txtGiaTien};
            for (JTextField txt : list) {
                focusLostUPDATE(txt);
            }
            return false;
        } else if (txtTenSanPham.getText().isBlank()) {
            MsgBox.alert(this, "Vui lòng nhập tên sản phẩm!");
            focusLostUPDATE(txtTenSanPham);
            return false;
        } else if (txtGiaTien.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập giá tiền!");
            focusLostUPDATE(txtGiaTien);
            return false;
        }

        try {
            double giaTien = Double.parseDouble(txtGiaTien.getText());
            if (giaTien <= 0) {
                MsgBox.alert(this, "Vui lòng giá lớn hơn 0!!");
                return false;
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Vui lòng nhập giá là số");
        }
//        if (lblHinhAnh.getIcon() == null) {
//            MsgBox.alert(this, "Vui lòng tải lên hình ảnh!");
//            return false;
//        }

        return true;

    }

    boolean checkID() {
        SanPham sp = dao.selectById(txtMaSanPham.getText());
        if (sp != null) {
            MsgBox.alert(this, "Mã chuyên đề đã tồn tại! <" + sp.getMaSanPham() + ">");
            focusLostUPDATE(txtMaSanPham);
            return false;
        }
        return true;
    }

    public void focusGraintUPDATE(JTextField str) {
        try {
            if (str.getBackground().equals(new Color(255, 204, 204))) {
                str.setBackground(new Color(255, 255, 255));
            }
        } catch (Exception e) {
        }
    }

    public void focusLostUPDATE(JTextField str) {
        try {
            if (str.getBackground().equals(new Color(255, 255, 255)) && str.getText().equals("")) {
                str.setBackground(new Color(255, 204, 204));
            }

        } catch (Exception e) {
        }
    }

    void autoID() {
        try {
            String lastUserID = dao.selectByLastId();
            String numericPart = lastUserID.substring(2);
            int numericValue = Integer.parseInt(numericPart);
            int newUserID = numericValue + 1;
            String formattID = String.format("SP%03d", newUserID);
            txtMaSanPham.setText(formattID);
        } catch (NumberFormatException | SQLException e) {
            MsgBox.alert(this, "Lỗi định dạng ID");
            throw new RuntimeException(e);
        }
    }

    public void setTrangThai(boolean tt) {
        if (themMoi == true) {
            txtMaSanPham.setEditable(!tt);
        } else {
            txtMaSanPham.setEditable(!tt);
        }
        txtTenSanPham.setEditable(tt);
        txtGiaTien.setEditable(tt);
        txtMoTa.setEditable(tt);
        btnLuu.setEnabled(tt);
        btnXoa.setEnabled(tt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        vach1 = new com.yoga.zcomponents.Vach();
        panelTransparent2 = new com.yoga.zswing.PanelTransparent();
        jLabel12 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new z.table.Table();
        jPanel3 = new javax.swing.JPanel();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        vach1.setBackground(new java.awt.Color(93, 136, 255));

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

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel12.setText("Tên sản phẩm");

        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTenSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenSanPham.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenSanPhamFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenSanPhamFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel13.setText("Giá tiền");

        txtGiaTien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtGiaTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGiaTien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGiaTienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiaTienFocusLost(evt);
            }
        });
        txtGiaTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaTienKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel16.setText("Phân loại");

        cboLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sản phẩm chính", "Sản phẩm phụ", "Sản phẩm đi kèm" }));

        btnLuu.setBackground(new java.awt.Color(197, 214, 254));
        btnLuu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(197, 214, 254));
        btnXoa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThemMoi.setBackground(new java.awt.Color(197, 214, 254));
        btnThemMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setBorderPainted(false);
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Phân loại"
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
        tbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sản phẩm", "Tên sản phẩm" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yoga/icon/icon_Search.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phân loại", "Sản phẩm chính", "Sản phẩm phụ", "Sản phẩm đi kèm" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Lọc");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 548, Short.MAX_VALUE)
                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnSearch)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel17.setText("Mã sản phẩm");

        txtMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMaSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaSanPham.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaSanPhamFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaSanPhamFocusLost(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel18.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTransparent2Layout = new javax.swing.GroupLayout(panelTransparent2);
        panelTransparent2.setLayout(panelTransparent2Layout);
        panelTransparent2Layout.setHorizontalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTenSanPham)
                        .addComponent(jLabel12)
                        .addComponent(jLabel17)
                        .addComponent(txtGiaTien)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(65, 65, 65))
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemMoi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
            .addComponent(jScrollPane2)
        );
        panelTransparent2Layout.setVerticalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(6, 6, 6)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTransparent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vach1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTransparent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        themMoi = false;
        this.row = tbl.getSelectedRow();
        String macd = (String) tbl.getValueAt(this.row, 0);
        SanPham sp = dao.selectById(macd);
        this.setForm(sp);
        JTextField list[] = {txtMaSanPham, txtTenSanPham, txtGiaTien};
        for (JTextField txt : list) {
            focusGraintUPDATE(txt);
        }
    }//GEN-LAST:event_tblMouseClicked

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:  
        clearForm();
        setTrangThai(true);
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:

        if (this.themMoi == true) {
            insert();
        }
        if (this.themMoi == false && this.row != -1) {
            update();
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void txtMaSanPhamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaSanPhamFocusGained
        // TODO add your handling code here:
        focusGraintUPDATE(txtMaSanPham);
    }//GEN-LAST:event_txtMaSanPhamFocusGained

    private void txtMaSanPhamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaSanPhamFocusLost
        // TODO add your handling code here:
        focusLostUPDATE(txtMaSanPham);
    }//GEN-LAST:event_txtMaSanPhamFocusLost

    private void txtTenSanPhamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSanPhamFocusGained
        // TODO add your handling code here:
        focusGraintUPDATE(txtTenSanPham);
    }//GEN-LAST:event_txtTenSanPhamFocusGained

    private void txtTenSanPhamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSanPhamFocusLost
        // TODO add your handling code here:
        focusLostUPDATE(txtTenSanPham);
    }//GEN-LAST:event_txtTenSanPhamFocusLost

    private void txtGiaTienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaTienFocusGained
        // TODO add your handling code here:
        focusGraintUPDATE(txtGiaTien);
    }//GEN-LAST:event_txtGiaTienFocusGained

    private void txtGiaTienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaTienFocusLost
        // TODO add your handling code here:
        focusLostUPDATE(txtGiaTien);
    }//GEN-LAST:event_txtGiaTienFocusLost

    private void txtGiaTienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTienKeyTyped
//         TODO add your handling code here:
        String currentText = txtGiaTien.getText();
        int caretPosition = txtGiaTien.getCaretPosition();
        char c = evt.getKeyChar();
        // Tạo chuỗi mới bằng cách thêm ký tự mới vào vị trí hiện tại của caret
        String newText = currentText.substring(0, caretPosition) + c + currentText.substring(caretPosition);

        // Kiểm tra chuỗi mới có tuân theo biểu thức chính quy không (cho số nguyên hoặc số thập phân)
        String regex = "^[0-9]*\\.?[0-9]*$";
        if (!newText.matches(regex)) {
            evt.consume();  // Loại bỏ ký tự không hợp lệ
        }

    }//GEN-LAST:event_txtGiaTienKeyTyped

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
        // TODO add your handling code here:
        fillTableFiter();
    }//GEN-LAST:event_cboFilterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinhAnh;
    private com.yoga.zswing.PanelTransparent panelTransparent2;
    private z.table.Table tbl;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenSanPham;
    private com.yoga.zcomponents.Vach vach1;
    // End of variables declaration//GEN-END:variables

}
