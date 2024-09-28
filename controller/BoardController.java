package kr.co.softsoldesk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.service.BoardService;
import kr.co.softsoldesk.service.ContestService;
import kr.co.softsoldesk.service.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private UserService userService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/community")
	private String community(@RequestParam("board_info_idx") int board_info_idx, Model model,
							@RequestParam(value = "page", defaultValue = "1")int page) {

		//게시판 이름 조회
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		//게시판 목록
		List<ContentBean> contentList = boardService.getContentList(board_info_idx,page);

		//조회수 Top10
		List<ContentBean> TopList = boardService.getTopPosts(board_info_idx);
				
		model.addAttribute("boardInfoName", boardInfoName);
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("contentList",contentList);
		model.addAttribute("loginUserBean", loginUserBean);
		model.addAttribute("TopList",TopList);
				
		PageBean pageBean = boardService.getContentCnt(board_info_idx, page);
		model.addAttribute("pageBean",pageBean);
				
		return "board/community";
	}

	@GetMapping("/community_write")
	private String community_write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
			@RequestParam("board_info_idx") int board_info_idx, Model model) {

		writeContentBean.setUser_idx(loginUserBean.getUser_idx());
		
		// 로그인 사용자 이름 조회
		String user_name = boardService.findName(loginUserBean.getUser_idx());
		model.addAttribute("user_name", user_name);
		model.addAttribute("board_info_idx", board_info_idx);

		return "board/community_write";
	}

	@PostMapping("/community_write_pro")
	public String community_write_pro(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
			@RequestParam("board_info_idx") int board_info_idx, Model model) {
		
		// 게시판에 내용 추가
		boardService.addContent(writeContentBean);
		model.addAttribute("board_info_idx", board_info_idx);
		
		// 성공적으로 추가된 후 리다이렉션
		return "board/write_success";		
	}
	
	@GetMapping("/read")
	private String read(@RequestParam("post_id") int post_id, @RequestParam("board_info_idx") int board_info_idx, Model model,CommentBean commentBean) {
		
		boardService.incrementViewCount(post_id);
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		
		ContentBean readContentBean = boardService.getContentInfo(post_id);
	    List<CommentBean> commentList = boardService.getCommentList(post_id); // 댓글 목록 추가
	    int loggedInUserId = loginUserBean.getUser_idx();
	    List<CommentBean> replycommentList = boardService.getCommentReplies(post_id);
	    System.out.println("11111111111111111" + readContentBean.getUser_idx());
	    model.addAttribute("readContentBean", readContentBean);	    
	    model.addAttribute("post_id", post_id);
	    model.addAttribute("board_info_idx", board_info_idx);    
	    model.addAttribute("loggedInUserId", loggedInUserId);	    
	    model.addAttribute("commentList", commentList); // 댓글 목록 추가
	    model.addAttribute("replycommentList",replycommentList);
	    model.addAttribute("boardInfoName",boardInfoName);
	    
	    CommentBean tempBean = new CommentBean();
	    tempBean.setUser_idx(loggedInUserId);  
	    tempBean.setPost_id(post_id);
	    tempBean.setBoard_info_idx(board_info_idx);       
	    model.addAttribute("addComment", tempBean);
	    
	    model.addAttribute("replyCommentBean", new CommentBean());
	    
	    return "board/read";
	}

	@PostMapping("/addComment")
	private String addComment(@Valid @ModelAttribute("addComment")CommentBean commentBean, BindingResult result, Model model) {
	   		
		// 로그인 성공 시
		if (loginUserBean.isUserLogin() == true) {
			
			// 유효성 검사 실패 시
		    if (result.hasErrors()) {
		    	
		        return "redirect:/board/read?post_id=" + commentBean.getPost_id() + "&board_info_idx=" + commentBean.getBoard_info_idx(); // 댓글 작성 폼으로 다시 이동
		    }	
				boardService.addComment(commentBean);
   
		    return "redirect:/board/read?post_id=" + commentBean.getPost_id() + "&board_info_idx=" + commentBean.getBoard_info_idx();
		// 로그인 실패 시
		} else {
			
			return "board/login_fail";
		}	
		
	
	}	
	@PostMapping("/addReplyComment")
	private String addReplyComment(@Valid @ModelAttribute("replyCommentBean")CommentBean commentBean, BindingResult result , Model model) {
	
		
		// 로그인 성공 시
		if (loginUserBean.isUserLogin() == true) {
			
			// 유효성 검사 실패 시
		    if (result.hasErrors()) {
		    	
		        return "redirect:/board/read?post_id=" + commentBean.getPost_id() + "&board_info_idx=" + commentBean.getBoard_info_idx(); // 댓글 작성 폼으로 다시 이동
		    }
		    // 대댓글 저장
		    int parentCommentId = commentBean.getParent_comment_id();
		    
		    // replyComment 객체에 필요한 속성 설정
		    commentBean.setParent_comment_id(parentCommentId);  // 부모 댓글 ID 설정
		    
		    // 대댓글 저장 서비스 호출 (서비스 클래스에서 저장 로직 구현)
		    boardService.addReplyComment(commentBean); 
		    return "redirect:/board/read?post_id=" + commentBean.getPost_id() + "&board_info_idx=" + commentBean.getBoard_info_idx();
		
		 // 로그인 실패 시
		} else {
			
			return "board/login_fail";
		}		
	}
	
	
	@GetMapping("/modify")
	private String modify(@RequestParam("post_id")int post_id,
						  @RequestParam("board_info_idx")int board_info_idx, Model model) {
		
		ContentBean modifyContentBean = boardService.getContentInfo(post_id); 
		model.addAttribute("modifyContentBean", modifyContentBean);
		
		model.addAttribute("post_id",post_id);
		model.addAttribute("board_info_idx",board_info_idx);
	
		return "board/modify";
	}
	
	@PostMapping("/modify_pro")
	private String modify_pro(@ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
							  @RequestParam("post_id")int post_id,
							  @RequestParam("board_info_idx")int board_info_idx,
							  Model model) {
			
		boardService.modifyContent(modifyContentBean);		
		model.addAttribute("modifyContentBean",modifyContentBean);
		model.addAttribute("post_id",post_id);
		model.addAttribute("board_info_idx",board_info_idx);
		
		
		return "board/modify_succcess";
	}
	
	@GetMapping("/delete")
	private String delete(@RequestParam("post_id")int post_id,
						  @RequestParam("board_info_idx")int board_info_idx,
						  Model model) {
		
		boardService.deleteContentInfo(post_id);
		model.addAttribute("board_info_idx",board_info_idx);
		
		
		return "board/delete";
	}
	@GetMapping("/deletecomment")
    private String deletecomment(@RequestParam("post_id") int post_id,
                                 @RequestParam("board_info_idx") int board_info_idx,
                                 @RequestParam("comments_id") int comments_id,
                                 Model model) {
		
        boardService.deleteComment(post_id, comments_id);
       
        model.addAttribute("post_id", post_id);
        model.addAttribute("board_info_idx", board_info_idx);
        model.addAttribute("comments_id",comments_id);

        return "board/delete_comment";
    }
	@GetMapping("/deleteReplyComment")
	private String deleteReplyComment(@RequestParam("post_id")int post_id,@RequestParam("board_info_idx")int board_info_idx,@RequestParam("comments_id")int comments_id, Model model) {
		
		boardService.deleteReplyCommet(comments_id);
		
		model.addAttribute("post_id", post_id);
        model.addAttribute("board_info_idx", board_info_idx);

		return "board/deleteReplyComment";
	}
	@GetMapping("/contestInfo")
	private String contestInfo() {
	
		return "board/contestInfo";
	}

	@GetMapping("/contestSend")
	public String sendToContestView(
	    @RequestParam("contestName") String contestName, 
	    @RequestParam("startDate") String startDate,
	    @RequestParam("endDate") String endDate,
	    @RequestParam("place") String place,
	    @RequestParam("organizer") String organizer,
	    @RequestParam("description") String description,
	    @RequestParam("imagePath") String imagePath,
	    RedirectAttributes redirectAttributes) {

	    // 파라미터를 리다이렉트 속성으로 추가
	    redirectAttributes.addAttribute("contestName", contestName);
	    redirectAttributes.addAttribute("startDate", startDate);
	    redirectAttributes.addAttribute("endDate", endDate);
	    redirectAttributes.addAttribute("place", place);
	    redirectAttributes.addAttribute("organizer", organizer);
	    redirectAttributes.addAttribute("description", description);
	    redirectAttributes.addAttribute("imagePath", imagePath);

	    // 리다이렉트
	    return "board/contestView";
	}
	
	
	@GetMapping("/contestSend_to_volunteer")
	@ResponseBody
	public List<ContestBean> sendContestsToVolunteer() {
		
	    List<ContestBean> allContests = contestService.findContestsWithVolunteers();

	    
	    return allContests;
	}

	@GetMapping("/contestWriteForward")
	public String contestWriteForward() {

		return "board/contest_write";
	}
	
	@GetMapping("/volunteer")
	public String forwardToVolunteer(){
		return "board/volunteer";
	}
	
	@PostMapping("/updateLike")
	@ResponseBody
	public Map<String, Object> updateLike(@RequestParam("post_id") int post_id, @RequestParam("action") String action) {
	    
		Map<String, Object> result = new HashMap<>();
	    try {
	        if ("like".equals(action)) {
	            boardService.incrementLike(post_id);
	        } else if ("dislike".equals(action)) {
	            boardService.incrementDislike(post_id);
	        }
	        int like_count = boardService.getLikeCount(post_id);
	        int dislike_count = boardService.getDislikeCount(post_id);	        
	        result.put("success", true);
	        result.put("like_count", like_count);
	        result.put("dislike_count", dislike_count);
	    } catch (Exception e) {
	        result.put("success", false);
	    }
	    return result;
	}
	
	@GetMapping("/volunteer_viewMore")
	public String forwardToVolunteerInfo(@RequestParam("contestName") String contestName, Model model) {
	    ContestBean contestInfo = contestService.getContestByName(contestName);
	    model.addAttribute("contestInfo", contestInfo);
	    return "board/volunteerViewMore";
	}
	
	@GetMapping("/volunteerList")
	public String volunteerList(Model model,
								@RequestParam("user_idx") int user_idx){
		
		List<VolunteerBean> myVolunteerList = userService.getMyVolunteerList(user_idx);
		
		model.addAttribute("myVolunteerList", myVolunteerList);
		model.addAttribute("user_idx", user_idx);
		
		return "board/volunteerList";
	}
}