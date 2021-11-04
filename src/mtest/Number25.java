// 20210010 김민

package mtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Number25 extends JFrame{
	
	int horizontalSize = 400, verticalSize = 420;
	int xPos, yPos;
	
	long startTime = System.currentTimeMillis();	
	long currentTime;
	
	ArrayList<Integer> numPos = new ArrayList<Integer>();
	
	
	Container container = getContentPane();
	JLabel[] numLabel = new JLabel[25];
	
	
	JPanel numLabelPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	
	JTextField timeTxtField = new JTextField("0 Sec", 6);
	
	JButton resetBtn = new JButton("reset");
	
	Random random = new Random();

	Font numFont = new Font("Arial", Font.BOLD, 24);
	
	public Number25()
	{
		genNumPosList();
		
		createWindow();
		createNumLabelPanel();
		createInfoPanel();
				
	}
	
	void createWindow()
	{
		setTitle("1~25 Game");
		setSize(horizontalSize, verticalSize);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		container.setLayout(new BorderLayout());
	}
	
	void genNumPosList()
	{
		for(int i=1;i<=25;i++)
		{
			numPos.add(i);
		}
	}
	
	void setRandomPosition()
	{		
		Collections.shuffle(numPos);
	}
	
	void genRandomNumLabel()
	{
		setRandomPosition();

		for(int i=0;i<numLabel.length;i++)
			numLabel[i].setText(Integer.toString(numPos.get(i)));

	}
	
	void setEnableAllNumLabel()
	{
		for(int i=0;i<numLabel.length;i++)
			numLabel[i].setEnabled(true);
	}
	
	void createNumLabelPanel()
	{		
		numLabelPanel.setLayout(new GridLayout(5, 5, 0, 0));
		for(int i=0;i<numLabel.length;i++)
		{
			numLabel[i] = new JLabel();
			numLabel[i].setSize(80, 80);
			numLabel[i].setBorder(new LineBorder(Color.LIGHT_GRAY));
			numLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			numLabel[i].setFont(numFont);
			
			numLabel[i].addMouseListener(new LabelRemoveMouseHandler());

			
			numLabelPanel.add(numLabel[i]);
			}
		
		genRandomNumLabel();
		
		container.add(numLabelPanel);
	}
	
	void createInfoPanel()
	{			
		resetBtn.addActionListener(new restartActionHandler());
	
		
		infoPanel.add(timeTxtField);
		infoPanel.add(resetBtn);
		
		container.add(infoPanel, "South");
	}
	
	public void updateTimeLabel()
	{
		currentTime = System.currentTimeMillis();
		double elapsedTime = (currentTime - startTime) / 1000.0;
		String elapsedTimeStr = String.format("%.1f Sec", elapsedTime);

		timeTxtField.setText(elapsedTimeStr);
		System.out.println(elapsedTimeStr);
	}
	
	public class LabelRemoveMouseHandler extends MouseAdapter
	{
		public static int count = 1;

		public void mouseClicked(MouseEvent e) {
			JLabel sourceLbl = (JLabel)e.getSource();

			int lblCount = Integer.parseInt(sourceLbl.getText());

			if(lblCount == count)
			{

				sourceLbl.setEnabled(false);
				count++;

				updateTimeLabel();
			}
		}
	}
	
	public class restartActionHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			genRandomNumLabel();
			setEnableAllNumLabel();
			
			LabelRemoveMouseHandler.count = 1;
			startTime = System.currentTimeMillis();
			timeTxtField.setText("0 Sec");
		}
		
	}
	
	public static void main(String[] args)
	{
		new Number25();
	}
}
