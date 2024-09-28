package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.ContestBean;

public interface ContestMapper {
	
	@Select("SELECT Contest_id, Name AS Contest_Name, TO_CHAR(Start_Date, 'YYYY-MM-DD') AS Start_Date, TO_CHAR(End_Date, 'YYYY-MM-DD') AS End_Date, event_id, place, organizer, description, image_path, need_volunteer FROM Contest")
	List<ContestBean> selectAllContests();
	
	@Insert("INSERT INTO Contest (Contest_id, Name, Start_Date, End_Date, Place, Event_id, organizer, description, image_path, need_volunteer) " +
            "VALUES (Contest_SEQ.NEXTVAL, #{Contest_Name}, TO_DATE(#{Start_Date}, 'YYYY-MM-DD'), TO_DATE(#{End_Date}, 'YYYY-MM-DD'), #{Place}, #{Event_id}, #{organizer}, #{description}, #{image_path}, #{need_volunteer})")
    void addContest(ContestBean contestBean);

	@Select("SELECT Name AS Contest_Name, TO_CHAR(Start_Date, 'YYYY-MM-DD') AS Start_Date, TO_CHAR(End_Date, 'YYYY-MM-DD') AS End_Date, event_id, place, organizer, description, image_path, need_volunteer FROM Contest WHERE need_volunteer = #{i}")
	List<ContestBean> findByNeedVolunteer(int i);

	@Select("SELECT Contest_id, Name AS Contest_Name, TO_CHAR(Start_Date, 'YYYY-MM-DD') AS Start_Date, TO_CHAR(End_Date, 'YYYY-MM-DD') AS End_Date, event_id, place, organizer, description, image_path, need_volunteer FROM Contest WHERE Name = #{contestName}")
	ContestBean getContestByName(String contestName);

	@Select("select name from Contest where Contest_id= #{contest_id}")
	String getUserNameById(int contest_id);

	@Select("select Contest_id from Contest where name = #{contest_name}")
	int getIdByUserName(String contest_name);

	// 유저의 idx 값 필요 
	// 지금 모든 inchargecontest 의 값을 가져온 contest_name 으로 바꾸고 잇음...
	@Update("UPDATE users SET inChargeContest = #{contest_name} WHERE User_idx = #{userIdx}")
	void addInChargeContest(@Param("contest_name") String contest_name, @Param("userIdx") int userIdx);
	
	@Select("SELECT Contest_id, Name AS Contest_Name, TO_CHAR(Start_Date, 'YYYY-MM-DD') AS Start_Date, TO_CHAR(End_Date, 'YYYY-MM-DD') AS End_Date, event_id, place, organizer, description, image_path, need_volunteer FROM Contest")
	List<ContestBean> getEveryContests();
}
