package kr.co.softsoldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.TeamPostsBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.mapper.MyPageMapper;

@Repository
public class MyPageDAO {

	@Autowired
	MyPageMapper mypageMapper;
	
	//마이페이지 user_idx값 끌고오기
	public UserBean readUserInfo(int user_idx) {
		
		return mypageMapper.readUserInfo(user_idx);
	}

	// 팀/팀원 팀리스트에 내가 속한 팀 모두 추출
    public List<UserBean> readMyTeam(int user_idx) {
    List<UserBean> teamList = mypageMapper.readMyTeam(user_idx);
    //System.out.println("DAO - readMyTeam: " + teamList);
    return teamList;
    }
    
	// mypage에 내가 팀장인 팀들과 팀원추철
    public List<UserBean> readMyTeam2(int user_idx) {
    List<UserBean> teamList = mypageMapper.readMyTeam2(user_idx);
    	return teamList;
    }
    
    //마이페이지 내 팀 목록 페에지에 팀원 리스트루력
    public List<UserBean> readMyTeamName(int user_idx) {
    List<UserBean> teamList2 = mypageMapper.readMyTeamName(user_idx);
 
    return teamList2;
    }

	public UserBean readTeamLeaderIdx(int user_idx) {
		//System.out.println("디에이오 : " +  user_idx);
		return mypageMapper.readTeamLeaderIdx(user_idx);
	}

	public void addTeam(EventBean team) {
		//System.out.println("디에오 : " + team.getEventName());
		mypageMapper.addTeam(team);
		
	}
	//내 팀 팀원구인글 작성
	public void add_teammember_pro(TeamPostsBean writeTeamPostBean) {
		
		mypageMapper.add_teammember_pro(writeTeamPostBean);
	}
	
	//내 팀원 구인글 이름 찾기
	public String findName(int user_idx) {
		return mypageMapper.findName(user_idx);
	}
	
	//add_teammember에서 팀선택시 종목,등급 자동추출 리스트로직
	public List<TeamPostsBean> getTeamsByUserIdx(int user_idx) {
	    List<TeamPostsBean> teamList3 = mypageMapper.getTeamsByUserIdx(user_idx);
		return teamList3;
	}
	
	//TeamOrTeamMember에서 팀 구인 게시글 카드리스트 
	public List<TeamPostsBean> getTeamPostList(int user_idx) {
		List<TeamPostsBean> postList = mypageMapper.getTeamPostList(user_idx);
		return postList;
	}
	//팀원 구인글 보고 신청하는 user 쿼리문
	public void applyTeamMember(TeamPostsBean tpb) {
		
		mypageMapper.applyTeamMember(tpb);
	}
	//팀원 신청 승인 거절
	public List<UserBean> ApprovalList(int user_idx) {
		List<UserBean> ApprovalList = mypageMapper.ApprovalList(user_idx);
		return ApprovalList;
	}
	//승인 버튼 눌렀을때 승인
	public void TeamMemberApprovalOk(UserBean eb) {
		mypageMapper.TeamMemberApprovalOk(eb);
	}
	//거절 버튼 눌렀을때 거절
	public void TeamMemberApprovalNo(UserBean eb2) {
		mypageMapper.TeamMemberApprovalNo(eb2);
	}
	

}
