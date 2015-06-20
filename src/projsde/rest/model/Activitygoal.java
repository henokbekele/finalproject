package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


import projsde.rest.dao.LifeCoachDao;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the activitygoal database table.
 * 
 */
@Entity
@Table(name="activitygoal")
//@NamedQuery(name="Activitygoal.findAll", query="SELECT a FROM Activitygoal a")

@NamedQueries({@NamedQuery(name="Activitygoal.findAll", query="SELECT a FROM Activitygoal a"),
@NamedQuery(name="Activitygoal.findBbPp", query="SELECT a FROM Activitygoal a WHERE a.personprofile=:ppfhp"),
@NamedQuery(name="Activitygoal.findfortoday", query="SELECT a FROM Activitygoal a WHERE a.personprofile=:ppfhp AND a.id.agstartdate<=:cdate AND a.agenddate>=:cdate"),
@NamedQuery(name="Activitygoal.findByPpandMd", query="SELECT a FROM Activitygoal a WHERE a.personprofile=:ppfhp AND a.measurementdefinition=:mdfhp")})


@XmlRootElement
public class Activitygoal implements Serializable {

/*	
 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActivitygoalPK id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date agenddate;

	@Column(nullable=false)
	private int agperday;

	@Column(nullable=false)
	private float agvalue;

	//bi-directional many-to-one association to Measurementdefinition
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="agmeasurement", nullable=false, insertable=true, updatable=true)
	private Measurementdefinition measurementdefinition;

	//bi-directional many-to-one association to Personprofile
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid", nullable=false, insertable=true, updatable=true)
	private Personprofile personprofile;

	public Activitygoal() {
	}

	public ActivitygoalPK getId() {
		return this.id;
	}

	public void setId(ActivitygoalPK id) {
		this.id = id;
	}

	public Date getAgenddate() {
		return this.agenddate;
	}

	public void setAgenddate(Date agenddate) {
		this.agenddate = agenddate;
	}

	public int getAgperday() {
		return this.agperday;
	}

	public void setAgperday(int agperday) {
		this.agperday = agperday;
	}

	public float getAgvalue() {
		return this.agvalue;
	}

	public void setAgvalue(float agvalue) {
		this.agvalue = agvalue;
	}
	
	@XmlTransient
	public Measurementdefinition getMeasurementdefinition() {
		return this.measurementdefinition;
	}

	public void setMeasurementdefinition(Measurementdefinition measurementdefinition) {
		this.measurementdefinition = measurementdefinition;
	}

	@XmlTransient
	public Personprofile getPersonprofile() {
		return this.personprofile;
	}

	public void setPersonprofile(Personprofile personprofile) {
		this.personprofile = personprofile;
	}

	
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Activitygoal getActivitygoalById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Activitygoal ag = em.find(Activitygoal.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return ag;
	}
	
	public static List<Activitygoal> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Activitygoal> list = em.createNamedQuery("Activitygoal.findAll", Activitygoal.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	public static List<Activitygoal> getbypersonprofile(Personprofile pp){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Activitygoal> ag=em.createNamedQuery("Activitygoal.findBbPp", Activitygoal.class).setParameter("ppfhp", pp).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return ag;
	}

	public static Activitygoal getbyPeProandMeDefi(Personprofile pp, Measurementdefinition md){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		Activitygoal ag=em.createNamedQuery("Activitygoal.findByPpandMd", Activitygoal.class).setParameter("ppfhp", pp).setParameter("mdfhp", md).getSingleResult();
		LifeCoachDao.instance.closeConnections(em);
		return ag;
	}


	public static List<Activitygoal> getActivitygoalfortoday(Personprofile pp, Date tdate){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Activitygoal> ag=em.createNamedQuery("Activitygoal.findfortoday", Activitygoal.class).setParameter("ppfhp", pp).setParameter("cdate", tdate).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return ag;
	}

	
	public static Activitygoal saveActivitygoal(Activitygoal ag) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(ag);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return ag;
	}
	
	public static Activitygoal updateActivitygoal(Activitygoal ag) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		ag=em.merge(ag);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return ag;
	}
	
	public static void removeActivitygoal(Activitygoal ag) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    ag=em.merge(ag);
	    em.remove(ag);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

	
	
}