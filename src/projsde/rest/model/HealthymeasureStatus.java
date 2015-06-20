package projsde.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="healthymeasureStatus")
public class HealthymeasureStatus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String measuretype;
	private String status;
	private String recomendation;
	
	//@XmlElement
	public String getMeasuretype() {
		return measuretype;
	}
	public void setMeasuretype(String measuretype) {
		this.measuretype = measuretype;
	}
	//@XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//@XmlElement
	public String getRecomendation() {
		return recomendation;
	}
	public void setRecomendation(String recomendation) {
		this.recomendation = recomendation;
	}
	public HealthymeasureStatus(String measuretype, String status,
			String recomendation) {
		super();
		this.measuretype = measuretype;
		this.status = status;
		this.recomendation = recomendation;
	}
	public HealthymeasureStatus() {
		super();
	}
	
	
	

}
