package ch11;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class Swing extends JFrame{

	public Swing() {
		setTitle("������ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c= getContentPane();
		
		c.setLayout(new FlowLayout());
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		p3.setLayout(new FlowLayout());
		p4.setLayout(new FlowLayout());
		
		p1.add(new JLabel("�̸� "));
		JTextField tf = new JTextField(10);
		tf.setBackground(Color.yellow);
		p1.add(tf);
		//p1.add(new JTextField(10));
		p1.add(new JLabel("��ȭ "));
		p1.add(new JTextField(15));
		
		ButtonGroup g = new ButtonGroup();
		JRadioButton man = new JRadioButton("����");
		JRadioButton woman = new JRadioButton("����");
		g.add(woman);
		g.add(man);
		
		p1.add(man);
		p1.add(woman);
		
		c.add(p1);
		
		p2.add(new JLabel("���� �ܱ��� ����"));
		JCheckBox english = new JCheckBox("����");
		JCheckBox chinese = new JCheckBox("�߱���");
		JCheckBox japanese = new JCheckBox("�Ϻ���");
		JCheckBox spain = new JCheckBox("�����ξ�");
		JCheckBox rushia = new JCheckBox("���þƾ�");
		
		p2.add(english);
		p2.add(chinese);
		p2.add(japanese);
		p2.add(spain);
		p2.add(rushia);
		
		c.add(p2);
		
		JTextArea ta = new JTextArea(7,40);
		c.add(new JScrollPane(ta));
		c.add(p3);
		
		p4.add(new JButton("�Է�"));
		p4.add(new JButton("����"));
		p4.add(new JButton("����"));
		p4.add(new JButton("���"));
		
		c.add(p4);
		
		setSize(500,300);
		setVisible(true);
		
		
		
		
		
		
		
		
	}

	public static void main(String[] args) {
		new Swing();

	}

}
