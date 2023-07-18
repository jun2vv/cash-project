package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;

public class CashbookService {
	CashbookDao cashbookDao;
	HashtagDao hashtagDao;
	Connection conn;
	
	// 1) 1번 dao
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth) {
		List<Cashbook> list = new ArrayList<>();
		this.cashbookDao = new CashbookDao();
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			list = cashbookDao.selectCashbookListByMonth(conn, memberId, targetYear, targetMonth);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	// 2) 2번dao
	public List<Cashbook> dateOneList(String memberId, int targetYear, int targetMonth, int targetDate) {
		List<Cashbook> list = new ArrayList<>();
		this.cashbookDao = new CashbookDao();
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			list = cashbookDao.dateOneList(conn, memberId, targetYear, targetMonth, targetDate);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	// 3) 3번dao 캐시북추가 && HashtagDao 1번dao 해시태그 추가 트랜잭션처리
	public int addCashbookHasttag(Cashbook cashbook, Hashtag hashtag) {
		this.cashbookDao = new CashbookDao();
		this.hashtagDao = new HashtagDao();
		int row = 0;
		conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			// dao하나만 실행시 자동커밋되지 않도록 오토커밋 false
			conn.setAutoCommit(false);
			
			int keyNo = cashbookDao.insertCashbook(conn, cashbook);
			System.out.println(keyNo + "<--- keyNo");
			
			if(keyNo > 0) {
			// cashbook이 추가 되면 실행
				System.out.println(keyNo + "해시태그추가 실행확인 디버깅");
				// 해시태그 변환작업
				String memo = cashbook.getMemo();
				// "#구디아카데미" -> " #구디아카데미" 이런식으로 #앞에 공백이 생기도록 바꾼다.
				String memo2 = memo.replace("#", " #");
				// 중복된 해시태그방지를 위해 set자료구조를 사용
				Set<String> set = new HashSet<String>(); 
				
				// 해시태그가 여러개라면 반복해서 입력.
				for(String ht : memo2.split(" ")) { // issue : split된 배열을 Set으로 변경하면 중복된 내용 제거 가능
					if(ht.startsWith("#")) {
						String ht2 = ht.replace("#", ""); // #을없앤다.
						if(ht.length() > 0) {
							set.add(ht2);
						}
					}
				}
				// cashbook에 입력한 해시태그가 #을기준으로 여러번 실행해서 추가시킴 (cashbookNo를(외래키) 기준으로)
				for(String s : set) {
					hashtag.setCashbookNo(keyNo);
					hashtag.setWord(s);
					row = hashtagDao.insertHashTag(conn, hashtag);
				}
			}
			// 에러없이 트랜잭션 정상처리 됬다면 커밋실행
			conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	// 4) 4번dao
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage) {
		List<Cashbook> list = new ArrayList<>();
		this.cashbookDao = new CashbookDao();
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			list = cashbookDao.selectCashbookListByTag(conn, memberId, word, beginRow, rowPerPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 4-1) 4-1번 dao
	public int selectCashbookListByTagCnt(String memberId, String word) {
		int totalRow = 0;
		this.cashbookDao = new CashbookDao();
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			totalRow = cashbookDao.selectCashbookListByTagCnt(conn, memberId, word);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return totalRow;
	}
	
}
