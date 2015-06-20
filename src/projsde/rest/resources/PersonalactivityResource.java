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

import projsde.rest.model.Healthprofilegoal;
import projsde.rest.model.HealthprofilegoalPK;
import projsde.rest.model.Measurementdefinition;
import projsde.rest.model.Personalactivity;
import projsde.rest.model.PersonalactivityPK;
import projsde.rest.model.Personprofile;

public class PersonalactivityResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonalactivityResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonalactivityResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Personalactivity> getPersonalactivity() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Personalactivity> personalactivity = Personalactivity.getbypersonprofile(pp);
		if (personalactivity == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return personalactivity;
	}

	 // for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Personalactivity> getPersonalactivityHTML() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Personalactivity> personalactivity =Personalactivity.getbypersonprofile(pp);
		if (personalactivity == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return personalactivity;
	}

	
	
	
	@POST
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Personalactivity newPersonalactivity(Personalactivity personalactivity) throws IOException {
		System.out.println("Creating new Personalactivity...");
		//System.out.print(healthprofile.getPersonprofile().get)
		Personalactivity  npa = new Personalactivity();
		System.out.println("retriving the personprofile...");
		Personprofile npp= Personprofile.getPersonprofileById(id);
		//System.out.println("Measurment definaation");
		//System.out.println(healthprofile.getId().getPmeasurement());
		
		
		//System.out.println("retriving measuredifination...on postman...");
		//System.out.print(healthprofilegoal.getId().getGmeasurement());
		
		//nhpg.setId(id);
		
		System.out.println("create primeary key of Personalactivity and set the values..");
		PersonalactivityPK papk= new PersonalactivityPK();	
		papk.setPameasurement(personalactivity.getId().getPameasurement());	
		//hpgid.setGmeasurement(nmd.getMeasurement());	
		System.out.println("set activity date for personalactivity...");	
		papk.setPadate(personalactivity.getId().getPadate());	
		//System.out.println("set personalprofile to healthprofilegoal...");	
		papk.setPid(npp.getPid());	
		npa.setId(papk);		
		System.out.println("set Person activity value to ...");
		npa.setPavalue(personalactivity.getPavalue());
		System.out.println("set personalprofile to personactivit...");
		
		npa.setPersonprofile(npp);		
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(personalactivity.getId().getPameasurement());
		System.out.println("set measurdefination to healthprofilegoal...");
		npa.setMeasurementdefinition(nmd);

		
		//nhpg.s

		return Personalactivity.savePersonalactivity(npa);
	}

	
	@GET
	@Path("Today")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<Personalactivity> Personalactivitytoday() throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Date cudate = new Date();
		List<Personalactivity> hpg=Personalactivity.gettodayactivity(npp, cudate);
		if (hpg == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
	
		return hpg;

	}

	
	
	@GET
	@Path("{measured}")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<Personalactivity> PersonalactivityByidandmeasure(@PathParam("measured") String mdif) throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdif);
		List<Personalactivity> hpg=Personalactivity.getbyPeProandMeDefi(npp, nmd);
		return hpg;

	}
	

	
	public Personprofile getPersonprofileById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Personprofile personprofile = Personprofile.getPersonprofileById(personId);
		System.out.println("Person profile: "+personprofile.toString());
		return personprofile;
	}
}