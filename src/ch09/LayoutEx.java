package ch09;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutEx extends JFrame
{
	public LayoutEx()
	{
		createWindow();
	}
	
	public void createWindow()
	{
		setTitle("LayoutEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	
		createMenu();
		
		setSize(500,500);
		setVisible(true);
	}
	
	public void createMenu()
	{
		JMenuBar mb = new JMenuBar();
		JMenu layoutMenu = new JMenu("Layout");
		
		JMenuItem flowLayoutItem = new JMenuItem("FlowLayout");
		JMenuItem borderLayoutItem = new JMenuItem("BorderLayout");
		JMenuItem gridLayoutItem = new JMenuItem("GridLayout");
		
		MenuActionListener listener = new MenuActionListener();
		
		// Menu Item
		layoutMenu.add(flowLayoutItem);
		layoutMenu.add(borderLayoutItem);
		layoutMenu.add(gridLayoutItem);
		
		flowLayoutItem.addActionListener(listener);
		borderLayoutItem.addActionListener(listener);
		gridLayoutItem.addActionListener(listener);
		
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
			case "FlowLayout":
				setContentPane(new FlowPanel());
				revalidate();
				repaint();
				break;
			case "BorderLayout":
				setContentPane(new BorderPanel());
				revalidate();
				repaint();
				break;
			case "GridLayout":
				setContentPane(new GridPanel());
				revalidate();
				repaint();
				break;
			}
		}
		
	}
	
	public class FlowPanel extends JPanel
	{
		Button[] btn = new Button[5];
		
		public FlowPanel()
		{
			setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));
			for(int i=0; i<5; i++)
			{
				btn[i] = new Button("버튼"+(i+1));
				add(btn[i]);
			}
		}
	}
	
	public class BorderPanel extends JPanel
	{
		Button[] btn = new Button[5];
		String[] dir = {"North", "West", "South", "East", "Center"};
		public BorderPanel()
		{
			setLayout(new BorderLayout());
			for(int i=0; i<5; i++)
			{
				btn[i] = new Button("버튼"+(i+1));
				add(btn[i], dir[i]);
			}
		}
	}
	
	public class GridPanel extends JPanel
	{
		Button[] btn = new Button[5];
		
		public GridPanel()
		{
			setLayout(new GridLayout(3, 3, 5, 5));
			for(int i=0; i<5; i++)
			{
				btn[i] = new Button("버튼"+(i+1));
				add(btn[i]);
			}
		}
	}
	
	public static void main(String[] args)
	{
		new LayoutEx();
	}
}
