package ch12;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicEx extends JFrame{



	public GraphicEx()
	{
		createWindow();
		setContentPane(new GraphicPanel());
	}

	void createWindow()
	{
		setTitle("GraphicEx");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}



	public class GraphicPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			
		}
	}

	
	public static void main(String[] args)
	{
		new GraphicEx();
	}
}