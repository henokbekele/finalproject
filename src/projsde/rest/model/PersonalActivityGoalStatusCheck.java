package projsde.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PersonalActivityGoalStatusCheck implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(unique=true, nullable=false)
//	 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Goal_SEQ")
//	 @SequenceGenerator(name="Goal_SEQ",sequenceName="Goal_SEQ",allocationSize=1)
	private String Pagsc;
	
	public String getPagsc() {
		return Pagsc;
	}

	public void setPagsc(String pagsc) {
		Pagsc = pagsc;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date ActivityStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date  ActivityEndDate;

	@Column(nullable=false)
	private String Activity;

	@Column(nullable=false)
	private float PlandActivityvalue;

	@Column(nullable=false)
	private List<DailyActivityStatus>  dailyactivities;

	public Date getActivityStartDate() {
		return ActivityStartDate;
	}

	public void setActivityStartDate(Date activityStartDate) {
		ActivityStartDate = activityStartDate;
	}

	public Date getActivityEndDate() {
		return ActivityEndDate;
	}

	public void setActivityEndDate(Date activityEndDate) {
		ActivityEndDate = activityEndDate;
	}

	public String getActivity() {
		return Activity;
	}

	public void setActivity(String activity) {
		Activity = activity;
	}

	public float getPlandActivityvalue() {
		return PlandActivityvalue;
	}

	public void setPlandActivityvalue(float plandActivityvalue) {
		PlandActivityvalue = plandActivityvalue;
	}

	public List<DailyActivityStatus> getDailyactivities() {
		return dailyactivities;
	}

	public void setDailyactivities(List<DailyActivityStatus> dailyactivities) {
		this.dailyactivities = dailyactivities;
	}

	public PersonalActivityGoalStatusCheck( String pagsc, String activity,
			Date activityStartDate, Date activityEndDate,
			float plandActivityvalue, List<DailyActivityStatus> dailyactivities) {
		super();
		Pagsc=pagsc;
		Activity = activity;
		ActivityStartDate = activityStartDate;
		ActivityEndDate = activityEndDate;
		PlandActivityvalue = plandActivityvalue;
		this.dailyactivities = dailyactivities;
	}

public PersonalActivityGoalStatusCheck()
{}


}
