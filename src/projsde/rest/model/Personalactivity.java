package projsde.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlTransient;

import projsde.rest.dao.LifeCoachDao;


/**
 * The persistent class for the personalactivities database table.
 * 
 */
@Entity
@Table(name="personalactivities")
//@NamedQuery(name="Personalactivity.findAll", query="SELECT p FROM Personalactivity p")

@NamedQueries({@NamedQuery(name="Personalactivity.findAll", query="SELECT p FROM Personalactivity p"),
@NamedQuery(name="Personalactivity.findBbPp", query="SELECT p FROM Personalactivity p WHERE p.personprofile=:ppfhp"),
@NamedQuery(name="Personalactivity.findByPpMdDa", query="SELECT p FROM Personalactivity p WHERE p.personprofile=:ppfhp AND p.measurementdefinition=:mdfhp AND p.id.padate=:adate"),
@NamedQuery(name="Personalactivity.findfortoday", query="SELECT p FROM Personalactivity p WHERE p.personprofile=:ppfhp AND p.id.padate=:adate"),
@NamedQuery(name="Personalactivity.findByPpandMd", query="SELECT p FROM Personalactivity p WHERE p.personprofile=:ppfhp AND p.measurementdefinition=:mdfhp")})

@XmlRootElement(name="personalactivity")
public class Personalactivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonalactivityPK id;

	
	
	@Column(nullable=false)
	private int pavalue;

	//bi-directional many-to-one association to Measurementdefinition
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pameasurement", nullable=false, insertable=true, updatable=true)
	private Measurementdefinition measurementdefinition;

	//bi-directional many-to-one association to Personprofile
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid", nullable=false, insertable=true, updatable=true)
	private Personprofile personprofile;

	public Personalactivity() {
	}

	//@XmlAttribute 
	
	public PersonalactivityPK getId() {
		return this.id;
	}

	public void setId(PersonalactivityPK id) {
		this.id = id;
	}

//	@XmlAttribute 
	public int getPavalue() {
		return this.pavalue;
	}

	public void setPavalue(int pavalue) {
		this.pavalue = pavalue;
	}
	@XmlTransient
//	@XmlElement
	public Measurementdefinition getMeasurementdefinition() {
		return this.measurementdefinition;
	}

	public void setMeasurementdefinition(Measurementdefinition measurementdefinition) {
		this.measurementdefinition = measurementdefinition;
	}
	@XmlTransient
//	@XmlElement
	public Personprofile getPersonprofile() {
		return this.personprofile;
	}

	public void setPersonprofile(Personprofile personprofile) {
		this.personprofile = personprofile;
	}

	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Personalactivity getPersonalactivitylById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Personalactivity pa = em.find(Personalactivity.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return pa;
	}
	
	public static List<Personalactivity> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Personalactivity> list = em.createNamedQuery("Personalactivity.findAll", Personalactivity.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	public static List<Personalactivity> getbypersonprofile(Personprofile pp){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Personalactivity> pa=em.createNamedQuery("Personalactivity.findBbPp", Personalactivity.class).setParameter("ppfhp", pp).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return pa;
	}

	public static List<Personalactivity> getbyPeProandMeDefi(Personprofile pp, Measurementdefinition md){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Personalactivity> pa=em.createNamedQuery("Personalactivity.findByPpandMd", Personalactivity.class).setParameter("ppfhp", pp).setParameter("mdfhp", md).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return pa;
	}
	public static Personalactivity getbyPeProandMeDefiandDate(Personprofile pp, Measurementdefinition md, Date adat){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		Personalactivity pa=em.createNamedQuery("Personalactivity.findByPpMdDa", Personalactivity.class).setParameter("ppfhp", pp).setParameter("mdfhp", md).setParameter("adate", adat).getSingleResult();
		LifeCoachDao.instance.closeConnections(em);
		return pa;
	}

	public static List<Personalactivity> gettodayactivity(Personprofile pp, Date adat){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Personalactivity> pa=em.createNamedQuery("Personalactivity.findfortoday", Personalactivity.class).setParameter("ppfhp", pp).setParameter("adate", adat).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return pa;
	}

	
	public static Personalactivity savePersonalactivity(Personalactivity pa) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(pa);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return pa;
	}
	
	public static Personalactivity updatePersonalactivity(Personalactivity pa) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		pa=em.merge(pa);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return pa;
	}
	
	public static void removePersonalactivity(Personalactivity pa) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    pa=em.merge(pa);
	    em.remove(pa);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

	
	
}