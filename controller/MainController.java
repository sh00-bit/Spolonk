package kr.co.softsoldesk.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.imageBean;
import kr.co.softsoldesk.service.AdminService;
import kr.co.softsoldesk.service.ImageService;



@Controller
public class MainController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	private ImageService imageService;

	@GetMapping("/main")
	private String main(Model model,  @RequestParam(value = "page", defaultValue = "1") int page) {	
		
		List<imageBean> pictureList = imageService.selectImage(page); 
		
		// 리스트에서 최신 6개의 이미지만 잘라냄
	    List<imageBean> latestImages = pictureList.stream().limit(6).collect(Collectors.toList());
	    	    
	    model.addAttribute("pictureList", latestImages);
	    
	    int userCount = adminService.usercount();  // 서비스에서 유저 카운트를 가져옴
        model.addAttribute("userCount", userCount); 

        int teamCount = adminService.teamcount();
        model.addAttribute("teamCount", teamCount);
        
        int contestCount = adminService.contestcount();
        model.addAttribute("contestCount", contestCount);
        
        int volunteerCount = adminService.volunteercount();
        model.addAttribute("volunteerCount", volunteerCount);
	    
	    
		
	    return "main";
	}
	@GetMapping("/merc_register")
	private String merc_register() {
		
		
		return "merc_register";
	}
	
	@GetMapping("/merc_register_1")
	private String merc_register_1() {
		
		
		return "merc_register_1";
	}
	
	@GetMapping("/info")
	private String info() {
		
		return "info";
	}
	

}
