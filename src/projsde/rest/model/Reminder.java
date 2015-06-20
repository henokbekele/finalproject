package projsde.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import projsde.rest.dao.LifeCoachDao;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reminders database table.
 * 
 */
@Entity
@Table(name="reminders")

@NamedQueries({@NamedQuery(name="Reminder.findAll", query="SELECT r FROM Reminder r"),
	@NamedQuery(name="Reminder.findBbPp", query="SELECT r FROM Reminder r WHERE r.personprofile=:ppfhp"),
	@NamedQuery(name="Reminder.findtoday", query="SELECT r FROM Reminder r WHERE r.personprofile=:ppfhp AND r.date=:currentdate"),
	@NamedQuery(name="Reminder.findcurrent", query="SELECT r FROM Reminder r WHERE r.personprofile=:ppfhp AND r.date>=:currentdate")})

@XmlRootElement
public class Reminder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReminderPK id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	private String toberemind;

	//bi-directional many-to-one association to Personprofile
	@ManyToOne
	@JoinColumn(name="pid")
	private Personprofile personprofile;

	public Reminder() {
	}

	public ReminderPK getId() {
		return this.id;
	}

	public void setId(ReminderPK id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getToberemind() {
		return this.toberemind;
	}

	public void setToberemind(String toberemind) {
		this.toberemind = toberemind;
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
	public static Reminder getReminderlById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Reminder ag = em.find(Reminder.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return ag;
	}
	
	public static List<Reminder> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Reminder> list = em.createNamedQuery("Reminder.findAll", Reminder.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	public static List<Reminder> getbypersonprofile(Personprofile pp){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Reminder> ag=em.createNamedQuery("Reminder.findBbPp", Reminder.class).setParameter("ppfhp", pp).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return ag;
	}

	public static List<Reminder>getPersonReminderBydate(Personprofile pp, Date ndate){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Reminder> ag=em.createNamedQuery("Reminder.findcurrent", Reminder.class).setParameter("ppfhp", pp).setParameter("currentdate", ndate).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return ag;
		}
	public static List<Reminder>getReminderToday(Personprofile pp, Date ndate){
		EntityManager em=LifeCoachDao.instance.createEntityManager();
		List<Reminder> ag=em.createNamedQuery("Reminder.findtoday", Reminder.class).setParameter("ppfhp", pp).setParameter("currentdate", ndate).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return ag;
		}


	
	public static Reminder saveReminder(Reminder ag) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(ag);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return ag;
	}
	
	public static Reminder updateReminder(Reminder ag) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		ag=em.merge(ag);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return ag;
	}
	
	public static void removeReminder(Reminder ag) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    ag=em.merge(ag);
	    em.remove(ag);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}


	
	
}