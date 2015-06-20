package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the hhealthprofile database table.
 * 
 */
@Embeddable
public class HhealthprofilePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int pid;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date hdate;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=30)
	private String hmeasurement;

	@Column(unique=true, nullable=false)
	private float hvale;

	public HhealthprofilePK() {
	}
	public int getPid() {
		return this.pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public java.util.Date getHdate() {
		return this.hdate;
	}
	public void setHdate(java.util.Date hdate) {
		this.hdate = hdate;
	}
	
	public float getHvale() {
		return this.hvale;
	}

	public void setHvale(float hvale) {
		this.hvale = hvale;
	}
	
	public String getHmeasurement() {
		return this.hmeasurement;
	}
	public void setHmeasurement(String hmeasurement) {
		this.hmeasurement = hmeasurement;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HhealthprofilePK)) {
			return false;
		}
		HhealthprofilePK castOther = (HhealthprofilePK)other;
		return 
			(this.pid == castOther.pid)
			&& this.hdate.equals(castOther.hdate)
			&& this.hmeasurement.equals(castOther.hmeasurement);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pid;
		hash = hash * prime + this.hdate.hashCode();
		hash = hash * prime + this.hmeasurement.hashCode();
		
		return hash;
	}
}