package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;


/**
 * The primary key class for the activitygoal database table.
 * 
 */
@Embeddable
public class ActivitygoalPK implements Serializable {

	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int pid;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=30)
	private String agmeasurement;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date agstartdate;

	public ActivitygoalPK() {
	}
	@XmlAttribute
	public int getPid() {
		return this.pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@XmlAttribute
	public String getAgmeasurement() {
		return this.agmeasurement;
	}
	public void setAgmeasurement(String agmeasurement) {
		this.agmeasurement = agmeasurement;
	}
	@XmlAttribute
	public java.util.Date getAgstartdate() {
		return this.agstartdate;
	}
	public void setAgstartdate(java.util.Date agstartdate) {
		this.agstartdate = agstartdate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActivitygoalPK)) {
			return false;
		}
		ActivitygoalPK castOther = (ActivitygoalPK)other;
		return 
			(this.pid == castOther.pid)
			&& this.agmeasurement.equals(castOther.agmeasurement)
			&& this.agstartdate.equals(castOther.agstartdate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pid;
		hash = hash * prime + this.agmeasurement.hashCode();
		hash = hash * prime + this.agstartdate.hashCode();
		
		return hash;
	}

	
}