package kr.co.softsoldesk.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.VolunteerAddingBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.dao.VolunteerDAO;

@Service
public class VolunteerService {
    @Autowired
    private VolunteerDAO volunteerDAO;

	public void processVolunteerRequest(int userIdx, int contestIdx) {
		
		volunteerDAO.processVolunteerRequest(userIdx,contestIdx);
	}

	public List<VolunteerBean> getAllVolunteers() {
		return volunteerDAO.getAllVolunteers();
	}

	public void VolunteerAdd(VolunteerAddingBean newVolunteer) {
		volunteerDAO.VolunteerAdd(newVolunteer);
	}

	public void deleteRequest(int a, int b) {
		volunteerDAO.deleteRequest(a,b);		
	}
    
    
}
