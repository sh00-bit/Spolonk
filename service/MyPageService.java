package kr.co.softsoldesk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.TeamPostsBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.dao.MyPageDAO;

@Service
public class MyPageService {

	@Autowired
	MyPageDAO mypageDAO;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	// 팀/팀원 팀리스트에 내가 속한 팀 모두 추출
	public UserBean readUserInfo(int user_idx) {
		
		return mypageDAO.readUserInfo(user_idx);

	}

	//내 팀 목록에 팀원리스트 뽑
    public List<UserBean> readMyTeam(int user_idx) {
        List<UserBean> teamList = mypageDAO.readMyTeam(user_idx);
        return teamList;
    }
    
	// mypage에 내가 팀장인 팀들과 팀원추철
    public List<UserBean> readMyTeam2(int user_idx) {
    List<UserBean> teamList = mypageDAO.readMyTeam2(user_idx);
    return teamList;
    }
    
    public List<UserBean> readMyTeamName(int user_idx) {
    List<UserBean> teamList2 = mypageDAO.readMyTeamName(user_idx);
    
    return teamList2;
    }
    
    //팀장_idx 찾기
	public UserBean readTeamLeaderIdx(int user_idx) {
		//System.out.println("서비스 : " +  user_idx);
		return mypageDAO.readTeamLeaderIdx(user_idx);
	}
	
	//팀 추가
	public void addTeam(EventBean team) {
		//종목이름을 종목번호로 변환
		String eventNumber = eventMap.get(team.getEventName());
		team.setEventNumber(eventNumber);
		//System.out.println("서비스 : " + team.getEventNumber());
		mypageDAO.addTeam(team);
	}   
	
	//#2 종목이름을 종목번호로 변환하는 로직
	private static final Map<String, String> eventMap = new HashMap<>();
	
	   static {
		      eventMap.put("サッカー", "1000");
		      eventMap.put("フットサル", "1001");
		      eventMap.put("野球", "1002");
		      eventMap.put("バレーボール", "1003");
		   }
	
	//내 팀원 구인 글 작성 저장	
	public void add_teammember_pro(TeamPostsBean writeTeamPostBean) {
		
		mypageDAO.add_teammember_pro(writeTeamPostBean);
		System.out.println(writeTeamPostBean.getCategory());
		
	}
	//내 팀 구인 글 이름찾기
	public String findName(int user_idx) {
		
		return mypageDAO.findName(user_idx);
	}
	
	//add_teammember에서 팀선택시 종목,등급 자동추출 리스트로직
	public List<TeamPostsBean> getTeamsByUserIdx(int user_idx) {
	    List<TeamPostsBean> teamList3 = mypageDAO.getTeamsByUserIdx(user_idx);
		return teamList3;
	}
	//TeamOrTeamMember에서 팀 구인 게시글 카드리스트 
	public List<TeamPostsBean> getTeamPostList(int user_idx) {
		List<TeamPostsBean> postList = mypageDAO.getTeamPostList(user_idx);
		return postList;
	}
	//팀원 구인글 보고 신청하는 user 쿼리문
	public void applyTeamMember(TeamPostsBean tpb) {
		
		mypageDAO.applyTeamMember(tpb);
	}
	//팀원 신청 승인 거절
	public List<UserBean> ApprovalList(int user_idx) {
		List<UserBean> ApprovalList = mypageDAO.ApprovalList(user_idx);
		return ApprovalList;
	}
	
	//승인버튼 눌렀을때 승인
	public void TeamMemberApprovalOk(UserBean eb) {
		 mypageDAO.TeamMemberApprovalOk(eb);
	}
	//거절버튼 눌렀을때 거절
	public void TeamMemberApprovalNo(UserBean eb2) {
		mypageDAO.TeamMemberApprovalNo(eb2);
	}
	
	
}
