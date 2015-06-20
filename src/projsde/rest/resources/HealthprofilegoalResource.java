package projsde.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;



//import projsde.rest.model.Healthprofile;
import projsde.rest.model.Healthprofilegoal;
import projsde.rest.model.HealthprofilegoalPK;
import projsde.rest.model.Measurementdefinition;
import projsde.rest.model.Personprofile;

public class HealthprofilegoalResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public HealthprofilegoalResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public HealthprofilegoalResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Healthprofilegoal> getHealthprofilegoal() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Healthprofilegoal> healthprofilegoal = Healthprofilegoal.getbypersonprofile(pp);
		if (healthprofilegoal == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return healthprofilegoal;
	}

	 // for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Healthprofilegoal> getPersonHTMLgoal() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Healthprofilegoal> healthprofilegoal = Healthprofilegoal.getbypersonprofile(pp);
		if (healthprofilegoal == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return healthprofilegoal;
	}

	
	
	
	@POST
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Healthprofilegoal newHealthprofilegoal(Healthprofilegoal healthprofilegoal) throws IOException {
		System.out.println("Creating new Healthprofile...");
		//System.out.print(healthprofile.getPersonprofile().get)
		Healthprofilegoal  nhpg = new Healthprofilegoal();
		System.out.println("retriving the personprofile...");
		Personprofile npp= Personprofile.getPersonprofileById(id);
		//System.out.println("Measurment definaation");
		//System.out.println(healthprofile.getId().getPmeasurement());
		
		
		//System.out.println("retriving measuredifination...on postman...");
		//System.out.print(healthprofilegoal.getId().getGmeasurement());
		
		//nhpg.setId(id);
		
		System.out.println("create primeary key of health profile goal and set the values..");
		HealthprofilegoalPK hpgid= new HealthprofilegoalPK();	
		hpgid.setGmeasurement(healthprofilegoal.getId().getGmeasurement());	
		//hpgid.setGmeasurement(nmd.getMeasurement());	
		System.out.println("set goaldate for healthprofilegoal...");	
		hpgid.setGoaldate(healthprofilegoal.getId().getGoaldate());	
		//System.out.println("set personalprofile to healthprofilegoal...");	
		hpgid.setPid(npp.getPid());	
		nhpg.setId(hpgid);		
		System.out.println("set mvalue to healthprofilegoal...");
		nhpg.setGoalvalue(healthprofilegoal.getGoalvalue());

		System.out.println("set personalprofile to healthprofilegoal...");
		nhpg.setPersonprofile(npp);		
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(healthprofilegoal.getId().getGmeasurement());
		System.out.println("set measurdefination to healthprofilegoal...");
		nhpg.setMeasurementdefinition(nmd);

		
		Healthprofilegoal c=Healthprofilegoal.getHealthprofilegoalById(healthprofilegoal.getId());
		if (c == null){
			return Healthprofilegoal.saveHealthprofilegoal(nhpg);
		}
		else{
		Healthprofilegoal.removeHealthprofilegoal(c);
		return Healthprofilegoal.saveHealthprofilegoal(nhpg);
		}
		
		//nhpg.s

		
	}

	
	
	@GET
	@Path("{measured}")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Healthprofilegoal HealthprofilegoalByidandmeasure(@PathParam("measured") String mdif) throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdif);
		Healthprofilegoal hpg=Healthprofilegoal.getbyPeProandMeDefi(npp, nmd);
		return hpg;

	}
	
	//to be modified for measure date and person
	/*
	@PUT
	@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Healthprofilegoal putHealthprofilegoal(Healthprofilegoal healthprofilegoal) {
		System.out.println("--> Updating Healthprofilegoal... " +this.id);
		System.out.println("--> "+healthprofilegoal.toString());
		//Healthprofilegoal.updateHealthprofilegoal(healthprofilegoal);
		
		return Healthprofilegoal.updateHealthprofilegoal(healthprofilegoal);

		}

*/
	
	
	@DELETE
	@Path("{measured}")
	public void deleteHealthprofilegoalbymeasure(@PathParam("measured") String mdf ) {
		Personprofile pp = Personprofile.getPersonprofileById(id);
		Measurementdefinition md = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdf);
		Healthprofilegoal c=Healthprofilegoal.getbyPeProandMeDefi(pp, md);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		Healthprofilegoal.removeHealthprofilegoal(c);
	}

	
	
/*	
	public Healthprofile getHealthprofileById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Healthprofile healthprofile = Healthprofile.getHealthprofileById(personId);
		System.out.println("Person: "+healthprofile.toString());
		return healthprofile;
	}
	*/
	
	public Personprofile getPersonprofileById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Personprofile personprofile = Personprofile.getPersonprofileById(personId);
		System.out.println("Person profile: "+personprofile.toString());
		return personprofile;
	}
}