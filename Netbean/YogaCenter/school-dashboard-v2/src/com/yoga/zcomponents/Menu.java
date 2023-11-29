package com.yoga.zcomponents;

import z.event.EventMenu;
import z.event.EventMenuSelected;
import z.event.EventShowPopupMenu;
import z.model.ModelMenu;
import com.yoga.zswing.MenuAnimation;
import com.yoga.zswing.MenuItem;
import com.yoga.zswing.PanelTransparent;
import z.scrollbar.ScrollBarCustom;
import java.awt.Component;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends PanelTransparent {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
        setTransparent(0.675f);
    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/5.png")), "Quản lý khách hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/2.png")), "Quản lý hóa đơn", "Hóa đơn", "Lịch sử hóa đơn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/1.png")), "Quản lý sản phẩm", "Sản phẩm", "Kho sản phẩm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/6.png")), "Quản lý gói dịch vụ", "Dịch vụ", "Khuyến mãi"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/3.png")), "Quản lý khóa học"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/4.png")), "Quản lý lớp học", "Lớp học", "Học viên"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/9.png")), "Thống kê", "TK khách hàng", "TK sản phẩm", "TK học viên"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/10.png")), "Quản lý người dùng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/yoga/icon/8.png")), "Tài khoản"));
    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new com.yoga.zcomponents.Profile();

        setBackground(new java.awt.Color(93, 136, 255));

        sp.setBackground(new java.awt.Color(147, 175, 252));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new java.awt.Color(163, 210, 251));
        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profile1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.yoga.zcomponents.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
