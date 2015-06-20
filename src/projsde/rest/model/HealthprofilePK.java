package projsde.rest.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the healthprofile database table.
 * 
 */
@Embeddable
public class HealthprofilePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int pid;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=30)
	private String pmeasurement;

	public HealthprofilePK() {
	}
	public int getPid() {
		return this.pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPmeasurement() {
		return this.pmeasurement;
	}
	public void setPmeasurement(String pmeasurement) {
		this.pmeasurement = pmeasurement;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HealthprofilePK)) {
			return false;
		}
		HealthprofilePK castOther = (HealthprofilePK)other;
		return 
			(this.pid == castOther.pid)
			&& this.pmeasurement.equals(castOther.pmeasurement);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pid;
		hash = hash * prime + this.pmeasurement.hashCode();
		
		return hash;
	}
}