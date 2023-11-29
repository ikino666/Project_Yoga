package com.yoga.ui;

import com.edusys.zCus_Table.TableCustom;
import com.yoga.entitys.KhuyenMai;
import com.yoga.models.KhuyenMaiDAO;
import com.yoga.utils.MsgBox;
import com.yoga.utils.validate;
import com.yoga.zswing.CboUI;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class Form_KhuyenMai extends javax.swing.JPanel {

    KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
    int row = -1;
    boolean themMoi = false;
    Calendar calendar = Calendar.getInstance();
    Date currentDate1 = calendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = dateFormat.format(currentDate1);
    boolean editmove = false;

    public Form_KhuyenMai() {
        initComponents();
        initTables();
        fillComboboxFilter();
        fillTable();
        setTrangThai(false);
        setOpaque(false);
        setComponentsDefault();
    }

    public void setComponentsDefault() {

        txtMaKhuyenMai.setEditable(false);
        txtNgayBD.setEnabled(true);
        txtNgayKT.setEnabled(true);
        rdoApDung.setEnabled(false);
        rdoHetThoiHan.setEnabled(false);
        txtMaKhuyenMai.setEnabled(false);
    }

    void initTables() {
        com.edusys.zCus_Table.TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setColumnCount(0);
        model.addColumn("Mã Khuyến Mãi");
        model.addColumn("Tên Khuyến Mãi");
        model.addColumn("Người Tạo");
        model.addColumn("Ngày Bắt Đầu");
        model.addColumn("Ngày Kết Thúc");

        model.addColumn("Mức Giảm");
        model.addColumn("Trạng Thái");
        model.addColumn("Mô Tả");
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(132, 196, 255)); // Đặt màu nền cho header
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15));
    }

    public void fillComboboxFilter() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboFilter.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cboSearch.getModel();
        model.removeAllElements();
        model2.removeAllElements();
        model.addElement("- Trạng Thái -");
        model.addElement("Áp dụng");
        model.addElement("Hết thời hạn");

        model2.addElement("Mã Khuyến Mãi");
        model2.addElement("Tên Khuyến Mãi");
        model2.addElement("Người tạo");
        model2.addElement("Mức Giảm");

    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        try {
            List<KhuyenMai> list = kmDAO.selectAll();
            for (KhuyenMai km : list) {
                Object[] rows = {
                    km.getMaKhuyenMai(),
                    km.getTenKhuyenMai(),
                    km.getNguoiTao(),
                    km.getNgayBD(),
                    km.getNgayKT(),
                    km.getMucGiam(),
                    km.getTrangthai() == 1 ? "Áp dụng" : "Hết thời hạn", // Thay đổi giá trị tùy thuộc vào trạng thái
                    km.getMota()
                };

                if (!km.isIsDelete()) {
                    model.addRow(rows);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void fillTableSearch() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        try {
            List<KhuyenMai> list = kmDAO.selectAllOrderByMaKhuyenMai();
            for (KhuyenMai km : list) {
                Object[] rows = {
                    km.getMaKhuyenMai(),
                    km.getTenKhuyenMai(),
                    km.getNguoiTao(),
                    km.getNgayBD(),
                    km.getNgayKT(),
                    km.getMucGiam(),
                    km.getTrangthai(),
                    km.getMota()
                };
                if (!km.isIsDelete()) {         //xét có bị xóa chưa?
                    model.addRow(rows);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private KhuyenMai getForm() {
        KhuyenMai km = new KhuyenMai();
        km.setMaKhuyenMai(txtMaKhuyenMai.getText().toUpperCase().trim());
        km.setTenKhuyenMai(txtTenKhuyenMai.getText().trim());
        try {
            int MucGiam = Integer.parseInt(txtMucGiam.getText().trim());
            km.setMucGiam(MucGiam);
        } catch (NumberFormatException e) {
            // Xử lý lỗi ở đây, tương tự như trên.
        }
        LocalDate today = LocalDate.now();
        if (txtNgayKT.getDate() != null
                && txtNgayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(today)) {
            rdoApDung.setSelected(true);
            km.setTrangthai(1);
        } else {
            rdoHetThoiHan.setSelected(true);
            km.setTrangthai(0);
        }

        km.setMota(txtMoTa.getText().trim());
        if (txtNguoiTao.getText().trim().equals("")) {
//            km.setNguoiTao(Auth.user.getMaNV());
        } else {
            km.setNguoiTao(txtNguoiTao.getText().trim());
        }
        km.setNgayBD(txtNgayBD.getDate());
        km.setNgayKT(txtNgayKT.getDate());
        if (km.getTrangthai() == 1) {
            rdoApDung.setSelected(true);
        } else {
            rdoHetThoiHan.setSelected(true);
        }
        km.setIsDelete(false);
        return km;
    }

    void setForm(KhuyenMai km) {

        txtMaKhuyenMai.setText(km.getMaKhuyenMai());
        txtTenKhuyenMai.setText(km.getTenKhuyenMai());
        txtNguoiTao.setText(km.getNguoiTao());
        txtNgayBD.setDate(km.getNgayBD());
        txtNgayKT.setDate(km.getNgayKT());

        txtMucGiam.setText(String.valueOf(km.getMucGiam()));
        txtMoTa.setText(km.getMota());
        km.getTrangthai();
    }

    public void edit(Integer row) {
        String makh = (String) tblKhuyenMai.getValueAt(this.row, 0);
        KhuyenMai km = kmDAO.selectById(makh);
        this.setForm(km);
        setTrangThai(true);
    }

    void clearForm() {
        row = -1;
        txtMaKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtMucGiam.setText("");
        rdoApDung.setSelected(false);
        rdoHetThoiHan.setSelected(false);
        txtNgayBD.setDate(currentDate1);
        txtNgayKT.setDate(currentDate1);
        txtNguoiTao.setText("");
        txtMoTa.setText("");
        txtSearch.setText("");
        setComponentsDefault();
    }

    void Add() {
        clearForm();
        themMoi = true;
        btnXoa.setEnabled(false);
        KhuyenMai kh = kmDAO.selectMaKhuyenMaiMoiNhat();
        txtMaKhuyenMai.setText(kh.getMaKhuyenMai());
        int temp = Integer.parseInt(kh.getMaKhuyenMai().substring(2)) + 1;
        String result = "";
        if (String.valueOf(temp).length() == 1) {
            result = "KM00" + String.valueOf(temp);
        } else if (String.valueOf(temp).length() == 2) {
            result = "KM0" + String.valueOf(temp);
        }
        if (String.valueOf(temp).length() == 3) {
            result = "KM" + String.valueOf(temp);
        }
        txtMaKhuyenMai.setText(result);
    }

    public void insert() {
        try {
            KhuyenMai kh = getForm();
            if (isEmpty(kh) == 1 && isDuplicated(kh) == false && validateKhuyenMai() == true) {
                kmDAO.insert(kh);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Thêm mới thành công");
                setTrangThai(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update() {
        KhuyenMai kh = getForm();
        try {
            if (isEmpty(kh) == 1 && validateKhuyenMai() == true) {
                kmDAO.update(kh);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Lưu thành công");
                setTrangThai(false);
            } else {
                MsgBox.alert(this, "Lưu thất bại");
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lưu thất bại!"
                    + "\n Lỗi không rõ!");
        }
    }

    public void delete() {
        String maKHoc = txtMaKhuyenMai.getText().trim();
        KhuyenMai km = kmDAO.selectAllFKGoiDichVu();
        if (MsgBox.confirm(this, "Xác nhận xóa khóa học '" + maKHoc + "'?")) {
            try {
                if (km != null) {
                    kmDAO.setIsDeleteTrue(maKHoc);
                } else if (km == null) {
                    kmDAO.delete(maKHoc);
                }
                this.fillTable();
                this.clearForm();
                this.setTrangThai(false);
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    public void search(Integer i, String keyWord) {
        List<KhuyenMai> list = kmDAO.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);

        try {
            for (KhuyenMai x : list) {
                switch (i) {
                    case 0: // Search by MaKhuyenMai
                        if (x.getMaKhuyenMai() != null && x.getMaKhuyenMai().toLowerCase().contains(keyWord.toLowerCase())) {
                            addToModel(model, x);
                        }
                        break;
                    case 1: // Search by TenKhuyenMai
                        if (x.getTenKhuyenMai() != null && x.getTenKhuyenMai().toLowerCase().contains(keyWord.toLowerCase())) {
                            addToModel(model, x);
                        }
                        break;
                    case 3: // Search by MucGiam
                        if (String.valueOf(x.getMucGiam()).contains(keyWord)) {
                            addToModel(model, x);
                        }
                        break;
                    case 2: // Search by NguoiTao
                        if (String.valueOf(x.getNguoiTao()).contains(keyWord)) {
                            addToModel(model, x);
                        }
                        break;
                    default:
                        // Handle invalid i value
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addToModel(DefaultTableModel model, KhuyenMai x) {
        Object[] rows = {
            x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getNguoiTao(),
            x.getNgayBD(), x.getNgayKT(), x.getMucGiam(), x.getTrangthai(), x.getMota()
        };
        if (!x.isIsDelete()) {
            model.addRow(rows);
        }
    }

    public int isEmpty(KhuyenMai kh) {
        int check = 1;
        String info = "";
        if (txtMaKhuyenMai.getText().equals("")) {
            info += " Mã khuyến mãi không được để trống!";
            validate.colorVali(txtMaKhuyenMai);
            check = 0;
        } else {
            validate.careUPDATE(txtMaKhuyenMai);
            check = 1;
        }

        if (txtTenKhuyenMai.getText().equals("")) {
            info += "\n Tên khóa học không được để trống!";
            validate.colorVali(txtTenKhuyenMai);
            check = 0;
        } else {
            validate.careUPDATE(txtTenKhuyenMai);
            check = 1;
        }

        if (txtMucGiam.getText().equals("")) {
            info += "\n Thời lượng không được để trống!";
            validate.colorVali(txtMucGiam);
            check = 0;
        } else {
            validate.careUPDATE(txtMucGiam);
            check = 1;
        }

        if (txtNguoiTao.getText().equals("")) {
            info += "\n Người tạo không được để trống!";
            validate.colorVali(txtNguoiTao);
            check = 0;
        } else {
            validate.careUPDATE(txtNguoiTao);
            check = 1;
        }

        if (check == 0) {
            MsgBox.alert(this, info);
        }
        return check;
    }

    boolean isDuplicated(KhuyenMai kh) {
        KhuyenMai km = null;
        km = kmDAO.selectById(kh.getMaKhuyenMai());
        if (km != null) {
            MsgBox.alert(this, "Mã khuyến mãi đã tồn tại!");
            return true;
        } else {
            return false;
        }
    }

    boolean validateKhuyenMai() {
        boolean check = true;
        String info = "";

        // Bắt lỗi khi người dùng nhập vào txtMucGiam không phải là chữ số
        if (!isNumeric(txtMucGiam.getText())) {
            info += "Mức giảm phải là một số nguyên dương.\n";
            validate.colorVali(txtMucGiam);
            check = false;
        } else {
            int thoiLuong = Integer.parseInt(txtMucGiam.getText());
            if (thoiLuong <= 0 || thoiLuong > 100) {
                info += "Mức giảm phải lớn hơn 0 và bé hơn 100.\n";
                validate.colorVali(txtMucGiam);
                check = false;
            } else {
                validate.careUPDATE(txtMucGiam);
            }
        }

        try {
            if (txtNgayBD.getDate() != null && txtNgayKT.getDate() != null) {
                LocalDate ngayBD = txtNgayBD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate ngayKT = txtNgayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (ngayBD.isAfter(ngayKT)) {
                    info += "Ngày khuyến mãi phải nhỏ hơn ngày kết thúc.\n";
                    check = false;
                }
            }
        } catch (Exception e) {
        }

        if (!check) {
            MsgBox.alert(this, info);
        }

        return check;
    }

// Phương thức kiểm tra xem một chuỗi có phải là số không
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setTrangThai(boolean tt) {
        if (themMoi == true) {
            txtMaKhuyenMai.setEditable(tt);
        } else {
            txtMaKhuyenMai.setEditable(!tt);
        }
        txtTenKhuyenMai.setEditable(tt);
        txtMucGiam.setEditable(tt);
        txtNguoiTao.setEditable(tt);
        txtMoTa.setEditable(tt);
        btnLuu.setEnabled(tt);
        btnXoa.setEnabled(tt);
    }

    private void filterByTrangThai() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblKhuyenMai.setRowSorter(sorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (cboFilter.getSelectedIndex() == 1) {
            // Nếu lựa chọn là 1, hiển thị chỉ TrangThai(1)
            filters.add(RowFilter.regexFilter("Áp dụng", 6));
        } else if (cboFilter.getSelectedIndex() == 2) {
            // Nếu lựa chọn là 2, hiển thị chỉ TrangThai(0)
            filters.add(RowFilter.regexFilter("Hết thời hạn", 6));
        }

        RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);

        if (cboFilter.getSelectedIndex() == 0) {
            // Nếu lựa chọn là 0, không áp dụng bất kỳ điều kiện lọc nào
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(combinedFilter);
        }
        // Sau khi áp dụng bộ lọc, gọi lại fillTable để cập nhật dữ liệu hiển thị
        fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        panelTransparent1 = new com.yoga.zswing.PanelTransparent();
        cboFilter = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        panelTransparent2 = new com.yoga.zswing.PanelTransparent();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        txtTenKhuyenMai = new javax.swing.JTextField();
        txtMaKhuyenMai = new javax.swing.JTextField();
        txtNguoiTao = new javax.swing.JTextField();
        rdoHetThoiHan = new javax.swing.JRadioButton();
        rdoApDung = new javax.swing.JRadioButton();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        vach1 = new com.yoga.zcomponents.Vach();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Quản Lý Khuyến Mãi");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng thái" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel25.setText("Lọc");

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Mức giảm" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yoga/icon/icon_Search.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSearchMouseReleased(evt);
            }
        });

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuyenMai);

        javax.swing.GroupLayout panelTransparent1Layout = new javax.swing.GroupLayout(panelTransparent1);
        panelTransparent1.setLayout(panelTransparent1Layout);
        panelTransparent1Layout.setHorizontalGroup(
            panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        panelTransparent1Layout.setVerticalGroup(
            panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch)
                    .addGroup(panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTransparent2.setBackground(new java.awt.Color(252, 252, 252));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel12.setText("Tên khuyến mãi");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel13.setText("Ngày bắt đầu");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel14.setText("Ngày kết thúc");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel15.setText("Mức giảm");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel16.setText("Trạng thái");

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

        btnLuu.setBackground(new java.awt.Color(197, 214, 254));
        btnLuu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel17.setText("Mã khuyến mãi");

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMoTa.setRows(5);
        txtMoTa.setBorder(null);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel19.setText("Mô tả");

        txtMucGiam.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMucGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtTenKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTenKhuyenMai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtMaKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMaKhuyenMai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKhuyenMaiActionPerformed(evt);
            }
        });

        txtNguoiTao.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNguoiTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        rdoHetThoiHan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rdoHetThoiHan.setText("Hết thời hạn");

        rdoApDung.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rdoApDung.setText("Áp dụng");

        txtNgayKT.setBackground(new java.awt.Color(253, 225, 253));
        txtNgayKT.setDateFormatString("dd/MM/yyyy");

        txtNgayBD.setBackground(new java.awt.Color(253, 225, 253));
        txtNgayBD.setDateFormatString("dd/MM/yyyy");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel18.setText("Người tạo");

        javax.swing.GroupLayout panelTransparent2Layout = new javax.swing.GroupLayout(panelTransparent2);
        panelTransparent2.setLayout(panelTransparent2Layout);
        panelTransparent2Layout.setHorizontalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel12)
                        .addComponent(jLabel18)
                        .addComponent(jLabel17)
                        .addComponent(txtTenKhuyenMai)
                        .addComponent(txtMaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(rdoApDung))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoHetThoiHan))
                    .addComponent(jLabel19)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
        panelTransparent2Layout.setVerticalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(7, 7, 7)
                        .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoApDung)
                            .addComponent(rdoHetThoiHan))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTransparent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vach1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTransparent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTransparent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTransparent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
        filterByTrangThai();

    }//GEN-LAST:event_cboFilterActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked

    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseReleased
        int i = cboSearch.getSelectedIndex();
        search(i, txtSearch.getText().trim());
    }//GEN-LAST:event_btnSearchMouseReleased

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed

        Add();
        setTrangThai(true);
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (this.themMoi == true) {
            insert();
        }
        if (this.row != -1 && this.themMoi == false) {
            update();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtMaKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKhuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKhuyenMaiActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        row = tblKhuyenMai.getSelectedRow();
        if (row != -1) {
            themMoi = false;
            edit(row);
        }
        fillTable();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.yoga.zswing.PanelTransparent panelTransparent1;
    private com.yoga.zswing.PanelTransparent panelTransparent2;
    private javax.swing.JRadioButton rdoApDung;
    private javax.swing.JRadioButton rdoHetThoiHan;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtMucGiam;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKhuyenMai;
    private com.yoga.zcomponents.Vach vach1;
    // End of variables declaration//GEN-END:variables
}
