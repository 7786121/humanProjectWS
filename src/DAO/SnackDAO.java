package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.SnackDTO;

public class SnackDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	private static SnackDAO sdao = null;
	public static SnackDAO getInstance() {
		if(sdao ==null) {
		sdao =new SnackDAO();
		}
		return sdao;
	}
	private SnackDAO() {
		
	}
	private ResultSet rs = null;
	
	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB연결이 되었습니다.");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 과자 등록
	public void insert(SnackDTO sdto) {
		String sql = "insert into snack values (?, ?, ?, ?, ?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, sdto.getNo()); // 번호
				ppst.setString(2, sdto.getName()); // 과자 이름
				ppst.setString(3, sdto.getCompany()); // 회사
				ppst.setInt(4, sdto.getPrice()); // 가격
				ppst.setInt(5, sdto.getCnt()); // 수량
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

	// 목록 보기
	public ArrayList<SnackDTO> list() {
		String sql = "select * from snack";
		ArrayList<SnackDTO> list = new ArrayList<>();
		Statement st = null;
		if(conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					SnackDTO tempDTO = new SnackDTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setName(rs.getString("name"));
					tempDTO.setCompany(rs.getNString("company"));
					tempDTO.setPrice(rs.getInt("price"));
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

	// 과자 삭제
	public void delete(int no) {
		String sql = "delete from snack where no =?";
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

	// 과자 가격 수정
	public void update(String name, int price) {
		String sql= "update snack set price=? where name=?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, price);
				ppst.setString(2, name);
				ppst.executeUpdate();
				System.out.println("수정 완료");
			} catch (Exception e) {
				System.out.println("실패");
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
	// 품절
	public ArrayList<SnackDTO> zero() {
		String sql = "select * from snack where cnt=0";
		Statement st = null;
		ArrayList<SnackDTO> tempList= new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					SnackDTO tempDTO= new SnackDTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setName(rs.getString("name"));
					tempDTO.setCompany(rs.getString("company"));
					tempDTO.setPrice(rs.getInt("price"));
					tempDTO.setCnt(rs.getInt("cnt"));
					tempList.add(tempDTO);
				}
			} catch (Exception e) {
			} finally {
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
		return tempList;
	}
}
