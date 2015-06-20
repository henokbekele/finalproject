package projsde.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import projsde.rest.dao.LifeCoachDao;


/**
 * The persistent class for the motivationalphrases database table.
 * 
 */
@Entity
@Table(name="motivationalphrases")
@NamedQuery(name="Motivationalphras.findAll", query="SELECT m FROM Motivationalphras m")

@XmlRootElement
public class Motivationalphras implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mid;

	private String motivationph;

	public Motivationalphras() {
	}

	public int getMid() {
		return this.mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMotivationph() {
		return this.motivationph;
	}

	public void setMotivationph(String motivationph) {
		this.motivationph = motivationph;
	}

	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Motivationalphras getMotivationalphrasById(int mpId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Motivationalphras p = em.find(Motivationalphras.class, mpId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Motivationalphras> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all ...");
	    List<Motivationalphras> list = em.createNamedQuery("Motivationalphras.findAll", Motivationalphras.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
}