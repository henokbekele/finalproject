package projsde.rest.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the reminders database table.
 * 
 */
@Embeddable
public class ReminderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int pid;

	private int numberofremdperday;

	public ReminderPK() {
	}
	public int getPid() {
		return this.pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getNumberofremdperday() {
		return this.numberofremdperday;
	}
	public void setNumberofremdperday(int numberofremdperday) {
		this.numberofremdperday = numberofremdperday;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReminderPK)) {
			return false;
		}
		ReminderPK castOther = (ReminderPK)other;
		return 
			(this.pid == castOther.pid)
			&& (this.numberofremdperday == castOther.numberofremdperday);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pid;
		hash = hash * prime + this.numberofremdperday;
		
		return hash;
	}
}