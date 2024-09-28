package kr.co.softsoldesk.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.softsoldesk.beans.Award;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/award")
public class AwardController {
	
	  private final DataSource dataSource;
	  
	  
	  public AwardController(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }
		/*
		 * @GetMapping("/add_userInfo_award") public String
		 * showAddUserInfoAwardPage(@RequestParam("user_idx") Long user_idx, Model
		 * model) { System.out.println("Received user_idx: " + user_idx); // 디버깅용 로그 추가
		 * model.addAttribute("user_idx", user_idx); return "award/add_userInfo_award";
		 * // 이 이름이 JSP 파일의 이름과 일치해야 합니다 }
		 * 
		 */

   

    // 수상 경력 목록을 보여주는 메서드
    @GetMapping("/add_userInfo_award")
    public String showAwardList(@RequestParam("user_idx") Long userIdx, Model model) {

        // 수상 경력 목록을 저장할 리스트
        List<Award> awardList = new ArrayList<>();

        String selectQuery = "SELECT Award_ID, Competition_Name, Start_Date, End_Date, Location, Rank, Organizer, Category, Domestic_International FROM Awards WHERE USER_IDX = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectQuery)) {
        	
            ps.setLong(1, userIdx);
            System.out.println(" 11111111111 " + ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Award award = new Award();
                    award.setAward_ID(rs.getInt("Award_ID"));
                    award.setCompetition_Name(rs.getString("Competition_Name"));
                    award.setStart_Date(rs.getDate("Start_Date"));
                    award.setEnd_Date(rs.getDate("End_Date"));
                    award.setLocation(rs.getString("Location"));
                    award.setRank(rs.getInt("Rank"));
                    award.setOrganizer(rs.getString("Organizer"));
                    award.setCategory(rs.getString("Category"));
                    award.setDomestic_International(rs.getString("Domestic_International"));
                    System.out.println( "222222222222222222222 " +  rs.getString("Competition_Name"));
                    // 추가된 로그
                    System.out.println("Award added: " + award.getCompetition_Name());

                    awardList.add(award);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 수상 경력 목록을 모델에 추가
        model.addAttribute("awardList", awardList);
        model.addAttribute("user_idx", userIdx);
        return "award/add_userInfo_award"; // 수상 경력 리스트 페이지로 이동
    }

    

    @PostMapping("/addAward")
    public String saveAward(
            @RequestParam("competition_name") String competitionName,
            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("location") String location,
            @RequestParam("rank") int rank,
            @RequestParam("organizer") String organizer,
            @RequestParam("category") String category,
            @RequestParam("domestic_international") String domesticInternational,
            @RequestParam("user_idx") int userIdx,
            Model model) {

        String insertQuery = "INSERT INTO Awards (Award_ID, USER_IDX, Competition_Name, Start_Date, End_Date, Location, Rank, Organizer, Category, Domestic_International) " +
                "VALUES (Award_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setInt(1, userIdx);
            ps.setString(2, competitionName);
            ps.setDate(3, new java.sql.Date(startDate.getTime()));
            ps.setDate(4, new java.sql.Date(endDate.getTime()));
            ps.setString(5, location);
            ps.setInt(6, rank);
            ps.setString(7, organizer);
            ps.setString(8, category);
            ps.setString(9, domesticInternational);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("message", "Database error: " + e.getMessage());
            return "award/addAward_fail"; // In case of error
            }
     // Redirect to the training list page immediately after successful insertion
        return "redirect:/award/add_userInfo_award?user_idx=" + userIdx;
    }


    @PostMapping("/deleteAward")
    public String deleteAward(@RequestParam("Award_ID") int Award_ID, @RequestParam("user_idx") int userIdx,
    		Model model) {

        String deleteQuery = "DELETE FROM Awards WHERE Award_ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setInt(1, Award_ID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("message", "Database error: " + e.getMessage());
            return "award/deleteAward_fail"; // In case of error
        }
        // Redirect to the training list page immediately after successful insertion
        return "redirect:/award/add_userInfo_award?user_idx=" + userIdx;
    }
}
