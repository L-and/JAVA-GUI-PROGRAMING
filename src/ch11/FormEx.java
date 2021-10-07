package ch11;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FormEx extends JFrame{
	
	JPanel panelHeader;
	JPanel panelMain;
	JPanel panelFooter;
	
	JLabel name = new JLabel("이름");
	JLabel phoneNumber = new JLabel("전화");
	
	JTextField nameInput = new JTextField(8);
	JTextField PNInput = new JTextField(15);
	
	JTextArea mainText;
	
	JButton IOButton[] = new JButton[4];
	String[] IO = {"입력", "수정", "삭제", "취소"}; 
	
	JRadioButton sexSelect[] = new JRadioButton[2];
	String sex[] = {"남자", "여자"};
	
	JCheckBox language[] = new JCheckBox[5];
	CheckboxGroup checkGroup = new CheckboxGroup();
	String langCheckBox[] = {"영어", "중국어", "일본어", "스페인어", "러시아어"};
	
	
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
		//헤더
		panelHeader.add(name);
		panelHeader.add(nameInput);
		panelHeader.add(phoneNumber);
		panelHeader.add(PNInput);
		
		for(int i=0; i<sexSelect.length; i++) {
			sexSelect[i] = new JRadioButton(sex[i]);
			group.add(sexSelect[i]);
			panelHeader.add(sexSelect[i]);
		}
		
		
		//메인
		JPanel languagePan = new JPanel(); // 언어패널 추가
		for(int i=0; i<langCheckBox.length; i++) {
			language[i] = new JCheckBox(langCheckBox[i]);
			languagePan.add(language[i]);
		}
		mainText = new JTextArea(15,50);
		panelMain.add(languagePan);

		languagePan.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "가능 외국어 선택"));

		
		
		
		panelMain.add(mainText);
		
		//푸터
		for(int i=0; i<IOButton.length; i++) {
			IOButton[i] = new JButton(IO[i]);
			panelFooter.add(IOButton[i]);
		}
		
	}
	
	void createFrame() {
		setTitle("응시자 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720, 480);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new FormEx();
	}

}