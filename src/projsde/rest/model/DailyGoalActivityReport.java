package projsde.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DailyGoalActivityReport {

	private float plandactivityvalue;
	private float activityvalue;
	private String activitytype;
	private String activitystatus;
	
	public float getPlandactivityvalue() {
		return plandactivityvalue;
	}
	public void setPlandactivityvalue(float plandactivityvalue) {
		this.plandactivityvalue = plandactivityvalue;
	}
	public float getActivityvalue() {
		return activityvalue;
	}
	public void setActivityvalue(float activityvalue) {
		this.activityvalue = activityvalue;
	}
	public String getActivitytype() {
		return activitytype;
	}
	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
	}
	public String getActivitystatus() {
		return activitystatus;
	}
	public void setActivitystatus(String activitystatus) {
		this.activitystatus = activitystatus;
	}
	public DailyGoalActivityReport(float plandactivityvalue,
			float activityvalue, String activitytype, String activitystatus) {
		super();
		this.plandactivityvalue = plandactivityvalue;
		this.activityvalue = activityvalue;
		this.activitytype = activitytype;
		this.activitystatus = activitystatus;
	}
	
	public DailyGoalActivityReport(){}
	
	
	
}
