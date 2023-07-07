package cash.listener;

import javax.servlet.ServletContext;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cash.service.CounterService;

/**
 * 세션이 생성될 때 실행되는 리스너
 *
 */
@WebListener
public class CounterListener implements HttpSessionListener {
	private CounterService countService;
	
	public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println(se.getSession().getId() + "의 새로운 세션이 생성되었습니다.");
    	
    	// 세션이 생길때마다 현재 접속자수+1 -> application.attribute 
    	ServletContext application = se.getSession().getServletContext();
    	int currentCounter = (int)(application.getAttribute("currentCounter"));
    	
    	application.setAttribute("currentCounter", currentCounter+1);
    	System.out.println(application.getAttribute("currentCounter") +"카운터리스너 currentCounter값");
    	
    	// 오늘 접속자수 db에도 +1
    	countService = new CounterService();
    	int counter = countService.getCounter();
    	
    	// 오늘날짜 카운터를 불러와서 counter_num이 0이면 방문자가 하나도 없던거니 추가 아니면 기존값+1증가되게 수정
    	if(counter == 0) {
    		// counter_num을 해당날짜에 1증가시킴
    		countService.addCounter();
    	} else {
    		// 1씩증가되게 업데이트
    		countService.modifyCounter();
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	
    	System.out.println(se.getSession().getId() + "세션이 소멸되었습니다.");
    	
    	// 세션이 사라질때마다 현재 접속자수-1 -> application.attribute db는 건들지 않는다.
    	ServletContext application = se.getSession().getServletContext();
    	int currentCounter = (int)(application.getAttribute("currentCounter"));
    	application.setAttribute("currentCounter", currentCounter-1);
    	
    
    }
	
}
