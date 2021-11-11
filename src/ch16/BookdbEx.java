package ch16;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import ch16.BookManagement.BookInfoInputPanel;

class BookManagement extends JFrame
{
	int windowVerticalSize = 400;
	int windowHorizontalSize = 700;

	Container container = getContentPane();

	public BookInfoInputPanel bookInfoInputPane = new BookInfoInputPanel();
	public BookInfoOutputPanel bookInfoOutputPane = new BookInfoOutputPanel();
	public BookInfoCRUD bookInfoCRUD = new BookInfoCRUD();

	JPanel northPane = new JPanel();
	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();

	public JPanel bookManagementPane;

	public BookManagement()
	{
		createWindow();
	}

	public void createWindow()
	{
		container.setLayout(new BorderLayout());

		setTitle("서적 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setSize(windowHorizontalSize, windowVerticalSize);
		setVisible(true);
		setLayout(new BorderLayout());

		northPane.add(bookInfoInputPane);
		centerPane.add(bookInfoOutputPane);
		southPane.add(bookInfoCRUD);

		add(northPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(southPane, BorderLayout.SOUTH);
	}


	class BookInfoInputPanel extends JPanel
	{
		JLabel bookInfoLbls[] = new JLabel[4];
		JTextField bookInfoTxtFlds[] = new JTextField[4];
		String[] bookInfos = {"ID", "Title", "Publisher", "Price"};

		public BookInfoInputPanel()
		{
			for(int i=0; i<4; i++)
			{
				bookInfoLbls[i] = new JLabel(bookInfos[i]);
				bookInfoTxtFlds[i] = new JTextField(10);
				add(bookInfoLbls[i]);
				add(bookInfoTxtFlds[i]);
			}

		}

		public String[] getBookInfoInputs()
		{
			String[] bookInfoInputs = new String[bookInfos.length];

			for(int i=0; i<bookInfos.length;i++)
			{
				bookInfoInputs[i] = bookInfoTxtFlds[i].getText();
			}	
			
			return bookInfoInputs;
		}

		public String[] getBookInfos()
		{
			return bookInfos;
		}

		public boolean isIdOverlap(ResultSet rs, String id)
		{
			String currentId;
			try
			{
				while(rs.next())
				{
					currentId = rs.getString(1);

					if(id.equals(currentId))
					{
						return true;
					}
				}

				return false;
			}
			catch(SQLException e)
			{
				e.printStackTrace();

				return true;
			}
		}

		public Boolean isEmpty() 
		{
			for(int i=0; i<bookInfoTxtFlds.length; i++)
			{
				if(bookInfoTxtFlds[i].getText().isEmpty())
					return bookInfoTxtFlds[i].getText().isEmpty();
			}

			return false;
		}
		
		public String getEmptyLabelText()
		{
			for(int i=0; i<bookInfoTxtFlds.length;i++)
			{
				if(bookInfoTxtFlds[i].getText().isEmpty())
				{
					return bookInfoLbls[i].getText();
				}
			}

			return null;
		}
	}

	class BookInfoOutputPanel extends JPanel
	{
		JTextArea bookInfoTxtArea=new JTextArea();
		JScrollPane bookInfoScroll = new JScrollPane(bookInfoTxtArea);

		public BookInfoOutputPanel()
		{
			setLayout(new BorderLayout());

			setPreferredSize(new Dimension(windowHorizontalSize - (windowHorizontalSize * 5 / 100),windowVerticalSize - (windowVerticalSize * 40 / 100)));

			add(bookInfoScroll);
		}
	}

	class BookInfoCRUD extends JPanel
	{
		public JButton CRUDBtns[] = new JButton[4];
		String CRUD[] = {"추가", "조회", "수정", "삭제"};

		public BookInfoCRUD()
		{
			for(int i=0; i<4; i++)
			{
				CRUDBtns[i] = new JButton(CRUD[i]);

				add(CRUDBtns[i]);
			}
		}
	}
}




public class BookdbEx
{
	BookManagement bookManagementFrame = new BookManagement();

	JButton[] CURDs = bookManagementFrame.bookInfoCRUD.CRUDBtns;
	JTextArea infoTxtArea = bookManagementFrame.bookInfoOutputPane.bookInfoTxtArea;

	ActionListener handler = new ActionHandler();

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public BookdbEx()
	{	
		for(int i=0;i<CURDs.length;i++)
		{
			CURDs[i].addActionListener(handler);
		}
	}

	class ActionHandler implements ActionListener
	{	
		public void load()
		{
			String sql = "SELECT * FROM book;";
			String row = "";
			infoTxtArea.setText("");

			try
			{
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					row = rs.getString("id") + "\t" ;
					row += rs.getString("title")+ "\t";
					row += rs.getString("publisher")+ "\t";
					row += rs.getString("price")+ "\n";
					infoTxtArea.append(row);
					row = "";
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			makeConnection();

			Object source = event.getSource();

			if(source instanceof JButton)
			{
				JButton btn = (JButton)source;

				BookInfoInputPanel bookInfoInputPane = bookManagementFrame.bookInfoInputPane;

				String command = btn.getText(); // 눌러진 버튼의 text
				String[] bookInfoInputs = new String[4]; // TextField에 압력된 책의 정보들
				String[] bookInfos = bookInfoInputPane.getBookInfos(); // TextLabel의 책의 정보들

				// 입력된 책 정보를 저장
				bookInfoInputs = bookInfoInputPane.getBookInfoInputs();

				if(command == "추가")
				{
					String sql;
					
					// 공백 체크
					if(bookInfoInputPane.isEmpty())
					{
						String emptyLblTxt = bookInfoInputPane.getEmptyLabelText();
						System.out.println("["+emptyLblTxt+"]공백입니다.");
						JOptionPane.showMessageDialog(null, "["+emptyLblTxt+"]공백입니다."); // 경고창 표시
						return;
					}


					sql = "SELECT * FROM book";
					try
					{
						rs = stmt.executeQuery(sql);

						// ID 중복 체크
						Boolean idOverlap = bookInfoInputPane.isIdOverlap(rs, bookInfoInputs[0]);

						if(idOverlap)
						{
							System.out.println("[ID]중복입니다.");
							JOptionPane.showMessageDialog(null, "[ID]중복입니다."); // 경고창 표시
						}
						else
						{
							sql = "INSERT INTO book(id, title, publisher, price) values('"+bookInfoInputs[0]+"','"+bookInfoInputs[1]+"','"+bookInfoInputs[2]+"','"+bookInfoInputs[3]+"')";

							System.out.println(sql);

							//							 책 정보를 DB에 입력
							try
							{
								stmt.executeUpdate(sql);
							}
							catch(SQLException e)
							{
								e.printStackTrace();
							}
							
							load();
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
				else if(command == "조회")
				{
					load();
				}
				else if(command == "수정")
				{
					String sql = "SELECT * FROM book;";
					
					// 공백 체크
					if(bookInfoInputPane.isEmpty())
					{
						String emptyLblTxt = bookInfoInputPane.getEmptyLabelText();
						System.out.println("["+emptyLblTxt+"]공백입니다.");
						JOptionPane.showMessageDialog(null, "["+emptyLblTxt+"]공백입니다."); // 경고창 표시
						return;
					}					
					
					try
					{
						rs = stmt.executeQuery(sql);
						
						sql = "SELECT * FROM book where id='"+bookInfoInputs[0]+"';";
						
						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
							sql = "UPDATE book SET title = '"+bookInfoInputs[1]+"', publisher = '"+bookInfoInputs[2]+"', price = '"+bookInfoInputs[3]+"' where id = '"+bookInfoInputs[0]+"';";
							System.out.println(sql);
							stmt.executeUpdate(sql);
						}
						else
						{
							System.out.println("존재하지않는 ID입니다.");
							JOptionPane.showMessageDialog(null, "존재하지않는 ID입니다"); // 경고창 표시
							return;
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					
					load();
				}
				else if(command == "삭제")
				{
					String sql="DELETE FROM book where id = '"+bookInfoInputs[0]+"'";
					
					// 공백 체크
					if(bookInfoInputs[0].equals(""))
					{
						System.out.println("[ID]공백입니다.");
						JOptionPane.showMessageDialog(null, "[ID]공백입니다."); // 경고창 표시
						return;
					}
					
					try
					{
						System.out.println(sql);
						if(stmt.executeUpdate(sql) == 0)
						{
							System.out.println("존재하지 않는 ID입니다.");
							JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다."); // 경고창 표시
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					load();
				}

			}

			disConnection();
		}
	}

	public Connection makeConnection()
	{
		String url = "jdbc:mysql://localhost:3306/book_db?useSSL=false&serverTimezone=UTC";
		String id="root";
		String password = "Land9923625m";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이브 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			stmt = con.createStatement();

			System.out.println("데이터베이스 연결 성공");

		}
		catch(ClassNotFoundException e)
		{
			System.out.println("드라이버를 찾을 수 없습니다");
		}
		catch(SQLException e)
		{
			System.out.println("데이터베이스 연결 실패");
		}

		return con;
	}

	public void disConnection()
	{
		try
		{
			con.close();
			stmt.close(); 
			rs.close();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		new BookdbEx();
	}
}