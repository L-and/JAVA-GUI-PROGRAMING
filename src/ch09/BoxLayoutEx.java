package ch09;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxLayoutEx extends JFrame{
	public BoxLayoutEx()
	{
		createWindow();
		
	}
	
	public void createWindow()
	{
		setTitle("BoxLayoutEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		createMenu();
		
		setSize(500,500);
		setVisible(true);
	}
	
	public void createMenu()
	{
		JMenuBar mb = new JMenuBar();
		JMenu layoutMenu = new JMenu("Layout");
		
		JMenuItem VerticalBoxLayoutItem = new JMenuItem("VerticalBoxLayout");
		JMenuItem HorizontalBoxLayoutItem = new JMenuItem("HorizontalBoxLayout");
		
		MenuActionListener listener = new MenuActionListener();
		
		// Menu Item
		layoutMenu.add(VerticalBoxLayoutItem);
		layoutMenu.add(HorizontalBoxLayoutItem);
		
		VerticalBoxLayoutItem.addActionListener(listener);
		HorizontalBoxLayoutItem.addActionListener(listener);
		
		mb.add(layoutMenu);
		
		this.setJMenuBar(mb);
	}
	
	class MenuActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			switch(cmd)
			{
			case "VerticalBoxLayout":
				setContentPane(new VerticalBoxPanel());
				revalidate();
				repaint();
				break;
			case "HorizontalBoxLayout":
				setContentPane(new HorizontalBoxPanel());
				revalidate();
				repaint();
				break;
			}
		}
		
	}
	
	public class VerticalBoxPanel extends JPanel
	{
		Button[] btn = new Button[5];
		
		public VerticalBoxPanel()
		{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			for(int i=0; i<5; i++)
			{
				btn[i] = new Button("버튼"+(i+1));
				add(btn[i]);
			}
		}
	}
	
	public class HorizontalBoxPanel extends JPanel
	{
		Button[] btn = new Button[5];
		
		public HorizontalBoxPanel()
		{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			for(int i=0; i<5; i++)
			{
				btn[i] = new Button("버튼"+(i+1));
				add(btn[i]);
			}
		}
	}
	
	public static void main(String[] args)
	{
		new BoxLayoutEx();
	}
}
