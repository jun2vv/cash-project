package cash.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cash.vo.Member;

public class MemberDao {

	// 1) 로그인 메서드
	public Member selectMemberById(Connection conn, Member paramMember) {
		Member returnMember = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT member_id memberId FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return returnMember;
	}
	
	// 2)회원가입
	public int insertMember(Connection conn, Member member) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="INSERT INTO member VALUES(?, PASSWORD(?), now(), now())";
		int row = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 3) 회원상세정보
	public Member selectMemberOne(Connection conn, String memberId) {
		Member memberOne = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM member WHERE member_id = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				memberOne = new Member();
				memberOne.setMemberId(rs.getString("member_id"));
				memberOne.setMemberPw(rs.getString("member_pw"));
				memberOne.setCreatedate(rs.getString("createdate"));
				memberOne.setUpdatedate(rs.getString("updatedate"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return memberOne;
	}
	
	// 4) 회원탈퇴
	public int deleteMember(Connection conn, String memberId, String memberPw) {
		int row = 0;
		PreparedStatement stmt = null;
		
		String sql ="DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			row = stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 5) 회원정보수정 (Member member 기존 비밀번호 확인용, modifyPw변경할비밀번호, modifyPw2변경할비밀번호재확인)
	public int updateMember(Connection conn, Member member, String modifyPw, String modifyPw2) {
		int row = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String ckSql = "SELECT count(*) FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		try {
			stmt = conn.prepareStatement(ckSql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			System.out.println(row +"비밀번호 일치");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(cnt > 0 && modifyPw.equals(modifyPw2)) {
			
			System.out.println("비밀번호조건부합");
			String sql ="UPDATE member SET member_pw = PASSWORD(?), updatedate = now() WHERE member_id = ?";
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, modifyPw2);
				stmt.setString(2, member.getMemberId());
				
				row = stmt.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}
	
}
