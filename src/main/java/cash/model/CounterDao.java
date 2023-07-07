package cash.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterDao {
	// 1) 오늘날짜 첫번째 접속 -> insert
		public int insertCounter(Connection conn) throws Exception {
			int row = 0;
			PreparedStatement stmt = null;
			String sql ="INSERT INTO counter VALUES(CURDATE(), 1)";
			try {
				stmt = conn.prepareStatement(sql);
				row = stmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
				// 예외를 던져야한다
				throw new Exception(); // 강제로 예외를 발생
				
			}finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return row;
		}

		// 2) 오늘날짜 첫번째가 아니면 -> update
		public int updateCounter(Connection conn) throws Exception {
			int row = 0;
			PreparedStatement stmt = null;
			String sql ="UPDATE counter SET counter_num = counter_num+1 WHERE counter_date = CURDATE() ";
			try {
				stmt = conn.prepareStatement(sql);
				row = stmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
				// 예외를 던져야한다
				throw new Exception(); // 강제로 예외를 발생
				
			}finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return row;
		}
		
		// 3) 오늘날짜 카운터
		public int selectCounterCurdate(Connection conn) throws Exception {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int counter = 0;
			String sql ="SELECT counter_num counterNum FROM counter WHERE counter_date = CURDATE()";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if(rs.next()) {
					counter = rs.getInt("counterNum");
				}
			}catch (Exception e) {
				e.printStackTrace();
				// 예외를 던져야한다
				throw new Exception(); // 강제로 예외를 발생
				
			}finally {
				try {
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return counter;
		}
		
		
		// 4) 누적 카운터
		public int selectCounterAll(Connection conn) throws Exception {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int totalCount = 0;
			String sql ="SELECT SUM(counter_num) totalCount FROM counter";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if(rs.next()) {
					totalCount = rs.getInt("totalCount");
				}
			}catch (Exception e) {
				e.printStackTrace();
				// 예외를 던져야한다
				throw new Exception(); // 강제로 예외를 발생
				
			}finally {
				try {
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return totalCount;
		}
}
