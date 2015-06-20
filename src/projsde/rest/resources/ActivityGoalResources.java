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

import projsde.rest.model.Activitygoal;
import projsde.rest.model.ActivitygoalPK;
import projsde.rest.model.Healthprofilegoal;
import projsde.rest.model.HealthprofilegoalPK;
import projsde.rest.model.Measurementdefinition;
import projsde.rest.model.Personprofile;

public class ActivityGoalResources {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public ActivityGoalResources(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public ActivityGoalResources(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Activitygoal> getActivitygoal() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Activitygoal> activitygoal = Activitygoal.getbypersonprofile(pp);
		if (activitygoal == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return activitygoal;
	}

	 // for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Activitygoal> getPersonActivityHTMLgoal() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Activitygoal> activitygoal = Activitygoal.getbypersonprofile(pp);
		if (activitygoal == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return activitygoal;
	}

	
	
	
	@POST
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Activitygoal newActivitygoal(Activitygoal activitygoal) throws IOException {
		System.out.println("Creating new Activitygoal...");
		//System.out.print(healthprofile.getPersonprofile().get)
		Activitygoal  nag = new Activitygoal();
		System.out.println("retriving the personprofile...");
		Personprofile npp= Personprofile.getPersonprofileById(id);
		//System.out.println("Measurment definaation");
		//System.out.println(healthprofile.getId().getPmeasurement());
		
		
		//System.out.println("retriving measuredifination...on postman...");
		//System.out.print(healthprofilegoal.getId().getGmeasurement());
		
		//nhpg.setId(id);
		
		System.out.println("create primeary key of Activitygoal and set the values..");
		ActivitygoalPK nagpk= new ActivitygoalPK();	
		nagpk.setAgmeasurement(activitygoal.getId().getAgmeasurement());	
		//hpgid.setGmeasurement(nmd.getMeasurement());	
		System.out.println("set start goaldate for Activitygoal...");	
		nagpk.setAgstartdate(activitygoal.getId().getAgstartdate());	
		//System.out.println("set personalprofile to healthprofilegoal...");	
		nagpk.setPid(npp.getPid());
		nag.setId(nagpk);		
		System.out.println("set end date for Activitygoal...");
		nag.setAgenddate(activitygoal.getAgenddate());

		System.out.println("set whether the activity is perday or not...");
		nag.setAgperday(activitygoal.getAgperday());		

		System.out.println("set end date for Activitygoal...");
		nag.setAgvalue(activitygoal.getAgvalue());

		System.out.println("set personalprofile to Activitygoal...");
		nag.setPersonprofile(npp);		

		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(activitygoal.getId().getAgmeasurement());
		System.out.println("set measurdefination to healthprofilegoal...");
		nag.setMeasurementdefinition(nmd);

		
		//nhpg.s

		return Activitygoal.saveActivitygoal(nag);
	}

	@GET
	@Path("Today")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<Activitygoal> Activitygoalfortoday() throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Date cudate = new Date();
		List<Activitygoal> hpg=Activitygoal.getActivitygoalfortoday(npp, cudate);
		if (hpg == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
	
		return hpg;

	}

	
	
	@GET
	@Path("{measured}")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Activitygoal ActivitygoalByidandmeasure(@PathParam("measured") String mdif) throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdif);
		Activitygoal hpg=Activitygoal.getbyPeProandMeDefi(npp, nmd);
		return hpg;

	}
	
	//to be modified for measure date and person
	
	@DELETE
	@Path("{measured}")
	public void deleteActivitygoalbymeasure(@PathParam("measured") String mdf ) {
		Personprofile pp = Personprofile.getPersonprofileById(id);
		Measurementdefinition md = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdf);
		Activitygoal c=Activitygoal.getbyPeProandMeDefi(pp, md);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		Activitygoal.removeActivitygoal(c);
	}

	
	
	
	public Personprofile getPersonprofileById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Personprofile personprofile = Personprofile.getPersonprofileById(personId);
		System.out.println("Person profile: "+personprofile.toString());
		return personprofile;
	}
}