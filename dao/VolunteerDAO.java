package kr.co.softsoldesk.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.VolunteerAddingBean;
import kr.co.softsoldesk.beans.VolunteerBean;
import kr.co.softsoldesk.mapper.VolunteerMapper;

@Repository
public class VolunteerDAO {
    
    @Autowired
    private VolunteerMapper volunteerMapper;

	public void processVolunteerRequest(int userIdx, int contestIdx) {
		volunteerMapper.processVolunteerRequest(userIdx, contestIdx);
		
	}

	public List<VolunteerBean> getAllVolunteers() {
		return volunteerMapper.getAllVolunteers();
	}

	public void VolunteerAdd(VolunteerAddingBean newVolunteer) {
		volunteerMapper.VolunteerAdd(newVolunteer);
	}

	public void deleteRequest(int a, int b) {
		volunteerMapper.deleteRequest(a,b);
		
	}

   
}
