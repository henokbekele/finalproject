package projsde.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DailyReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<DailyGoalActivityReport> dailyGoalActivityReport;
	
	private List<Reminder> reminder;
	
	private  String motivationalphrase;

//	@XmlElementWrapper(name="dailyGoalActivityReports")
	public List<DailyGoalActivityReport> getDailyGoalActivityReport() {
		return dailyGoalActivityReport;
	}

	public void setDailyGoalActivityReport(
			List<DailyGoalActivityReport> dailyGoalActivityReport) {
		this.dailyGoalActivityReport = dailyGoalActivityReport;
	}
	
//	@XmlElementWrapper(name="reminders")
	public List<Reminder> getReminder() {
		return reminder;
	}

	public void setReminder(List<Reminder> reminder) {
		this.reminder = reminder;
	}

	public String getMotivationalphrase() {
		return motivationalphrase;
	}

	public void setMotivationalphrase(String motivationalphrase) {
		this.motivationalphrase = motivationalphrase;
	}

	public DailyReport(List<DailyGoalActivityReport> dailyGoalActivityReport,
			List<Reminder> reminder, String motivationalphrase) {
		super();
		this.dailyGoalActivityReport = dailyGoalActivityReport;
		this.reminder = reminder;
		this.motivationalphrase = motivationalphrase;
	}

	public DailyReport() {
		
	}
	
	

	
	
}
