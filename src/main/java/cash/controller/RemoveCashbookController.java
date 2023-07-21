package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.service.CashbookService;

@WebServlet("/on/removeCashbook")
public class RemoveCashbookController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성검사 onFilter에서처리
		int targetYear = Integer.parseInt(request.getParameter("targetYear")); 
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth")); 
		int targetDate = Integer.parseInt(request.getParameter("targetDate")); 
		
		CashbookService cashbookService = new CashbookService();
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo")); 
		
		System.out.println("targetYear" +targetYear);
		System.out.println("targetMonth" +targetMonth);
		System.out.println("targetDate" +targetDate);
		System.out.println("cashbookNo" +cashbookNo);
		
		int row = cashbookService.deleteCashbook(cashbookNo);
		System.out.println("RemoveCashbookController deleteCashbook() row -->" + row);
		
		if(row > 0) {
			System.out.println("삭제완료");
			response.sendRedirect(request.getContextPath()+"/on/dateOne?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
		}
	}

}
