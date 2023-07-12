package cash.model;

import java.beans.Statement;
import java.sql.*;
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
	
	// 3) 캐시북 추가 반환값: cashbook_no 키값을통해 해시태그 동시추가
	public int insertCashbook(Cashbook cashbook) {
		int cashbookNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate) VALUES(?, ?, ?, ?, ?, now(), now())";
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
			System.out.println(stmt + "insertCashbook DAO");
			
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
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
		
		return cashbookNo;
	}
	
	// 4) 해시태그별 전체리스트
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage) {
		List<Cashbook> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="SELECT c.cashbook_no cashbookNo, c.category, c.cashbook_date cashbookDate, c.price, c.memo, c.updatedate, c.createdate FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ? ORDER BY c.cashbook_date DESC LIMIT ?, ?";
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCreatedate(rs.getString("createdate"));
				c.setUpdatedate(rs.getString("updatedate"));
				list.add(c);
			}
			System.out.println(stmt + "selectCashbookListByTag DAO");
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
	
	// 4-1) 해시태그별 전체리스트의 count(*) 페이징용
	public int selectCashbookListByTagCnt(String memberId, String word) {
		int totalRow = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="SELECT count(*) FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ? ORDER BY c.cashbook_date DESC";
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalRow = rs.getInt(1);
			}
			System.out.println(totalRow + "<-- totalRow selectCashbookListByTagCnt DAO");
			
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
		return totalRow;
	}
}
