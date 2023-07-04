package cash.vo;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
}
