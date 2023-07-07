package cash.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 톰캣이 켜질때 실행되는 리스너
 *
 */
@WebListener
public class BootListener implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
		System.out.println("실행확인 : ServletContextListener.contextInitialized");
		
		// ServletContext sc = new ServletContext(); // ServletContext는 인터페이스 이므로 new로 객체 생성이 불가능
		
		// application속성 영역에 "currentCounter"속성변수 0으로초기화
		// sce.getServletContext()메서드가 ServletContext객체를 가져오는 메서드
		ServletContext application = sce.getServletContext();
		application.setAttribute("currentCounter", 0);
		
		System.out.println(application.getAttribute("currentCounter") +"톰캣리스너 currentCounter값");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch (Exception e) {
			System.out.println("mariadb 드라이버 로딩 실패");
			e.printStackTrace();
		}
		
		System.out.println("mariadb 드라이버 로딩 성공");
		
	}

	
}
