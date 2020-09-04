package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.BoardDTO;

public class BoardDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	private static BoardDAO bdao = null;
	public static BoardDAO getInstance() {
		bdao =new BoardDAO();
		return bdao;
	}
	private ResultSet rs = null;
	
	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB������ �Ǿ����ϴ�.");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// �� ���
	public void insert(BoardDTO bdto) {
		String sql = "insert into board values (?, ?, ?, ?, ?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, bdto.getNo()); // no
				ppst.setString(2, bdto.getTitle()); // title
				ppst.setString(3, bdto.getContent()); // content
				ppst.setString(4, bdto.getWriter()); // �ۼ���
				ppst.setInt(5, bdto.getCnt()); // ��ȸ��
				ppst.executeUpdate();
			} catch (Exception e) {
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ��� ����
	public ArrayList<BoardDTO> list() {
		String sql = "select * from board";
		ArrayList<BoardDTO> list = new ArrayList<>();
		Statement st = null;
		if(conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					BoardDTO tempDTO = new BoardDTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setTitle(rs.getString("title"));
					tempDTO.setContent(rs.getNString("content"));
					tempDTO.setWriter(rs.getNString("writer"));
					tempDTO.setCnt(rs.getInt("cnt"));
					list.add(tempDTO);
				}
			} catch (Exception e) {
			}finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// �� ����
	public void delete(int no) {
		String sql = "delete from board where no =?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				ppst.executeUpdate();
			} catch (Exception e) {
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// �� ����
	public void update(int no, String title) {
		String sql= "update board set title=? where no=?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setString(1, title);
				ppst.setInt(2, no);
				ppst.executeUpdate();
				System.out.println("������Ʈ �Ϸ�");
			} catch (Exception e) {
				System.out.println("������Ʈ ���ܹ߻�");
			} finally {
				try {
					if(ppst!= null);
					ppst.close();
					if(conn!= null);
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
