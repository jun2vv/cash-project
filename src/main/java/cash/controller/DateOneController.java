package cash.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/dateOne")
public class DateOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성검사 로그인시에만 접근가능
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// session 로그인값 받은후 변수 저장
		Member member = (Member)session.getAttribute("loginMember");
		String loginMember = member.getMemberId();
		
		int targetYear = 0;
		int targetMonth = 0;
		int targetDate = 0;
		
		// 월별 hashtag리스트에서 보내준 cashbookDate값
		if(request.getParameter("cashbookDate") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			targetDate = Integer.parseInt(request.getParameter("cashbookDate"));
		}
		// 전체날짜 cashbookListByTag에서 보내준 cashbookDateByTag값
		else if(request.getParameter("cashbookDateByTag") !=null) {
			// 년도만 추출
			targetYear = Integer.parseInt(request.getParameter("cashbookDateByTag").substring(0, 4));
			// 월만 추출 자바코드로 월을 보내주는게 아닌 쿼리에서 뽑은 데이터로 보내주는거라 -1을 해줘야 한다
			targetMonth = Integer.parseInt(request.getParameter("cashbookDateByTag").substring(5, 7)) -1;
			// 일만 추출
			targetDate = Integer.parseInt(request.getParameter("cashbookDateByTag").substring(8, 10));
		} else {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			targetDate = Integer.parseInt(request.getParameter("targetDate"));
		}
		
		
		System.out.println(targetYear + "targetYearTest");
		System.out.println(targetMonth + "targetMonthTest");
		System.out.println(targetDate + "targetDateTest");
		
		// calendar에서 보낸 날짜 요청값 저장
		
		
		
		// Dao호출
		List<Cashbook> list = new CashbookDao().dateOneList(loginMember, targetYear, targetMonth+1, targetDate);
		
		// view에 보낼 값 request에 저장
		request.setAttribute("list", list);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		
		request.getRequestDispatcher("/WEB-INF/view/dateOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
