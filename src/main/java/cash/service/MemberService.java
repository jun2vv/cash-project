package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.model.MemberDao;
import cash.vo.Cashbook;
import cash.vo.Member;

public class MemberService {

	private MemberDao memberDao;
	Connection conn = null;
	
	// 1번dao 로그인
	public Member selectMemberId(Member member) {
		this.memberDao = new MemberDao();
		conn = null;
		// dao에 반환값을 저장할 객체 컨트롤러에서 값체크용으로 사용
		Member resultMember = new Member();
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://3.37.133.115:3306/cash","root","java1234");
			resultMember = memberDao.selectMemberById(conn, member);
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
		
		return resultMember;
	}
	
	// 2번dao 회원가입
	public int addMember(Member member) {
		this.memberDao = new MemberDao();
		int row = 0;
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://3.37.133.115:3306/cash","root","java1234");
			row = memberDao.insertMember(conn, member);
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
		
		return row;
	}
	
	// 3번dao회원 상세보기
	public Member MemberOne(String memberId) {
		this.memberDao = new MemberDao();
		Member resultMember = new Member();
		
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://3.37.133.115:3306/cash","root","java1234");
			resultMember = memberDao.selectMemberOne(conn, memberId);
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
		
		return resultMember;
	}
	
	// 4번 dao 회원탈퇴 회원탈퇴시 그 회원의 cashbook과 hashtag전부 삭제
	public int removeMember(String memberId, String memberPw) {
		this.memberDao = new MemberDao();
		HashtagDao hashtagDao = new HashtagDao();
		CashbookDao cashbookDao = new CashbookDao();
		
		int row =0;
		int hashtagRow = 0;
		conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://3.37.133.115:3306/cash","root","java1234");
			conn.setAutoCommit(false);
			// ArrayList에다가 cashbookNo를 아이디로 조회해서 담는다.
			ArrayList<Cashbook> cashbookNoList = memberDao.selectCashbookNoById(conn, memberId);
			// 그 후 지워야할 데이터가 있거나 없어도 hashtag를 cashbookNo키값으로 지운다
			if(cashbookNoList.size() >= 0 ) {
				for(Cashbook c : cashbookNoList) {
					hashtagRow = hashtagDao.deleteHashTag(conn, c.getCashbookNo());
					
					// 지워진 해시태그가 있다면 cashbook을 지운다.
					if(hashtagRow > 0) {
						cashbookDao.deleteCashbook(conn, c.getCashbookNo());
					}
				}
				// 그 후 회원 삭제.
				row = memberDao.deleteMember(conn, memberId, memberPw);
			}
			
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
		
	}
	
	// 5번 dao 회원정보 수정
	public int modifyMember(Member member, String modifyPw, String modifyPw2) {
		this.memberDao = new MemberDao();
		int row =0;
		try {
			// conn.getAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://3.37.133.115:3306/cash","root","java1234");
			row = memberDao.updateMember(conn, member, modifyPw, modifyPw2);
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
		return row;
	}
	
}
