package projsde.rest.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the healthprofilegoal database table.
 * 
 */
@Embeddable
public class HealthprofilegoalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int pid;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=30)
	private String gmeasurement;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date goaldate;

	public HealthprofilegoalPK() {
	}
	public int getPid() {
		return this.pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getGmeasurement() {
		return this.gmeasurement;
	}
	public void setGmeasurement(String gmeasurement) {
		this.gmeasurement = gmeasurement;
	}
	public java.util.Date getGoaldate() {
		return this.goaldate;
	}
	public void setGoaldate(java.util.Date goaldate) {
		this.goaldate = goaldate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HealthprofilegoalPK)) {
			return false;
		}
		HealthprofilegoalPK castOther = (HealthprofilegoalPK)other;
		return 
			(this.pid == castOther.pid)
			&& this.gmeasurement.equals(castOther.gmeasurement)
			&& this.goaldate.equals(castOther.goaldate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pid;
		hash = hash * prime + this.gmeasurement.hashCode();
		hash = hash * prime + this.goaldate.hashCode();
		
		return hash;
	}
}