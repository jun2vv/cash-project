package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cash.vo.Hashtag;

public class HashtagDao {
	public int insertHashTag(Hashtag hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		String sql ="INSERT INTO hashtag(cashbook_no, word, updatedate, createdate) VALUES(?, ?, now(), now())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
			row = stmt.executeUpdate();
			
			System.out.println(stmt + "insertHashTag DAO");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		return row;
	}
	
	// 2) 매달 해시태그별 개수리스트
	public List<Map<String, Object>> selectWordCountByMonth(String memberId, int targetYear, int targetMonth) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT word, COUNT(*) cnt"
				+ " FROM hashtag h INNER JOIN cashbook c"
				+ " ON h.cashbook_no = c.cashbook_no"
				+ " WHERE c.member_id = ?"
				+ " AND YEAR(c.cashbook_date) = ?"
				+ " AND MONTH(c.cashbook_date) = ?"
				+ " GROUP BY word"
				+ " ORDER BY COUNT(*) DESC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("word", rs.getString("word"));
				map.put("cnt", rs.getString("cnt"));
				list.add(map);
			}
			
			System.out.println(stmt + "selectWordCountByMonth DAO");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 3) 해시태그별 리스트
	public List<Map<String, Object>> selectHashtagList(String memberId, int targetYear, int targetMonth, String hashtag) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT c.cashbook_no cashbookNo, c.category, substring(c.cashbook_date, 9, 10) cashbookDate, c.price, c.memo, c.updatedate, c.createdate \r\n"
				+ "FROM hashtag h INNER JOIN cashbook c\r\n"
				+ "ON h.cashbook_no = c.cashbook_no\r\n"
				+ "WHERE c.member_id = ? AND year(c.cashbook_date) = ? AND MONTH(c.cashbook_date) = ? AND h.word = ?\r\n"
				+ "ORDER BY c.createdate DESC;";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setString(4, hashtag);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getString("cashbookNo"));
				map.put("category", rs.getString("category"));
				map.put("cashbookDate", rs.getString("cashbookDate"));
				map.put("price", rs.getInt("price"));
				map.put("memo", rs.getString("memo"));
				map.put("updatedate", rs.getString("updatedate"));
				map.put("createdate", rs.getString("createdate"));
				list.add(map);
			}
			System.out.println(stmt + "selectHashtagList DAO");
			System.out.println(list + "list");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
