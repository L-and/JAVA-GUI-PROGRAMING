package ch11;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserManagement extends JFrame{

	int windowVerticalSize = 430;
	int windowHorizontalSize = 480;

	Container container = getContentPane();



	public UserManagement()
	{
		createWindow();
	}

	public void createWindow()
	{
		container.setLayout(new BorderLayout());

		setTitle("응시자 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		JPanel userInfoPanel = new UserInfoPanel();
		container.add(userInfoPanel);

		setSize(windowVerticalSize, windowHorizontalSize);
		setVisible(true);
	}

	public class UserInfoPanel extends JPanel
	{
		List<UserInformation> userInfoList = new ArrayList<UserInformation>(); // 유저들의 정보를 저장하기위한 리스트
		public class EventListener implements ActionListener
		{
			static String selectedSex; // 선택된 성별을 저장하기위한 변수



			@Override
			public void actionPerformed(ActionEvent event)
			{
				Object source = event.getSource();

				UserInformation userInfo = new UserInformation();

				if(source instanceof JRadioButton)
				{
					JRadioButton radioBtn = (JRadioButton)source;

					selectedSex = radioBtn.getActionCommand(); //선택된 설별을 저장
				}

				if(source instanceof JButton)
				{
					JButton btn = (JButton)source;
					String btnTxt = btn.getText();

					DefaultListModel<String> userInfoListModel = userInfoListPanel.getUserInfoListModelObject();
					JList<String> userInfoJlist = userInfoListPanel.getUserInfoListObject();

					if(btnTxt == "입력")
					{
						Boolean success = userInfo.createUserInfo(selectedSex); //입력값을 잘 가져왔는지 저장

						if(success)
						{
							userInfoListModel.addElement(userInfo.toString()); // 리스트모델에 유저정보추가
							userInfoList.add(userInfo); //유저정보리스트에 유저정보추가

							userPsInfoPanel.clearField();
							foreignLangBtnPanel.clearSelect();
						}
					}
					else if(btnTxt =="수정")
					{
						int selectedIndex = userInfoJlist.getSelectedIndex(); // 선택된 인덱스를 저장

						if(selectedIndex >= 0)
						{
							UserInformation selectedUserInfo = userInfoList.get(selectedIndex);
							selectedUserInfo.loadUserInfo();
							userInfoListModel.remove(selectedIndex);
							userInfoList.remove(selectedIndex);
						}
					}
					else if(btnTxt =="삭제")
					{
						int index = userInfoJlist.getSelectedIndex(); //선택된 멤버의 인덱스를 저장, 선택이안되었으면 -1을 리턴하는 함수
						if(index >= 0) // 선택이 되어있다면
							userInfoListModel.remove(index);
					}
					else if(btnTxt =="취소")
					{
						//						텍스트필드와 버튼들 초기화
						userPsInfoPanel.clearField();
						foreignLangBtnPanel.clearSelect();
					}
				}
			}

		}


		//		각 패널객체 생성
		UserPersonalInfoPanel userPsInfoPanel = new UserPersonalInfoPanel();
		SexRadioBtnPanel sexRadioBtnPanel = new SexRadioBtnPanel();
		ForeignLanguageBtnPanel foreignLangBtnPanel = new ForeignLanguageBtnPanel();
		UserInfoListPanel userInfoListPanel = new UserInfoListPanel();
		EventButtonPanel eventButtonPanel = new EventButtonPanel();

		public UserInfoPanel()
		{				
			setLayout(new BorderLayout());
			
			JPanel northPan = new JPanel();
			JPanel centerPan = new JPanel();
			JPanel southPan = new JPanel();
			
			//			유저정보패널에 하위패널들 추가
			northPan.add(userPsInfoPanel);
			northPan.add(sexRadioBtnPanel);
			
			centerPan.add(foreignLangBtnPanel);
			centerPan.add(userInfoListPanel);
			
			southPan.add(eventButtonPanel);	
			
			centerPan.setLayout(new BoxLayout(centerPan, BoxLayout.Y_AXIS));
			
			add(northPan, BorderLayout.NORTH);
			add(centerPan, BorderLayout.CENTER);
			add(southPan, BorderLayout.SOUTH);

		}

		//		유저개인정보패널 클래스
		public class UserPersonalInfoPanel extends JPanel
		{
			JTextField nameTextField = new JTextField(5);
			JTextField phoneTextField = new JTextField(13);
			public UserPersonalInfoPanel()
			{
				JLabel nameLabel = new JLabel("이름");
				JLabel phoneLabel = new JLabel("전화");

				//				배경색상 변경
				nameTextField.setBackground(Color.YELLOW);


				//				유저개인정보패널에 라벨과 텍스트필드 추가
				add(nameLabel);
				add(nameTextField);
				add(phoneLabel);
				add(phoneTextField);

			}

			public String getUserName()
			{
				return nameTextField.getText();
			}

			public String getUserPhone()
			{
				return phoneTextField.getText();
			}

			public void setUserName(String name)
			{
				nameTextField.setText(name);
			}

			public void setUserPhone(String phone)
			{
				phoneTextField.setText(phone);
			}
			
			public void clearField()
			{
				nameTextField.setText("");
				phoneTextField.setText("");
			}
		}

		//		성별라디오버튼패널 클래스
		public class SexRadioBtnPanel extends JPanel
		{
			JRadioButton manRadioBtn = new JRadioButton("남자");
			JRadioButton womanRadioBtn = new JRadioButton("여자");

			ButtonGroup sexBtnGroup = new ButtonGroup();
			public SexRadioBtnPanel()
			{
				//				배경색상 변경
				manRadioBtn.setBackground(Color.LIGHT_GRAY);
				womanRadioBtn.setBackground(Color.LIGHT_GRAY);
				setBackground(Color.LIGHT_GRAY);

				//				버튼그룹에 라디오버튼 추가
				sexBtnGroup.add(manRadioBtn);
				sexBtnGroup.add(womanRadioBtn);

				//				각 버튼에 리스너 추가
				manRadioBtn.addActionListener(new EventListener());
				womanRadioBtn.addActionListener(new EventListener());

				//				성별라디오버튼패널에 라디오버튼 추가
				add(manRadioBtn);
				add(womanRadioBtn);
			}

			public void setSelectBtn(String sex)
			{
				if(sex == "남자")
				{
					manRadioBtn.setSelected(true);
				}
				else if(sex == "여자")
				{ 
					womanRadioBtn.setSelected(true);
				}
			}
			
		}

		//		외국어버튼패널 클래스
		public class ForeignLanguageBtnPanel extends JPanel
		{
			List<JCheckBox> languageBtnList = new ArrayList<>();			
			String[] foreignLanguage = {"영어", "중국어", "일본어", "스페인어", "러시아어"};
			JCheckBox[] foreignLanguageBtn = new JCheckBox[foreignLanguage.length];


			public ForeignLanguageBtnPanel()
			{
				//				체크박스 객체생성
				for(int i=0; i<5; i++)
				{
					foreignLanguageBtn[i] = new JCheckBox(foreignLanguage[i]);
					add(foreignLanguageBtn[i]); // 패널에 버튼추가

					languageBtnList.add(foreignLanguageBtn[i]); // 버튼그룹에 버튼추가
				}


				//				패널사이즈 설정
				setPreferredSize(new Dimension(windowHorizontalSize - windowHorizontalSize / 5, 70));

				//				테두리 설정
				setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "가능 외국어 선택"));
			}


			public List<String> getSelectedLanguageList()
			{
				List<String> selectedLanguageList = new ArrayList<String>();

				//				체크가 되어있다면 리스트에 외국어이름을 추가
				for(int i=0; i<foreignLanguage.length; i++)
				{
					JCheckBox button = languageBtnList.get(i);

					if (button.isSelected())
					{
						selectedLanguageList.add(button.getText());
					}
				}

				return selectedLanguageList;
			}

			public void setSelectLanguage(List<String> languageList)
			{
//				외국어리스트에 있는것은 선택 없는것은 선택해제
				for(int i=0; i<foreignLanguage.length; i++)
				{
					JCheckBox button = languageBtnList.get(i);

					if(languageList.contains(foreignLanguage[i]))
					{
						button.setSelected(true);
					}
					else
					{
						button.setSelected(false);
					}
				}
			}

			public void clearSelect()
			{
				for(int i=0; i<foreignLanguage.length; i++)
				{
					languageBtnList.get(i).setSelected(false);
				}
			}
		}

		//		유저정보리스트패널 클래스
		public class UserInfoListPanel extends JPanel
		{
			DefaultListModel<String> userInfoModel = new DefaultListModel<String>();
			JList<String> userInfoList = new JList<String>(userInfoModel);


			public UserInfoListPanel()
			{
				//				레이아웃 설정
				setLayout(new BorderLayout());

				//				사이즈 설정
				setPreferredSize(new Dimension(windowHorizontalSize - windowHorizontalSize / 4, windowHorizontalSize / 2));

				//				테두리색상 설정
				setBorder(new LineBorder(Color.LIGHT_GRAY));

				//				하나만 선택되도록 설정
				userInfoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				//				패널에 추가
				add(userInfoList);

			}

			public DefaultListModel<String> getUserInfoListModelObject()
			{
				return userInfoModel;
			}

			public JList<String> getUserInfoListObject()
			{
				return userInfoList;
			}
		}

		//		이벤트버튼패널 클래스
		public class EventButtonPanel extends JPanel
		{
			public EventButtonPanel()
			{
				JButton[] eventBtn = new JButton[4];
				String[] eventName = {"입력", "수정", "삭제", "취소"};


				for(int i=0; i<4; i++)
				{

					eventBtn[i] = new JButton(eventName[i]); // 버튼객체 생성
					add(eventBtn[i]); // 이벤트버튼패널에 버튼 추가

					eventBtn[i].addActionListener(new EventListener()); // 리스너 추가

				}
			}
		}

		//		유저정보 클래스
		public class UserInformation
		{
			String name;
			String phone;
			String sex;
			//						가능외국어 리스트를 저장
			List<String> langList;

			//			필드입력값으로 유저정보 생성
			public Boolean createUserInfo(String sex) 
			{
				//입력값들을 저장
				name = userPsInfoPanel.getUserName();
				phone = userPsInfoPanel.getUserPhone();

				langList = foreignLangBtnPanel.getSelectedLanguageList();

				this.sex = sex;


				//입력값에 공백이없으면 true 리턴
				if(!name.equals("") && !phone.equals("") && !sex.equals(""))
					return true;
				else // 공백이있다면 false리턴
					return false;
			}

			
			//			필드로 유저정보 불러오기
			public void loadUserInfo()
			{
				userPsInfoPanel.setUserName(name);
				userPsInfoPanel.setUserPhone(phone);

				sexRadioBtnPanel.setSelectBtn(sex);

				foreignLangBtnPanel.setSelectLanguage(langList);

			}

			@Override
			public String toString()
			{
				return name + " " + phone + " " + sex + " " + langList;
			}
		}
	}

	public static void main(String[] args) {
		new UserManagement();
	}
	
}
