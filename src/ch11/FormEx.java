package ch11;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FormEx extends JFrame{
	
	JPanel panelHeader;
	JPanel panelMain;
	JPanel panelFooter;
	
	JLabel name = new JLabel("�̸�");
	JLabel phoneNumber = new JLabel("��ȭ");
	
	JTextField nameInput = new JTextField(8);
	JTextField PNInput = new JTextField(15);
	
	JTextArea mainText;
	
	JButton IOButton[] = new JButton[4];
	String[] IO = {"�Է�", "����", "����", "���"}; 
	
	JRadioButton sexSelect[] = new JRadioButton[2];
	String sex[] = {"����", "����"};
	
	JCheckBox language[] = new JCheckBox[5];
	CheckboxGroup checkGroup = new CheckboxGroup();
	String langCheckBox[] = {"����", "�߱���", "�Ϻ���", "�����ξ�", "���þƾ�"};
	
	
	ButtonGroup group = new ButtonGroup();
	
	public FormEx() {
		
		panelHeader = new JPanel();
		panelMain = new JPanel();
		panelFooter = new JPanel();
		
		
		Container c = getContentPane();
		
		createFrame();
		setLayout(new BorderLayout());
		add(panelHeader, BorderLayout.NORTH);
		add(panelMain, BorderLayout.CENTER);
		add(panelFooter, BorderLayout.SOUTH);
		
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		//���
		panelHeader.add(name);
		panelHeader.add(nameInput);
		panelHeader.add(phoneNumber);
		panelHeader.add(PNInput);
		
		for(int i=0; i<sexSelect.length; i++) {
			sexSelect[i] = new JRadioButton(sex[i]);
			group.add(sexSelect[i]);
			panelHeader.add(sexSelect[i]);
		}
		
		
		//����
		JPanel languagePan = new JPanel(); // ����г� �߰�
		for(int i=0; i<langCheckBox.length; i++) {
			language[i] = new JCheckBox(langCheckBox[i]);
			languagePan.add(language[i]);
		}
		mainText = new JTextArea(15,50);
		panelMain.add(languagePan);

		languagePan.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "���� �ܱ��� ����"));

		
		
		
		panelMain.add(mainText);
		
		//Ǫ��
		for(int i=0; i<IOButton.length; i++) {
			IOButton[i] = new JButton(IO[i]);
			panelFooter.add(IOButton[i]);
		}
		
	}
	
	void createFrame() {
		setTitle("������ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720, 480);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new FormEx();
	}

}