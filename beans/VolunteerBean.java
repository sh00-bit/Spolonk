package kr.co.softsoldesk.beans;

public class VolunteerBean {
	
	private int volunteer_id;
	private int contest_id;
	private int user_id;
	
	
	//어드민 페이지 필요한 정보
	private String user_name;
	private String Phone;
	private String contest_name;
	private String user_email;
	private String gender;
	
	//유진
	private String start_date;  // start_date 필드 추가
	
	
	//*******************************************************
	
	
	public int getVolunteer_id() {
		return volunteer_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getContest_name() {
		return contest_name;
	}
	public void setContest_name(String contest_name) {
		this.contest_name = contest_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setVolunteer_id(int volunteer_id) {
		this.volunteer_id = volunteer_id;
	}
	public int getContest_id() {
		return contest_id;
	}
	public void setContest_id(int contest_id) {
		this.contest_id = contest_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	

	
	
}
