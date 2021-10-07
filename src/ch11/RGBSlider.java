package ch11;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RGBSlider extends JFrame {
	
	JSlider rgb[] = new JSlider[3];

	JPanel controllerPnl, colorPnl;

	JLabel colorStr;
	
	public RGBSlider() {
		setTitle("RGB 슬라이더");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controllerPnl = new JPanel();
		colorPnl = new JPanel();

		colorStr = new JLabel("JAVA는 즐거워");
		
		Container c = getContentPane();
		setLayout(new BorderLayout());

		c.add(controllerPnl, BorderLayout.NORTH);
		controllerPnl.setLayout(new BoxLayout(controllerPnl,BoxLayout.Y_AXIS));
		c.add(colorPnl, BorderLayout.CENTER);
		
		for(int i=0; i<rgb.length; i++) {
			rgb[i] = new JSlider(JSlider.HORIZONTAL,0,255,128);
			rgb[i].setPaintLabels(true);
			rgb[i].setPaintTicks(true);
			rgb[i].setPaintTrack(true);
			rgb[i].setMajorTickSpacing(50);
			rgb[i].setMinorTickSpacing(10);
			rgb[i].addChangeListener(new ControlColorChange());
			controllerPnl.add(rgb[i]);
		}
		
		rgb[0].setForeground(Color.red);
		rgb[1].setForeground(Color.green);
		rgb[2].setForeground(Color.blue);
		
		int r = rgb[0].getValue();
		int g = rgb[1].getValue();
		int b = rgb[2].getValue();
		
		colorStr.setBackground(new Color(r,g,b));
		colorPnl.setBackground(new Color(r,g,b));
		colorPnl.add(colorStr);
		setSize(720,480);
		setVisible(true);
	}
	
	class ControlColorChange implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			int r = rgb[0].getValue();
			int g = rgb[1].getValue();
			int b = rgb[2].getValue();
			colorStr.setBackground(new Color(r,g,b));
			colorPnl.setBackground(new Color(r,g,b));
		}
	}

	public static void  main(String[] args) {
		new RGBSlider();
	}
}