package ch11;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class Slider extends JFrame{
	private JLabel colorLabel = new JLabel("JAVA´Â Áñ°Å¿ö.");
	private JSlider [] sl = new JSlider[3];
	
	JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
	public Slider() {
		
		
		setTitle("JSlider ¿¹Á¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c= getContentPane();
		c.setLayout(new BorderLayout());

		colorLabel.setFont(new Font("µ¸¿òÃ¼",Font.BOLD,30));

		
		c.add(p1 ,BorderLayout.NORTH);
		c.add(p2, BorderLayout.CENTER);

		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p2.add(colorLabel);

		for(int i=0;i<sl.length;i++) {
			sl[i]=new JSlider(JSlider.HORIZONTAL,0,255,0);

			sl[i].setPaintLabels(true);
			sl[i].setPaintTicks(true);
			sl[i].setPaintTrack(true);
			sl[i].setMajorTickSpacing(50);
			sl[i].setMinorTickSpacing(10);

			sl[i].addChangeListener(new MyChangeListener());
			p1.add(sl[i]);
		}


		int r = sl[0].getValue();
		int g = sl[1].getValue();
		int b = sl[2].getValue();

		colorLabel.setForeground(new Color(r,g,b));
		colorLabel.setOpaque(true);
		colorLabel.setBackground(new Color(255-r,255-g,255-b));
		p2.setBackground(new Color(255-r,255-g,255-b));

		p2.add(colorLabel);
		c.add(p2);
		setSize(300,300);
		setVisible(true);
	}

	class MyChangeListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int r = sl[0].getValue();
			int g = sl[1].getValue();
			int b = sl[2].getValue();

			colorLabel.setForeground(new Color(r,g,b));
			colorLabel.setBackground(new Color(255-r,255-g,255-b));
			p2.setBackground(new Color(255-r,255-g,255-b));
		}
	}

	public static void main(String[] args) {
		new Slider();

	}

}
