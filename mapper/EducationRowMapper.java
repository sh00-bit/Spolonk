package kr.co.softsoldesk.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.softsoldesk.beans.Education;

public class EducationRowMapper implements RowMapper<Education>{
	
	@Override
    public Education mapRow(ResultSet rs, int rowNum) throws SQLException {
        Education education = new Education();
        education.setEducation_id(rs.getLong("education_id"));
        education.setUser_idx(rs.getInt("user_idx"));
        education.setSchool_name(rs.getString("school_name"));
        education.setStart_date(rs.getDate("start_date"));
        education.setEnd_date(rs.getDate("end_date"));
        education.setIs_Final_Education(rs.getString("is_final_education"));
        return education;
    }

}
