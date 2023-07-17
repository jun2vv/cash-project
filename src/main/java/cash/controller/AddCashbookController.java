package cash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.service.CashbookService;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@WebServlet("/on/addCashbook")
public class AddCashbookController extends HttpServlet {
       
	@Override
	// 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session 유효성 검사 Filter에서 처리
		
		HttpSession session = request.getSession();
		
		String loginMember = (String)session.getAttribute("loginMember");
		
		// request 요청값 
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		
		// 나머지는 사용자가 입력폼에서 직접 입력
		// view에 보낼 값 request에 저장
		request.setAttribute("loginMember", loginMember);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		
		// 포워드방식으로 보냄
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
	}
	
	// 입력 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL()+"doPost");
		
		HttpSession session = request.getSession();
		String loginMember = (String)session.getAttribute("loginMember");
		
		// 데이터 입력 요청값 
		String date = request.getParameter("date");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		Cashbook cashbook = new Cashbook();
		// 추가할 데이터 CashbookVO에 입력
		cashbook.setMemberId(loginMember);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(date);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		Hashtag hashtag = new Hashtag();
		
		
		CashbookService cashbookService = new CashbookService();
		
		int row = cashbookService.addCashbookHasttag(cashbook, hashtag);
		
		if(row > 0) {
			System.out.println(row + "캐시북 추가성공");
			response.sendRedirect(request.getContextPath()+"/on/calendar");
			return;
		}
			System.out.println(row + "캐시북 추가실패");
		response.sendRedirect(request.getContextPath()+"/on/calendar");
	}

}
