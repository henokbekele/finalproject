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
import javax.ws.rs.core.UriInfo;

import projsde.rest.model.Healthprofile;
import projsde.rest.model.HealthprofilePK;
import projsde.rest.model.Hhealthprofile;
import projsde.rest.model.HhealthprofilePK;
import projsde.rest.model.Measurementdefinition;
import projsde.rest.model.Personprofile;

public class HealthprofileResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public HealthprofileResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public HealthprofileResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Healthprofile> getHealthprofile() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Healthprofile> healthprofile = Healthprofile.getbypersonprofile(pp);
		if (healthprofile == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return healthprofile;
	}

	 // for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Healthprofile> getPersonHTML() {
		Personprofile pp = this.getPersonprofileById(id);
		List<Healthprofile> healthprofile = Healthprofile.getbypersonprofile(pp);
		if (healthprofile == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		return healthprofile;
	}

	
	
	
	@POST
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Healthprofile newHealthprofile(Healthprofile healthprofile) throws IOException {
		System.out.println("Creating new Healthprofile...");
		//System.out.print(healthprofile.getPersonprofile().get)
		Healthprofile  nhp = new Healthprofile();
		System.out.println("retriving the personprofile...");
		Personprofile npp= Personprofile.getPersonprofileById(id);
		//System.out.println("Measurment definaation");
		//System.out.println(healthprofile.getId().getPmeasurement());
		
		System.out.println("retriving measuredifination...");
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(healthprofile.getId().getPmeasurement());
		System.out.println("set personalprofile to healthprofile...");
		nhp.setPersonprofile(npp);
		
		/* to remove the old profile measure to hhistoryprofile */
		
		Healthprofile old= Healthprofile.getbyPeProandMeDefi(npp, nmd);
		if (old!=null)
		{
			Hhealthprofile hhp = new Hhealthprofile();
			HhealthprofilePK hhpid = new HhealthprofilePK();
			hhpid.setHdate(old.getPdate());
			hhpid.setHmeasurement(nmd.getMeasurement());
			hhpid.setPid(npp.getPid());
			//set hvalue form pvalue
			hhpid.setHvale(old.getPvalue());
			
			//set id
			hhp.setId(hhpid);
/*			//set hvalue form pvalue
			hhp.setHvale(old.getPvalue());
	*/		
			hhp.setMeasurementdefinition(nmd);
			hhp.setPersonprofile(npp);
			
			//save it on oldvalue to hhealthprofile(history)
			//Hhealthprofile.saveHhealthprofile(hhp);
			
			//remove from healthprofile as it is old
			Healthprofile.removeHealthprofile(old);
		}
		
		
		System.out.println("set measurdefination to healthprofile...");
		nhp.setMeasurementdefinition(nmd);
		System.out.println("set date to healthprofile...");
		nhp.setPdate(healthprofile.getPdate());
		System.out.println("set mvalue to healthprofile...");
		nhp.setPvalue(healthprofile.getPvalue());	
		System.out.println("set mvalue to healthprofile id...");
		nhp.setId(healthprofile.getId() );
		return Healthprofile.saveHealthprofile(nhp);
		//return Healthprofile.saveHealthprofile(healthprofile);
	}

	
	//to be modified for measure date and person
	
	@PUT
	@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Healthprofile putHealthprofile(Healthprofile healthprofile) {
		System.out.println("--> Updating Healthprofile... " +this.id);
		System.out.println("--> "+healthprofile.toString());
		//Healthprofilegoal.updateHealthprofilegoal(healthprofile);
		Healthprofile  nhp = new Healthprofile();
		
		HealthprofilePK hhpid = new HealthprofilePK();
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(healthprofile.getId().getPmeasurement());

		System.out.println("set measurdefination to healthprofile...");
		nhp.setMeasurementdefinition(nmd);
		System.out.println("set date to healthprofile...");
		nhp.setPdate(healthprofile.getPdate());
		System.out.println("set mvalue to healthprofile...");
		nhp.setPvalue(healthprofile.getPvalue());	
		System.out.println("set mvalue to healthprofile id...");
		
		hhpid.setPmeasurement(nmd.getMeasurement());
		hhpid.setPid(id);
		nhp.setPersonprofile(Personprofile.getPersonprofileById(id));
		nhp.setId(hhpid);
		return Healthprofile.updateHealthprofile(nhp);

		}


	
	
	
	@GET
	@Path("{measured}")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public Healthprofile HealthprofileByidandmeasure(@PathParam("measured") String mdif) throws IOException {
		Personprofile npp= Personprofile.getPersonprofileById(id);
		Measurementdefinition nmd = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdif);
		Healthprofile hp=Healthprofile.getbyPeProandMeDefi(npp, nmd);
		return hp;

	}
	
	@DELETE
	@Path("{measured}")
	public void deleteHealthprofilebymeasure(@PathParam("measured") String mdf ) {
		Personprofile pp = Personprofile.getPersonprofileById(id);
		Measurementdefinition md = Measurementdefinition.getMeasurementdefinitionByMeasurment(mdf);
		Healthprofile c=Healthprofile.getbyPeProandMeDefi(pp, md);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		Healthprofile.removeHealthprofile(c);
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
