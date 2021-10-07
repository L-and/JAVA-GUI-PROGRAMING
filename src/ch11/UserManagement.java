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

		setTitle("������ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		JPanel userInfoPanel = new UserInfoPanel();
		container.add(userInfoPanel);

		setSize(windowVerticalSize, windowHorizontalSize);
		setVisible(true);
	}

	public class UserInfoPanel extends JPanel
	{
		List<UserInformation> userInfoList = new ArrayList<UserInformation>(); // �������� ������ �����ϱ����� ����Ʈ
		public class EventListener implements ActionListener
		{
			static String selectedSex; // ���õ� ������ �����ϱ����� ����



			@Override
			public void actionPerformed(ActionEvent event)
			{
				Object source = event.getSource();

				UserInformation userInfo = new UserInformation();

				if(source instanceof JRadioButton)
				{
					JRadioButton radioBtn = (JRadioButton)source;

					selectedSex = radioBtn.getActionCommand(); //���õ� ������ ����
				}

				if(source instanceof JButton)
				{
					JButton btn = (JButton)source;
					String btnTxt = btn.getText();

					DefaultListModel<String> userInfoListModel = userInfoListPanel.getUserInfoListModelObject();
					JList<String> userInfoJlist = userInfoListPanel.getUserInfoListObject();

					if(btnTxt == "�Է�")
					{
						Boolean success = userInfo.createUserInfo(selectedSex); //�Է°��� �� �����Դ��� ����

						if(success)
						{
							userInfoListModel.addElement(userInfo.toString()); // ����Ʈ�𵨿� ���������߰�
							userInfoList.add(userInfo); //������������Ʈ�� ���������߰�

							userPsInfoPanel.clearField();
							foreignLangBtnPanel.clearSelect();
						}
					}
					else if(btnTxt =="����")
					{
						int selectedIndex = userInfoJlist.getSelectedIndex(); // ���õ� �ε����� ����

						if(selectedIndex >= 0)
						{
							UserInformation selectedUserInfo = userInfoList.get(selectedIndex);
							selectedUserInfo.loadUserInfo();
							userInfoListModel.remove(selectedIndex);
							userInfoList.remove(selectedIndex);
						}
					}
					else if(btnTxt =="����")
					{
						int index = userInfoJlist.getSelectedIndex(); //���õ� ����� �ε����� ����, �����̾ȵǾ����� -1�� �����ϴ� �Լ�
						if(index >= 0) // ������ �Ǿ��ִٸ�
							userInfoListModel.remove(index);
					}
					else if(btnTxt =="���")
					{
						//						�ؽ�Ʈ�ʵ�� ��ư�� �ʱ�ȭ
						userPsInfoPanel.clearField();
						foreignLangBtnPanel.clearSelect();
					}
				}
			}

		}


		//		�� �гΰ�ü ����
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
			
			//			���������гο� �����гε� �߰�
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

		//		�������������г� Ŭ����
		public class UserPersonalInfoPanel extends JPanel
		{
			JTextField nameTextField = new JTextField(5);
			JTextField phoneTextField = new JTextField(13);
			public UserPersonalInfoPanel()
			{
				JLabel nameLabel = new JLabel("�̸�");
				JLabel phoneLabel = new JLabel("��ȭ");

				//				������ ����
				nameTextField.setBackground(Color.YELLOW);


				//				�������������гο� �󺧰� �ؽ�Ʈ�ʵ� �߰�
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

		//		����������ư�г� Ŭ����
		public class SexRadioBtnPanel extends JPanel
		{
			JRadioButton manRadioBtn = new JRadioButton("����");
			JRadioButton womanRadioBtn = new JRadioButton("����");

			ButtonGroup sexBtnGroup = new ButtonGroup();
			public SexRadioBtnPanel()
			{
				//				������ ����
				manRadioBtn.setBackground(Color.LIGHT_GRAY);
				womanRadioBtn.setBackground(Color.LIGHT_GRAY);
				setBackground(Color.LIGHT_GRAY);

				//				��ư�׷쿡 ������ư �߰�
				sexBtnGroup.add(manRadioBtn);
				sexBtnGroup.add(womanRadioBtn);

				//				�� ��ư�� ������ �߰�
				manRadioBtn.addActionListener(new EventListener());
				womanRadioBtn.addActionListener(new EventListener());

				//				����������ư�гο� ������ư �߰�
				add(manRadioBtn);
				add(womanRadioBtn);
			}

			public void setSelectBtn(String sex)
			{
				if(sex == "����")
				{
					manRadioBtn.setSelected(true);
				}
				else if(sex == "����")
				{ 
					womanRadioBtn.setSelected(true);
				}
			}
			
		}

		//		�ܱ����ư�г� Ŭ����
		public class ForeignLanguageBtnPanel extends JPanel
		{
			List<JCheckBox> languageBtnList = new ArrayList<>();			
			String[] foreignLanguage = {"����", "�߱���", "�Ϻ���", "�����ξ�", "���þƾ�"};
			JCheckBox[] foreignLanguageBtn = new JCheckBox[foreignLanguage.length];


			public ForeignLanguageBtnPanel()
			{
				//				üũ�ڽ� ��ü����
				for(int i=0; i<5; i++)
				{
					foreignLanguageBtn[i] = new JCheckBox(foreignLanguage[i]);
					add(foreignLanguageBtn[i]); // �гο� ��ư�߰�

					languageBtnList.add(foreignLanguageBtn[i]); // ��ư�׷쿡 ��ư�߰�
				}


				//				�гλ����� ����
				setPreferredSize(new Dimension(windowHorizontalSize - windowHorizontalSize / 5, 70));

				//				�׵θ� ����
				setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "���� �ܱ��� ����"));
			}


			public List<String> getSelectedLanguageList()
			{
				List<String> selectedLanguageList = new ArrayList<String>();

				//				üũ�� �Ǿ��ִٸ� ����Ʈ�� �ܱ����̸��� �߰�
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
//				�ܱ����Ʈ�� �ִ°��� ���� ���°��� ��������
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

		//		������������Ʈ�г� Ŭ����
		public class UserInfoListPanel extends JPanel
		{
			DefaultListModel<String> userInfoModel = new DefaultListModel<String>();
			JList<String> userInfoList = new JList<String>(userInfoModel);


			public UserInfoListPanel()
			{
				//				���̾ƿ� ����
				setLayout(new BorderLayout());

				//				������ ����
				setPreferredSize(new Dimension(windowHorizontalSize - windowHorizontalSize / 4, windowHorizontalSize / 2));

				//				�׵θ����� ����
				setBorder(new LineBorder(Color.LIGHT_GRAY));

				//				�ϳ��� ���õǵ��� ����
				userInfoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				//				�гο� �߰�
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

		//		�̺�Ʈ��ư�г� Ŭ����
		public class EventButtonPanel extends JPanel
		{
			public EventButtonPanel()
			{
				JButton[] eventBtn = new JButton[4];
				String[] eventName = {"�Է�", "����", "����", "���"};


				for(int i=0; i<4; i++)
				{

					eventBtn[i] = new JButton(eventName[i]); // ��ư��ü ����
					add(eventBtn[i]); // �̺�Ʈ��ư�гο� ��ư �߰�

					eventBtn[i].addActionListener(new EventListener()); // ������ �߰�

				}
			}
		}

		//		�������� Ŭ����
		public class UserInformation
		{
			String name;
			String phone;
			String sex;
			//						���ɿܱ��� ����Ʈ�� ����
			List<String> langList;

			//			�ʵ��Է°����� �������� ����
			public Boolean createUserInfo(String sex) 
			{
				//�Է°����� ����
				name = userPsInfoPanel.getUserName();
				phone = userPsInfoPanel.getUserPhone();

				langList = foreignLangBtnPanel.getSelectedLanguageList();

				this.sex = sex;


				//�Է°��� �����̾����� true ����
				if(!name.equals("") && !phone.equals("") && !sex.equals(""))
					return true;
				else // �������ִٸ� false����
					return false;
			}

			
			//			�ʵ�� �������� �ҷ�����
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
