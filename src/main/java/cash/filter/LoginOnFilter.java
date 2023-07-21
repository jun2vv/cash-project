package cash.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그인이 되어 있을때만 필터지나침
 */
@WebFilter("/on/*")
public class LoginOnFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("/on/*전");
		
		// 부모타입이 자식타입의 메서드를 사용할 수 없으니 다운캐스팅시킨다
		HttpServletRequest req = (HttpServletRequest)request;
		// 세션값을 받아오기 위해 세션객체 생성
		HttpSession session = req.getSession();
		
		// 로그인 되어 있을때 지나가는 필터이므로 로그인 안되어있으면 login컨트롤러로 보냄
		if(session.getAttribute("loginMember") == null) {
			HttpServletResponse rep = (HttpServletResponse)response;
			rep.sendRedirect(req.getContextPath()+"/off/login");
			return;
			
		}
		
		// 로그인 되어 있으면 실행
		chain.doFilter(request, response);
		
		System.out.println("/on/*후");
	}

}
