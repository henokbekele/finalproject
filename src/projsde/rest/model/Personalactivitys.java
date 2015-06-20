package projsde.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement(name="personalactivities")
public class Personalactivitys {

	private List<Personalactivity> personalactivity;

	@XmlElement
	public List<Personalactivity> getPersonalactivity() {
		return personalactivity;
	}

	public void setPersonalactivity(List<Personalactivity> personalactivity) {
		this.personalactivity = personalactivity;
	}

	public Personalactivitys(List<Personalactivity> personalactivity) {
		super();
		this.personalactivity = personalactivity;
	}
	
	public Personalactivitys(){}
}
