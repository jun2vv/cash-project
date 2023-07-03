package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cash.vo.Cashbook;
import cash.vo.Member;

public class CashbookDao {
	
	// 1) 전체 달력에 표시할 데이터
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth) {
		List<Cashbook> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT cashbook_no cashbookNo, category, price, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND year(cashbook_date) = ? AND month(cashbook_date) = ? ORDER BY cashbook_date";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			System.out.println(stmt);
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));;
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
			}
			 
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
	
	// 2) 날짜별 상세보기
	public List<Cashbook> dateOneList(String loginMember, int targetYear, int targetMonth, int targetDate) {
		List<Cashbook> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT cashbook_no cashbookNo, category, price, memo, updatedate, createdate\r\n"
				+ "FROM cashbook \r\n"
				+ "WHERE member_id = ? AND year(cashbook_date) = ? AND month(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, loginMember);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDate);
			rs = stmt.executeQuery();
			System.out.println(stmt + "날짜별 stmt");
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));;
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setUpdatedate(rs.getString("updatedate"));
				c.setCreatedate(rs.getString("createdate"));
				list.add(c);
			}
			 
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
