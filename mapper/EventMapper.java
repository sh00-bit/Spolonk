package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.softsoldesk.beans.EventBean;

public interface EventMapper {
	
	@Select("select * from event")
	List<EventBean> selectAllEvents();

}
