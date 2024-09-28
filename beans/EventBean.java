package kr.co.softsoldesk.beans;

import java.util.HashMap;
import java.util.Map;

public class EventBean {
	
	private int user_idx; //유저 고유값 리퀘스트파룸
	private int team_id; //팀 인덱스 값
    private String teamName; // 팀 이름
    private String eventName; //종목이름
    private String eventNumber; // 종목 번호
    private String category; //팀 등급
    
    //승인버튼 누를때 team에 담긴거 임시로 담암놓기위에 Bean 등록
    private String user_Phone;
    private String user_email;
    
    //용민
    private int Event_id;
    private String Name;
    
    private String user_name;
    
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getEvent_id() {
		return Event_id;
	}
	public void setEvent_id(int event_id) {
		Event_id = event_id;
	}
	public String getUser_Phone() {
		return user_Phone;
	}
	public void setUser_Phone(String user_Phone) {
		this.user_Phone = user_Phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}
    
    
}
