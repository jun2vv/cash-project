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


@WebServlet("/off/addMember")
public class AddMemberController extends HttpServlet {
	
	// 회원가입폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session 유효검사 offFilter에서 처리
		
		// jsp페이지로 포워드(디스패치)
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		
		
	}
	
	// 회원가입 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL()+"doPost");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId + "회원가입컨트롤러 memberId");
		System.out.println(memberPw + "회원가입컨트롤러 memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 회원가입 DAO호출
		MemberService memberService = new MemberService();
		int row = memberService.addMember(member);
		
	    String msg = null;
		// 회원가입 성공시
		if(row > 0) { 
			msg = "회원가입 되었습니다!";
			System.out.println("회원가입 성공");
			System.out.println(row + "row");
			response.sendRedirect(request.getContextPath()+"/off/login?msg=" + URLEncoder.encode(msg, "UTF-8"));
			
		// 회원가입 실패시	
		} else if (row == 0){ 
			msg = "회원가입에 실패하였습니다!";
			System.out.println("회원가입 실패");
			System.out.println(row + "row");
			response.sendRedirect(request.getContextPath()+"/off/addMember?msg=" + URLEncoder.encode(msg, "UTF-8"));
		} else {
			System.out.println("에러!");
			System.out.println(row + "row");
		}
		
	}

}
