package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.Education;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;

public interface UserMapper {
	
	//회원가입
		@Insert("insert into users(user_idx, user_name, user_id, user_email, user_pass1, user_Phone) values(user_seq.nextval, #{user_name}, #{user_id}, #{user_email}, #{user_pass1}, #{user_Phone})")
		void addUser(UserBean joinUserBean);
			
		//ID 중복확인
		@Select("select user_id from users where user_id = #{user_id}")
		String checkUserIdExist(String user_id);
		
		//로그인
		@Select("select user_idx, user_name, profileImagePath, authority, inChargeContest from users where user_id= #{user_id} and user_pass1= #{user_pass1}")
		UserBean getLoginInfo(UserBean loginBean);
		
		//ID 찾기
		@Select("select user_id from users where user_name = #{user_name} and user_email = #{user_email} and user_Phone = #{user_Phone}")
		String findId(UserBean findIdBean);
		
		
		//비밀번호 찾기  
		@Select("select user_idx from users where user_id = #{user_id} and user_name = #{user_name} and user_email = #{user_email} and user_Phone = #{user_Phone}")
		String findPW(UserBean PassBean);

		//새 비밀번호 설정을 위한 user_idx 조회
		@Select("select user_idx from users where user_id = #{user_id} and user_name = #{user_name} and user_email = #{user_email} and user_Phone = #{user_Phone}")
		Integer NewPW(UserBean PassBean);
			
		//새 비밀번호 등록
		@Update("update users set user_pass1 = #{user_pass1} where user_idx = #{user_idx}")
		void NewPass(UserBean NewpassBean);
		
		// 이메일로 사용자 조회
	    @Select("select user_idx, user_name, user_id, user_email, user_pass1, user_Phone " +
	            "FROM users WHERE user_email = #{user_email}")
	    UserBean getUserByEmail(String user_email);
	    
	    //닉네임으로 사용자 조회
	    @Select("select user_idx, user_name, user_id, user_email, user_pass1, user_Phone " +
	            "FROM users WHERE user_name = #{userNickname}")
		UserBean getUserByNickname(String userNickname);
	    
		//글쓰기 이름 찾기
		@Select("select user_name from users where user_idx= #{user_idx}")
		String findName(int user_idx);
		
		 //회원수정할 회원정보 가져오기
	    @Select("select user_id, user_name, gender, event_id, user_email, user_Phone from users where user_idx = #{user_idx}")
		UserBean getModifyUserInfo(int user_idx);

		//회원 수정
	    @Update("UPDATE users SET gender = #{gender, jdbcType=VARCHAR}, event_id = #{event_id, jdbcType=INTEGER},  user_pass1 = #{user_pass1}, user_Phone = #{user_Phone}, user_email = #{user_email} WHERE user_idx = #{user_idx}")
	    void modifyUserInfo(UserBean modifyUserBean);
    
    
    
  //사용자 ID로 사용자 정보를 조회하는 쿼리 
    @Select("SELECT user_idx, user_id, user_name, user_email, user_pass1, user_Phone FROM users WHERE user_id = #{user_id}")
    UserBean getUserById(String user_id);

    @Select("SELECT user_idx, user_id, user_name, user_email, user_pass1, user_Phone FROM users WHERE user_idx = #{user_idx}")
    UserBean getUserByIdx(int user_idx);

	// 사용자 IDX로 사용자 정보를 삭제
	@Delete("DELETE FROM users WHERE user_idx = #{user_idx}") 
	Integer deleteMember(UserBean deleteUserBean);
    
	// 로그인된 사용자 정보 가져오기
	@Select("SELECT * FROM users WHERE user_id = #{user_id}")
	UserBean getLoggedUser(@Param("user_id") int user_id);
	   
	// 소개 업데이트
	@Update  ("UPDATE users SET introduction = #{introduction} WHERE user_idx = #{user_idx}")
	void updateIntroduction(@Param("user_idx") int user_idx, @Param("introduction") String introduction);

	@Select("SELECT user_name FROM users WHERE user_idx = #{user_idx}")
	String findUserNameByIdx(int user_idx);

	// 프로필 이미지 업데이트
	@Update("update users SET profileImagePath = #{profileImagePath} where user_idx = #{user_idx}")
	void updateProfileImage(@Param("profileImagePath") String profileImagePath, @Param("user_idx") int user_idx);
	    
	// 프로필 이미지 조회
	@Select("select profileImage from users where user_idx = #{user_idx}")
	String getProfileImage(int user_idx);
	
	//학력 입력
	@Insert("insert into Education (Education_ID, USER_IDX, School_Name, Start_Date, End_Date, Is_Final_Education) VALUES (#{education_Id}, #{user_Idx}, #{school_Name}, #{start_Date}, #{end_Date}, #{is_Final_Education})")
	void insertEducation(Education educationBean);		

	//학력 정보 가져오기
	@Select("SELECT * FROM Education WHERE USER_IDX = #{user_idx}")
	String getEducationByUserIdx(int user_idx);
	    
	//학력정보 삭제
	@Delete(" DELETE FROM Education WHERE Education_ID = #{education_Id}") 
	int deleteEducation(int education_id);	
	
	// 인덱스 값으로 이름 가져오기
		@Select("select user_name from users where user_idx= #{user_id}")
		String getUserNameById(int user_id);

		
		// 이름 값으로 인덱스 가져오기
		@Select("select user_idx from users where user_name= #{user_name}")
		int getIdbyUserName(String user_name);

		// 권한 설정
		@Update("update users set authority = #{managerAuthority} where User_idx = #{userIdx}")
		void alterAuthority(@Param("managerAuthority") int managerAuthority, @Param("userIdx") int userIdx);

		
		@Select("select inChargeContest from users where User_idx = #{userId}")
		String getInChargeContestByUserId(int userId);

		
		// 각 대회별로 리스트 가져오는 코드
		@Select("select * from volunteer where Contest_id = #{inChargeContest}")
		List<VolunteerBean> getVolunteersByContest(@Param("inChargeContest") int inChargeContest);
		
		//내가 쓴 게시글
	      @Select("SELECT p.post_id, p.title, p.content, to_char(p.writing_time, 'YYYY-MM-DD') AS writing_time, " +
	              "       p.user_idx, p.board_info_idx, b.board_info_idx, b.board_info_name " +
	              "FROM Post p " +
	              "JOIN board_info b ON p.board_info_idx = b.board_info_idx " +
	              "WHERE p.user_idx = #{user_idx}" +
	              "ORDER BY p.writing_time DESC, p.post_id DESC")
	      List<ContentBean> MyContentList(@Param("user_idx") int user_idx);
	      
	      //내가 신청한 봉사활동
	      @Select("SELECT v.Volunteer_id, v.Contest_id, c.Name AS contest_name, to_char(c.Start_Date, 'YYYY-MM-DD') AS start_date " +
	              "FROM Volunteer v " +
	              "JOIN Contest c ON v.Contest_id = c.Contest_id " +
	              "WHERE v.User_id = #{user_idx} " +  // user_idx로 수정
	              "ORDER BY c.Start_Date DESC, v.Volunteer_id DESC")
	      List<VolunteerBean> getMyVolunteerList(@Param("user_idx") int user_idx);  // 파라미터 이름을 user_idx로 통일


		
		//상태메세지 불러오기
		@Select("SELECT introduction FROM users WHERE user_idx = #{user_idx}")
		   String getIntroductionByIdx(@Param("user_idx") int user_idx);
		

}
