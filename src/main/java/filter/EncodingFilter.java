package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

// 모든 컨트롤러를 지나치는 인코딩필터
@WebFilter("/*")

public class EncodingFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("인코딩 전");
		
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
		
		System.out.println("인코딩 후");
	}


}
