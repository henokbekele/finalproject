package projsde.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DailyActivityStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false)
	private String sid;
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date activitydate;
	@Column(nullable=false)
	private String activitytype;
	@Column(nullable=false)
	private float activityvalue;
	@Column(nullable=false)
	private String  activitystatus;

	public Date getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(Date activitydate) {
		this.activitydate = activitydate;
	}

	public String getActivitytype() {
		return activitytype;
	}

	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
	}

	public float getActivityvalue() {
		return activityvalue;
	}

	public void setActivityvalue(float activityvalue) {
		this.activityvalue = activityvalue;
	}

	public String getActivitystatus() {
		return activitystatus;
	}

	public void setActivitystatus(String activitystatus) {
		this.activitystatus = activitystatus;
	}

	public DailyActivityStatus(String sid, Date activitydate, String activitytype,
			float activityvalue, String activitystatus) {
		super();
		this.sid=sid;
		this.activitydate = activitydate;
		this.activitytype = activitytype;
		this.activityvalue = activityvalue;
		this.activitystatus = activitystatus;
	}
	public DailyActivityStatus(){}
	
	
	
}
