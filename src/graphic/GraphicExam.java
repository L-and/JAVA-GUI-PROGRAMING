package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import javax.swing.*;


public class GraphicExam extends JFrame implements ActionListener{
	JPanel pnl_text;
	BarGraphs bar;
	JTextField[] tf;
	JButton btn;
	JCheckBox cb;
	int itemCnt;
	public GraphicExam(){
		int[] y={28,53,54,94,67,92,56,116,88,120,68,46};
//		int[] y={28,53,66};
		itemCnt=y.length;
		setSize(itemCnt*70+20,350);
		setTitle("Graphic Test - Bar Graphics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnl_text=new JPanel();
		tf=new JTextField[itemCnt];
		for(int i=0;i<itemCnt;i++){
			tf[i]=new JTextField(Integer.toString(y[i]),4);
			tf[i].setHorizontalAlignment(JTextField.RIGHT);
		}
		btn=new JButton("Draw");
		cb=new JCheckBox("Show");

		setComponents();
		add(pnl_text,BorderLayout.NORTH);
		bar=new BarGraphs(y,Color.LIGHT_GRAY,cb);
		add(bar);
		setVisible(true);
	}

	void setComponents(){
		for(int i=0;i<itemCnt;i++){
			pnl_text.add(tf[i]);
		}
		pnl_text.add(btn);
		pnl_text.add(cb);
		cb.setSelected(true);
		btn.addActionListener(this);
		cb.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		int[] y=new int[itemCnt];
		for(int i=0;i<tf.length;i++){
			y[i]=Integer.parseInt(tf[i].getText());
		}
		bar.y=y;
		bar.color=Color.BLUE;
		repaint();
	}
	public static void main(String[] args){
		new GraphicExam();
	}
}

