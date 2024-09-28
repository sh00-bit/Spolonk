package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.beans.TeamPostsBean;
import kr.co.softsoldesk.beans.UserBean;

public interface MyPageMapper {

	// 마이페이지 user_idx값 땡겨오기
	@Select("select user_name from users where User_idx = #{User_idx}")
	UserBean readUserInfo(int User_idx);

	// 팀/팀원 팀리스트에 내가 속한 팀 모두 추출
	@Select("SELECT t.Name AS teamName, " + "u.user_name AS user_name, " + "u.user_id AS user_id, "
			+ "u.user_Phone user_Phone, " + "u.user_email AS user_email " + "FROM TeamMembers tm "
			+ "JOIN Team t ON tm.Team_id = t.Team_id " + "JOIN users u ON tm.User_idx = u.User_idx "
			+ "WHERE t.Team_id IN (" + "    SELECT tm2.Team_id " + " FROM TeamMembers tm2 "
			+ "WHERE tm2.User_idx = #{user_idx} AND tm2.STATUS = 3) AND tm.STATUS = 3 "
			+ "Order by t.Name")
	List<UserBean> readMyTeam(int user_idx);
	
	// mypage에 내가 팀장인 팀들과 팀원추철
	  @Select("SELECT t.Name AS teamName, " + "u.user_name AS user_name, " +
	  "u.user_id AS user_id, " + "u.user_Phone user_Phone, " +
	  "u.user_email AS user_email " + "FROM TeamMembers tm " +
	  "JOIN Team t ON tm.Team_id = t.Team_id " +
	  "JOIN users u ON tm.User_idx = u.User_idx " + "WHERE t.Team_id IN (" +
	  "    SELECT tm2.Team_id " + "    FROM Team tm2 " +
	  "WHERE tm2.User_idx = #{user_idx}) AND tm.STATUS = 3 " + "Order by t.Name")
	  List<UserBean> readMyTeam2(int user_idx);
	
	// MYTEAM 페이지 내가 속한 팀 리스트 추출
	@Select("SELECT t.Name AS TeamName, e.Name AS EventName " + "FROM TeamMembers tm "
			+ "JOIN Team t ON tm.Team_id = t.Team_id " + "JOIN Event e ON t.Event_id = e.Event_id "
			+ "WHERE tm.User_idx = #{user_idx} AND STATUS = 3 ")
	List<UserBean> readMyTeamName(int user_idx);

	// 팀장_idx값 찾기
	@Select("select distinct user_idx from Team where user_idx = #{user_idx}")
	UserBean readTeamLeaderIdx(int User_idx);

	// 새로운 팀 등록
	@Insert("insert into Team(Team_id, Name, Event_id, User_idx, Category) values(team_seq.nextval, #{teamName}, #{eventNumber}, #{user_idx}, #{category})")
	void addTeam(EventBean team);

	// 팀원 구인 포스터 작성하기
	@Insert("insert into TeamPosts (teampost_id, title, content, user_idx, team_id, category) "
			+ "values (TeamPosts_seq.NEXTVAL, #{title}, #{content}, #{user_idx}, #{team_id}, #{category})")
	void add_teammember_pro(TeamPostsBean writeTeamPostBean);

	// 팀원 구인 포스터 작성 팀 선택 셀렉트박스 기입용
	@Select("select team_id, t.name as teamName, e.name as eventName, user_idx, category "
			+ "from Team t, Event e "
			+ "where user_idx = #{user_idx} and e.event_id = t.event_id")
	List<TeamPostsBean> getTeamsByUserIdx(int user_idx);

	// 작성자 이름 가져오기
	@Select("select user_name from users where user_idx = #{user_idx}")
	String findName(int user_idx);
	
	//팀원 구인 게시판 카드표시
	@Select("SELECT tp.title as title, tp.team_id as team_id, t.name AS teamName, e.name AS eventName, tp.user_idx as user_idx, tp.category as category, teampost_id, content "
        + "FROM TeamPosts tp "
        + "JOIN Team t ON tp.team_id = t.team_id "
        + "JOIN Event e ON t.event_id = e.event_id ")
	List<TeamPostsBean> getTeamPostList(int user_idx);

	//팀원 구인글 보고 신청하는 user 쿼리문
	@Insert("insert into TeamMembers (team_id, user_idx, Status) "
			+ "values (#{team_id}, #{user_idx}, 1)")
	void applyTeamMember(TeamPostsBean tpb);
	
	//팀원 신청 승인 거절
	@Select("SELECT te.team_id AS team_id, te.name AS teamName, u.user_idx AS user_idx, u.user_name AS user_name, " +
	        "u.user_id AS user_id, u.user_Phone AS user_Phone, u.user_email AS user_email " +
	        "FROM TeamMembers t " +
	        "JOIN users u ON t.user_idx = u.user_idx " +
	        "JOIN (SELECT team_id, name FROM team WHERE user_idx = #{user_idx}) te ON te.team_id = t.team_id " +
	        "WHERE t.status = 1")
	List<UserBean> ApprovalList(int user_idx);
	
	//승인 버튼 누르면 1에서 3으로 update
	@Update("update TeamMembers SET Status = 3 where User_idx = #{user_idx} and team_id = #{team_id}")
	void TeamMemberApprovalOk(UserBean eb);

	//거절 버튼 누르면 1에서 2로 update
	@Update("update TeamMembers SET Status = 2 where User_idx = #{user_idx} and team_id = #{team_id}")
	void TeamMemberApprovalNo(UserBean eb2);
	
	
	
}
