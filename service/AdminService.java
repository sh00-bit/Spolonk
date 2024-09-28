package kr.co.softsoldesk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.dao.AdminDAO;

@Service
public class AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	//유저리스트 풀로 꺼내기
	public List<UserBean> UserRead() {
		List<UserBean> userList = adminDAO.UserRead();
		return userList;
	}
	//회원수정 선택 회원 꺼내기
	public UserBean getUserById(int user_idx) {
		
		return adminDAO.getUserById(user_idx);
	}
	
	//특정 사용자 정보 가져오는 메서드
	public void getUserById(UserBean AdminUsermodify) {
		UserBean tempAdminUsermodify = adminDAO.getUserById(AdminUsermodify.getUser_idx());
		if (tempAdminUsermodify != null) {
			AdminUsermodify.setUser_id(tempAdminUsermodify.getUser_id());
			AdminUsermodify.setUser_name(tempAdminUsermodify.getUser_name());
			AdminUsermodify.setUser_idx(tempAdminUsermodify.getUser_idx());
			AdminUsermodify.setUser_email(tempAdminUsermodify.getUser_email());
			AdminUsermodify.setUser_Phone(tempAdminUsermodify.getUser_Phone());
			AdminUsermodify.setUser_pass1(tempAdminUsermodify.getUser_pass1());
			/* AdminUsermodify.setGender(tempAdminUsermodify.getGender()); */
			AdminUsermodify.setEventName(tempAdminUsermodify.getEventName());
		} else {
			// 예외 처리 또는 기본값 설정
		}
	}
	//회원 수정 DB보내버리기
	public void user_modifyInfo(UserBean adminUsermodify) {
		adminDAO.user_modifyInfo(adminUsermodify);
	}
	//회원 삭제
	public void user_deletetable(int user_idx) {
		adminDAO.user_deletetable(user_idx);
	}
	//회원추가 
	public void user_addtable(UserBean adminUseradd) {
		adminDAO.user_addtable(adminUseradd);
		
	}
	
	//팀 리스트 추출
	   public List<EventBean> teamRead(){
	      return adminDAO.teamRead();	
	   }

	   //팀 삭제
	   public void team_deletetable(int team_id){
	      adminDAO.team_deletetable(team_id);
	   }
	public List<ContestBean> contestRead() {
		List<ContestBean> contestList = adminDAO.contestRead();
		return contestList;
	}
	
    //게시판 리스트 추출
    public List<ContentBean> PostRead() {
       
       return adminDAO.PostRead();
    }
    
    //게시판 삭제
    public void post_deletetable(int post_id) {
       
      adminDAO.post_deletetable(post_id);
   }
    
    //게시판 수정
    public void postModifyContent(ContentBean postModifyContentBean) {
       
       adminDAO.postModifyContent(postModifyContentBean);
    }
    //댓글 리스트 추출
	public List<CommentBean> commentsRead() {
		return adminDAO.commentsRead();
	}
	//자원봉사 리스트 추출'
	public List<VolunteerBean> volunteerRead() {
		return adminDAO.volunteerRead();
	}
	//댓글 삭제
	public void comments_deletetable(int comments_id) {
		adminDAO.comments_deletetable(comments_id);
	}
	public void contest_deletetable(int contest_id) {
		adminDAO.contest_deltetable(contest_id);
	}
	//유저 권한 주기
	public void authority_pro(int user_idx) {
		adminDAO.authority_pro(user_idx);
	}
	//유저 카운트
	public int usercount() {
		return adminDAO.usercount(); 
	}
	public int teamcount() {
		return adminDAO.teamcount();
	}
	public int contestcount() {
		return adminDAO.contestcount();
	}
	public int volunteercount() {
		return adminDAO.volunteercount();
	}
}
