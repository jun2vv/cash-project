package cash.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.service.CashbookService;
import cash.vo.Cashbook;
import cash.vo.Member;

// 전체날짜를 기준으로 해시태그별 리스트
@WebServlet("/on/cashbookListByTag")
public class CashbookListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session 유효성 onFilter에서 처리
		HttpSession session = request.getSession();
		
		// session에서 아이디값 받기
		String loginMember = (String)session.getAttribute("loginMember");
		// 해시태그 요청값 받기
		String hashtag = request.getParameter("hashtag");
		
		
		
		CashbookService cashbookServiceDao = new CashbookService();
	
		// ----------페이징 처리-------------
		//현재페이지 변수
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 페이지당 출력할 행의 수
		int rowPerPage = 5;
		
		// 페이지당 시작 행번호
		int beginRow = (currentPage-1) * rowPerPage;
		
		// 전체 행번호 호출
		int totalRow = cashbookServiceDao.selectCashbookListByTagCnt(loginMember, hashtag);
		
		int lastPage = totalRow / rowPerPage;
		//rowPerPage가 딱 나뉘어 떨어지지 않으면 그 여분을 보여주기 위해 +1
		if(totalRow % rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		// 페이지 네비게이션 페이징
		int pagePerPage = 10;

		// 마지막 페이지 구하기
		// 최소페이지,최대페이지 구하기
		int minPage = ((currentPage-1) / pagePerPage) * pagePerPage + 1;
		int maxPage = minPage + (pagePerPage -1);
		
		// maxPage가 마지막 페이지를 넘어가지 않도록 함
		if(maxPage > lastPage) {
			maxPage = lastPage;
		}
		
		
		// ----------페이징 끝-------------
		
		List<Cashbook> list = new ArrayList<>();
		list = cashbookServiceDao.selectCashbookListByTag(loginMember, hashtag, beginRow, rowPerPage);
		
		// 변수값들을 요청 객체에 저장
		// 페이징변수
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("minPage", minPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("lastPage", lastPage);
		// 리스트 출력 변수
		request.setAttribute("hashtag", hashtag);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}

}
