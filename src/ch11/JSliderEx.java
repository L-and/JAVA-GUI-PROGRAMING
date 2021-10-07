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
		setTitle("JSlider ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	
//		���̾ƿ� ����
		setLayout(new BorderLayout());
		
		colorSliderPan = new ColorSliderPanel();
		lblPan = new LabelPanel();
		
//		�г� �߰�
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
//			���̾ƿ� ����
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			for(int i=0; i<3; i++)
			{
				colorSlider[i] = new JSlider(0, 255);
				
//				�ʱⰪ ����
				colorSlider[i].setValue(0);
				
//				���� ����
				colorSlider[i].setPaintTicks(true);
				colorSlider[i].setPaintLabels(true);
				colorSlider[i].setMajorTickSpacing(50);
				colorSlider[i].setMinorTickSpacing(10);
				
//				�������߰�
				colorSlider[i].addChangeListener(new ColorChangeListener());
				
				add(colorSlider[i]); // �����̴� �߰�
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
			label = new JLabel("Java�� ��ſ�.");
			
//			��Ʈ ����
			label.setFont(new Font("����", Font.BOLD, 30));
			
//			�гο� �߰�
			add(label);
		}
		
//		�������� �Լ�
		public void setBackground(int[] values)
		{
			setBackground(new Color(255 - values[0],255 - values[1],255 - values[2]));
		}
		
//		 ��Ʈ������ �Լ�
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
