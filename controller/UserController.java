package kr.co.softsoldesk.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.softsoldesk.Validator.UserValidator;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.service.ContestService;
import kr.co.softsoldesk.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ContestService contestService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	// 회원가입
	// #3 서버 응답(jsp 반환)
	@GetMapping("/join")
	private String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		// #4 modelAttribute로 UserBean 객체를 jsp로 이동

		return "user/join";
	}

	// 회원가입 유효성 검사
	@PostMapping("/join_pro")
	private String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {

		if (result.hasErrors()) {
			return "user/join";
		} // 유효성 검사에 실패한 경우 "user/join" 뷰를 반환

		userService.addUser(joinUserBean);

		return "user/join_success";
	}// 유효성 검사에 성공한 경우 "user/join_success" 뷰를 반환

	// @InitBinder: Spring MVC에서 특정 컨트롤러에 대한 데이터 바인딩 및 검증 설정을 초기화하는 메서드에 적용
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// UserValidator 객체 생성
		UserValidator validator1 = new UserValidator();
		// WebDataBinder 객체에 UserValidator를 추가하여 유효성 검사 설정
		binder.addValidators(validator1);
	}

	// 로그인
	@GetMapping("/login")
	private String loginBean(@ModelAttribute("loginBean") UserBean loginBean,
			@RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) {

		model.addAttribute("fail", fail);

		return "user/login";
	}

	// 로그인 유효성 검사
	@PostMapping("/login_pro")
	private String login(@Valid @ModelAttribute("loginBean") UserBean loginBean, BindingResult result) {
		// 유효성 검사에 걸리면 에러
		if (result.hasErrors()) {
			return "user/login";
		}
		userService.getLoginInfo(loginBean);

		// 로그인 성공 시
		if (loginUserBean.isUserLogin() == true) {
			return "user/login_success";
			// 로그인 실패 시
		} else {
			return "user/login_fail";
		}
	}

	// 로그아웃
	@GetMapping("/logout")
	private String logout() {

		loginUserBean.setUserLogin(false);

		return "user/logout";

	}

	// 아이디 찾기
	@GetMapping("/find_id")
	private String findid(@ModelAttribute("userFindBean") UserBean findIdBean) {

		return "user/find_id";
	}

	@PostMapping("/find_id_pro")
	private String findid_pro(@ModelAttribute("userFindBean") UserBean findIdBean, Model model) {

		String find_id = userService.findId(findIdBean);

		model.addAttribute("find_id", find_id);

		return "user/findId_success_fail";

	}
	
	//비밀번호 찾기
	@GetMapping("/find_password")
	private String findpassword(@ModelAttribute("FindPassBean") UserBean PassBean) {
		
		return "user/find_password";
	}
	
	@PostMapping("/find_password")
	private String find_password(@ModelAttribute("FindPassBean") UserBean PassBean, Model model) {
	    boolean find_pw = userService.findPW(PassBean);
	    
	    Integer NewPW = userService.NewPW(PassBean);
	    
	    if (find_pw==true) {
	        PassBean.setUser_idx(NewPW);
	    	model.addAttribute("PassBean", PassBean);
	        return "user/findpw_success"; // 비밀번호 재설정 페이지로 이동
	    } else {
	        return "user/findpw_fail"; // 비밀번호 찾기 실패 페이지로 이동
	    }
	}
	
	//새 비밀번호 
	@PostMapping("/find_Newpassword")
	private String find_Newpassword(@Valid @ModelAttribute("PassBean") UserBean PassBean, BindingResult result) {
	    if (result.hasErrors()) {
	        return "user/findpw_success"; // 유효성 검사 실패 시 다시 폼으로 돌아갑니다.
	    }
	    userService.NewPass(PassBean);
	    return "user/newpass_success"; // 비밀번호 변경 성공 페이지로 이동
	}
	
	@PostMapping("/get_inChargeContest")
	public String getInChargeContest(Model model) {
	    // 로그인한 유저의 user_idx 값을 가져옴
	    int userId = loginUserBean.getUser_idx();

	    // 유저가 담당하고 있는 대회 이름을 가져옴
	    String inChargeContest = userService.getInChargeContestByUserId(userId);
	    
	    System.out.println("inChargeContest 값 : " + inChargeContest);
	    
	    // 모델에 대회 이름을 추가하여 JSP에 전달
	    model.addAttribute("inChargeContest", inChargeContest);
	    
	    // 대회 이름으로 해당 대회의 idx 값을 가져옴
	    int inChargeContestIdx = contestService.getIdByUserName(inChargeContest);

	    // 해당 대회의 자원봉사 신청 목록을 가져와서 모델에 추가
	    List<VolunteerBean> volunteerList = userService.getVolunteersByContest(inChargeContestIdx);
	    
	    model.addAttribute("volunteerList", volunteerList);

	    // 해당 대회의 자원봉사 신청 목록을 표시할 JSP로 이동
	    return "user/volunteer_list";
	}	
	//용민이 관계자 페이지
	@PostMapping("/get_name")
    @ResponseBody
    public String getUserName(@RequestParam("user_id") int user_id) throws UnsupportedEncodingException {
        // UserService를 통해 유저 이름을 가져옴
        String userName = userService.getUserNameById(user_id);
        String userName2 = URLEncoder.encode(userName, "UTF-8");
        System.out.println(user_id);
        return userName2;
    }
	
	

}
	

