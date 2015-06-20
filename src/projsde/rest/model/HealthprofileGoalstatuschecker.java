package projsde.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
public class HealthprofileGoalstatuschecker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
//	@Column(unique=true, nullable=false)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Goal_SEQ")
	 @SequenceGenerator(name="Goal_SEQ",sequenceName="Goal_SEQ",allocationSize=1)
	private int gid;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hgoaldate;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hprofiledate;

	@Column(nullable=false)
	private String mmeasure;

	@Column(nullable=false)
	private float hpvalue;

	@Column(nullable=false)
	private float hgvalue;

	@Column(nullable=false)
	private String status;
	
	public HealthprofileGoalstatuschecker(){
			
		}
	
	public HealthprofileGoalstatuschecker(Date hpdate, Date hgdate, String measure, float pvalue, float gvalue, String hstatus ){
		this.hprofiledate=hpdate;
		this.hgoaldate=hgdate;
		this.mmeasure=measure;
		this.hpvalue=pvalue;
		this.hgvalue=gvalue;
		this.status=hstatus;
		//this.gid=ggid;
	}
	
	@XmlTransient
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	
	public Date getHgoaldate() {
		return hgoaldate;
	}

	public void setHgoaldate(Date hgoaldate) {
		this.hgoaldate = hgoaldate;
	}

	public Date getHprofiledate() {
		return hprofiledate;
	}

	public void setHprofiledate(Date hprofiledate) {
		this.hprofiledate = hprofiledate;
	}

	public String getMmeasure() {
		return mmeasure;
	}

	public void setMmeasure(String mmeasure) {
		this.mmeasure = mmeasure;
	}

	public float getHpvalue() {
		return hpvalue;
	}

	public void setHpvalue(float hpvalue) {
		this.hpvalue = hpvalue;
	}

	public float getHgvalue() {
		return hgvalue;
	}

	public void setHgvalue(float hgvalue) {
		this.hgvalue = hgvalue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	

}
