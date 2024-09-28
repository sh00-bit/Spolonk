package kr.co.softsoldesk.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.VolunteerAddingBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.service.ContestService;
import kr.co.softsoldesk.service.UserService;
import kr.co.softsoldesk.service.VolunteerService;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;
    
    @Autowired
    private ContestService contestService;
    
    @Autowired
    private UserService userService;
    
    @Resource(name = "loginUserBean")
	private UserBean loginUserBean;
    
    

    @PostMapping("/volunteer_request")
    public String volunteerRequest(@RequestParam("user_idx") int userIdx, 
                                   @RequestParam("contest_idx") int contestIdx) {
        System.out.println("컨트롤러 진입");
        System.out.println("user_idx: " + userIdx);
        System.out.println("contest_idx: " + contestIdx);

        // VolunteerService를 이용해 필요한 로직 수행
        volunteerService.processVolunteerRequest(userIdx, contestIdx);

        return "redirect:/board/volunteer"; // 신청 완료 후 리다이렉트

    
    }
    
    
    @GetMapping("/volunteer_get")
    @ResponseBody
    public List<VolunteerBean> volunteerGet() {
        System.out.println("자원봉사 테이블 가져오기");

        
        List<VolunteerBean> volunteerLists = volunteerService.getAllVolunteers();
        /*
        for(int i=0; i<volunteerLists.size(); i++) {
        	
        	System.out.println(volunteerLists.get(i).getContest_id());        	
        }*/
        

        // 일단 컨트롤러 까ㅣ지는 가져오는거 확인
        return volunteerLists; // JSON 형식으로 반환됨
    }
    
    
    // volunteerBean 에 관한 정보를 받아와서 db 테이블에 넣기
    // 자원봉사자 넣기
    @PostMapping("/volunteer_add")
    @ResponseBody
    public void VolunteerAdd(@RequestBody VolunteerAddingBean newVolunteer) {
    	
    	
    	String decodedVolunteerName = decode(newVolunteer.getVolunteer_name());
        String decodedContestName = decode(newVolunteer.getVolunteer_contest());

        System.out.println("Received Volunteer Name: " + decodedVolunteerName);
        System.out.println("Received Contest Name: " + decodedContestName);
 
        VolunteerAddingBean VAB = new VolunteerAddingBean();
        VAB.setVolunteer_contest(decodedContestName);
        VAB.setVolunteer_name(decodedVolunteerName);
        // 서비스 레이어로 전달하여 처리
        
        int contest_id = contestService.getIdByUserName(decodedContestName);
        int user_id = userService.getIDByUserName(decodedVolunteerName);
        
        System.out.println("contest Id : " + contest_id + "user_id  : " + user_id);
        
        
        //일단 정보 넣고 정보 삭제
        volunteerService.VolunteerAdd(VAB);
        volunteerService.deleteRequest(contest_id, user_id);

    }
    
    @PostMapping("/volunteer_reject")
    @ResponseBody
    public void VolunteerReject(@RequestBody VolunteerAddingBean newVolunteer) {
        System.out.println("자원봉사자 거절 요청");
        
        String decodedVolunteerName = decode(newVolunteer.getVolunteer_name());
        String decodedContestName = decode(newVolunteer.getVolunteer_contest());
        
        System.out.println(decodedVolunteerName);
        System.out.println(decodedContestName);

        
        VolunteerAddingBean VAB = new VolunteerAddingBean();
        
        VAB.setVolunteer_contest(decodedContestName);
        VAB.setVolunteer_name(decodedVolunteerName);
        
        int contest_id = contestService.getIdByUserName(decodedContestName);
        int volunteer_id = userService.getIDByUserName(decodedVolunteerName);
        
        System.out.println("contest Id : " + contest_id + "volunteer Id : " + volunteer_id);
        
        
        volunteerService.deleteRequest(contest_id , volunteer_id);

    }
    
 
    
    //디코팅 메서드
    private String decode(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // UTF-8은 항상 지원되지만, 예외를 처리해줍니다.
            e.printStackTrace();
            return value;
        }
    }

    
    
    
    

}
