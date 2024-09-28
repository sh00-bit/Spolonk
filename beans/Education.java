package kr.co.softsoldesk.beans;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("education")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Education {
	
	private long education_id;
	private int user_idx;
	private String school_name;
    private Date start_date;
    private Date end_date;
    private String is_Final_Education;
	
    
	
	 public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public long getEducation_id() {
		return education_id;
	}

	public void setEducation_id(long education_id) {
		this.education_id = education_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getIs_Final_Education() {
		return is_Final_Education;
	}

	public void setIs_Final_Education(String is_Final_Education) {
		this.is_Final_Education = is_Final_Education;
	}
}
