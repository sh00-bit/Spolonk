package kr.co.softsoldesk.beans;

public class ContestBean {

	
	private int Contest_id;
    private String Contest_Name;  // SQL 쿼리의 `Name`과 매핑
    private String Start_Date;    // SQL 쿼리에서 문자열로 변환된 날짜
    private String End_Date;      // SQL 쿼리에서 문자열로 변환된 날짜
    private int Event_id;
    private String Place;
    private String organizer;
    private String description;
    private String image_path;
    private int need_volunteer;  // 0 이면 자원봉사 안받음 1 이면 받음 (이값 외는 안받음)

    
    public int getContest_id() {
		return Contest_id;
	}

	public void setContest_id(int contest_idx) {
		this.Contest_id = contest_idx;
	}

	public int getNeed_volunteer() {
		return need_volunteer;
	}

	public void setNeed_volunteer(int need_volunteer) {
		this.need_volunteer = need_volunteer;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return Place;
	}

	public void setPlace(String place) {
		Place = place;
	}

	public int getEvent_id() {
		return Event_id;
	}

	public void setEvent_id(int event_id) {
		Event_id = event_id;
	}

	public String getContest_Name() {
        return Contest_Name;
    }

    public void setContest_Name(String contest_Name) {
        Contest_Name = contest_Name;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }
}
