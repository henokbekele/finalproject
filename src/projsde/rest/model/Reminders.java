package projsde.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reminders {
	
	private List<Reminder> reminder;
	
	@XmlElement
	public List<Reminder> getReminder() {
		return reminder;
	}

	public void setReminder(List<Reminder> reminder) {
		this.reminder = reminder;
	}

	public Reminders(List<Reminder> reminder) {
		super();
		this.reminder = reminder;
	}

	public Reminders() {
		
	}

}
