package kr.co.softsoldesk.controller;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.Education;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/education")
public class EducationController {

    private final DataSource dataSource;
    
 // 연수 목록을 보여주는 메서드
    @GetMapping("/add_userInfo_edu")
    public String showEduList(@RequestParam("user_idx") Long user_idx, Model model) {

        // 연수 목록을 저장할 리스트
        List<Education> educationList = new ArrayList<>();

        String selectQuery = "SELECT Education_ID, School_Name, Start_Date, End_Date, Is_Final_Education FROM Education WHERE USER_IDX = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectQuery)) {

            ps.setLong(1, user_idx);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                  
                    Education education = new Education();
                    education.setEducation_id(rs.getLong("Education_ID"));
                    education.setSchool_name(rs.getString("School_Name"));
                    education.setStart_date(rs.getDate("Start_Date"));
                    education.setEnd_date(rs.getDate("End_Date"));
                    education.setIs_Final_Education(rs.getString("Is_Final_Education"));
                  
                    educationList.add(education);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 연수 목록을 모델에 추가
        model.addAttribute("educationList", educationList);
        model.addAttribute("user_idx", user_idx); // 사용자 ID 전달
        return "education/add_userInfo_edu";  // JSP 파일로 리턴
    }
	/*
	 * @GetMapping("/add_userInfo_edu") public String
	 * showAddUserInfoEduPage(@RequestParam("user_idx") Long user_idx, Model model)
	 * { System.out.println("Received user_idx: " + user_idx); // 디버깅용 로그 추가
	 * model.addAttribute("user_idx", user_idx); return
	 * "education/add_userInfo_edu"; // 이 이름이 JSP 파일의 이름과 일치해야 합니다 }
	 */
    
    public EducationController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostMapping("/addEducation")
    public String saveEducation(
            @RequestParam("school_name") String school_name,
            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_date,
            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_date,
            @RequestParam("is_final_education") String is_final_education,
            @RequestParam("user_idx") int user_idx,
            Model model) {

        String insertQuery = "INSERT INTO Education (Education_ID, USER_IDX, School_Name, Start_Date, End_Date, Is_Final_Education) " +
                "VALUES (Education_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setInt(1, user_idx);
            ps.setString(2, school_name);
            ps.setDate(3, new java.sql.Date(start_date.getTime()));
            ps.setDate(4, new java.sql.Date(end_date.getTime()));
            ps.setString(5, is_final_education);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("message", "Database error: " + e.getMessage());
            return "education/addEdu_fail"; // 에러가 발생하면 실패 페이지로 이동
        }

        // 학력 추가 후 성공적으로 리스트 페이지로 리다이렉트
        return "redirect:/education/add_userInfo_edu?user_idx=" + user_idx;
    }

    @PostMapping("/deleteEdu")
    public String deleteEdu(@RequestParam("education_id") Long education_id, @RequestParam("user_idx") Long userIdx,
    		Model model) {

        String deleteQuery = "DELETE FROM Education WHERE Education_ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setLong(1, education_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("message", "Database error: " + e.getMessage());
            return "education/deleteEdu_fail"; // In case of error
        }
        // Redirect to the training list page immediately after successful insertion
        return "redirect:/education/add_userInfo_edu?user_idx=" + userIdx;
    }
}