package kr.co.softsoldesk.controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.ContestService;
import kr.co.softsoldesk.service.UserService;

@Controller
@RequestMapping("/contest")
public class ContestController {
    
    @Autowired
    private ContestService contestService;
    
    @Autowired
    private UserService userService;
    
    
    @Resource(name = "loginUserBean")
	private UserBean loginUserBean;
    
    
    @GetMapping("/select_all_contests")
    @ResponseBody
    public List<ContestBean> selectAllContest(Model model) {
        List<ContestBean> contestList = contestService.selectAllContests();
        model.addAttribute("contestList", contestList);
        return contestList;
    }
    
    @PostMapping("/contest_write_pro")
    public String contestWritePro(@RequestParam("contestImage") MultipartFile imageFile, HttpServletRequest request) {


        // 폼 필드 값 가져오기
        String contestName = request.getParameter("Contest_Name");
        String startDate = request.getParameter("Start_Date");
        String endDate = request.getParameter("End_Date");
        String place = request.getParameter("Place");
        String organizer = request.getParameter("organizer");
        String description = request.getParameter("description");
        String category = request.getParameter("Event_id");
        String needVolunteer = request.getParameter("need_volunteer");

        
        int needVolunteerModified = 0;
        try {
            if (needVolunteer.equals("on")) {
                needVolunteerModified = 1;
            } else {
                needVolunteerModified = 0;
            }
        } catch (Exception e) {
            needVolunteerModified = 0;
        }

        // 이미지 파일 저장
        String originalFilename = imageFile.getOriginalFilename();
        String uploadDir = "C:\\Users\\leade\\Documents\\workspace-sts-3.9.18.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpoLinkProject\\WEB-INF\\resource\\upload";
        String filePath = uploadDir + "\\" + originalFilename;
        try {
            imageFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Event_id가 null이거나 비어있다면 기본값 설정
        int eventId = 0;
        if (category != null && !category.trim().isEmpty()) {
            eventId = Integer.parseInt(category);
        }

        // ContestBean 설정
        ContestBean contestBean = new ContestBean();
        contestBean.setContest_Name(contestName);
        contestBean.setStart_Date(startDate);
        contestBean.setEnd_Date(endDate);
        contestBean.setPlace(place);
        contestBean.setOrganizer(organizer);
        contestBean.setDescription(description);
        contestBean.setEvent_id(eventId);
        contestBean.setImage_path(originalFilename);
        contestBean.setNeed_volunteer(needVolunteerModified);

        // 서비스 호출
        contestService.addContest(contestBean);

        // 2024.09.05
        // inChargeContest 수정 - 로그인된 유저의 idx를 사용
        contestService.addInChargeContest(contestBean,loginUserBean.getUser_idx());
//        System.out.println("로그인유저빈의 정보 : " + loginUserBean.getUser_idx() + "/n" + loginUserBean.getUser_name());
        int managerAuthority = 1;
        userService.alterAuthority(managerAuthority, loginUserBean.getUser_idx());
        loginUserBean.setInChargeContest(contestBean.getContest_Name());
        return "redirect:/board/contestInfo";
    }

    
    
    @GetMapping("/volunteer_viewMore")
    @ResponseBody
    public ContestBean volunteerViewMore(@RequestParam("contestName") String contestName) {
       
        ContestBean contest = contestService.getContestByName(contestName);
        
        return contest;
    }
    
    @PostMapping("/get_name")
    @ResponseBody
    public String getVolunteerName(@RequestParam("contest_id") int contest_id) throws UnsupportedEncodingException {
        // UserService를 통해 유저 이름을 가져옴
		System.err.println("유저 컨트롤러 진입");
		
		String contestName = contestService.getUserNameById(contest_id);
		
        String contestName2 =   URLEncoder.encode(contestName, "UTF-8");
        
        System.err.println("contestName : " + contestName2);
        return contestName2;
        
    }
    
    @PostMapping("/forward_to_contests_by_date")
    public String forwardToContestsByDate(Model model, @RequestParam("month") int month) {
        System.out.println("월별 대회 리스트 조회: 월 = " + month);

        // month 값이 1자리일 경우 앞에 0을 붙여 2자리로 만듦
        String month_formed = String.format("%02d", month);

        // 월에 기반한 대회 리스트를 가져옴
        List<ContestBean> contests = contestService.getEveryContests();

        // 필터링된 대회 리스트를 담을 리스트 초기화
        List<ContestBean> formed_contests = new ArrayList<>();

        System.out.println("월 형식 (month_formed): " + month_formed);
        
        for (ContestBean c : contests) {
            String startDate = c.getStart_Date();

            // 날짜가 null이 아니고 형식이 맞는지 확인
            if (startDate != null && startDate.length() >= 7) {
                // 월 부분 추출 후 공백 제거
                String contestMonth = startDate.substring(5, 7).trim();

                // 디버깅용 출력
                System.out.println("Start Date: " + startDate + ", 추출된 월: " + contestMonth);
                
                System.out.println("참 거짓 확인 : " +contestMonth.equals(month_formed));
                // 월이 일치하는지 비교
                if (contestMonth.equals(month_formed)) {
                	System.out.println("대회 추가: " + c.getContest_Name() + " (" + c.getStart_Date() + ")");
                    formed_contests.add(c);
                }
            }
        }

        // 필터링된 대회 리스트 출력 (디버깅용)
        for (ContestBean c : formed_contests) {
            System.out.println("formed_contests: " + c.getContest_Name());
        }

        // 모델에 대회 리스트를 담아서 JSP 페이지로 전달
        model.addAttribute("month_contest", formed_contests);
        model.addAttribute("month", month); // 월 정보도 전달

        return "board/contest_month"; // JSP 페이지 이름
    }
    
}
