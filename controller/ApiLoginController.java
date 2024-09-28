package kr.co.softsoldesk.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.softsoldesk.service.UserService;

@Controller
@RequestMapping("/login")
public class ApiLoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/google")
	@ResponseBody
	public Map<String, Object> google(@RequestBody Map<String, String> request) {
	    Map<String, Object> response = new HashMap<>();
	    String user_email = request.get("api_email");
	    System.out.println("Received email: " + user_email);

	    // 예시: 이메일로 사용자 확인
		// false
		  boolean userExists = userService.checkUserExists(user_email);
			    
	    
//	    확인용
		  
			/* boolean userExists = true; */	  		
	    response.put("success", userExists);
	    
	    return response;
	}
	
	@PostMapping("/kakao")
	@ResponseBody
	public Map<String, Object> kakao(@RequestBody Map<String, String> request) {
	    Map<String, Object> response = new HashMap<>();
	    
	    String userId = request.get("api_id");
	    String userNickname = request.get("api_nickname");
	    String userEmail = request.get("api_email");// 널값
	    
	    System.out.println("Received ID: " + userId);
	    System.out.println("Received Nickname: " + userNickname);
	    System.out.println("Received Email: " + userEmail);

	    // Example: Check if the user exists by email
		
	    boolean userExists = userService.checkUserExistsKakao(userNickname);
		 
	    
		/* boolean userExists = true; */
	    response.put("success", userExists);

	    // Further logic can be added here, like saving user info if needed

	    return response;
	}
}
