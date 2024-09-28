package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerBean;

public interface AdminMapper {
	
		//유저 리스트 추출
		@Select("select u.user_idx AS user_idx, u.user_id AS user_id, u.user_name AS user_name, u.user_email AS user_email, u.user_pass1 AS user_pass1, u.user_Phone AS user_Phone, e.name AS EventName, u.gender AS gender "
				+ "from users u LEFT JOIN event e ON u.event_id = e.event_id ")
		List<UserBean> UserRead();
	
	//회원 수정할 정보 가져오기
	@Select("select u.user_idx AS user_idx, u.user_id AS user_id, u.user_name AS user_name, u.user_email AS user_email, u.user_pass1 AS user_pass1, u.user_Phone AS user_Phone, e.name AS EventName "
			+ "from users u JOIN event e ON u.event_id = e.event_id "
			+ "where u.user_idx = #{user_idx} ")
	UserBean getUserById(int user_idx);
	
	//회원 정보 수정 기입
    @Update("UPDATE users SET gender = #{gender, jdbcType=VARCHAR}, event_id = #{event_id, jdbcType=INTEGER},  user_pass1 = #{user_pass1}, user_Phone = #{user_Phone}, user_email = #{user_email}, user_name = #{user_name}, user_id = #{user_id} WHERE user_idx = #{user_idx}")
	void user_modifyInfo(UserBean adminUsermodify);

    //회원 삭제
    @Delete("delete from users where user_idx = #{user_idx}")
    void user_deletetable(int user_idx);
    
    @Insert("INSERT INTO users (user_idx, user_name, user_id, user_email, user_pass1, user_Phone, event_id, gender) "
            + "VALUES (user_seq.nextval, #{user_name}, #{user_id}, #{user_email}, #{user_pass1}, #{user_Phone}, #{event_id}, #{gender})")
    void user_addtable(UserBean adminUseradd);
    
    //팀 리스트 추출
    @Select("SELECT t.team_id AS team_id, t.Name AS teamName, ev.name as eventName, us.user_name, category from team t join (select user_name, user_idx from users) us on us.user_idx = t.user_idx join (select event_id, name from Event) ev on ev.event_id = t.event_id")
    List<EventBean> teamRead();

    //팀 삭제
     @Delete("delete from team where team_id = #{team_id} ")
     void team_deletetable(int team_id);
    
     //대회정보 리스트 뽑기
     @Select("select contest_id, name AS Contest_Name, TO_CHAR(Start_date, 'YYYY-MM-DD') AS Start_date, TO_CHAR(End_date, 'YYYY-MM-DD') AS End_date, Place, Event_id, organizer, description from Contest")
	List<ContestBean> contestRead();
     
     //게시판 리스트 추출
     @Select("SELECT b.board_info_idx, b.board_info_name, p.view_count, p.post_id, p.title, p.content, " +
               "TO_CHAR(p.writing_time, 'YYYY-MM-DD') AS writing_time, p.like_count, p.dislike_count, u.user_name " +
               "FROM board_info b " +
               "JOIN Post p ON b.board_info_idx = p.board_info_idx " + 
               "JOIN users u ON p.user_idx = u.user_idx")
       List<ContentBean> PostRead();
     
     //게시판 삭제
     @Delete("delete from Post where post_id = #{post_id}")
     void post_deletetable (@Param("post_id") int post_id);
     
     //게시판 수정
     @Update("update Post set title = #{title}, content = #{content} where post_id = #{post_id}")
     void postModifyContent(ContentBean postModifyContentBean);
     
     //댓글리스트
     @Select("SELECT c.comments_id, b.board_info_name, p.title AS posttitle, c.content, TO_CHAR(c.writing_time, 'yyyy-mm-dd') AS writing_time, u.user_name "
     		+ "FROM comments c "
     		+ "JOIN board_info b ON c.board_info_idx = b.board_info_idx "
     		+ "JOIN users u ON c.user_idx = u.user_idx "
     		+ "JOIN Post p ON c.post_id = p.post_id ")
	List<CommentBean> commentsRead();

    //자원봉사 리스트
    @Select("SELECT DISTINCT c.Name AS contest_name, u.user_name, u.user_email, u.user_Phone AS Phone, u.gender "
    		+ "FROM Volunteer v "
    		+ "JOIN Contest c ON v.Contest_id = c.Contest_id "
    		+ "JOIN Users u ON v.User_id = u.user_idx "
    		+ "ORDER BY c.Name ASC, u.user_name ASC")
	List<VolunteerBean> volunteerRead();
    
    //댓글 삭제 
    @Delete("delete from comments where comments_id = #{comments_id}")
	void comments_deletetable(@Param("comments_id") int comments_id);
    
    //대회 삭제
    @Delete("delete form contest where contest_id = #{contest_id}")
    void contest_deletetable(@Param("contest_id") int contest_id);
    
    //관계자 권한주기
    @Update("update users set authority = 1 where user_idx = #{user_idx}")
	void authority_pro(@Param("user_idx") int user_idx);
    
    //유저 카운트
    @Select("select count(*) from users")
	int usercount();
    
    @Select("select count(*) from team")
	int teamcount();

    @Select("select count(*) from contest")
	int contestcount();
    
    @Select("select count(*) from volunteer")
	int volunteercount();
     
}
