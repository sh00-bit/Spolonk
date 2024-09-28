package kr.co.softsoldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;


public class TopMenuInterceptor implements HandlerInterceptor{

	//인터셉터에서는 자동주입(@Autowired 등) 불가능
	private UserBean loginUserBean;
	
	
	//생성자를 통해 주입
	public TopMenuInterceptor(UserBean loginUserBean) {
	
		this.loginUserBean = loginUserBean;
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		request.setAttribute("loginUserBean", loginUserBean);
		
		return  true;
	}
	
		
	
}
