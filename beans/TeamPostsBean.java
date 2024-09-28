package kr.co.softsoldesk.beans;

public class TeamPostsBean {
    
    private int user_idx;  // 작성자 ID (외래 키)
    private int team_id;  // 팀 ID (외래 키)
    
    private Long teampost_id;  // 게시물 기본 키
    private String title;  // 게시물 제목
    private String content;  // 게시물 내용
    private String teamName; // 팀 이름
    private String eventName; // 종목 이름
    private String category;  // 카테고리 프로 아마 개인
    private String event_id; //팀 종목 idx값
    
    
    
    public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	// Getters and Setters
    public int getUser_idx() {
        return user_idx;
    }
    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }
    public int getTeam_id() {
        return team_id;
    }
    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
    public Long getTeampost_id() {
        return teampost_id;
    }
    public void setTeampost_id(Long teampost_id) {
        this.teampost_id = teampost_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
