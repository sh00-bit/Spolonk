package kr.co.softsoldesk.beans;

import java.util.Date;

public class Award {

	private int Award_ID;
	private String Competition_Name;
	private Date Start_Date;
	private Date End_Date;
	private String Location;
	private int Rank;
	private String Organizer;
	private String Category;
	private String Domestic_International;
	
	public int getAward_ID() {
		return Award_ID;
	}
	public void setAward_ID(int award_ID) {
		Award_ID = award_ID;
	}
	public String getCompetition_Name() {
		return Competition_Name;
	}
	public void setCompetition_Name(String competition_Name) {
		Competition_Name = competition_Name;
	}
	public Date getStart_Date() {
		return Start_Date;
	}
	public void setStart_Date(Date start_Date) {
		Start_Date = start_Date;
	}
	public Date getEnd_Date() {
		return End_Date;
	}
	public void setEnd_Date(Date end_Date) {
		End_Date = end_Date;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getRank() {
		return Rank;
	}
	public void setRank(int rank) {
		Rank = rank;
	}
	public String getOrganizer() {
		return Organizer;
	}
	public void setOrganizer(String organizer) {
		Organizer = organizer;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getDomestic_International() {
		return Domestic_International;
	}
	public void setDomestic_International(String domestic_International) {
		Domestic_International = domestic_International;
	}
	
	
}
