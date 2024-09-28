package kr.co.softsoldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.mapper.ContestMapper;
import kr.co.softsoldesk.mapper.EventMapper;

@Repository
public class EventDAO {
    
    @Autowired
    private EventMapper eventMapper;

    public List<EventBean> selectAllEvents() {
        return eventMapper.selectAllEvents();
    }
}
