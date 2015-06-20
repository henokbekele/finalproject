package projsde.rest.model;


import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import projsde.rest.dao.LifeCoachDao;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the personprofile database table.
 * 
 */
@Entity
@Table(name="personprofile")
@NamedQuery(name="Personprofile.findAll", query="SELECT p FROM Personprofile p")
@XmlRootElement
public class Personprofile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int pid;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dofb;

	@Column(nullable=false, length=30)
	private String fname;

	@Column(nullable=false, length=30)
	private String lname;

	@Column(nullable=false, length=1)
	private String sex;

	//bi-directional many-to-one association to Activitygoal
	@OneToMany(mappedBy="personprofile")
	private List<Activitygoal> activitygoals;

	//bi-directional many-to-one association to Healthprofile
	@OneToMany(mappedBy="personprofile",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Healthprofile> healthprofiles;

	//bi-directional many-to-one association to Healthprofilegoal
	@OneToMany(mappedBy="personprofile",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Healthprofilegoal> healthprofilegoals;

	//bi-directional many-to-one association to Hhealthprofile
	@OneToMany(mappedBy="personprofile",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Hhealthprofile> hhealthprofiles;

	//bi-directional many-to-one association to Personalactivity
	@OneToMany(mappedBy="personprofile",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Personalactivity> personalactivities;

	//bi-directional many-to-one association to Reminder
	@OneToMany(mappedBy="personprofile",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Reminder> reminders;

	public Personprofile() {
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Date getDofb() {
		return this.dofb;
	}

	public void setDofb(Date dofb) {
		this.dofb = dofb;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Activitygoal> getActivitygoals() {
		return this.activitygoals;
	}

	public void setActivitygoals(List<Activitygoal> activitygoals) {
		this.activitygoals = activitygoals;
	}

	public Activitygoal addActivitygoal(Activitygoal activitygoal) {
		getActivitygoals().add(activitygoal);
		activitygoal.setPersonprofile(this);

		return activitygoal;
	}

	public Activitygoal removeActivitygoal(Activitygoal activitygoal) {
		getActivitygoals().remove(activitygoal);
		activitygoal.setPersonprofile(null);

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
		healthprofile.setPersonprofile(this);

		return healthprofile;
	}

	public Healthprofile removeHealthprofile(Healthprofile healthprofile) {
		getHealthprofiles().remove(healthprofile);
		healthprofile.setPersonprofile(null);

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
		healthprofilegoal.setPersonprofile(this);

		return healthprofilegoal;
	}

	public Healthprofilegoal removeHealthprofilegoal(Healthprofilegoal healthprofilegoal) {
		getHealthprofilegoals().remove(healthprofilegoal);
		healthprofilegoal.setPersonprofile(null);

		return healthprofilegoal;
	}

	public List<Hhealthprofile> getHhealthprofiles() {
		return this.hhealthprofiles;
	}

	public void setHhealthprofiles(List<Hhealthprofile> hhealthprofiles) {
		this.hhealthprofiles = hhealthprofiles;
	}

	public Hhealthprofile addHhealthprofile(Hhealthprofile hhealthprofile) {
		getHhealthprofiles().add(hhealthprofile);
		hhealthprofile.setPersonprofile(this);

		return hhealthprofile;
	}

	public Hhealthprofile removeHhealthprofile(Hhealthprofile hhealthprofile) {
		getHhealthprofiles().remove(hhealthprofile);
		hhealthprofile.setPersonprofile(null);

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
		personalactivity.setPersonprofile(this);

		return personalactivity;
	}

	public Personalactivity removePersonalactivity(Personalactivity personalactivity) {
		getPersonalactivities().remove(personalactivity);
		personalactivity.setPersonprofile(null);

		return personalactivity;
	}

	public List<Reminder> getReminders() {
		return this.reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	public Reminder addReminder(Reminder reminder) {
		getReminders().add(reminder);
		reminder.setPersonprofile(this);

		return reminder;
	}

	public Reminder removeReminder(Reminder reminder) {
		getReminders().remove(reminder);
		reminder.setPersonprofile(null);

		return reminder;
	}

	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Personprofile getPersonprofileById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Personprofile p = em.find(Personprofile.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Personprofile> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Personprofile> list = em.createNamedQuery("Personprofile.findAll", Personprofile.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Personprofile savePersonprofile(Personprofile p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Personprofile updatePersonprofile(Personprofile p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removePersonprofile(Personprofile p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}