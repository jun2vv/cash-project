package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.service.MemberService;
import cash.vo.Member;

@WebServlet("/on/memberOne")
public class MemberOneController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session유효성검사 onFilter에서 처리
		
		HttpSession session = request.getSession();
		
		// msg값 요청값 검사 후 저장
		String msg = null;
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		
		System.out.println(msg + "회원정보컨트롤러 msg");
		request.setAttribute("msg", msg);
		
		String loginMember = (String)session.getAttribute("loginMember");
		
		
		// 모델 값 구하기(dao메서드 호출)
		MemberService memberService = new MemberService();
		Member member = memberService.MemberOne(loginMember);
			
		// member 출력하는 (포워딩대상)memberOne.jsp에도 공유되어야한다
		// request가 공유되니까 request안에 넣어서 함께 사용한다
		request.setAttribute("member", member);
		
		// memberOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

}
