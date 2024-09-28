package kr.co.softsoldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.dao.ContestDAO;

@Service
public class ContestService {
    @Autowired
    private ContestDAO contestDAO;
    
    public List<ContestBean> selectAllContests() {
        return contestDAO.selectAllContests();
    }

	public void addContest(ContestBean contestBean) {
		contestDAO.addContest(contestBean);
		
	}

	public List<ContestBean> findContestsWithVolunteers() {
		return contestDAO.findByNeedVolunteer(1);
	}

	public ContestBean getContestByName(String contestName) {
		return contestDAO.getContestByName(contestName);
	}

	public String getUserNameById(int contest_id) {
		return contestDAO.getUserNameById(contest_id);
	}
	
	public int getIdByUserName(String contest_name) {
		return contestDAO.getIdByUserName(contest_name);
	}

	public void addInChargeContest(ContestBean contestBean, int userIdx) {
		contestDAO.addInChargeContest(contestBean,userIdx);
	}

	public List<ContestBean> getEveryContests() {
		List<ContestBean> contests = contestDAO.getEveryContests();		
		
		return contests;
	}

}
