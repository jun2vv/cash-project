package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;


@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {

	// 회원가입폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효검사(null)일때
		HttpSession session = request.getSession();
		if(session.getAttribute("loignMember") != null) {
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		
		// jsp페이지로 포워드(디스패치)
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		
		
	}
	
	// 회원가입 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		// 요청값
		if(request.getParameter("memberId") == null
			|| request.getParameter("memberPw") == null
			|| request.getParameter("memberId").equals("")
			|| request.getParameter("memberPw").equals("")) {
			
			response.sendRedirect(request.getContextPath()+"/WEB-INF/view/addMember.jsp");
			return;
		}
		*/
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId + "회원가입컨트롤러 memberId");
		System.out.println(memberPw + "회원가입컨트롤러 memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 회원가입 DAO호출
		MemberDao mDao = new MemberDao();
		int row = mDao.insertMember(member);
		
	    String msg = null;
		// 회원가입 성공시
		if(row > 0) { 
			msg = "회원가입 되었습니다!";
			System.out.println("회원가입 성공");
			System.out.println(row + "row");
			response.sendRedirect(request.getContextPath()+"/login?msg=" + URLEncoder.encode(msg, "UTF-8"));
			
		// 회원가입 실패시	
		} else if (row == 0){ 
			msg = "회원가입에 실패하였습니다!";
			System.out.println("회원가입 실패");
			System.out.println(row + "row");
			response.sendRedirect(request.getContextPath()+"/addMember?msg=" + URLEncoder.encode(msg, "UTF-8"));
		} else {
			System.out.println("에러!");
			System.out.println(row + "row");
		}
		
	}

}
