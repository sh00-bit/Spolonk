package kr.co.softsoldesk.beans;

import java.util.Date;

public class Training {
	 	private String trainingName;
	    private Date startDate;
	    private Date endDate;
	    private String institutionName;
	    private String content;
	    private int training_Id;

	    // Getters and Setters
	    public String getTrainingName() {
	        return trainingName;
	    }

	    public void setTrainingName(String trainingName) {
	        this.trainingName = trainingName;
	    }

	    public Date getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(Date startDate) {
	        this.startDate = startDate;
	    }

	    public Date getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(Date endDate) {
	        this.endDate = endDate;
	    }

	    public String getInstitutionName() {
	        return institutionName;
	    }

	    public void setInstitutionName(String institutionName) {
	        this.institutionName = institutionName;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

		public int getTraining_Id() {
			return training_Id;
		}

		public void setTraining_Id(int training_Id) {
			this.training_Id = training_Id;
		}
	}

