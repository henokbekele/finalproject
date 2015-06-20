package projsde.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import projsde.rest.dao.LifeCoachDao;


/**
 * The persistent class for the healthymeasure database table.
 * 
 */
@Entity
@Table(name="healthymeasure")
@NamedQuery(name="Healthymeasure.findAll", query="SELECT h FROM Healthymeasure h")
@XmlRootElement
public class Healthymeasure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=30)
	private String nmeasurement;

	@Column(nullable=false)
	private float nmaxvalue;

	@Column(nullable=false)
	private float nminvalue;

	//bi-directional one-to-one association to Measurementdefinition
	@OneToOne
	@JoinColumn(name="nmeasurement", nullable=false, insertable=false, updatable=false)
	private Measurementdefinition measurementdefinition;

	public Healthymeasure() {
	}

	public String getNmeasurement() {
		return this.nmeasurement;
	}

	public void setNmeasurement(String nmeasurement) {
		this.nmeasurement = nmeasurement;
	}

	public float getNmaxvalue() {
		return this.nmaxvalue;
	}

	public void setNmaxvalue(float nmaxvalue) {
		this.nmaxvalue = nmaxvalue;
	}

	public float getNminvalue() {
		return this.nminvalue;
	}

	public void setNminvalue(float nminvalue) {
		this.nminvalue = nminvalue;
	}
	@XmlTransient
	public Measurementdefinition getMeasurementdefinition() {
		return this.measurementdefinition;
	}

	public void setMeasurementdefinition(Measurementdefinition measurementdefinition) {
		this.measurementdefinition = measurementdefinition;
	}

	
	
	
	
	
	public static Healthymeasure getHealthymeasureById(String measurement) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Healthymeasure hm = em.find(Healthymeasure.class, measurement);
		LifeCoachDao.instance.closeConnections(em);
		return hm;
	}
	
	public static List<Healthymeasure> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Healthymeasure> list = em.createNamedQuery("Healthymeasure.findAll", Healthymeasure.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Healthymeasure saveHealthymeasure(Healthymeasure hm) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(hm);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hm;
	}
	
	public static Healthymeasure updateHealthymeasure(Healthymeasure hm) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		hm=em.merge(hm);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hm;
	}
	
	public static void removeHealthymeasure(Healthymeasure hm) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    hm=em.merge(hm);
	    em.remove(hm);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
	
	
	
}