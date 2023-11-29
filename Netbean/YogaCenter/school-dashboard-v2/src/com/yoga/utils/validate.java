//Tran Trung Tin - PC07488
package com.yoga.utils;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class validate {
    static public void colorVali(JTextField str){
        str.setText("");
        str.setBackground(Color.pink);
        str.requestFocus();
    }
    
    static public void careUPDATE(JTextField str) {
        try {
            if (str.getText().equals("")) {
                str.setBackground(Color.pink);
            } else {
                str.setBackground(new Color(0,0,0,1));
            }
        } catch (Exception e) {
        }
    }
  
    public static String email3 = "";
}
