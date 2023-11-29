package com.yoga.main;

import com.yoga.zcomponents.Header;
import com.yoga.zcomponents.Menu;
import z.event.EventMenuSelected;
import z.event.EventShowPopupMenu;
import com.yoga.ui.Form_Quanly_KhachHang;
import com.yoga.ui.MainForm;
import com.yoga.zswing.MenuItem;
import com.yoga.zswing.PopupMenu;
import z.swing.icons.GoogleMaterialDesignIcons;
import z.swing.icons.IconFontSwing;
import com.formdev.flatlaf.FlatLightLaf;
import com.yoga.ui.Form_GoiDichVu;
import com.yoga.ui.Form_HoaDon;
import com.yoga.ui.Form_KhoSanPham;
import com.yoga.ui.Form_KhuyenMai;
import com.yoga.ui.Form_LichSuHoaDon;
import com.yoga.ui.Form_NguoiDung;
import com.yoga.ui.Form_Quanly_HocVien;
import com.yoga.ui.Form_Quanly_KhachHang;
import com.yoga.ui.Form_Quanly_KhoaHoc;
import com.yoga.ui.Form_Quanly_LopHoc;
import com.yoga.ui.Form_SanPham;
import com.yoga.ui.Form_TaiKhoan;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import ru.krlvm.swingacrylic.SwingAcrylic;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;

    public Main() {
        initComponents();
        init();
    }

    private void init() {
        layout = new MigLayout("fill", "10[]10[100%, fill]10", "10[fill, top]10");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {           //Khách hàng
                    main.showForm(new Form_Quanly_KhachHang());
                }
                if (menuIndex == 1) {
                    if (subMenuIndex == 0) {            //Hóa đơn
                        main.showForm(new Form_HoaDon());
                    } else if (subMenuIndex == 1) {     //Lịch sử hóa đơn
                        main.showForm(new Form_LichSuHoaDon());
                    } else if (subMenuIndex == 2) {      //Thống kê
//                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 2) {
                    if (subMenuIndex == 0) {            //Sản phẩm
                        main.showForm(new Form_SanPham());
                    } else if (subMenuIndex == 1) {     //Kho sản phẩm
                        main.showForm(new Form_KhoSanPham());
                    }
                }
                if (menuIndex == 3) {
                    if (subMenuIndex == 0) {            //Gói dịch vụ
                        main.showForm(new Form_GoiDichVu());
                    } else if (subMenuIndex == 1) {     //Khuyến mãi
                        main.showForm(new Form_KhuyenMai());
                    }
                }
                if (menuIndex == 4) {                            //Khóa học
                        main.showForm(new Form_Quanly_KhoaHoc());
                }
                if (menuIndex == 5) {
                    if (subMenuIndex == 0) {            //Lớp học
                        main.showForm(new Form_Quanly_LopHoc());
                    }  else if (subMenuIndex == 1) {     //Học viên
                        main.showForm(new Form_Quanly_HocVien());
                    }
                }
                if (menuIndex == 6) {
                    if (subMenuIndex == 0) {            //Thống kê
//                        main.showForm(new Form_Quanly_KhachHang());
                    } else if (subMenuIndex == 1) {     //Thống kê
//                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 7) {           //Người dùng
                    main.showForm(new Form_NguoiDung());
                }
                if (menuIndex == 8) {           //Tài khoản
                    main.showForm(new Form_TaiKhoan());
                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 62;
                int y = Main.this.getY() + com.getY() + 95;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Start with this form
        main.showForm(new Form_HoaDon());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new com.yoga.zswing.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1354, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (Exception ex) {
                    System.err.println("Failed to initialize LaF");
                }
                SwingAcrylic.prepareSwing();
                Main frame = new Main();
                frame.setVisible(true);
                SwingAcrylic.processFrame(frame);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.yoga.zswing.PanelTransparent bg;
    // End of variables declaration//GEN-END:variables
}
