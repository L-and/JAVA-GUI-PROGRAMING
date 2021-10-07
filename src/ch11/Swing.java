package ch11;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class Swing extends JFrame{

	public Swing() {
		setTitle("응시자 관리");
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
		
		p1.add(new JLabel("이름 "));
		JTextField tf = new JTextField(10);
		tf.setBackground(Color.yellow);
		p1.add(tf);
		//p1.add(new JTextField(10));
		p1.add(new JLabel("전화 "));
		p1.add(new JTextField(15));
		
		ButtonGroup g = new ButtonGroup();
		JRadioButton man = new JRadioButton("남자");
		JRadioButton woman = new JRadioButton("여자");
		g.add(woman);
		g.add(man);
		
		p1.add(man);
		p1.add(woman);
		
		c.add(p1);
		
		p2.add(new JLabel("가능 외국어 선택"));
		JCheckBox english = new JCheckBox("영어");
		JCheckBox chinese = new JCheckBox("중국어");
		JCheckBox japanese = new JCheckBox("일본어");
		JCheckBox spain = new JCheckBox("스페인어");
		JCheckBox rushia = new JCheckBox("러시아어");
		
		p2.add(english);
		p2.add(chinese);
		p2.add(japanese);
		p2.add(spain);
		p2.add(rushia);
		
		c.add(p2);
		
		JTextArea ta = new JTextArea(7,40);
		c.add(new JScrollPane(ta));
		c.add(p3);
		
		p4.add(new JButton("입력"));
		p4.add(new JButton("수정"));
		p4.add(new JButton("삭제"));
		p4.add(new JButton("취소"));
		
		c.add(p4);
		
		setSize(500,300);
		setVisible(true);
		
		
		
		
		
		
		
		
	}

	public static void main(String[] args) {
		new Swing();

	}

}
