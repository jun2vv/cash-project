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

@WebServlet("/modifyMember")
public class ModifyMemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view에서 온 msg변수 유효성검사 후 저장
		String msg = null;
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		System.out.println(msg + "회원수정컨트롤러 msg");
		request.setAttribute("msg", msg);
		
		// request값 포워드 방식으로 보내기
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 로그인 값 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 요청값 저장
		String memberPw = request.getParameter("memberPw");
		String modifyPw = request.getParameter("modifyPw");
		String modifyPw2 = request.getParameter("modifyPw2");
		
		// dao 호출
		MemberDao memberDao = new MemberDao();
		
		Member member = new Member(loginMember.getMemberId(), memberPw, null, null);
		
		int row = memberDao.modifyMember(member, modifyPw, modifyPw2);
		
		String msg = null;
		if(row > 0) {
			msg = "정보수정에 성공하였습니다";
			System.out.println("정보수정완료");
			System.out.println(row);
			response.sendRedirect(request.getContextPath()+"/memberOne?msg=" +  URLEncoder.encode(msg, "UTF-8"));
			
		} else {
			msg = "정보수정을 실패하였습니다";
			System.out.println("정보수정실패");
			System.out.println(row);
			response.sendRedirect(request.getContextPath()+"/modifyMember?msg=" +  URLEncoder.encode(msg, "UTF-8"));
		}
		
	}

}
