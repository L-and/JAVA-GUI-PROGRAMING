package FriendCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyCodeRemakeEx extends JFrame {
	Container c = getContentPane();
	
	JLabel[] lbl = new JLabel[3];
	
	public KeyCodeRemakeEx() {
		setTitle("asdfasdf");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720,480);
		String[] str = {"getKeyCode()","getKeyChar()","getKeyText()"};
		KeyHandler1 handler = new KeyHandler1();
		c.setLayout(new FlowLayout());
		for(int i=0; i<lbl.length; i++) {
			lbl[i]=new JLabel(str[i]);
			lbl[i].setOpaque(true);
			lbl[i].setBackground(Color.YELLOW);
			c.add(lbl[i]);
			c.addKeyListener(handler);
		}
		setVisible(true);
		c.requestFocus();
	}
	
//	public void CreateWindow() {
//		setTitle("KeyCode");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(720,480);
//		setVisible(true);
//	}
	
	class KeyHandler1 extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			char keyChar = e.getKeyChar();
			int keyCode = e.getKeyCode();
			String keyText = KeyEvent.getKeyText(keyCode);
			
			lbl[0].setText(keyCode+"");
			lbl[1].setText(keyChar+"");
			lbl[2].setText(keyText);
			
			//��Ʈ ������ ����
			int size=lbl[0].getFont().getSize();
			
			if(keyCode == KeyEvent.VK_UP) {
				lbl[0].setFont(new Font("Arial",Font.BOLD,size+5));
				lbl[1].setFont(new Font("Arial",Font.BOLD,size+5));
				lbl[2].setFont(new Font("Arial",Font.BOLD,size+5));
			} 
			else if(keyCode == KeyEvent.VK_DOWN) {
				lbl[0].setFont(new Font("Arial",Font.BOLD,size-5));
				lbl[1].setFont(new Font("Arial",Font.BOLD,size-5));
				lbl[2].setFont(new Font("Arial",Font.BOLD,size-5));
			}
			else if(keyCode==KeyEvent.VK_F1) {
				c.setBackground(Color.green);
			}
			else if(keyChar=='%') {
				c.setBackground(Color.blue);
			}
		}
	}
	
	public static void main(String[] args) {
		new KeyCodeRemakeEx();
	}
}
