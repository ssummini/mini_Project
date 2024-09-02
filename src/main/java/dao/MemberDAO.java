package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDTO;
import dto.ReviewDTO;

public class MemberDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "C##JAVA";
	private String password = "1234";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public void getConnection() {
	    try {
	        Class.forName(driver);  // JDBC 드라이버 로드
	        con = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public String Login(String user_id, String user_pwd) {
		String user_name = null;
		getConnection();
		String sql = "select user_name from member where user_id = ? and user_pwd = ?";
		System.out.println("SQL Query: " + sql);
	    System.out.println("Parameters: user_id=" + user_id + ", user_pwd=" + user_pwd);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				user_name = rs.getString("user_name");
			}else {
				System.out.println("No matching record found.");
				user_name = null;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return user_name;
	}
	
	public int IdCheck(String user_id) {
		int result = 0;
		getConnection();

		String sql = "select * from member where user_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = 1;
			}else
				result = 0;	

		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int Join(MemberDTO dto) {
		int result = 0;
		getConnection();
		String sql = "insert into member values(member_seq.NEXTVAL,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_id());
			pstmt.setString(3, dto.getUser_email());
			pstmt.setString(4, dto.getUser_pwd());
			result = pstmt.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int reviewWrite(String user_id, String title, String content) {
		int result = 0;
		getConnection();
		String sql = "insert into review values(review_seq.NEXTVAL,?,?,?,sysdate)";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public List<ReviewDTO> reviewList(){
		List<ReviewDTO> reviewList = new ArrayList<>();
		getConnection();
		String sql = "select * from review order by review_seq desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd");
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				String logtime = sdf.format(rs.getDate("logtime"));
				
				dto.setReview_seq(rs.getInt("review_seq"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setLogtime(logtime);
				reviewList.add(dto);
				System.out.println(logtime);
			}
			if(reviewList.size() == 0) {
				reviewList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return reviewList;
	}
	
	public ReviewDTO review(int review_seq) {
		ReviewDTO reviewDTO = null;
		getConnection();
		String sql = "select * from review where review_seq=?";
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd");
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewDTO = new ReviewDTO();
				String logtime = sdf.format(rs.getDate("logtime"));
				reviewDTO.setUser_id(rs.getString("user_id"));
				reviewDTO.setTitle(rs.getString("title"));
				reviewDTO.setContent(rs.getString("content"));
				reviewDTO.setLogtime(logtime);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reviewDTO;
	}
	
	public int Delete_Review(int review_seq) {
		int result = 0;
		getConnection();
		String sql = "delete from review where review_seq =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_seq);			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public int Update_Review(int review_seq, String title, String content) {
		int result = 0;
		getConnection();
		String sql = "UPDATE review SET title = ?, content = ? WHERE review_seq = ?";


		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);			
			pstmt.setString(2, content);			
			pstmt.setInt(3, review_seq);			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public int updateArticle(String user_id, String user_pwd){
		int result = 0;
		getConnection();
		
		try {
			pstmt = con.prepareStatement("update member set user_pwd = ? where user_id = ?");
		
			pstmt.setString(1, user_pwd);
			pstmt.setString(2, user_id);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public int checkUser(String user_id, String user_pwd) {
		int result = 0;
		getConnection();
		String sql = "select * from member where user_id=? and user_pwd=?";
	    System.out.println("Parameters: user_id=" + user_id + ", user_pwd=" + user_pwd);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public int deleteUser(String user_id, String user_pwd) {
		int result = 0;
		getConnection();
		String sql = "delete from member where user_id=? and user_pwd=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
}
