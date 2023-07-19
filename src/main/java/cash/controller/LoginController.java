package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cash.model.MemberDao;
import cash.service.MemberService;
import cash.vo.Member;

@WebServlet("/off/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session 유효성검사 LogOffFilter에서 처리
		// 로그인이 되어있다면 cashbook으로 아니면 login으로 다시
		
		// 쿠키에 저장된 로그인성공된 아이디가 있다면 request속성에 저장해서 view에서 value로 출력 
		Cookie[] cookies = request.getCookies(); // getCookies메서드를 통하여 모든 쿠키값을 배열에 저장
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("cookieLoginId") == true) {
					// request.setAttribute에다가 찾은 쿠키값 저장
					request.setAttribute("cookieId", c.getValue());
					System.out.println(request.getAttribute("cookieId") + ": 저장한 cookieId");
				}
			}
		}
		
		
		// alert창을 띄울 메세지 
		String msg = null;
		if(request.getParameter("msg")!= null) {
			msg = request.getParameter("msg");
		}
		
		System.out.println(msg + "로그인컨트롤러 msg");
		request.setAttribute("msg", msg);
		 
		
		// 포워드방식으로 view에 값전달
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL()+"doPost");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId + "<-- LoginController memberId");
		System.out.println(memberPw + "<-- LoginController memberPw");
		
		Member member = new Member(memberId, memberPw, null, null);
		String loginId = (String)member.getMemberId();
		System.out.println(loginId +"login Id");
		
		// 서비스에서 호출
		MemberService memberService = new MemberService();
		Member loginMember = memberService.selectMemberId(member);
		
		// null 로그인실패
		
		if(loginMember == null) {
			System.out.println("로그인실패");
			response.sendRedirect(request.getContextPath()+"/off/login");
			return;
		} 
			// 로그인 성공시 session사용
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginId);
			
			// idSave 체크박스 값이 넘어왔다면 쿠키에 아이디값저장
			if(request.getParameter("idSave") != null) {
				Cookie loginIdCookie = new Cookie("cookieLoginId",loginId);
				// 클라이언트한테 쿠키 전송
				response.addCookie(loginIdCookie);
			}
			
			System.out.println("로그인성공");
			response.sendRedirect(request.getContextPath()+"/on/cashbook");
	}

}
