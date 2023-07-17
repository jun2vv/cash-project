package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cash.model.HashtagDao;

public class HashtagService {
	private HashtagDao hashtagDao;
	Connection conn = null;
	// 1번 dao는 CashbookService에서 트랜젝션 처리
	
	
	// 2번 dao 월별 해시태그개수 리스트 (그달에 해시태그별 목록들
	public List<Map<String, Object>> selectWordCountByMonthService(String memberId, int targetYear, int targetMonth) {
		List<Map<String, Object>> list = new ArrayList<>();
		// 해시태그 dao사용을위한 객체생성
		this.hashtagDao = new HashtagDao();
		conn = null;
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			list = hashtagDao.selectWordCountByMonth(conn, memberId, targetYear, targetMonth);
		} catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 3번 dao 해시태그별(월별) 리스트
	public List<Map<String, Object>> selectHashtagListSerivce(String memberId, int targetYear, int targetMonth, String hashtag) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		this.hashtagDao = new HashtagDao();
		conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			list = hashtagDao.selectHashtagList(conn, memberId, targetYear, targetMonth, hashtag);
		} catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
