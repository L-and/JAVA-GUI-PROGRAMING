package Friend;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BookdbView extends JFrame {
	JPanel[] pnl = new JPanel[2];
	JLabel[] lbl = new JLabel[4];
	JTextField[] tf = new JTextField[5];
	JButton[] button = new JButton[4];
	JTextArea ta = new JTextArea();
	BookdbView() {
		String[] lbl_tf = {"ID", "Title", "Publisher", "Price"};
		String[] lbl_button = {"추가", "조회", "수정", "삭제"};
		Container c = getContentPane();
		pnl[0] = new JPanel();
		pnl[1] = new JPanel();
		
		for (int i = 0; i < 4; i++) {
			tf[i] = new JTextField(10);
			lbl[i] = new JLabel(lbl_tf[i]);
			button[i] = new JButton(lbl_button[i]);
		}
		

		for(int i = 0; i < 4; i++) {
			pnl[0].add(lbl[i]);
			pnl[0].add(tf[i]);
			pnl[1].add(button[i]);
		}
		
		c.add(pnl[0], BorderLayout.NORTH);
		c.add(new JScrollPane(ta), BorderLayout.CENTER);
		c.add(pnl[1], BorderLayout.SOUTH);

		
		setTitle("서적 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		
		setVisible(true);
	}
	
}

public class BookdbEx {
	BookdbView v = new BookdbView();
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	public BookdbEx() {
		makeConnection();
		try {
			v.button[1].addActionListener(new ActionHandler());
//			String sql = "insert into book (id, title, publisher, price) values ('CE1005', '파이썬프로그래밍', '위정출판사', 31000)";
//			System.out.println(sql);
//			stmt = con.createStatement();
//			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == v.button[1]) {
				String sql = "SELECT * FROM book_db.book;";
				String row = "";
				try {
					stmt.executeQuery(sql);
					while(rs.next()) {
						row = rs.getString("id") + "\t" ;
						row += rs.getString("title")+ "\t";
						row += rs.getString("publisher")+ "\t";
						row += rs.getString("price")+ "\n";
						v.ta.append(row);
						row = "";
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
				
		}
	}
	public Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/book_db?serverTimezone=Asia/Seoul";
		String id = "root";
		String password = "dkssudgktpdy1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이브 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
			
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			
		} catch(SQLException e) {
			System.out.println("연결 실패");
		}
		return con;
	}
	
	public static void main(String[] args) {
		new BookdbEx();

	}

}