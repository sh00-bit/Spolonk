package kr.co.softsoldesk.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.softsoldesk.beans.UserBean;

//최초의 요청
@Controller
public class HomeController {
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean; //최초의 요청 시에는 false
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		System.out.println("dddd");
		
		return "redirect:/main";
	}
}

