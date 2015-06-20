package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import projsde.rest.dao.LifeCoachDao;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the healthprofile database table.
 * 
 */
@Entity
@Table(name="healthprofile")
@NamedQueries({@NamedQuery(name="Healthprofile.findAll", query="SELECT h FROM Healthprofile h"),
@NamedQuery(name="Healthprofile.findBbPp", query="SELECT h FROM Healthprofile h WHERE h.personprofile=:ppfhp"),
@NamedQuery(name="Healthprofile.findByPpandMd", query="SELECT h FROM Healthprofile h WHERE h.personprofile=:ppfhp AND h.measurementdefinition=:mdfhp")})
@XmlRootElement
public class Healthprofile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HealthprofilePK id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date pdate;

	@Column(nullable=false)
	private float pvalue;

	//bi-directional many-to-one association to Measurementdefinition
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pmeasurement", nullable=false, insertable=true, updatable=true)
	private Measurementdefinition measurementdefinition;

	//bi-directional many-to-one association to Personprofile
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pid", nullable=false, insertable=true, updatable=true)
	private Personprofile personprofile;

	public Healthprofile() {
	}

	public HealthprofilePK getId() {
		return this.id;
	}

	public void setId(HealthprofilePK id) {
		this.id = id;
	}

	public Date getPdate() {
		return this.pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public float getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(float pvalue) {
		this.pvalue = pvalue;
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
	public static Healthprofile getHealthprofileById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Healthprofile hp = em.find(Healthprofile.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return hp;
	}
	
	public static List<Healthprofile> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Healthprofile> list = em.createNamedQuery("Healthprofile.findAll", Healthprofile.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	public static List<Healthprofile> getbypersonprofile(Personprofile pp){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Healthprofile> hp=em.createNamedQuery("Healthprofile.findBbPp", Healthprofile.class).setParameter("ppfhp", pp).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return hp;
	}

	public static Healthprofile getbyPeProandMeDefi(Personprofile pp, Measurementdefinition md){
		try{
			EntityManager em=LifeCoachDao.instance.createEntityManager();
			Healthprofile hp=em.createNamedQuery("Healthprofile.findByPpandMd", Healthprofile.class).setParameter("ppfhp", pp).setParameter("mdfhp", md).getSingleResult();
			LifeCoachDao.instance.closeConnections(em);
			return hp;
		}
		catch(Exception e){
		    return null; }
	}

	
	public static Healthprofile saveHealthprofile(Healthprofile hp) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(hp);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hp;
	}
	
	public static Healthprofile updateHealthprofile(Healthprofile hp) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		hp=em.merge(hp);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hp;
	}
	
	public static void removeHealthprofile(Healthprofile hp) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    hp=em.merge(hp);
	    em.remove(hp);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
	
}