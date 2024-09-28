package kr.co.softsoldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.mapper.AdminMapper;

@Repository
public class AdminDAO {

	@Autowired
	AdminMapper adminMapper;

	//user_datatable 모든 유저 꺼내기
	public List<UserBean> UserRead() {
		List<UserBean> userList = adminMapper.UserRead();
		return userList;
	}
	
	//user_modify 수정할 회원 정보 꺼내기
	public UserBean getUserById(int user_idx) {
		
		return adminMapper.getUserById(user_idx);
	}

	//회원 수정 완료 보내버리기
	public void user_modifyInfo(UserBean adminUsermodify) {
		adminMapper.user_modifyInfo(adminUsermodify);
	}
	//삭제 
	public void user_deletetable(int user_idx) {
		adminMapper.user_deletetable(user_idx);
	}
	//회원 추가
	public void user_addtable(UserBean adminUseradd) {
		adminMapper.user_addtable(adminUseradd);
	}
	
	//팀 리스트 추출
	   public List<EventBean> teamRead(){
	      return adminMapper.teamRead();
	   }

	   //팀 삭제
	   public void team_deletetable(int team_id){
	      adminMapper.team_deletetable(team_id);
	   }

	public List<ContestBean> contestRead() {
		List<ContestBean> contestList = adminMapper.contestRead();
		return contestList;
	}
	//게시판 리스트 추출
    public List<ContentBean> PostRead() {
       
      return adminMapper.PostRead();
    }
    
    //게시판 삭제
    public void post_deletetable(int post_id) {
       
       adminMapper.post_deletetable(post_id);
    }
    
    //게시판 수정
    public void postModifyContent(ContentBean postModifyContentBean) {
       
      adminMapper.postModifyContent(postModifyContentBean);
    }

	public List<CommentBean> commentsRead() {
		return adminMapper.commentsRead();
	}

	public List<VolunteerBean> volunteerRead() {
		return adminMapper.volunteerRead();
	}

	public void comments_deletetable(int comments_id) {
		adminMapper.comments_deletetable(comments_id);
	}

	public void contest_deltetable(int contest_id) {
		adminMapper.contest_deletetable(contest_id);
	}
	//관계자 권한주기
	public void authority_pro(int user_idx) {
		adminMapper.authority_pro(user_idx);
	}
	//유저 카운트
	public int usercount() {
		return adminMapper.usercount();
	}

	public int teamcount() {
		return adminMapper.teamcount();
	}

	public int contestcount() {
		return adminMapper.contestcount();
	}

	public int volunteercount() {
		return adminMapper.volunteercount();
	}
}
