package kr.co.softsoldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.ContestBean;
import kr.co.softsoldesk.mapper.ContestMapper;

@Repository
public class ContestDAO {
    
    @Autowired
    private ContestMapper contestMapper;

    public List<ContestBean> selectAllContests() {
        return contestMapper.selectAllContests();
    }

	public void addContest(ContestBean contestBean) {
		contestMapper.addContest(contestBean);
	}

	public List<ContestBean> findByNeedVolunteer(int i) {
		return contestMapper.findByNeedVolunteer(i);
	}

	public ContestBean getContestByName(String contestName) {
		return contestMapper.getContestByName(contestName);
	}

	public String getUserNameById(int contest_id) {
		System.err.println("asdsadasdsa"+contestMapper.getUserNameById(contest_id));
		return contestMapper.getUserNameById(contest_id);
	}
	
	public int getIdByUserName(String contest_name) {
		return contestMapper.getIdByUserName(contest_name);
	}

	public void addInChargeContest(ContestBean contestBean, int userIdx) {
		contestMapper.addInChargeContest(contestBean.getContest_Name(),userIdx);
	}

	public List<ContestBean> getEveryContests() {
		List<ContestBean> contests = contestMapper.getEveryContests();		
		return contests;
	}
}
