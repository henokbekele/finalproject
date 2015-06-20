package projsde.rest.dao;
import projsde.rest.model.Personprofile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum LifeCoachDao {
	instance;
	private EntityManagerFactory emf;
	
	private LifeCoachDao() {
		if (emf!=null) {
			emf.close();
		}
		emf = Persistence.createEntityManagerFactory("introsde-jpa");
	}
	
	public EntityManager createEntityManager() {
		try {
			return emf.createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    
	}

	public void closeConnections(EntityManager em) {
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static Personprofile getPersonById(int personId) {
		EntityManager em = instance.createEntityManager();
		Personprofile p = em.find(Personprofile.class, personId);
		instance.closeConnections(em);
		return p;
	}
	
	public static List<Personprofile> getAll() {
		EntityManager em = instance.createEntityManager();
	    List<Personprofile> list = em.createNamedQuery("Personprofile.findAll", Personprofile.class).getResultList();
	    instance.closeConnections(em);
	    return list;
	}
	
	// add other database global access operations

}
