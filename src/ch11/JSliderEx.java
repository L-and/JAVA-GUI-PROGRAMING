package ch11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.jdi.event.Event;

public class JSliderEx extends JFrame
{
	int windowVerticalSize = 300;
	int windowHorizontalSize = 300;

	Container container = getContentPane();
	
	ColorSliderPanel colorSliderPan;
	LabelPanel lblPan;
	
	public JSliderEx()
	{
		createWindow();
	}
	
	public void createWindow()
	{
		setTitle("JSlider 에제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	
//		레이아웃 설정
		setLayout(new BorderLayout());
		
		colorSliderPan = new ColorSliderPanel();
		lblPan = new LabelPanel();
		
//		패널 추가
		container.add(colorSliderPan, BorderLayout.NORTH);
		container.add(lblPan, BorderLayout.CENTER);
		
		setSize(windowVerticalSize, windowHorizontalSize);
		setVisible(true);
	}
	
	public class ColorSliderPanel extends JPanel
	{
		JSlider colorSlider[] = new JSlider[3];
		
		public ColorSliderPanel()
		{
//			레이아웃 설정
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			for(int i=0; i<3; i++)
			{
				colorSlider[i] = new JSlider(0, 255);
				
//				초기값 설정
				colorSlider[i].setValue(0);
				
//				눈금 설정
				colorSlider[i].setPaintTicks(true);
				colorSlider[i].setPaintLabels(true);
				colorSlider[i].setMajorTickSpacing(50);
				colorSlider[i].setMinorTickSpacing(10);
				
//				리스너추가
				colorSlider[i].addChangeListener(new ColorChangeListener());
				
				add(colorSlider[i]); // 슬라이더 추가
			}
		}
	
		public int[] getValue()
		{
			int[] values = new int[3];
			
			for(int i=0; i<3; i++)
			{
				values[i] = colorSlider[i].getValue();
			}
			
			return values;
		}
	}
	
	public class LabelPanel extends JPanel
	{
		JLabel label;
		
		public LabelPanel()
		{
			label = new JLabel("Java는 즐거워.");
			
//			폰트 설정
			label.setFont(new Font("돋움", Font.BOLD, 30));
			
//			패널에 추가
			add(label);
		}
		
//		배경색설정 함수
		public void setBackground(int[] values)
		{
			setBackground(new Color(255 - values[0],255 - values[1],255 - values[2]));
		}
		
//		 폰트색설정 함수
		public void setFontColor(int[] values)   
		{
			label.setForeground(new Color(values[0], values[1], values[2]));
		}
		
	}
	
	public class ColorChangeListener implements ChangeListener 
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			int[] values = colorSliderPan.getValue();
			
			lblPan.setBackground(values);
			lblPan.setFontColor(values);
		}
	}
	public static void main(String[] args)
	{
		new JSliderEx();
	}
}
