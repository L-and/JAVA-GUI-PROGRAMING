package FriendCode;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class MouseAssign extends JFrame {
	
	Container c = getContentPane();
    int s = 1;
    JLabel[] lbl = new JLabel[26];
    JPanel pnl1, pnl2;
    JButton btn = new JButton("restart");
    
    public MouseAssign(){
        setTitle("1부터 25까지의 숫자 순서대로 누르기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnl1 = new JPanel();
        pnl2 = new JPanel();
        pnl1.setLayout(null);
        
        for(int i = 1; i < 26; i++){
            lbl[i]= new JLabel();
            lbl[i].setText("" + i);
            lbl[i].setSize(18, 18);
            int x = (int)(Math.random()*250);
            int y = (int)(Math.random()*250);
            lbl[i].setLocation(x, y);
            lbl[i].addMouseListener(new MyMouseListener());
            pnl1.add(lbl[i]);
        }
        
        btn.addActionListener(new MyActionListener());
        pnl2.add(btn);

        pnl2.setLayout(new FlowLayout());
        
        c.add(pnl1, BorderLayout.CENTER);
        c.add(pnl2, BorderLayout.SOUTH);

        setSize(300,360);
        setVisible(true);
    }
    
    class MyMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
        	JLabel lb = (JLabel)e.getSource();
            if(lbl[s] == lb){
                lb.setVisible(false);
                s++;
            }

        }
        
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    
    class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton bn = (JButton)e.getSource();
	
			if(btn == bn) {
				if (lbl[24].isVisible() == false) {
					
				    int s = 1;
				    pnl1.setLayout(null);
					for(int i = 1; i < 26; i++){
			            lbl[i].setText("" + i);
			            lbl[i].setSize(18, 18);
			            int x = (int)(Math.random()*250);
			            int y = (int)(Math.random()*250);
			            lbl[i].setLocation(x, y);
			            lbl[i].addMouseListener(new MyMouseListener());
		//	            pnl1.add(lbl[i]);
			        }

					lbl[s].setVisible(true);
					s++;
					
					
				}
				
			}
		}
    	
    }

    public static void main(String[] args) {
    	new MouseAssign();
    }
}


