package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import projsde.rest.dao.LifeCoachDao;

import java.util.List;


/**
 * The persistent class for the measurementdefinition database table.
 * 
 */
@Entity
@Table(name="measurementdefinition")
@NamedQuery(name="Measurementdefinition.findAll", query="SELECT m FROM Measurementdefinition m")
@XmlRootElement
public class Measurementdefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=30)
	private String measurement;

	@Column(nullable=false, length=30)
	private String type;

	//bi-directional many-to-one association to Activitygoal
	@OneToMany(mappedBy="measurementdefinition")
	private List<Activitygoal> activitygoals;

	//bi-directional many-to-one association to Healthprofile
	@OneToMany(mappedBy="measurementdefinition")
	private List<Healthprofile> healthprofiles;

	//bi-directional many-to-one association to Healthprofilegoal
	@OneToMany(mappedBy="measurementdefinition")
	private List<Healthprofilegoal> healthprofilegoals;

	//bi-directional one-to-one association to Healthymeasure
	@OneToOne(mappedBy="measurementdefinition")
	private Healthymeasure healthymeasure;

	//bi-directional many-to-one association to Hhealthprofile
	@OneToMany(mappedBy="measurementdefinition")
	private List<Hhealthprofile> hhealthprofiles;

	//bi-directional many-to-one association to Personalactivity
	@OneToMany(mappedBy="measurementdefinition")
	private List<Personalactivity> personalactivities;

	public Measurementdefinition() {
	}

	public String getMeasurement() {
		return this.measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Activitygoal> getActivitygoals() {
		return this.activitygoals;
	}

	public void setActivitygoals(List<Activitygoal> activitygoals) {
		this.activitygoals = activitygoals;
	}

	public Activitygoal addActivitygoal(Activitygoal activitygoal) {
		getActivitygoals().add(activitygoal);
		activitygoal.setMeasurementdefinition(this);

		return activitygoal;
	}

	public Activitygoal removeActivitygoal(Activitygoal activitygoal) {
		getActivitygoals().remove(activitygoal);
		activitygoal.setMeasurementdefinition(null);

		return activitygoal;
	}

	public List<Healthprofile> getHealthprofiles() {
		return this.healthprofiles;
	}

	public void setHealthprofiles(List<Healthprofile> healthprofiles) {
		this.healthprofiles = healthprofiles;
	}

	public Healthprofile addHealthprofile(Healthprofile healthprofile) {
		getHealthprofiles().add(healthprofile);
		healthprofile.setMeasurementdefinition(this);

		return healthprofile;
	}

	public Healthprofile removeHealthprofile(Healthprofile healthprofile) {
		getHealthprofiles().remove(healthprofile);
		healthprofile.setMeasurementdefinition(null);

		return healthprofile;
	}

	public List<Healthprofilegoal> getHealthprofilegoals() {
		return this.healthprofilegoals;
	}

	public void setHealthprofilegoals(List<Healthprofilegoal> healthprofilegoals) {
		this.healthprofilegoals = healthprofilegoals;
	}

	public Healthprofilegoal addHealthprofilegoal(Healthprofilegoal healthprofilegoal) {
		getHealthprofilegoals().add(healthprofilegoal);
		healthprofilegoal.setMeasurementdefinition(this);

		return healthprofilegoal;
	}

	public Healthprofilegoal removeHealthprofilegoal(Healthprofilegoal healthprofilegoal) {
		getHealthprofilegoals().remove(healthprofilegoal);
		healthprofilegoal.setMeasurementdefinition(null);

		return healthprofilegoal;
	}

	public Healthymeasure getHealthymeasure() {
		return this.healthymeasure;
	}

	public void setHealthymeasure(Healthymeasure healthymeasure) {
		this.healthymeasure = healthymeasure;
	}

	public List<Hhealthprofile> getHhealthprofiles() {
		return this.hhealthprofiles;
	}

	public void setHhealthprofiles(List<Hhealthprofile> hhealthprofiles) {
		this.hhealthprofiles = hhealthprofiles;
	}

	public Hhealthprofile addHhealthprofile(Hhealthprofile hhealthprofile) {
		getHhealthprofiles().add(hhealthprofile);
		hhealthprofile.setMeasurementdefinition(this);

		return hhealthprofile;
	}

	public Hhealthprofile removeHhealthprofile(Hhealthprofile hhealthprofile) {
		getHhealthprofiles().remove(hhealthprofile);
		hhealthprofile.setMeasurementdefinition(null);

		return hhealthprofile;
	}

	public List<Personalactivity> getPersonalactivities() {
		return this.personalactivities;
	}

	public void setPersonalactivities(List<Personalactivity> personalactivities) {
		this.personalactivities = personalactivities;
	}

	public Personalactivity addPersonalactivity(Personalactivity personalactivity) {
		getPersonalactivities().add(personalactivity);
		personalactivity.setMeasurementdefinition(this);

		return personalactivity;
	}

	public Personalactivity removePersonalactivity(Personalactivity personalactivity) {
		getPersonalactivities().remove(personalactivity);
		personalactivity.setMeasurementdefinition(null);

		return personalactivity;
	}
	
	
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Measurementdefinition getMeasurementdefinitionByMeasurment(String measurmentd) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Measurementdefinition p = em.find(Measurementdefinition.class, measurmentd);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	

	

}