package kr.co.softsoldesk.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import kr.co.softsoldesk.Validator.UserValidator;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.TeamPostsBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.mapper.UserMapper;
import kr.co.softsoldesk.service.BoardService;
import kr.co.softsoldesk.service.MyPageService;
import kr.co.softsoldesk.service.UserService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@Autowired
	MyPageService mypageService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	@Qualifier("loginUserBean") // 명확하게 어떤 빈을 사용할지 지정
	private UserBean loginUserBean; // 로그인한 사용자 정보

	@Autowired
	@Qualifier("loginUserBean") // 명확하게 어떤 빈을 사용할지 지정
	private UserBean deleteUserBean;
	
	@Autowired
	public MyPageController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@InitBinder("user")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	/*
	 * @Resource(name = "loginUserBean") UserBean loginUserBean;
	 */

	// 마이페이지
	@GetMapping("/mypage")
	public String mypage(@RequestParam("user_idx") int user_idx, Model model) {
		// USER_IDX값 끌고오기
		UserBean readUserBean = mypageService.readUserInfo(user_idx);
		model.addAttribute("user_idx", user_idx);
		model.addAttribute("readUserBean", readUserBean);

		// 해당 USER_IDX값이 속한 팀 보여주기
		List<UserBean> teamList2 = mypageService.readMyTeamName(user_idx);
		// System.out.println(teamList2.get(0).getEventName());
		model.addAttribute("teamList2", teamList2);

		// 팀장 IDX일 때 :내팀관리: 탭 보이게하기
		// 팀장 idx 끌고옴
		UserBean teamLeaderIdx = mypageService.readTeamLeaderIdx(user_idx);
		// 팀장인지 확인하고
		boolean isTeamLeader;

		if (teamLeaderIdx != null) {
			isTeamLeader = (loginUserBean.getUser_idx() == teamLeaderIdx.getUser_idx());
			model.addAttribute("conditionForMyTeamManagement", isTeamLeader);
		}
		
		//유진 
		//int user_idx = loginUserBean.getUser_idx(); // 로그인한 사용자의 user_idx 가져오기
		 String user_name = userService.getUserNameByIdx(user_idx); String
		 introduction = userService.getIntroductionByIdx(user_idx); //추가
		 
		 // 사용자 게시물 목록을 가져옴 
		 List<ContentBean> myPostList = userService.MyContentList(user_idx); 
		 List<VolunteerBean> myVolunteerList = userService.getMyVolunteerList(user_idx);
		 
		 model.addAttribute("user_idx", user_idx); // user_idx를 모델에 추가
		 model.addAttribute("loginUserBean", loginUserBean);
		 model.addAttribute("user_name", user_name); model.addAttribute("myPostList",myPostList); 
		 model.addAttribute("myVolunteerList",myVolunteerList);
		 model.addAttribute("introduction", introduction); //추가
		 
		 // 사용자의 프로필 이미지 경로를 가져옴 
		 String img = loginUserBean.getProfileImagePath(); 
		 if (img == null || img.isEmpty()) 
		 { 
			 img = "default_image.jpg"; // 기본 이미지 설정 (예: 기본 프로필 이미지) 
		 }
		 
		 model.addAttribute("img", img);
		 
		//자기소개
		 model.addAttribute("introduction", loginUserBean.getIntroduction());
		 	
		return "myPage/mypage";
	}
	

	@PostMapping("/mypage")
	public String mypage(Model model, HttpServletRequest request) {
		
		String introMessage = request.getParameter("introEdit");
		
		userService.updateIntroduction(loginUserBean.getUser_idx(), introMessage);
	
		int user_idx = loginUserBean.getUser_idx();
				
		model.addAttribute("message", "수정되었습니다");
		model.addAttribute("user_idx ", user_idx);

		System.out.println("찍히나2222222222222222 ");
		
		return "myPage/mypage_introEdit_success";
	}
	
	
	// 나의 팀
	@GetMapping("/myteam")
	public String myteam(@RequestParam("user_idx") int user_idx, Model model) {
		// mypage에 내가 팀장인 팀들과 팀원추철
		List<UserBean> teamList = mypageService.readMyTeam2(user_idx);
		model.addAttribute("teamList", teamList);
		// 팀필터링 로직
		ArrayList<String> teamArrayList = new ArrayList<>();
		Iterator<UserBean> ub = teamList.iterator();
		while(ub.hasNext()) {
			String user = ub.next().getTeamName();
			if(!teamArrayList.contains(user)) {
				teamArrayList.add(user);
			}
		}
		
		model.addAttribute("teamArrayList", teamArrayList);
		return "myPage/myteam";
	}

	// 마이페이지
	@GetMapping("/TeamOrTeamMemberAdd")
	public String TeamOrTeamMemberAdd(@RequestParam("user_idx") int user_idx, Model model) {
		// 팀/팀원 팀리스트에 내가 속한 팀 모두 추출
		List<UserBean> teamList = mypageService.readMyTeam(user_idx);
		model.addAttribute("teamList", teamList);
		
		// 팀장 IDX일 때 :내팀관리: 탭 보이게하기
		// 팀장 idx 끌고옴
		UserBean teamLeaderIdx = mypageService.readTeamLeaderIdx(user_idx);
		// 팀장인지 확인하고
		boolean isTeamLeader;
		if (teamLeaderIdx != null) {
			isTeamLeader = (loginUserBean.getUser_idx() == teamLeaderIdx.getUser_idx());
			model.addAttribute("conditionForMyTeamManagement", isTeamLeader);
		}
		
		//팀원 구인 게시글 postlist
		List<TeamPostsBean> postList = mypageService.getTeamPostList(user_idx);
		model.addAttribute("postList", postList);
		/* 잘 찍히는지 리스트 확인
		 * for (TeamPostsBean post : postList) { System.out.println("Title: " +
		 * post.getTitle()); System.out.println("Team Name: " + post.getTeamName());
		 * System.out.println("Event Name: " + post.getEventName());
		 * System.out.println("User_idx: " + post.getUser_idx());
		 * System.out.println("Category: " + post.getCategory()); }
		 */
		
		return "myPage/TeamOrTeamMemberAdd";
	}

	// 팀 등록
	@GetMapping("/add_team")
	public String add_team(@RequestParam("user_idx") int user_idx, @ModelAttribute("team") EventBean team,
			Model model) {
		model.addAttribute("user_idx", user_idx);
		return "myPage/add_team";
	}

	// 팀 등록#2
	@PostMapping("/addTeam_pro")
	public String addTeam(@RequestParam("user_idx") int user_idx, @ModelAttribute("team") EventBean team) {

		mypageService.addTeam(team);
		// System.out.println("컨트롤러 : " + team.getEventName());

		return "myPage/add_team_success";
	}

	@GetMapping("/add_teammember")
	private String add_teammember(@ModelAttribute("writeTeamPostBean") TeamPostsBean writeTeamPostBean, Model model,
			@RequestParam("user_idx") int user_idx) {

		// 로그인된 사용자의 이름을 가져옵니다.
		String user_name = mypageService.findName(loginUserBean.getUser_idx());
		model.addAttribute("user_name", user_name);

		// user_idx를 모델에 추가
		model.addAttribute("user_idx", user_idx);
		
		// 팀원 구직 게시글 카드 리스트
		List<TeamPostsBean> teamList3 = mypageService.getTeamsByUserIdx(user_idx);
		model.addAttribute("teamList3", teamList3);

		return "myPage/add_teammember";
	}

	@PostMapping("/add_teammember_pro")
	public String add_teammember_pro(@ModelAttribute("writeTeamPostBean") TeamPostsBean writeTeamPostBean, Model model,
			@RequestParam("user_idx") int user_idx) {

		//System.out.println(writeTeamPostBean.getTeamName() + "teamName");
		//System.out.println(writeTeamPostBean.getTeam_id() + "team_id");
		
		// 게시판에 내용 추가
		mypageService.add_teammember_pro(writeTeamPostBean);

		// user_idx를 모델에 추가
		model.addAttribute("user_idx", user_idx);
		//System.out.println("Category: " + writeTeamPostBean.getCategory());

		return "myPage/add_teammember_success";
	}
	
	@GetMapping("/read_add_postteammember")
	public String read_add_postteammember(@RequestParam("teampost_id") int teampost_id, Model model) {
		
		int user_idx = loginUserBean.getUser_idx();
		List<TeamPostsBean> postList = mypageService.getTeamPostList(user_idx);
		Iterator<TeamPostsBean> po = postList.iterator();
		TeamPostsBean expo = new TeamPostsBean();
		while(po.hasNext()) {
			 expo = po.next();
			if(expo.getTeampost_id() == teampost_id) {
				break;
			}
		}
		model.addAttribute("postList", expo);
		
		return "myPage/read_add_postteammember";
	}
	//팀원 신청 
	@GetMapping("/applyTeamMember")
	public String applyTeamMember(@RequestParam("team_id") int team_id, @RequestParam("user_idx") int user_idx,Model model) {
		
		TeamPostsBean tpb = new TeamPostsBean();
		tpb.setTeam_id(team_id);
		tpb.setUser_idx(user_idx);
		
		//System.out.println(tpb.getTeam_id());
		//System.out.println(tpb.getUser_idx());
		
		mypageService.applyTeamMember(tpb);
		
		return "myPage/Team_Member_Application_success";
	}
	
	//팀장 전용 : 팀원 관리 (승인/거절)페이지
	@GetMapping("/TeamMemberApprovalService")
	public String TeamMemberApprovalService(@RequestParam("user_idx") int user_idx, Model model) {
		
		List<UserBean> ApprovalList = mypageService.ApprovalList(user_idx);
		model.addAttribute("ApprovalList", ApprovalList);
		
		/*
		 * for (UserBean user : ApprovalList) { System.out.println("user_ idx : " +
		 * user.getUser_idx()); System.out.println("team_id : " + user.getTeam_id());
		 * 
		 * System.out.println("User ID: " + user.getUser_id());
		 * System.out.println("User Name: " + user.getUser_name());
		 * System.out.println("Team Name: " + user.getTeamName());
		 * System.out.println("Phone: " + user.getUser_Phone());
		 * System.out.println("Email: " + user.getUser_email()); }
		 */
			
		//필터링
		ArrayList<String> ApprovalArrayList = new ArrayList<>();
		Iterator<UserBean> ub = ApprovalList.iterator();
		while(ub.hasNext()) {
			String user = ub.next().getTeamName();
			if(!ApprovalArrayList.contains(user)) {
				ApprovalArrayList.add(user);
			}
		}
		
		model.addAttribute("ApprovalArrayList", ApprovalArrayList);
		
		// 잘 찍히는지 리스트 확인
		/*
		 * for (UserBean post : ApprovalList ) { System.out.println("팀 이름 : " +
		 * post.getTeamName()); System.out.println("유저Name: " + post.getUser_name());
		 * System.out.println("유저 id: " + post.getUser_id());
		 * System.out.println("User 폰 : " + post.getUser_Phone());
		 * System.out.println("유저 이메일 : " + post.getUser_email()); }
		 */
		  
		return "myPage/TeamMemberApprovalService";
	}
	
	//승인 버튼 눌렀을때 승인해서 update
	@GetMapping("/TeamMemberApprovalOk")
	public String TeamMemberApprovalOk(@RequestParam("user_idx") int user_idx, @RequestParam("team_id") int team_id, Model model) {
		
		UserBean eb = new UserBean();
		eb.setUser_idx(user_idx);
		eb.setTeam_id(team_id);
		
		//System.out.println("제발뜨게해주세요" +  eb.getTeam_id());
		//System.out.println(eb.getUser_idx());
		
		mypageService.TeamMemberApprovalOk(eb);
		
		//mypageService.TeamMemberApprovalOk(team_id,user_idx);
		//System.out.println("뭐가 문제니 " + team_id);
		//System.out.println(user_idx);
		
		return "myPage/TeamMemberApprovalOk_success";
	}
	
	//거절 버튼 눌렀을때 거절 update
	@GetMapping("/TeamMemberApprovalNo")
	public String TeamMemberApprovalNo(@RequestParam("user_idx") int user_idx, @RequestParam("team_id") int team_id, Model model) {
		
		UserBean eb2 = new UserBean();
		eb2.setUser_idx(user_idx);
		eb2.setTeam_id(team_id);
		
		
	
		mypageService.TeamMemberApprovalNo(eb2);
		
		return "myPage/TeamMemberApprovalNo_No";
	}
	
	// 회원수정 페이지 로드
		@GetMapping("/modify")
		public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
			userService.getModifyUserInfo(modifyUserBean);
			return "myPage/modify";
		}

		// 회원수정
		@PostMapping("/modify_pro")
		public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {

			if (result.hasErrors()) {

				return "myPage/modify";
			}

			userService.modifyUserInfo(modifyUserBean);

			return "myPage/modify_success";
		}

		// 회원 탈퇴 페이지 로드
		@GetMapping("/delete_member")
		public String delete_member(@RequestParam("user_idx") int user_idx, Model model) {

			model.addAttribute("loginUserBean", loginUserBean);
			return "myPage/delete_member";
		}

		@PostMapping("/delete_member_pro")
		public String delete_member_pro(@ModelAttribute("loginUserBean") UserBean userBean, BindingResult result,
				@RequestParam("user_idx") int user_idx, Model model) {

			// 비밀번호 검증
			userValidator.validate(userBean, result);

			if (result.hasErrors()) {
				model.addAttribute("user_idx", user_idx);
				return "myPage/delete_member";
			}
			// 비밀번호 일치 여부 확인
			if (userService.checkPassword(user_idx, userBean.getUser_pass1())) {
				// userBean의 user_idx를 확인하고 설정
				userBean.setUser_idx(user_idx);

				// 로그 추가
				System.out.println("Attempting to delete user with ID: " + userBean.getUser_idx());

				boolean deleteSuccess = userService.deleteMember(userBean); // userService에서 삭제 로직 수행
				if (deleteSuccess) {
					model.addAttribute("user_idx", user_idx);
					model.addAttribute("deleteUserBean", userBean); // userBean을 넘겨줌
					return "myPage/delete_success";
				} else {
					model.addAttribute("errorMessage", "회원 탈퇴에 실패했습니다.");
					model.addAttribute("user_idx", user_idx);
					return "myPage/delete_fail";
				}
			} else {
				model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
				model.addAttribute("user_idx", user_idx);
				return "myPage/delete_fail";
			}
		}


		// 프로필 이미지 수정
		@Autowired
		private UserMapper userMapper;

		@GetMapping("/updateProfile")
		private String updateProfile(@RequestParam("user_idx") int user_idx, Model model) {

			String img = userMapper.getProfileImage(user_idx);
			System.out.println("controller 컨트롤ㄹ런오란도라ㅑ도교:" + img);

			model.addAttribute("img", img);

			return "myPage/updateProfile";
		}
		
		  
		//여기까지 ㄱㅊ
		
		
		
		@GetMapping("/add_userInfo_edu")
	       public String addUserInfoEdu() {
	           // 뷰의 이름을 반환합니다. 예: add_userInfo_edu.jsp
	           return "add_userInfo_edu";
	       }
	     
	   
	       @GetMapping("/mypage/posts") 
	       public String getUserPosts(Model model,
	                            @RequestParam("user_idx") int userIdx,
	                            @RequestParam(value = "page", defaultValue = "1") int page)  { 
	      
	       List<ContentBean> userPosts = boardService.getUserContentList(userIdx);
	       
	       // 로그 출력 
	       System.out.println("User Posts: " + userPosts);
	       
	          model.addAttribute("myPostList", userPosts); 
	          model.addAttribute("currentPage", page); 
	          model.addAttribute("user_idx", userIdx); 
	          
	       return "myPage/mypage"; 
	      }
	      
	       @GetMapping("/volunteerList")
	       public String getMyVolunteerList(@RequestParam("user_idx") int user_idx, Model model) {
	           // user_idx로 봉사활동 목록을 가져오는 로직 추가
	           List<VolunteerBean> myVolunteerList = userService.getMyVolunteerList(user_idx);
	           model.addAttribute("myVolunteerList", myVolunteerList);
	           
	           return "myPage/volunteerList"; // JSP 파일 경로 (ex: /WEB-INF/views/myPage/volunteerList.jsp)
	       }
	       
	       @GetMapping("/MyBoardList")
	       public String getMyBoardList(@RequestParam("user_idx") int user_idx, @RequestParam("page") int page, Model model) {
	          
	          List<ContentBean> myPostList = userService.getUserContentList(user_idx);
	          System.out.println(myPostList.get(0).getTitle());
	          model.addAttribute("myPostList", myPostList);
	          
	          return "myPage/MyBoardList";
	       }

		  
		  
	}


