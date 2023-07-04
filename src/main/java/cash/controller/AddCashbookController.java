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
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {
       
	@Override
	// 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		Member member = (Member)session.getAttribute("loginMember");
		String loginMember = member.getMemberId();
		
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
		request.getRequestDispatcher("WEB-INF/view/addCashbook.jsp").forward(request, response);
	}
	
	// 입력 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		String loginMember = member.getMemberId();
		
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
		
		CashbookDao cashbookDao = new CashbookDao();
		int cashbookNo = cashbookDao.insertCashbook(cashbook); // key값 반환
		
		// 입력성공시 1반환
		if(cashbookNo == 0) {
			System.out.println(cashbookNo + "입력실패");
			response.sendRedirect(request.getContextPath()+"/calendar");
			return;
			
		}
		
		System.out.println(cashbookNo + "입력성공");
		// 입력실패시 0반환
		
		
		// 입력성공시 -> 해시태그 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
		// 해시태그 추출 알고리즘
		HashtagDao hashtagDao = new HashtagDao();
		
		int hashtagRow = 0;
		memo = cashbook.getMemo();
		String memo2 = memo.replace("#", " #"); // "#구디아카데미" -> " #구디아카데미" 이런식으로 #앞에 공백이 생기도록 바꾼다.
		
		Set<String> set = new HashSet<String>(); // 중복된 해시태그방지를 위해 set자료구조를 사용
		
		// 해시태그가 여러개라면 반복해서 입력.
		for(String ht : memo2.split(" ")) {  // issue : split된 배열을 Set으로 변경하면 중복된 내용 제거 가능
			if (ht.startsWith("#")) {
				String ht2 = ht.replace("#", ""); // #을없앤다.
				if(ht.length() > 0) {
					set.add(ht2);
				
				}
			}
		}
		
		for(String s : set) {
			Hashtag hashtag = new Hashtag();
			hashtag.setCashbookNo(cashbookNo);
			hashtag.setWord(s);
			hashtagRow = hashtagDao.insertHashTag(hashtag);
		}
		
		if(hashtagRow > 0) {
			System.out.println(hashtagRow + "해쉬태그 입력성공");
			response.sendRedirect(request.getContextPath()+"/calendar");
			return;
		}
			System.out.println(hashtagRow + "해쉬태그 입력실패");
		response.sendRedirect(request.getContextPath()+"/calendar");
	}

}
