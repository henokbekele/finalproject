package projsde.rest.resources;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import projsde.rest.model.Personprofile;
import projsde.rest.model.Reminder;
import projsde.rest.model.ReminderPK;

public class ReminderResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public ReminderResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public ReminderResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Reminder> getReminder() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Reminder> reminder = Reminder.getbypersonprofile(pp);
		if (reminder == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return reminder;
	}

	 // for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Reminder> getPersonReminder() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Reminder> reminder = Reminder.getbypersonprofile(pp);
		if (reminder == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return reminder;
	}

	
	
	
	@POST
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Reminder newReminder(Reminder reminder) throws IOException {
		System.out.println("Creating new Reminder...");
		//System.out.print(healthprofile.getPersonprofile().get)
		Reminder  nag = new Reminder();
		System.out.println("retriving the personprofile...");
		Personprofile npp= Personprofile.getPersonprofileById(id);
		//System.out.println("Measurment definaation");
		//System.out.println(healthprofile.getId().getPmeasurement());
		
		
		//System.out.println("retriving measuredifination...on postman...");
		//System.out.print(healthprofilegoal.getId().getGmeasurement());
		
		//nhpg.setId(id);
		
		System.out.println("create primeary key of Reminder and set the values..");
		ReminderPK nagpk= new ReminderPK();	
		nagpk.setNumberofremdperday(reminder.getId().getNumberofremdperday());	
		nagpk.setPid(npp.getPid());
		nag.setId(nagpk);
		nag.setDate(reminder.getDate());
		nag.setToberemind(reminder.getToberemind());
		nag.setPersonprofile(npp);

		
		//nhpg.s
		System.out.println("saving the Reminder values..");

		return Reminder.saveReminder(nag);
	}

	
	
	@GET
	@Path("Tobe")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<Reminder> ReminderBydate() throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Date cudate = new Date();
		List<Reminder> lr= Reminder.getPersonReminderBydate(npp,cudate);
		if (lr == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return lr;

	}
	@GET
	@Path("Today")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<Reminder> ReminderforToday() throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Date cudate = new Date();
		List<Reminder> lr= Reminder.getReminderToday(npp,cudate);
		if (lr == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return lr;

	}
	
	
	public Personprofile getPersonprofileById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Personprofile personprofile = Personprofile.getPersonprofileById(personId);
		System.out.println("Person profile: "+personprofile.toString());
		return personprofile;
	}
}