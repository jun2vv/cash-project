package cash.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.HashtagDao;
import cash.service.HashtagService;
import cash.vo.Member;

// 선택한월을 기준으로 해시태그별리스트
@WebServlet("/on/hashtag")
public class HashtagController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass()+", "+request.getRequestURL());
		// session 검증 코드 onFilter에서 처리
		
		HttpSession session = request.getSession();
		
		// session에서 받아온 로그인값 변수선언
		String memberId = (String)session.getAttribute("loginMember");
		// calendar에서 a태그로 보내준 요청값 저장
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		
		String hashtag = request.getParameter("hashtag");
		
		// List객체 ,HashtagDao객체 생성후 dao호출
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		HashtagService hashtagService = new HashtagService();
		
		list = hashtagService.selectHashtagListSerivce(memberId, targetYear, targetMonth+1, hashtag);
		
		
		// request에 view로 값 전달
		request.setAttribute("list", list);
		request.setAttribute("hashtag", hashtag);
		// 날짜는 그 데이터클릭시 가계부를 작성한 날짜의 상세보기로가기 위함.
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/hashtagList.jsp").forward(request, response);
		
	}


}
