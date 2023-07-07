package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.service.CounterService;

@WebServlet("/on/cashbook")
public class CashbookController extends HttpServlet {
	private CounterService counterService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		
		// 현재 방문자 수를 알기위한 counterService
		counterService = new CounterService();
		
		// 오늘접속자 수
		int counter = counterService.getCounter();
		// 누적 접속자 수
		int totalCounter = counterService.getCounterAll();
		request.setAttribute("counter", counter);
		request.setAttribute("totalCounter", totalCounter);
		
		// session 유효성 검사 onFilter에서처리
		
		// 이번달 달력에 가계부목록의 모델값을 셋팅
		
		// include해도 상관없음 근데 모두가 forward사용
		request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);
	}

}
