package ch09;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class MyFrame extends JFrame {
	int windowHorizontalSize = 400;
	int windowVerticalSize = 400;
	
	public MyFrame()
	{
		setTitle("500x300 스윙 프레임 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		
		setLayout(new FlowLayout());
		
		JPanel p = new JPanel();
		
		p.setBackground(Color.GREEN);
		
		p.setPreferredSize(new Dimension(windowHorizontalSize - windowHorizontalSize / 2, windowVerticalSize));
		
		
		add(p);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}

}
