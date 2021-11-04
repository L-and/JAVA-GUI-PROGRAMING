package ch16;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch11.UserManagement.UserInfoPanel;



class BookManagement extends JFrame 
{
	int windowVerticalSize = 700;
	int windowHorizontalSize = 400;

	Container container = getContentPane();

	public JPanel bookManagementPane;
	
	BookManagement()
	{
		createWindow();
	}

	public void createWindow()
	{
		container.setLayout(new BorderLayout());

		setTitle("서적 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		bookManagementPane = new BookManagementPanel();
		container.add(bookManagementPane);

		setSize(windowVerticalSize, windowHorizontalSize);
		setVisible(true);
	}

	public class BookManagementPanel extends JPanel
	{
		public BookInfoInputPanel bookInfoInputPane = new BookInfoInputPanel();
		public BookInfoOutputPanel bookInfoOutputPane = new BookInfoOutputPanel();
		public BookInfoCRUD bookInfoCRUD = new BookInfoCRUD();

		public BookManagementPanel()
		{
			setLayout(new BorderLayout());

			JPanel northPane = new JPanel();
			JPanel centerPane = new JPanel();
			JPanel southPane = new JPanel();

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

			String bookInfos[] = {"ID", "Title", "Publisher", "Price"};

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
		}

		class BookInfoOutputPanel extends JPanel
		{
			JTextField bookInfo = new JTextField();

			public BookInfoOutputPanel()
			{
				setLayout(new BorderLayout());

				setPreferredSize(new Dimension(windowVerticalSize,windowHorizontalSize - (windowHorizontalSize * 40 / 100)));

				add(bookInfo);
			}
		}

		class BookInfoCRUD extends JPanel
		{
			JButton CRUDBtns[] = new JButton[4];
			String CRUD[] = {"추가", "조회", "수정", "삭제"};

			public BookInfoCRUD()
			{
				for(int i=0; i<4; i++)
				{
					CRUDBtns[i] = new JButton(CRUD[i]);

					CRUDBtns[i].addActionListener(new BookdbEx());
					
					add(CRUDBtns[i]);
				}
			}
		}
	}



}

public class BookdbEx
{
	BookManagement bookmanagementFrame = new BookManagement();
	Connection con;
	Statement stmt;
	ResultSet rs;

	public BookdbEx()
	{

		makeConnection();

//		try
//		{
//			
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}


		disConnection();
	}

	
	public Connection makeConnection()
	{
		String url = "jdbc:mysql://localhost:3306/book_db?useSSL=false&serverTimezone=UTC";
		String id="root";
		String password = "Land9923625a";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이브 적재 성공");
			con = DriverManager.getConnection(url, id, password);
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
			//			rs.close();
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

public class ActionHandler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		
		if(source instanceof JButton)
		{
			JButton btn = (JButton)source;
			
			if(btn.getText() == "조회")
			{
				String sql = "SELECT * FROM book_db.book;";
				String row = "";
				try
				{
					rs = stmt.executeQuery(sql);
					while(rs.next())
					{
						row = rs.getString("id") + "\t" ;
						row += rs.getString("title")+ "\t";
						row += rs.getString("publisher")+ "\t";
						row += rs.getString("price")+ "\n";
						
						row = "";
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
}
