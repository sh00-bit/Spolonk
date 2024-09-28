package kr.co.softsoldesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.service.AdminService;
import kr.co.softsoldesk.service.BoardService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	BoardService boardService;
	
	//관계자페이지
	@GetMapping("/forwardToAdminPage")
	private String toAdminPage() {
		
		return "admin/admin_page";
	}
	
	//admin 메인페이지
	@GetMapping("/adminpage")
	public String adminpage(Model model) {
		
		int userCount = adminService.usercount();  // 서비스에서 유저 카운트를 가져옴
        model.addAttribute("userCount", userCount); 

        int teamCount = adminService.teamcount();
        model.addAttribute("teamCount", teamCount);
        
        int contestCount = adminService.contestcount();
        model.addAttribute("contestCount", contestCount);
        
        int volunteerCount = adminService.volunteercount();
        model.addAttribute("volunteerCount", volunteerCount);
        		
        
		
		return "/admin/adminpage";
	}
		
	//admin_user 리스트 추출
	@GetMapping("/user_datatable")
	public String user_datatable(Model model) {
		List<UserBean> userList = adminService.UserRead();
		model.addAttribute("userList", userList);
		return "/admin/user_datatable";
	}
	
	//admin_user 수정대상자 불러오기
	@GetMapping("/user_modifytable")
	public String user_modifytable(@RequestParam("user_idx") int user_idx,
			@ModelAttribute("AdminUsermodify") UserBean AdminUsermodify, Model model) {
		
		AdminUsermodify.setUser_idx(user_idx);
		adminService.getUserById(AdminUsermodify); 
		model.addAttribute("user_idx", user_idx);
		
		return "/admin/user_modifytable";
	}
	//admin_user 수정내역 DB저장
	@PostMapping("/user_modifytable_pro")
	public String submitUserInfo(@ModelAttribute("AdminUsermodify") UserBean AdminUsermodify,
			@RequestParam("user_idx") int user_idx) {	
	
	AdminUsermodify.setUser_idx(user_idx);
	adminService.user_modifyInfo(AdminUsermodify);

	return "/admin/user_modifytable_pro_success"; 
	}
	
	//admin_user 유저 삭제
	@GetMapping("/user_deletetable")
	public String user_deletetable(@RequestParam("user_idx") int user_idx) {
		System.out.println("삭제 전");
		adminService.user_deletetable(user_idx);
		System.out.println("삭제 후");
		
		return "/admin/user_deletetable_success";
	}
	//회원 추가 화면 띄우기
	@GetMapping("/user_addtable")
	public String showUserAddForm(Model model) {
	    model.addAttribute("AdminUseradd", new UserBean());
	    return "/admin/user_addtable_pro";  // 사용자 추가 폼 페이지로 이동
	}
	
	//회원 추가
	@PostMapping("/user_addtable_pro")
	public String user_addtable(@ModelAttribute("AdminUseradd") UserBean AdminUseradd, Model model) {
	    model.addAttribute("AdminUseradd", new UserBean());  // AdminUseradd라는 이름으로 UserBean 객체를 전달
		adminService.user_addtable(AdminUseradd);
		
		return "/admin/user_addtable_success";
	}
	//admin_team 리스트 추출
	@GetMapping("/team_datatable")
	public String team_datatable(Model model) {
		List<EventBean> teamList = adminService.teamRead();
		model.addAttribute("teamList", teamList);
	      
	      
	    return "/admin/team_datatable";
	}

	//admin_team 팀 삭제
	@GetMapping("/team_deletetable")
	public String team_deletetable(@RequestParam("team_id") int team_id) {
	      
	    adminService.team_deletetable(team_id);
	      
	    return "/admin/team_deletetable_success";
	}
	
	//대회 데이터테이블 리스트
	@GetMapping("/contest_datatable")
	public String contest_datatable(Model model) {
		List<ContestBean> contestList = adminService.contestRead();
		model.addAttribute("contestList", contestList);
		return "/admin/contest_datatable";
	}
	
	   //post_datatable post 리스트 추출
	   @GetMapping("/post_datatable")
	   public String post_datatable(Model model) {
	      
	      List<ContentBean> post_list = adminService.PostRead();
	      
	      model.addAttribute("post_list",post_list);
	      
	      return "/admin/post_datatable";
	   }
	   //게시판삭제
	   @GetMapping("/post_deletetable")
	   public String post_datatable_success(@RequestParam("post_id") int post_id) {
	      
	      adminService.post_deletetable(post_id);
	      
	      return "/admin/post_datatable_success";
	   }
	   //게시판 수정
	   @GetMapping("/post_modify")
	   public String post_modify(@RequestParam("post_id")int post_id, Model model) {      
	      
	      ContentBean postModifyContentBean = boardService.getContentInfo(post_id);
	      
	      model.addAttribute("postModifyContentBean",postModifyContentBean);
	      model.addAttribute("post_id",post_id);
	      
	      return "/admin/post_modify";
	   }
	   //게시판 수정
	   @PostMapping("/post_modify_pro")
	   public String post_modify_pro(@ModelAttribute("postModifyContentBean")ContentBean postModifyContentBean,
	                         Model model) {
	      
	      adminService.postModifyContent(postModifyContentBean);
	      model.addAttribute("postModifyContentBean",postModifyContentBean);
	      
	      
	      return "/admin/post_modify_success";
	   }
	   //댓글 리스트 불러오기
	   @GetMapping("/comments_datatable")
	   public String comments_datatable(Model model) {
	      List<CommentBean> comments_list = adminService.commentsRead();
	      model.addAttribute("comments_list",comments_list);
	      
	      return "/admin/comments_datatable";
	   }
	
	//자원봉사 리스트 끌고오기
	   @GetMapping("/volunteer_datatable")
	   public String volunteer_datatable(Model model) {
		   List<VolunteerBean> volunteer_list = adminService.volunteerRead();
		   model.addAttribute("volunteer_list", volunteer_list);
		   
		   return "/admin/volunteer_datatable";
		   
	   }
	   //댓글 삭제
	   @GetMapping("/comments_deletetable")
	   public String comments_deletetable(@RequestParam("comments_id") int comments_id) {
	      
	      adminService.comments_deletetable(comments_id);
	      
	      return "/admin/comments_deletetable_success";
	   }
	   //대회 삭제
	   @GetMapping("/contest_deletetable")
	   public String contest_deletetable(@RequestParam("contest_id") int contest_id) {
	      
	      adminService.contest_deletetable(contest_id);
	      
	      return "/admin/contest_deletetable_success";
	   }
	   //관계자 권한 주기
	   @GetMapping("/authority_pro")
	   public String authority(@RequestParam("user_idx") int user_idx) {
		   
		   adminService.authority_pro(user_idx);
		   
		   return "/admin/authority_success";
	   }
	  
	   //권한 주기 위한 admin_user 리스트 추출
		@GetMapping("/authority")
		public String authority(Model model) {
			List<UserBean> userList = adminService.UserRead();
			model.addAttribute("userList", userList);
			return "/admin/authority";
		}
		
		//메인 화면 유저 카운트
		@GetMapping("/usercont")
		public String usercount(Model model) {
			
			int userCount = adminService.usercount();  // 서비스에서 유저 카운트를 가져옴
	        model.addAttribute("userCount", userCount); 
	        
	        
	        System.out.println("1231232113ㄱ2ㄱ" + userCount);
			
			return "/admin/adminpage";
		}
		
		
}