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
import cash.vo.Member;

// 선택한월을 기준으로 해시태그별리스트
@WebServlet("/hashtag")
public class HashtagController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 검증 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		// session에서 받아온 로그인값 변수선언
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		// calendar에서 a태그로 보내준 요청값 저장
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		
		String hashtag = request.getParameter("hashtag");
		
		// List객체 ,HashtagDao객체 생성후 dao호출
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		HashtagDao hashtagDao = new HashtagDao();
		
		list = hashtagDao.selectHashtagList(memberId, targetYear, targetMonth+1, hashtag);
		
		
		// request에 view로 값 전달
		request.setAttribute("list", list);
		request.setAttribute("hashtag", hashtag);
		// 날짜는 그 데이터클릭시 가계부를 작성한 날짜의 상세보기로가기 위함.
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/hashtagList.jsp").forward(request, response);
		
	}


}
