package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ShoppingDTO;

public class ShoppingDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	private static ShoppingDAO sdao = null;

	public static ShoppingDAO getInstance() {
		if (sdao == null) {
			sdao = new ShoppingDAO();
		}
		return sdao;
	}

	private ShoppingDAO() {
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

	// 구매
	public void insert(ShoppingDTO shop) {
		String sql = "insert into shopping values(?, ?, ?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, shop.getSid());
				ppst.setString(2, shop.getSname());
				ppst.setInt(3, shop.getCnt());
				ppst.executeUpdate();
			} catch (Exception e) {

			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}

	// 구매목록
	public ArrayList<ShoppingDTO> list() {
		String sql = "select * from shopping";
		Statement st = null;
		ArrayList<ShoppingDTO> tempList = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					ShoppingDTO tempDTO = new ShoppingDTO();
					tempDTO.setSid(rs.getString("sid"));
					tempDTO.setSname(rs.getString("sname"));
					tempDTO.setCnt(rs.getInt("cnt"));
					tempList.add(tempDTO);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
		return tempList;
	}
}
