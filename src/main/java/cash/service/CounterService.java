package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cash.model.CounterDao;

public class CounterService {

	private CounterDao counterDao;
	Connection conn = null;
	
	// 1번Dao호출
	public void addCounter () {
		this.counterDao = new CounterDao();
		conn = null;
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counterDao.insertCounter(conn);
			
		}catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
	}
	
	// 2번Dao호출
	public void modifyCounter() {
		this.counterDao = new CounterDao();
		conn = null;
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counterDao.updateCounter(conn);
			
		}catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
		
	}
	
	// 3번Dao호출
	public int getCounter() {
		this.counterDao = new CounterDao();
		conn = null;
		int counter = 0;
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counter = counterDao.selectCounterCurdate(conn);
			
		}catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
		
		return counter;
	}
	
	// 4번Dao호출
	public int getCounterAll() {
		this.counterDao = new CounterDao();
		conn = null;
		int totalCounter = 0;
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			totalCounter = counterDao.selectCounterAll(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
		
		return totalCounter;
	}
}
