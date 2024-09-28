package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.softsoldesk.beans.VolunteerAddingBean;
import kr.co.softsoldesk.beans.VolunteerBean;

public interface VolunteerMapper {
	
	@Insert("INSERT INTO Volunteer (volunteer_id, contest_id, user_id) VALUES (volunteer_seq.NEXTVAL, #{contest_id}, #{user_id})")
	void processVolunteerRequest(@Param("user_id") int user_id, @Param("contest_id") int contest_id);

	@Select("Select * from volunteer")
	List<VolunteerBean> getAllVolunteers();

	@Insert("INSERT INTO volunteerAccepted (volunteer_name, volunteer_contest) VALUES (#{volunteer_name}, #{volunteer_contest})")
	void VolunteerAdd(VolunteerAddingBean newVolunteer);

	
	// 삭제문
	@Delete("DELETE FROM Volunteer WHERE user_id = #{user_id} AND contest_id = #{contest_id}")
	void deleteRequest(@Param("contest_id") int contestId, @Param("user_id") int user_id);
	
}
