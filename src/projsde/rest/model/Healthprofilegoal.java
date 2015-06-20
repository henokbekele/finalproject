package projsde.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import projsde.rest.dao.LifeCoachDao;


/**
 * The persistent class for the healthprofilegoal database table.
 * 
 */
@Entity
@Table(name="healthprofilegoal")

@NamedQueries({@NamedQuery(name="Healthprofilegoal.findAll", query="SELECT h FROM Healthprofilegoal h"),
@NamedQuery(name="Healthprofilegoal.findBbPp", query="SELECT h FROM Healthprofilegoal h WHERE h.personprofile=:ppfhp"),
@NamedQuery(name="Healthprofilegoal.findByPpandMd", query="SELECT h FROM Healthprofilegoal h WHERE h.personprofile=:ppfhp AND h.measurementdefinition=:mdfhp")})


@XmlRootElement
public class Healthprofilegoal implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HealthprofilegoalPK id;

	@Column(nullable=false)
	private float goalvalue;

	//bi-directional many-to-one association to Measurementdefinition
	@ManyToOne
	@JoinColumn(name="gmeasurement", nullable=false, insertable=true, updatable=true)
	private Measurementdefinition measurementdefinition;

	//bi-directional many-to-one association to Personprofile
	@ManyToOne
	@JoinColumn(name="pid", nullable=false, insertable=true, updatable=true)
	private Personprofile personprofile;

	public Healthprofilegoal() {
	}

	public HealthprofilegoalPK getId() {
		return this.id;
	}

	public void setId(HealthprofilegoalPK id) {
		this.id = id;
	}

	public float getGoalvalue() {
		return this.goalvalue;
	}

	public void setGoalvalue(float goalvalue) {
		this.goalvalue = goalvalue;
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
	public static Healthprofilegoal getHealthprofilegoalById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Healthprofilegoal hpg = em.find(Healthprofilegoal.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return hpg;
	}
	
	public static Healthprofilegoal getHealthprofilegoalById(HealthprofilegoalPK personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Healthprofilegoal hpk = em.find(Healthprofilegoal.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return hpk;
	}

	
	public static List<Healthprofilegoal> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Healthprofilegoal> list = em.createNamedQuery("Healthprofilegoal.findAll", Healthprofilegoal.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	public static List<Healthprofilegoal> getbypersonprofile(Personprofile pp){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Healthprofilegoal> hpg=em.createNamedQuery("Healthprofilegoal.findBbPp", Healthprofilegoal.class).setParameter("ppfhp", pp).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return hpg;
	}

	public static Healthprofilegoal getbyPeProandMeDefi(Personprofile pp, Measurementdefinition md){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		Healthprofilegoal hpg=em.createNamedQuery("Healthprofilegoal.findByPpandMd", Healthprofilegoal.class).setParameter("ppfhp", pp).setParameter("mdfhp", md).getSingleResult();
		LifeCoachDao.instance.closeConnections(em);
		return hpg;
	}

	
	public static Healthprofilegoal saveHealthprofilegoal(Healthprofilegoal hpg) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(hpg);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hpg;
	}
	
	public static Healthprofilegoal updateHealthprofilegoal(Healthprofilegoal hpg) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		hpg=em.merge(hpg);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hpg;
	}
	
	public static void removeHealthprofilegoal(Healthprofilegoal hpg) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    hpg=em.merge(hpg);
	    em.remove(hpg);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}