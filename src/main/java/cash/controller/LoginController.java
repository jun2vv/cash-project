package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// forward방식으로 
		// 로그인이 되어있다면 cashbook으로 아니면 login으로 다시
		
		// alert창을 띄울 메세지 tmxm
		String msg = null;
		if(request.getParameter("msg")!= null) {
			msg = request.getParameter("msg");
		}
		
		System.out.println(msg + "로그인컨트롤러 msg");
		request.setAttribute("msg", msg);
		
		// session 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loignMember") != null) {
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		 
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId + "<-- LoginController memberId");
		System.out.println(memberPw + "<-- LoginController memberPw");
		
		Member member = new Member(memberId, memberPw, null, null);
		
		MemberDao memberDao = new MemberDao();
		Member  loginMember = memberDao.selectMemberById(member);
		// null 로그인실패
		
		if(loginMember == null) {
			System.out.println("로그인실패");
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} 
			// 로그인 성공시 session사용
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			System.out.println("로그인성공");
			response.sendRedirect(request.getContextPath()+"/cashbook");
	}

}
