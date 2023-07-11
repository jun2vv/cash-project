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
import cash.service.MemberService;
import cash.vo.Member;

@WebServlet("/on/removeMember")
public class RemoveMemberController extends HttpServlet {
       
	// 비밀번호 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session유효성검사 onFilter에서 처리
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
		
	}

	// 탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL()+"doPost");
		// session객체생성
		HttpSession session = request.getSession();
		
		// 세션아이디값 변수저장
		String loginMember = (String)(session.getAttribute("loginMember"));
		// 요청값 저장
		String memberPw = request.getParameter("memberPw");
		
		// dao호출
		MemberService memberService = new MemberService();
		int row = memberService.removeMember(loginMember, memberPw);
		
		String msg = null;
		if(row > 0) {
			System.out.println("회원탈퇴 성공");
			// 탈퇴 성공시 
			session.invalidate();
			msg = "회원탈퇴 성공";
			response.sendRedirect(request.getContextPath()+"/off/login?msg="+ URLEncoder.encode(msg, "UTF-8"));
			
		} else {
			msg = "회원탈퇴 실패";
			System.out.println("회원탈퇴 실패");
			response.sendRedirect(request.getContextPath()+"/on/memberOne?msg="+ URLEncoder.encode(msg, "UTF-8"));
			
		}
		
	}
}
