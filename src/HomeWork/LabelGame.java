package HomeWork;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class LabelGame extends JFrame
{
	int horizontalSize = 500;
	int verticalSize = 500;

	long startTime = System.currentTimeMillis();	
	long currentTime;

	Random random = new Random();
	JLabel[] numLabel = new JLabel[25];
	JLabel timeLabel;
	JButton restartBtn;
	
	Container contentPane = getContentPane();
	
	int xPos;
	int yPos;
	
	public LabelGame()
	{
		createWindow();
		createNumLabel();
		createTimeLabel();
		createRestartButton();
		addLabel();
		addRestartButton();
	}

	public void createWindow()
	{
		setTitle("라벨게임");
		setSize(horizontalSize, verticalSize);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		contentPane.setLayout(null);
	}

	public void setNumLabelVisibleTrue()
	{
		for(int i=0;i<numLabel.length;i++)
		{
			numLabel[i].setVisible(true);
		}
	}
	
	public void getRandomPosition()
	{
		//test
//		xPos = 250;
//		yPos = 250;
		
		xPos = random.nextInt(horizontalSize - 200) + 100;
		yPos = random.nextInt(verticalSize - 200) + 100;
	}
	
	public void createNumLabel()
	{
		for(int i=0;i<numLabel.length;i++)
		{

			numLabel[i] = new JLabel(Integer.toString(i + 1));
			
			getRandomPosition();
			numLabel[i].setLocation(xPos, yPos);
			numLabel[i].setSize(15, 15);
			numLabel[i].setBackground(Color.RED);
			numLabel[i].setOpaque(true);

			numLabel[i].addMouseListener(new LabelRemoveMouseHandler());
		}
	}

	public void createTimeLabel()
	{
		timeLabel = new JLabel("0초");
		timeLabel.setSize(50,15);
		timeLabel.setLocation(10,10);
		timeLabel.setBackground(Color.white);
		timeLabel.setOpaque(true);
	}

	public void createRestartButton()
	{
		restartBtn = new JButton("재시작");
		restartBtn.setLocation(10, 30);
		restartBtn.setSize(80,40);
		restartBtn.addActionListener(new restartActionHandler());
	}
	
	public void addRestartButton()
	{
		contentPane.add(restartBtn);
	}
	
	public void addLabel()
	{
		for(int i=0;i<numLabel.length;i++)
		{
			contentPane.add(numLabel[i]);
		}

		contentPane.add(timeLabel);
	}

	public void updateTimeLabel()
	{
		currentTime = System.currentTimeMillis();
		double elapsedTime = (currentTime - startTime) / 1000.0;
		String elapsedTimeStr = String.format("%.2f초", elapsedTime);

		timeLabel.setText(elapsedTimeStr);
	}

	public void resetNunLabelPosition()
	{
		for(int i=0;i<numLabel.length;i++)
		{
			getRandomPosition();
			numLabel[i].setLocation(xPos, yPos);
		}
	}

	public class LabelRemoveMouseHandler extends MouseAdapter
	{
		public static int count = 1;

		public void mouseClicked(MouseEvent e) {
			JLabel sourceLbl = (JLabel)e.getSource();

			int lblCount = Integer.parseInt(sourceLbl.getText());

			if(lblCount == count)
			{
				sourceLbl.setVisible(false);
				count++;

				updateTimeLabel();
			}
		}
	}

	public class restartActionHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(numLabel[24].isVisible() == false)
			{
				resetNunLabelPosition();
				setNumLabelVisibleTrue();
				LabelRemoveMouseHandler.count = 1;
				startTime = System.currentTimeMillis();
				timeLabel.setText("0초");
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		new LabelGame();
	}
}

//1. 1~25사이의 레이블을 발생시킨다.
//2. 임의의 위치에 배치한다. (배치관리자를 NULL 로 처리)
//3. 낮은 수부터 클릭으로 레이블을 없앤다.
//4. 시간을 측정한다. [심화]