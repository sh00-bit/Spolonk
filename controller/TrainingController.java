package kr.co.softsoldesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.softsoldesk.beans.Training;

import org.springframework.format.annotation.DateTimeFormat;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/training")
public class TrainingController {

    private final DataSource dataSource;

    public TrainingController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
 // 연수 목록을 보여주는 메서드
    @GetMapping("/add_userInfo_training")
    public String showTrainingList(@RequestParam("user_idx") Long user_idx, Model model) {

        // 연수 목록을 저장할 리스트
        List<Training> trainingList = new ArrayList<>();

        String selectQuery = "SELECT Training_Id, Training_Name, Start_Date, End_Date, Institution_Name, Content " +
                             "FROM Training WHERE USER_IDX = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectQuery)) {

            ps.setLong(1, user_idx);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Training training = new Training();
                    training.setTraining_Id(rs.getInt("training_Id"));
                    training.setTrainingName(rs.getString("Training_Name"));
                    training.setStartDate(rs.getDate("Start_Date"));
                    training.setEndDate(rs.getDate("End_Date"));
                    training.setInstitutionName(rs.getString("Institution_Name"));
                    training.setContent(rs.getString("Content"));
                    trainingList.add(training);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 연수 목록을 모델에 추가
        model.addAttribute("trainingList", trainingList);
        model.addAttribute("user_idx", user_idx); // 사용자 ID 전달
        return "training/add_userInfo_training";  // JSP 파일로 리턴
    }


    @PostMapping("/addTraining")
    public String saveTraining(
            @RequestParam("training_name") String trainingName,
            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("institution_name") String institutionName,
            @RequestParam("content") String content,
            @RequestParam("user_idx") Long userIdx,
            Model model) {

        String insertQuery = "INSERT INTO Training (Training_ID, USER_IDX, Training_Name, Start_Date, End_Date, Institution_Name, Content) " +
                "VALUES (Training_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setLong(1, userIdx);
            ps.setString(2, trainingName);
            ps.setDate(3, new java.sql.Date(startDate.getTime()));
            ps.setDate(4, new java.sql.Date(endDate.getTime()));
            ps.setString(5, institutionName);
            ps.setString(6, content);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("message", "Database error: " + e.getMessage());
            return "training/addTraining_fail"; // In case of error
        }

        // Redirect to the training list page immediately after successful insertion
        return "redirect:/training/add_userInfo_training?user_idx=" + userIdx;
    }

    @PostMapping("/deleteTraining")
    public String deleteTraining(@RequestParam("training_id") Long training_Id, @RequestParam("user_idx") Long userIdx,
    		Model model) {

        String deleteQuery = "DELETE FROM Training WHERE Training_ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setLong(1, training_Id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("message", "Database error: " + e.getMessage());
            return "training/deleteTraining_fail"; // In case of error
        }
        // Redirect to the training list page immediately after successful insertion
        return "redirect:/training/add_userInfo_training?user_idx=" + userIdx;
    }
}