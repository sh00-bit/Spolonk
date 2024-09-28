package kr.co.softsoldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.dao.EventDAO;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;
    
    public List<EventBean> selectAllEvents() {
        return eventDAO.selectAllEvents();
    }
}
