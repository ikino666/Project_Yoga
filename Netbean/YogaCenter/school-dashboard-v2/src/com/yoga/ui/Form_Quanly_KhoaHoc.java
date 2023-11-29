package com.yoga.ui;

import com.edusys.zCus_Table.TableCustom;
import com.yoga.models.KhoaHocDAO;
import com.yoga.entitys.KhoaHoc;
import com.yoga.utils.MsgBox;
import com.yoga.utils.XDate;
import com.yoga.utils.XImage;
import com.yoga.utils.XJdbc;
import com.yoga.utils.validate;
import com.yoga.zswing.CboUI;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Form_Quanly_KhoaHoc extends javax.swing.JPanel {       
    KhoaHocDAO kHDao = new KhoaHocDAO();
    int row = -1;
    boolean themMoi = false;
    JFileChooser fileChooser = new JFileChooser();
    boolean editmove = false;

    Calendar calendar = Calendar.getInstance();
    Date currentDate1 = calendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String formattedDate = dateFormat.format(currentDate1);

    public Form_Quanly_KhoaHoc() {
        initComponents();
        initTables();
        fillComboboxFilter();
        fillTable();
        setTrangThai(false);
        setOpaque(false);
        setComponentsDefault();
    }

    public void setComponentsDefault() {
        txtMaKhoaHoc.setBackground(new Color(0,0,0,1));
        txtTenKhoaHoc.setBackground(new Color(0,0,0,1));
        txtHocPhi.setBackground(new Color(0,0,0,1));
        txtThoiLuong.setBackground(new Color(0,0,0,1));
        txtNguoiTao.setBackground(new Color(0,0,0,1));
        txtNgayTao.setDate(currentDate1);

        pnlForm.setTransparent(1f);
        pnlTable.setTransparent(1f);

        txtMaKhoaHoc.setEditable(false);
        txtNgayTao.setEnabled(false);
    }

    void initTables() {
        com.edusys.zCus_Table.TableCustom.apply(jScrollPane3, TableCustom.TableType.DEFAULT);
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
        model.setColumnCount(0);
        model.addColumn("Mã Khóa Học");
        model.addColumn("Tên Khóa Học");
        model.addColumn("Thời Lượng");
        model.addColumn("Học Phí");
        model.addColumn("Mô Tả");
        model.addColumn("Ngày Tạo");
        model.addColumn("Người Tạo");

        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15));
    }

    public void fillComboboxFilter() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboFilter.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cboSearch.getModel();
        model.removeAllElements();
        model2.removeAllElements();
        model.addElement("Mã khóa học");
        model.addElement("Tên khóa học");
        model.addElement("Thời lượng");
        model.addElement("Học phí");
        model.addElement("Ngày tạo");

        model2.addElement("Mã khóa học");
        model2.addElement("Tên khóa học");
        model2.addElement("Thời lượng");
        model2.addElement("Học phí");
        model2.addElement("Ngày tạo");
        model2.addElement("Người tạo");
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
        model.setRowCount(0);
        try {
            List<KhoaHoc> list = kHDao.selectAll();
            for (KhoaHoc kh : list) {
                Object[] rows = {kh.getMaKHoc(), kh.getTenKHoc(), kh.getThoiLuong(), kh.getHocPhi(), kh.getMoTa(),
                    XDate.toString(kh.getNgayTao(), "dd/MM/yyyy"), kh.getNguoiTao()};
                if (!kh.isIsDelete()) {         //xét có bị xóa chưa?
                    model.addRow(rows);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void filter(int index) {
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
        model.setRowCount(0);

        try {
            List<KhoaHoc> list = null;

            switch (index) {
                case 0:
                    list = kHDao.filterMaKH();
                    break;
                case 1:
                    list = kHDao.filterTenKH();
                    break;
                case 2:
                    list = kHDao.filterThoiLuong();
                    break;
                case 3: 
                    list = kHDao.filterHocPhi();
                    break;
                case 4:
                    list = kHDao.filterNgayTao();
                    break;
                default:
                    list = kHDao.selectAll();
                    break;
            }

            for (KhoaHoc kh : list) {
                Object[] rows = {kh.getMaKHoc(), kh.getTenKHoc(), kh.getThoiLuong(), kh.getHocPhi(), kh.getMoTa(),
                    XDate.toString(kh.getNgayTao(), "dd/MM/yyyy"), kh.getNguoiTao()};
                if (!kh.isIsDelete()) {         //xét có bị xóa chưa?
                    model.addRow(rows);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void fillTableSearch() {
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
        model.setRowCount(0);
        try {
            List<KhoaHoc> list = kHDao.selectAllOrderByMaKhoaHoc();
            for (KhoaHoc kh : list) {
                Object[] rows = {kh.getMaKHoc(), kh.getTenKHoc(), kh.getThoiLuong(), kh.getHocPhi(), kh.getMoTa(),
                    XDate.toString(kh.getNgayTao(), "dd/MM/yyyy"), kh.getNguoiTao()};
                if (!kh.isIsDelete()) {         //xét có bị xóa chưa?
                    model.addRow(rows);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private KhoaHoc getForm() {
        KhoaHoc kh = new KhoaHoc();
        kh.setMaKHoc(txtMaKhoaHoc.getText().toUpperCase().trim());
        kh.setTenKHoc(txtTenKhoaHoc.getText().trim());
        try {
            int thoiLuong = Integer.parseInt(txtThoiLuong.getText().trim());
            kh.setThoiLuong(thoiLuong);
        } catch (NumberFormatException e) {
            // Xử lý lỗi ở đây, tương tự như trên.
        }
        try {
            double hocPhi = Double.parseDouble(txtHocPhi.getText().trim());
            kh.setHocPhi(hocPhi);
        } catch (NumberFormatException e) {
            // Xử lý lỗi ở đây, ví dụ: hiển thị thông báo lỗi hoặc gán giá trị mặc định.
            // Ví dụ: cd.setHocPhi(0.0); hoặc thông báo lỗi cho người dùng.           
        }

        kh.setHinhAnh(lblHinh.getToolTipText());
        kh.setMoTa(txtMoTa.getText().trim());
        if (txtNguoiTao.getText().trim().equals("")) {
//            kh.setNguoiTao(Auth.user.getMaNV());
        } else {
            kh.setNguoiTao(txtNguoiTao.getText().trim());
        }
        kh.setNgayTao(txtNgayTao.getDate());
        kh.setIsDelete(false);
        return kh;
    }

    void setForm(KhoaHoc kh) {
        txtNgayTao.setDate(kh.getNgayTao());
        txtNguoiTao.setText(kh.getNguoiTao());
        txtMaKhoaHoc.setText(kh.getMaKHoc());
        txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
        txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
        txtTenKhoaHoc.setText(kh.getTenKHoc());
        txtMoTa.setText(kh.getMoTa());
        if (kh.getHinhAnh() != null) {
            lblHinh.setToolTipText(kh.getHinhAnh());
            lblHinh.setIcon(XImage.read(kh.getHinhAnh()));
        } else {
            lblHinh.setIcon(null);
        }
    }

    public void edit(Integer row) {
        String makh = (String) tblKhoaHoc.getValueAt(this.row, 0);
        KhoaHoc kh = kHDao.selectById(makh);
        this.setForm(kh);
        isEmpty(kh);
        setTrangThai(true);
    }

    void clearForm() {
        row = -1;
        lblHinh.setIcon(null);
        lblHinh.setToolTipText(null);
        txtMaKhoaHoc.setText("");
        txtTenKhoaHoc.setText("");
        txtThoiLuong.setText("");
        txtHocPhi.setText("");
        txtNgayTao.setDate(currentDate1);
        txtNguoiTao.setText("");
        txtMoTa.setText("");
        txtSearch.setText("");
        setComponentsDefault();
    }

    void themMoi() {
        clearForm();
        themMoi = true;
        btnXoa.setEnabled(false);
        KhoaHoc kh = kHDao.selectMaKhoaHocMoiNhat();
        txtMaKhoaHoc.setText(kh.getMaKHoc());
        int temp = Integer.parseInt(kh.getMaKHoc().substring(2)) + 1;
        String result = "";
        if (String.valueOf(temp).length() == 1) {
            result = "KH00" + String.valueOf(temp);
        } else if (String.valueOf(temp).length() == 2) {
            result = "KH0" + String.valueOf(temp);
        }
        if (String.valueOf(temp).length() == 3) {
            result = "KH" + String.valueOf(temp);
        }
        txtMaKhoaHoc.setText(result);
    }

    public void insert() {
        try {
            KhoaHoc kh = getForm();
            if (isEmpty(kh) == 1 && isDuplicated(kh) == false && validateKhoaHoc() == true) {
                kHDao.insert(kh);
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
        KhoaHoc kh = getForm();
        try {
            if (isEmpty(kh) == 1 && validateKhoaHoc() == true) {
                kHDao.update(kh);
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
        String maKHoc = txtMaKhoaHoc.getText().trim();
        KhoaHoc kh1 = kHDao.selectAllFKLopHoc();
        KhoaHoc kh2 = kHDao.selectAllFKGoiDichVu();
        if (MsgBox.confirm(this, "Xác nhận xóa khóa học '" + maKHoc + "'?")) {
            try {
                if (kh1 != null || kh2 != null) {
                    kHDao.setIsDeleteTrue(maKHoc);
                } else if (kh1 == null && kh2 == null) {
                    kHDao.delete(maKHoc);
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
        List<KhoaHoc> list = kHDao.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
        model.setRowCount(0);
        try {
            if (i == 0) {
                for (KhoaHoc x : list) {
                    if ((x.getMaKHoc().toLowerCase()).contains(keyWord.toLowerCase())) {
                        Object[] rows = {x.getMaKHoc(), x.getTenKHoc(), x.getThoiLuong(), x.getHocPhi(), x.getMoTa(),
                            XDate.toString(x.getNgayTao(), "dd/MM/yyyy"), x.getNguoiTao()};
                        if (!x.isIsDelete()) {         //xét có bị xóa chưa?
                            model.addRow(rows);
                        }
                    }
                }
            } else if (i == 1) {            //Tìm kiếm theo tên khóa học
                for (KhoaHoc x : list) {
                    if ((x.getTenKHoc().toLowerCase()).contains(keyWord.toLowerCase())) {
                        Object[] rows = {x.getMaKHoc(), x.getTenKHoc(), x.getThoiLuong(), x.getHocPhi(), x.getMoTa(),
                            XDate.toString(x.getNgayTao(), "dd/MM/yyyy"), x.getNguoiTao()};
                        if (!x.isIsDelete()) {         //xét có bị xóa chưa?
                            model.addRow(rows);
                        }
                    }
                }
            } else if (i == 2) {             //Tìm kiếm theo thời lượng
                for (KhoaHoc x : list) {
                    if (String.valueOf(x.getThoiLuong()).contains(keyWord)) {
                        Object[] rows = {x.getMaKHoc(), x.getTenKHoc(), x.getThoiLuong(), x.getHocPhi(), x.getMoTa(),
                            XDate.toString(x.getNgayTao(), "dd/MM/yyyy"), x.getNguoiTao()};
                        if (!x.isIsDelete()) {         //xét có bị xóa chưa?
                            model.addRow(rows);
                        }
                    }
                }
            } else if (i == 3) {             //Tìm kiếm theo học phí
                for (KhoaHoc x : list) {
                    if (String.valueOf(x.getHocPhi()).contains(keyWord)) {
                        Object[] rows = {x.getMaKHoc(), x.getTenKHoc(), x.getThoiLuong(), x.getHocPhi(), x.getMoTa(),
                            XDate.toString(x.getNgayTao(), "dd/MM/yyyy"), x.getNguoiTao()};
                        if (!x.isIsDelete()) {         //xét có bị xóa chưa?
                            model.addRow(rows);
                        }
                    }
                }
            } else if (i == 4) {            //Tìm kiếm theo ngày tạo
                for (KhoaHoc x : list) {
                    if (String.valueOf(x.getNgayTao()).contains(keyWord)) {
                        Object[] rows = {x.getMaKHoc(), x.getTenKHoc(), x.getThoiLuong(), x.getHocPhi(), x.getMoTa(),
                            XDate.toString(x.getNgayTao(), "dd/MM/yyyy"), x.getNguoiTao()};
                        if (!x.isIsDelete()) {         //xét có bị xóa chưa?
                            model.addRow(rows);
                        }
                    }
                }
            } else if (i == 5) {            //Tìm kiếm theo người tạo
                for (KhoaHoc x : list) {
                    if (String.valueOf(x.getNguoiTao().toLowerCase()).contains(keyWord.toLowerCase())) {
                        Object[] rows = {x.getMaKHoc(), x.getTenKHoc(), x.getThoiLuong(), x.getHocPhi(), x.getMoTa(),
                            XDate.toString(x.getNgayTao(), "dd/MM/yyyy"), x.getNguoiTao()};
                        if (!x.isIsDelete()) {         //xét có bị xóa chưa?
                            model.addRow(rows);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int isEmpty(KhoaHoc kh) {
        int check = 1;
        String info = "";
        if (txtMaKhoaHoc.getText().equals("")) {
            info += " Mã khóa học không được để trống!";
            validate.colorVali(txtMaKhoaHoc);
            check = 0;
        } else {
            validate.careUPDATE(txtMaKhoaHoc);
            check = 1;
        }

        if (txtTenKhoaHoc.getText().equals("")) {
            info += "\n Tên khóa học không được để trống!";
            validate.colorVali(txtTenKhoaHoc);
            check = 0;
        } else {
            validate.careUPDATE(txtTenKhoaHoc);
            check = 1;
        }

        if (txtThoiLuong.getText().equals("")) {
            info += "\n Thời lượng không được để trống!";
            validate.colorVali(txtThoiLuong);
            check = 0;
        } else {
            validate.careUPDATE(txtThoiLuong);
            check = 1;
        }

        if (txtHocPhi.getText().equals("")) {
            info += "\n Học phí không được để trống!";
            validate.colorVali(txtHocPhi);
            check = 0;
        } else {
            validate.careUPDATE(txtHocPhi);
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

    boolean isDuplicated(KhoaHoc kh) {
        KhoaHoc khoaHoc = null;
        khoaHoc = kHDao.selectById(kh.getMaKHoc());
        if (khoaHoc != null) {
            MsgBox.alert(this, "Mã khóa học đã tồn tại!");
            return true;
        } else {
            return false;
        }
    }

    boolean validateKhoaHoc() {
        boolean check = true;
        String info = "";
        try {
            int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
            if (thoiLuong <= 0 || thoiLuong > 9999) {
                info += "Thời lượng phải lớn hơn 0 và bé hơn 5 số";
                validate.colorVali(txtThoiLuong);
                check = false;
            } else {
                validate.careUPDATE(txtThoiLuong);
            }

        } catch (NumberFormatException e) {
            info += "Thời lượng phải là một số nguyên dương";
            validate.colorVali(txtThoiLuong);
            check = false;
        }
        try {
            Double hocPhi = Double.parseDouble(txtHocPhi.getText());
            if (hocPhi <= 0 || hocPhi > 999999999) {
                info += "Thời lượng phải lớn hơn 0 và bé hơn 9 số";
                validate.colorVali(txtHocPhi);
                check = false;
            } else {
                validate.careUPDATE(txtHocPhi);
            }
        } catch (NumberFormatException e) {
            info += "\nHọc phí phải là một số dương";
            validate.colorVali(txtHocPhi);
            check = false;
        }

        if (check == false) {
            MsgBox.alert(this, info);
        }
        return check;
    }

    public void setTrangThai(boolean tt) {
        txtMaKhoaHoc.setEditable(!tt);
        txtTenKhoaHoc.setEditable(tt);
        txtThoiLuong.setEditable(tt);
        txtHocPhi.setEditable(tt);
        txtNguoiTao.setEditable(tt);
        txtMoTa.setEditable(tt);
        btnLuu.setEnabled(tt);
        btnXoa.setEnabled(tt);
    }

    void choosePicture() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileChooser.setDialogTitle("Choose Image");
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            int labelWidth = lblHinh.getWidth();
            int labelHeight = lblHinh.getHeight();

            // Thay đổi kích thước của ảnh để phù hợp với JLabel
            Image image = icon.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            lblHinh.setToolTipText(file.getName());
            lblHinh.setIcon(icon);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        pnlTable = new com.yoga.zswing.PanelTransparent();
        jLabel10 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox<>();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhoaHoc = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnlForm = new com.yoga.zswing.PanelTransparent();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKhoaHoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        txtTenKhoaHoc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblHinh = new javax.swing.JLabel();
        txtNgayTao = new com.toedter.calendar.JDateChooser();
        txtNguoiTao = new javax.swing.JTextField();
        txtThoiLuong = new javax.swing.JTextField();
        vach1 = new com.yoga.zcomponents.Vach();

        pnlTable.setBackground(new java.awt.Color(252, 252, 252));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Lọc");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thời lượng" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
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

        tblKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhoaHoc.setRowHeight(30);
        tblKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoaHocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKhoaHoc);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Quản Lý Khóa Học");

        pnlForm.setBackground(new java.awt.Color(252, 252, 252));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel2.setText("Người tạo");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setText("Mã khóa học*");

        txtMaKhoaHoc.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtMaKhoaHoc.setForeground(new java.awt.Color(0, 82, 147));
        txtMaKhoaHoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setText("Thời lượng*");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Tên khóa học*");

        txtHocPhi.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtHocPhi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtHocPhi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHocPhiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHocPhiFocusLost(evt);
            }
        });

        txtTenKhoaHoc.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtTenKhoaHoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenKhoaHoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKhoaHocFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenKhoaHocFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setText("Học phí*");

        btnLuu.setBackground(new java.awt.Color(197, 214, 254));
        btnLuu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLuu.setText("Lưu");
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
        jLabel8.setText("Ngày tạo");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel9.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblHinhMouseReleased(evt);
            }
        });

        txtNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayTao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNgayTao.setDateFormatString("dd/MM/yyyy");
        txtNgayTao.setEnabled(false);
        txtNgayTao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtNguoiTao.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtNguoiTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtThoiLuong.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtThoiLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtThoiLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtThoiLuongFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtThoiLuongFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(txtThoiLuong)
                        .addComponent(txtMaKhoaHoc)
                        .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtHocPhi)
                    .addComponent(txtTenKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addComponent(txtMaKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
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
            .addComponent(pnlTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vach1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (this.themMoi == true) {
            insert();
        }
        if (this.row != -1 && this.themMoi == false) {
            update();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        setTrangThai(true);
        themMoi();

    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblHinhMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseReleased
        choosePicture();
    }//GEN-LAST:event_lblHinhMouseReleased

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
        int index = cboFilter.getSelectedIndex();
        filter(index);
    }//GEN-LAST:event_cboFilterActionPerformed

    private void btnSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseReleased
        int i = cboSearch.getSelectedIndex();
        search(i, txtSearch.getText().trim());
    }//GEN-LAST:event_btnSearchMouseReleased

    private void tblKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoaHocMouseClicked

        row = tblKhoaHoc.getSelectedRow();
        if (row != -1) {
            themMoi = false;
            edit(row);
        }
    }//GEN-LAST:event_tblKhoaHocMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        int i = cboSearch.getSelectedIndex();
        search(i, txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtTenKhoaHocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhoaHocFocusGained
        txtTenKhoaHoc.setBackground(Color.white);
    }//GEN-LAST:event_txtTenKhoaHocFocusGained

    private void txtTenKhoaHocFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhoaHocFocusLost
        if (txtTenKhoaHoc.getText().equals("")) {
            txtTenKhoaHoc.setBackground(Color.pink);
        } else {
            txtTenKhoaHoc.setBackground(Color.white);
        }
    }//GEN-LAST:event_txtTenKhoaHocFocusLost

    private void txtThoiLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtThoiLuongFocusGained
        txtThoiLuong.setBackground(Color.white);
    }//GEN-LAST:event_txtThoiLuongFocusGained

    private void txtThoiLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtThoiLuongFocusLost
        if (txtThoiLuong.getText().equals("")) {
            txtThoiLuong.setBackground(Color.pink);
        } else {
            txtThoiLuong.setBackground(Color.white);
        }
    }//GEN-LAST:event_txtThoiLuongFocusLost

    private void txtHocPhiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHocPhiFocusGained
        txtHocPhi.setBackground(Color.white);
    }//GEN-LAST:event_txtHocPhiFocusGained

    private void txtHocPhiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHocPhiFocusLost
        if (txtHocPhi.getText().equals("")) {
            txtHocPhi.setBackground(Color.pink);
        } else {
            txtHocPhi.setBackground(Color.white);
        }
    }//GEN-LAST:event_txtHocPhiFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHinh;
    private com.yoga.zswing.PanelTransparent pnlForm;
    private com.yoga.zswing.PanelTransparent pnlTable;
    private javax.swing.JTable tblKhoaHoc;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaKhoaHoc;
    private javax.swing.JTextArea txtMoTa;
    private com.toedter.calendar.JDateChooser txtNgayTao;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKhoaHoc;
    private javax.swing.JTextField txtThoiLuong;
    private com.yoga.zcomponents.Vach vach1;
    // End of variables declaration//GEN-END:variables

}
