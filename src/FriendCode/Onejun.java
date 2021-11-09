package FriendCode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.util.*;

public class Onejun extends JFrame {
    
    static int WIDTH = 720, HEIGHT = 480;
    
    JLabel[] lbl = new JLabel[25];
    
    JPanel pnl;
    
    public Onejun() {
        Random rand = new Random();
        
        setTitle("임의의 레이블 생성");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        pnl = new JPanel();
        pnl.setLayout(null);
        c.add(pnl);
       
        pnl.setLayout(null);
        
        for(int i=0; i<lbl.length; i++) {
            lbl[i] = new JLabel(i+" Label");
        }
        
        for(int i=0; i<lbl.length; i++) {
            lbl[i].setSize(100,20);
            lbl[i].setLocation(rand.nextInt(), rand.nextInt());
        }
        
        for(int i=0; i<lbl.length; i++) {
            pnl.add(lbl[i]);
 
        }
        
        
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        
    }
//    class MouseClicked implements MouseListener {
//        
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            lbl = (JLabel)e.getSource();
//        }
//    }
    
    public static void main(String[] args) {
        new Onejun();
    }
}