package kr.co.softsoldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.mapper.UserMapper;

@Repository
public class UserDAO {
	
	@Autowired
	private UserMapper userMapper;

	//회원가입
	public void addUser(UserBean joinUserBean) {	
		
		userMapper.addUser(joinUserBean);
	}
	
	//ID 중복확인
	public String checkUserIdExist(String user_id) {
		
		return userMapper.checkUserIdExist(user_id);	
	}
	
	//로그인
	public UserBean getLoginInfo(UserBean loginBean) {
		
		return userMapper.getLoginInfo(loginBean);
	}
	
	//ID 찾기
	public String findId(UserBean findIdBean) {
		
		return userMapper.findId(findIdBean);
	}
	
	//비밀번호 찾기
	public String findPW(UserBean PassBean) {
		return userMapper.findPW(PassBean);		
	}
		
	//새 비밀번호 설정을 위한 user_idx 조회
	public Integer NewPW(UserBean PassBean) {
		return userMapper.NewPW(PassBean);
	}
		
	//새 비밀번호 등록
	public void NewPass(UserBean NewpassBean) {
		userMapper.NewPass(NewpassBean);
	}
	
	public UserBean getUserByEmail(String user_email) {
		
		return userMapper.getUserByEmail(user_email);
	}

	public UserBean getUserByNickname(String userNickname) {
		return userMapper.getUserByNickname(userNickname);
	}

	//수정할 회원 정보 가져오기
		public UserBean getModifyUserInfo(int user_idx) {
					
			return userMapper.getModifyUserInfo(user_idx);
		}			
		public void modifyUserInfo(UserBean modifyUserBean) {
					
			userMapper.modifyUserInfo(modifyUserBean);
					
		}
		// 사용자 ID로 사용자 정보를 가져오는 메서드
		public UserBean getUserById(String user_id) {
		   
			return userMapper.getUserById(user_id);
	    }		    
		// 사용자 IDX로 사용자 정보를 가져오는 메서드
		 public UserBean getUserByIdx(int user_idx) {
			 
			 return userMapper.getUserByIdx(user_idx);
		}
		//회원 탈퇴 메서드
		 public Integer deleteMember(UserBean deleteUserBean) {
			    	
			return userMapper.deleteMember(deleteUserBean);
		}		      
		 public void updateIntroduction(int user_idx, String introduction) {
					// UserMapper의 updateIntroduction 메서드를 호출합니다.
					//UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.updateIntroduction(user_idx, introduction);
		}
		 
		 public String getUserNameById(int user_id) {
				
				return userMapper.getUserNameById(user_id);
			}

			public int getIDByUserName(String user_name) {
				return userMapper.getIdbyUserName(user_name);
			}

			public void alterAuthority(int managerAuthority, int userIdx) {
				userMapper.alterAuthority(managerAuthority, userIdx);
				
			}

			public String getInChargeContestByUserId(int userId) {
				return userMapper.getInChargeContestByUserId(userId);
			}

			public List<VolunteerBean> getVolunteersByContest(int inChargeContest) {
				return userMapper.getVolunteersByContest(inChargeContest);
			}
			
			//마이페이지 게시판 목록
			public List<ContentBean> MyContentList(int user_idx) {
			    
				return userMapper.MyContentList(user_idx);
			}
			//내가 신청한 봉사활동
			public List<VolunteerBean> getMyVolunteerList(int user_idx) {
				
				return userMapper.getMyVolunteerList(user_idx);
			}
			//상태 메세지 출력
		   public String getIntroductionByIdx(int user_idx) {
			      return userMapper.getIntroductionByIdx(user_idx);
			   }

}
