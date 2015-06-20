package projsde.rest.model;

import java.util.List;

  

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;  
  
@XmlRootElement  
public class Activitygoals {
	private List<Activitygoal> activitygoal;

	@XmlElement
	public List<Activitygoal> getActivitygoal() {
		return activitygoal;
	}

	public void setActivitygoal(List<Activitygoal> activitygoal) {
		this.activitygoal = activitygoal;
	}

	public Activitygoals(List<Activitygoal> activitygoal) {
		super();
		this.activitygoal = activitygoal;
	}  
	
	public Activitygoals(){}
}
