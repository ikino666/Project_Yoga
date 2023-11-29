package com.yoga.ui;

import com.edusys.zCus_Table.TableCustom;
import com.yoga.models.KhachHangDAO;
import com.yoga.entitys.KhachHang;
import com.yoga.utils.MsgBox;
import com.yoga.zswing.CboUI;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Form_Quanly_KhachHang extends javax.swing.JPanel {

    int row = -1;
    KhachHangDAO khDAO = new KhachHangDAO();

    public Form_Quanly_KhachHang() {
        initComponents();
        initTables();
        setOpaque(false);

        cboPhanLoai.setUI(CboUI.createUI(this));
        cboSearch.setUI(CboUI.createUI(this));
        cboFilter.setUI(CboUI.createUI(this));
        fillTable();
    }

    void initTables() {
        com.edusys.zCus_Table.TableCustom.apply(jScrollPane3, TableCustom.TableType.DEFAULT);
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setColumnCount(0);
        model.addColumn("Mã Khách Hàng");
        model.addColumn("Phân Loại");
        model.addColumn("Tên Khách Hàng");
        model.addColumn("Giới Tính");
        model.addColumn("Địa chỉ");
        model.addColumn("Email");

        model.addColumn("SĐT");
        model.addColumn("Ngày Đăng Ký");
        model.addColumn("Ghi Chú");
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(132, 196, 255)); // Đặt màu nền cho header
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15));
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtSearch.getText();
            List<KhachHang> list = khDAO.selectByKeyword(keyword);
            for (KhachHang kh : list) {
                Object[] row = {kh.getMaKhachHang(), kh.getMaLoaiKH(), kh.getTenKhachHang(),
                    kh.isGioiTinh(), kh.getDiaChi(), kh.getEmail(),
                    kh.getDienThoai(), kh.getNgayDK(), kh.getGhiChu(), kh.isIsDelete()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void setForm(KhachHang kh) {
        txtTenKH.setText(kh.getTenKhachHang());
        txtSDT.setText(kh.getDienThoai());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        txtGhiChu.setText(kh.getGhiChu());
        cboPhanLoai.setSelectedIndex((kh.getMaLoaiKH() - 1));
        if (kh.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtNgayDangKi.setDate(kh.getNgayDK());

    }

    KhachHang getForm() {
        KhachHang khackhang = new KhachHang();
        try {
//            khackhang.setMaKhachHang((int) tblKhachHang.getValueAt(row, 0));
            khackhang.setTenKhachHang(txtTenKH.getText());
            khackhang.setDienThoai(txtSDT.getText());
            khackhang.setEmail(txtEmail.getText());
            khackhang.setDiaChi(txtDiaChi.getText());
            khackhang.setGhiChu(txtGhiChu.getText());
            khackhang.setMaLoaiKH(cboPhanLoai.getSelectedIndex() + 1);
            if (rdoNam.isSelected()) {
                khackhang.setGioiTinh(true);
            } else {
                khackhang.setGioiTinh(false);
            }
            khackhang.setNgayDK(txtNgayDangKi.getDate());
            return khackhang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khackhang;
    }

    boolean isValidName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    boolean isValidDate() {
        Date ngayDangKi = txtNgayDangKi.getDate();
        Date ngayHienTai = new Date();
        if (ngayDangKi.before(ngayHienTai)) {
            return false;
        }
        return true;
    }

    boolean isValidPhone(String phone) {
        String regex = "^(03[2-9]\\d{7}|05[6-8]\\d{7}|07[0-9]\\d{7}|08[1-9]\\d{7}|09\\d{8})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    boolean isValidEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateGetForm() {
        if (txtTenKH.getText().isBlank()
                || txtSDT.getText().isBlank()
                || txtEmail.getText().isBlank()
                || txtDiaChi.getText().isBlank()
                || txtGhiChu.getText().isBlank()
                || txtNgayDangKi.getDate() == null
                || (!rdoNam.isSelected() && !rdoNu.isSelected())) {
            MsgBox.alert(this, "Vui lòng điền đầy đủ thông tin.");
            return false;
        }
        if (!isValidName(txtTenKH.getText().trim())) {
            MsgBox.alert(this, "Họ và tên không đúng định dạng.");
            return false;
        }
        if (isValidDate()) {
            MsgBox.alert(null, "Ngày đăng ký không hợp lệ");
            return false;
        }
        if (!isValidPhone(txtSDT.getText().trim())) {
            MsgBox.alert(null, "Số điện thoại không hợp lệ");
            return false;
        }
        if (!isValidEmail(txtEmail.getText().trim())) {
            MsgBox.alert(null, "Email không hợp lệ");
            return false;
        }
        KhachHang email = khDAO.selectByEmail(txtEmail.getText());
        KhachHang sdt = khDAO.selectBySDT(txtSDT.getText());
        if (sdt != null) {
            if (txtSDT.getText().equalsIgnoreCase(sdt.getDienThoai())) {
                MsgBox.alert(this, "Số điện thoại đã tồn tại " + sdt.getDienThoai());
                return false;
            }
        }
        if (email != null) {
            if (txtEmail.getText().equalsIgnoreCase(email.getEmail())) {
                MsgBox.alert(this, "Email đã tồn tại " + email.getEmail());
                return false;
            }
        }
        return true;
    }

    void clearForm() {
        txtTenKH.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtGhiChu.setText("");
        cboPhanLoai.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        txtNgayDangKi.setDate(Calendar.getInstance().getTime());
    }

    void insert() {
        if (validateGetForm()) {
            KhachHang kh = getForm();
            try {
                khDAO.insert(kh);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
            }
        }
    }

    void update() {
        if (validateGetForm()) {
            KhachHang kh = getForm();
            try {
                khDAO.update(kh);
                this.fillTable();
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void delete() {
        KhachHang kh = getForm();
        kh.setMaKhachHang((int) tblKhachHang.getValueAt(row, 0));
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa sản phẩm này!")) {
            try {
                khDAO.delete(kh.getMaKhachHang());
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            fillTable();
        }
    }

    public void search() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        String keyword = txtSearch.getText();

        try {
            List<KhachHang> list = null;
            int index = cboSearch.getSelectedIndex();

            if (index == 0) {
                list = khDAO.searchTen(keyword);
            } else {
                list = khDAO.searchSDT(keyword);
            }

            for (KhachHang kh : list) {
                Object[] row = {kh.getMaKhachHang(), kh.getMaLoaiKH(), kh.getTenKhachHang(),
                    kh.isGioiTinh(), kh.getDiaChi(), kh.getEmail(),
                    kh.getDienThoai(), kh.getNgayDK(), kh.getGhiChu(), kh.isIsDelete()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void filter() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);

        try {
            List<KhachHang> list = null;
            int index = cboFilter.getSelectedIndex();

            if (index == 0) {
                list = khDAO.filterDate();
            } else if (index == 1) {
                list = khDAO.filterCode();
            } else if (index == 2) {
                list = khDAO.filterGender();
            }

            for (KhachHang kh : list) {
                Object[] row = {kh.getMaKhachHang(), kh.getMaLoaiKH(), kh.getTenKhachHang(),
                    kh.isGioiTinh(), kh.getDiaChi(), kh.getEmail(),
                    kh.getDienThoai(), kh.getNgayDK(), kh.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelTransparent2 = new com.yoga.zswing.PanelTransparent();
        jLabel10 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox<>();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        panelTransparent3 = new com.yoga.zswing.PanelTransparent();
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
        btnMoi = new javax.swing.JButton();
        vach1 = new com.yoga.zcomponents.Vach();
        jLabel1 = new javax.swing.JLabel();

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Lọc");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày đăng ký", "Mã khách hàng", "Giới tính" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên", "SĐT" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yoga/icon/icon_Search.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSearchMouseReleased(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKhachHang);

        javax.swing.GroupLayout panelTransparent2Layout = new javax.swing.GroupLayout(panelTransparent2);
        panelTransparent2.setLayout(panelTransparent2Layout);
        panelTransparent2Layout.setHorizontalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)))
                .addContainerGap())
        );
        panelTransparent2Layout.setVerticalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel2.setText("Ngày đăng ký");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setText("Phân loại");

        cboPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KH mới", "KH thường xuyên", "KH VIP" }));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Số điện thoại");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setText("Email");

        btnLuu.setBackground(new java.awt.Color(197, 214, 254));
        btnLuu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLuu.setText("Cập nhật");
        btnLuu.setBorderPainted(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
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

        btnXoa.setBackground(new java.awt.Color(197, 214, 254));
        btnXoa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel8.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel9.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        txtNgayDangKi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayDangKi.setDateFormatString("dd/MM/yyyy");

        btnMoi.setBackground(new java.awt.Color(197, 214, 254));
        btnMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setBorderPainted(false);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTransparent3Layout = new javax.swing.GroupLayout(panelTransparent3);
        panelTransparent3.setLayout(panelTransparent3Layout);
        panelTransparent3Layout.setHorizontalGroup(
            panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(rdoNam))
                        .addGap(1, 1, 1)
                        .addComponent(rdoNu))
                    .addComponent(jLabel8)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH)
                    .addComponent(txtDiaChi)
                    .addComponent(txtNgayDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(141, 141, 141))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemMoi)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
        );
        panelTransparent3Layout.setVerticalGroup(
            panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTransparent3Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addComponent(jLabel7)
                            .addGap(8, 8, 8)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(jLabel9)
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelTransparent3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(6, 6, 6)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTransparent3Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(6, 6, 6)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTransparent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelTransparent3Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel4)
                                    .addGap(6, 6, 6)
                                    .addComponent(cboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelTransparent3Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(jLabel5)
                                    .addGap(6, 6, 6)
                                    .addComponent(rdoNam))
                                .addGroup(panelTransparent3Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(rdoNu)))
                            .addGap(14, 14, 14)
                            .addComponent(jLabel8)
                            .addGap(6, 6, 6)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(jLabel2)
                            .addGap(6, 6, 6)
                            .addComponent(txtNgayDangKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelTransparent3Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

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

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Quản Lý Khách Hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTransparent2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vach1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTransparent3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(841, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTransparent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        insert();
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        update();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        search();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
        filter();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseReleased

    }//GEN-LAST:event_btnSearchMouseReleased

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        this.row = tblKhachHang.getSelectedRow();
        int makh = (Integer) tblKhachHang.getValueAt(this.row, 0);
        KhachHang kh = khDAO.selectById(makh);
        this.setForm(kh);

    }//GEN-LAST:event_tblKhachHangMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnMoi;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboPhanLoai;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.yoga.zswing.PanelTransparent panelTransparent2;
    private com.yoga.zswing.PanelTransparent panelTransparent3;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private com.toedter.calendar.JDateChooser txtNgayDangKi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKH;
    private com.yoga.zcomponents.Vach vach1;
    // End of variables declaration//GEN-END:variables
}
