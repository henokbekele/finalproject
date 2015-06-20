package projsde.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeneralCurrentStatus {
	
	
	private DailyReport dailRreport;
	private HealthprofileGoalstatuschecker healthprofileGoalstatuschecker;
	private HealthymeasureStatus healthymeasureStatus;
	
	
	public DailyReport getDailRreport() {
		return dailRreport;
	}
	public void setDailRreport(DailyReport dailRreport) {
		this.dailRreport = dailRreport;
	}
	public HealthprofileGoalstatuschecker getHealthprofileGoalstatuschecker() {
		return healthprofileGoalstatuschecker;
	}
	public void setHealthprofileGoalstatuschecker(
			HealthprofileGoalstatuschecker healthprofileGoalstatuschecker) {
		this.healthprofileGoalstatuschecker = healthprofileGoalstatuschecker;
	}
	public HealthymeasureStatus getHealthymeasureStatus() {
		return healthymeasureStatus;
	}
	public void setHealthymeasureStatus(HealthymeasureStatus healthymeasureStatus) {
		this.healthymeasureStatus = healthymeasureStatus;
	}
	
	
	public GeneralCurrentStatus(DailyReport dailRreport,
			HealthprofileGoalstatuschecker healthprofileGoalstatuschecker,
			HealthymeasureStatus healthymeasureStatus) {
		super();
		this.dailRreport = dailRreport;
		this.healthprofileGoalstatuschecker = healthprofileGoalstatuschecker;
		this.healthymeasureStatus = healthymeasureStatus;
	}
	
	public GeneralCurrentStatus() {
		
	}
	

}
