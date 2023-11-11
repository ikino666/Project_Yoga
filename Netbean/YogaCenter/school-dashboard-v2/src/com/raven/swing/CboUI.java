
package com.raven.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;


public class CboUI extends BasicComboBoxUI {

    Color c = new Color(199,173,250);

    public static ComboBoxUI createUI(JComponent com) {
        return new CboUI();
    }

    @Override
    protected ListCellRenderer createRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                super.getListCellRendererComponent(jlist, o, i, bln, bln1); 
                
                
                jlist.setSelectionBackground(c);

                return this;
            }
        };
    }
}
