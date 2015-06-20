package projsde.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import projsde.rest.dao.LifeCoachDao;


/**
 * The persistent class for the hhealthprofile database table.
 * 
 */
@Entity
@Table(name="hhealthprofile")
//@NamedQuery(name="Hhealthprofile.findAll", query="SELECT h FROM Hhealthprofile h")
@NamedQueries({@NamedQuery(name="Hhealthprofile.findAll", query="SELECT h FROM Hhealthprofile h"),
@NamedQuery(name="Hhealthprofile.findBbPp", query="SELECT h FROM Hhealthprofile h WHERE h.personprofile=:ppfhp"),
@NamedQuery(name="Hhealthprofile.findByPpandMd", query="SELECT h FROM Hhealthprofile h WHERE h.personprofile=:ppfhp AND h.measurementdefinition=:mdfhp")})

@XmlRootElement
public class Hhealthprofile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HhealthprofilePK id;

/*	@Column(nullable=false)
	private float hvale;
*/
	//bi-directional many-to-one association to Measurementdefinition
	@ManyToOne
	@JoinColumn(name="hmeasurement", nullable=false, insertable=true, updatable=true)
	private Measurementdefinition measurementdefinition;

	//bi-directional many-to-one association to Personprofile
	@ManyToOne
	@JoinColumn(name="pid", nullable=false, insertable=true, updatable=true)
	private Personprofile personprofile;

	public Hhealthprofile() {
	}

	public HhealthprofilePK getId() {
		return this.id;
	}

	public void setId(HhealthprofilePK id) {
		this.id = id;
	}
/*
	public float getHvale() {
		return this.hvale;
	}

	public void setHvale(float hvale) {
		this.hvale = hvale;
	}
	
*/	
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
	/*public static Hhealthprofile getHhealthprofileById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Hhealthprofile hp = em.find(Hhealthprofile.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return hp;
	}
	*/
	public static List<Hhealthprofile> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Hhealthprofile> list = em.createNamedQuery("Hhealthprofile.findAll", Hhealthprofile.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	public static List<Hhealthprofile> getbypersonprofile(Personprofile pp){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Hhealthprofile> hp=em.createNamedQuery("Hhealthprofile.findBbPp", Hhealthprofile.class).setParameter("ppfhp", pp).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return hp;
	}

	public static List<Hhealthprofile> getbyPeProandMeDefi(Personprofile pp, Measurementdefinition md){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Hhealthprofile> hp=em.createNamedQuery("Hhealthprofile.findByPpandMd", Hhealthprofile.class).setParameter("ppfhp", pp).setParameter("mdfhp", md).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return hp;
	}

	
	public static Hhealthprofile saveHhealthprofile(Hhealthprofile hp) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(hp);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hp;
	}
	
	public static Hhealthprofile updateHhealthprofile(Hhealthprofile hp) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		hp=em.merge(hp);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hp;
	}
	
	public static void removeHhealthprofile(Hhealthprofile hp) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    hp=em.merge(hp);
	    em.remove(hp);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

	
}