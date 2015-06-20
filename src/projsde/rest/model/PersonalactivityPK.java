package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

/**
 * The primary key class for the personalactivities database table.
 * 
 */
@Embeddable
public class PersonalactivityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int pid;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=30)
	private String pameasurement;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date padate;

	public PersonalactivityPK() {
	}
	@XmlElement
	public int getPid() {
		return this.pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@XmlElement
	public String getPameasurement() {
		return this.pameasurement;
	}
	public void setPameasurement(String pameasurement) {
		this.pameasurement = pameasurement;
	}
	@XmlElement
	public java.util.Date getPadate() {
		return this.padate;
	}
	public void setPadate(java.util.Date padate) {
		this.padate = padate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonalactivityPK)) {
			return false;
		}
		PersonalactivityPK castOther = (PersonalactivityPK)other;
		return 
			(this.pid == castOther.pid)
			&& this.pameasurement.equals(castOther.pameasurement)
			&& this.padate.equals(castOther.padate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pid;
		hash = hash * prime + this.pameasurement.hashCode();
		hash = hash * prime + this.padate.hashCode();
		
		return hash;
	}
}