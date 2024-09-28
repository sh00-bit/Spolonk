package kr.co.softsoldesk.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.dao.BoardDAO;
import kr.co.softsoldesk.dao.UserDAO;
import kr.co.softsoldesk.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserMapper userMapper;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	 
	//회원가입
	public void addUser(UserBean joinUserBean) {
		
		userDAO.addUser(joinUserBean);
	}
	
	//ID 중복확인
	public boolean checkUserIdExist(String user_id) {
		
		String checkId = userDAO.checkUserIdExist(user_id);
		
		if(checkId==null) {
			return true;
		}else {
			return false;
		}
	}
	
	//로그인
	public void  getLoginInfo(UserBean loginBean) {
		 // 로그인 정보를 데이터베이스에서 조회
		UserBean loginBean2 = userDAO.getLoginInfo(loginBean);
		
		
		// 로그인 정보가 null인지 확인
		if(loginBean2 != null) {
			
			System.err.println("**********"+loginBean2.getInChargeContest());
			loginUserBean.setUser_idx(loginBean2.getUser_idx());
			loginUserBean.setUser_name(loginBean2.getUser_name());
			loginUserBean.setUserLogin(true);
			loginUserBean.setProfileImagePath(loginBean2.getProfileImagePath());
			loginUserBean.setAuthority(loginBean2.getAuthority());
			loginUserBean.setInChargeContest(loginBean2.getInChargeContest());
			loginUserBean.setIntroduction(loginBean2.getIntroduction());
			/*전에있던거
			 * loginUserBean.setUser_idx(loginBean2.getUser_idx());
			 * loginUserBean.setUser_name(loginBean2.getUser_name());
			 * loginUserBean.setUserLogin(true);
			 */
		}
	}
	
	//ID찾기
	public String findId(UserBean findIdBean) {
		
		String findid = userDAO.findId(findIdBean);
		
		if(findid==null) {
			return null;
		}else {
			return findid;
		}
	}
	

	// 비밀번호 찾기
	public boolean findPW(UserBean PassBean) {
		
		String findpw = userDAO.findPW(PassBean);
		if(findpw!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	// 비밀번호 찾기
	public Integer NewPW(UserBean PassBean) {
		
		return userDAO.NewPW(PassBean);		
	}
	
	
	//새 비밀번호
	public void NewPass(UserBean NewpassBean) {
		
		System.out.println(NewpassBean.getUser_pass1());
		
		
		userDAO.NewPass(NewpassBean);
	}
	
	//아이디가 존재하는지 확인
    public boolean checkUserExists(String user_email) {
    	
        UserBean user = userDAO.getUserByEmail(user_email);
     // 여기서 UserBean => boolean 타입으로 변경
        return user != null;
    }

    
    // 카카오용 (닉네임으로 찾기)
	public boolean checkUserExistsKakao(String userNickname) {
		UserBean user = userDAO.getUserByNickname(userNickname);

		return user != null;
	}
	
	//회원정보 가져오기
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean = userDAO.getModifyUserInfo(loginUserBean.getUser_idx());
		if (tempModifyUserBean != null) {
			modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
			modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
			modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
			modifyUserBean.setUser_email(tempModifyUserBean.getUser_email());
			modifyUserBean.setUser_Phone(tempModifyUserBean.getUser_Phone());
			modifyUserBean.setUser_pass1(tempModifyUserBean.getUser_pass1());
			modifyUserBean.setGender(tempModifyUserBean.getGender());
			modifyUserBean.setEvent_id(tempModifyUserBean.getEvent_id());
		} else {
			// 예외 처리 또는 기본값 설정
		}
		/*전에꺼
		 * UserBean tempModifyUserBean =
		 * userDAO.getModifyUserInfo(loginUserBean.getUser_idx());
		 * modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		 * modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		 * modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		 * 
		 */
	}
		public void modifyUserInfo(UserBean modifyUserBean) {

			userMapper.modifyUserInfo(modifyUserBean);
		}

			// 사용자 ID로 사용자 정보를 가져오는 메서드
		public UserBean getUserById(String user_id) {
		
			return userMapper.getUserById(user_id);
		}

		public boolean checkPassword(int user_idx, String user_pass1) {

				if (user_pass1 == null) {
					return false;
		}

				// 데이터베이스에서 사용자 정보를 가져옵니다.
				UserBean user = userDAO.getUserByIdx(user_idx);

				// 사용자 정보가 존재하는지 확인합니다.
				if (user != null) {
					// 비밀번호 비교
					return user.getUser_pass1().equals(user_pass1);
				}

				return false;
		}

		// 회원 탈퇴 메서드

		@Transactional
		public boolean deleteMember(UserBean deleteUserBean) {
				try {
					// user_idx가 올바르게 전달되고 있는지 확인 (디버깅용)
					System.out.println("Attempting to delete user with ID: " + deleteUserBean.getUser_idx());

					// userDAO의 deleteMember 메서드를 호출하여 사용자 삭제
					Integer result = userDAO.deleteMember(deleteUserBean);

					if (result == 1) {
						System.out.println("User deleted successfully.");
						return true; // 삭제 성공 시 true 반환
					} else {
						System.out.println("User not found or could not be deleted.");
						return false; // 삭제 실패 시 false 반환
					}
				} catch (Exception e) {
					// 예외 발생 시 로그 출력 및 예외 처리
					System.out.println("Error occurred during user deletion: " + e.getMessage());
					e.printStackTrace();
					return false; // 예외 발생 시 false 반환
				}
			}

		public void updateIntroduction(int user_idx, String introduction) {

				loginUserBean.setIntroduction(introduction);

				System.out.println("서비스 " + loginUserBean.getIntroduction());

				userDAO.updateIntroduction(user_idx, introduction);
		}

		public String getUserNameByIdx(int user_idx) {
				// 데이터베이스에서 user_idx를 기반으로 사용자 이름을 가져옵니다.
				return userMapper.findUserNameByIdx(user_idx);
		}
			
		public void updateProfileImage(String profileImage, int user_idx) {
			        
				userMapper.updateProfileImage(profileImage, user_idx);
		}


		public UserBean authenticate(int user_idx, String user_pass1) {
				 // 데이터베이스에서 사용자 정보를 가져옴
		        UserBean user = userMapper.getUserByIdx(user_idx);

		        if (user != null && user.getUser_pass1().equals(user_pass1)) {
		            // 비밀번호가 일치하면 사용자 객체를 반환
		            return user;
		        }
		        // 인증 실패 시 null 반환
		        return null;
		}
		
		public String getUserNameById(int user_id) {
			return userDAO.getUserNameById(user_id);

		}
		
		public int getIDByUserName(String user_name) {
			return userDAO.getIDByUserName(user_name);

		}

		public void alterAuthority(int managerAuthority, int userIdx) {
			userDAO.alterAuthority(managerAuthority,userIdx);
					
		}

		public String getInChargeContestByUserId(int userId) {
			return userDAO.getInChargeContestByUserId(userId);
		}

		public List<VolunteerBean> getVolunteersByContest(int inChargeContest) {
			return userDAO.getVolunteersByContest(inChargeContest);
		}
		
		// 사용자가 작성한 게시글 목록을 페이지네이션하여 가져오는 메서드
	       public List<ContentBean> getUserContentList(int user_idx) {
	           // DAO를 통해 게시글 목록을 가져옴
	           return boardDAO.getUserContentList(user_idx);
	       }
	       
	     //마이페이지 게시판 목록
	     public List<ContentBean> MyContentList(int user_idx) {
	          
	          return userDAO.MyContentList(user_idx);
	       }
	    //내가 신청한 봉사활동
	     public List<VolunteerBean> getMyVolunteerList(int user_idx) {
	              
	           return userDAO.getMyVolunteerList(user_idx);
	        }

		public String getIntroductionByIdx(int user_idx) {
			
			return userDAO.getIntroductionByIdx(user_idx);
		}  
	}
	
	
