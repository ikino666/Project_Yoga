package com.raven.main;

import com.raven.component.Header;
import com.raven.component.Menu;
import z.event.EventMenuSelected;
import z.event.EventShowPopupMenu;
import com.raven.form.Form_Quanly_KhachHang;
import com.raven.form.Form_Home;
import com.raven.form.MainForm;
import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import com.formdev.flatlaf.FlatLightLaf;
import com.raven.form.Form_GoiDichVu;
import com.raven.form.Form_HoaDon;
import com.raven.form.Form_KhoSanPham;
import com.raven.form.Form_KhuyenMai;
import com.raven.form.Form_LichSuHoaDon;
import com.raven.form.Form_NguoiDung;
import com.raven.form.Form_Quanly_HocVien;
import com.raven.form.Form_Quanly_KhoaHoc;
import com.raven.form.Form_Quanly_LopHoc;
import com.raven.form.Form_SanPham;
import com.raven.form.Form_TaiKhoan;
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
                if (menuIndex == 0) {
                    if (subMenuIndex == 0) {            //Hóa đơn
                        main.showForm(new Form_HoaDon());
                    } else if (subMenuIndex == 1) {     //Lịch sử hóa đơn
                        main.showForm(new Form_LichSuHoaDon());
                    } else if (subMenuIndex == 2) {      //Thống kê
                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 1) {
                    if (subMenuIndex == 0) {            //Sản phẩm
                        main.showForm(new Form_SanPham());
                    } else if (subMenuIndex == 1) {     //Kho sản phẩm
                        main.showForm(new Form_KhoSanPham());
                    } else if (subMenuIndex == 2) {      //Thống kê
                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 2) {
                    if (subMenuIndex == 0) {            //Gói dịch vụ
                        main.showForm(new Form_GoiDichVu());
                    } else if (subMenuIndex == 1) {     //Khuyến mãi
                        main.showForm(new Form_KhuyenMai());
                    }
                }
                if (menuIndex == 3) {
                    if (subMenuIndex == 0) {            //Khóa học
                        main.showForm(new Form_Quanly_KhoaHoc());
                    } else if (subMenuIndex == 1) {     //Thống kê
                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 4) {
                    if (subMenuIndex == 0) {            //Lớp học
                        main.showForm(new Form_Quanly_LopHoc());
                    }  else if (subMenuIndex == 1) {     //Học viên
                        main.showForm(new Form_Quanly_HocVien());
                    }else if (subMenuIndex == 2) {     //Thống kê
                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 5) {
                    if (subMenuIndex == 0) {            //Khách hàng
                        main.showForm(new Form_Quanly_KhachHang());

                    } else if (subMenuIndex == 1) {     //Thống kê
                        main.showForm(new Form_Home());
                    }
                }
                if (menuIndex == 6) {           //Người dùng
                    main.showForm(new Form_NguoiDung());
                }
                if (menuIndex == 7) {           //Tài khoản
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
        main.showForm(new Form_Home());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new com.raven.swing.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private com.raven.swing.PanelTransparent bg;
    // End of variables declaration//GEN-END:variables
}
