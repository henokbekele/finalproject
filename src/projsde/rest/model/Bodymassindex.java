package projsde.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bodymassindex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float bmi;

	public float getBmi() {
		return bmi;
	}

	public void setBmi(float bmi) {
		this.bmi = bmi;
	}
	public Bodymassindex()
	{}

	public Bodymassindex(float bmi) {
		super();
		this.bmi = bmi;
	}
	
}
