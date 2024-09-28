package kr.co.softsoldesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.softsoldesk.beans.EventBean;
import kr.co.softsoldesk.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @GetMapping("/select_all_events")
    @ResponseBody
    public List<EventBean> selectAllEvents(Model model){
        List<EventBean> eventList = eventService.selectAllEvents();
//        for(int i=0; i<eventList.size(); i++) {
//        	System.out.println(eventList.get(i).getEvent_id());
//        	System.out.println(eventList.get(i).getName());
//        }
        model.addAttribute("eventList", eventList);
        
        return eventList;
    }
}
