package projsde.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import projsde.rest.model.Healthymeasure;



@Stateless
@LocalBean//Will map the resource to the URL /ehealth/v2
@Path("/HealthyMeasurement")
public class HealthyMeasurementValueResource {

	
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// THIS IS NOT WORKING
	@PersistenceUnit(unitName="introsde-jpa")
	EntityManager entityManager;
	
	// THIS IS NOT WORKING
    @PersistenceContext(unitName = "introsde-jpa",type=PersistenceContextType.TRANSACTION)
    private EntityManagerFactory entityManagerFactory;

	// Return the list of people to the user in the browser
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public List<Healthymeasure> getHealthymeasure() {
		System.out.println("Getting list of people...");
	    List<Healthymeasure> hm = Healthymeasure.getAll();
	    System.out.println("the number of people which are registered are");
	    System.out.print(hm.size());
	    System.out.println("first name: " + hm.get(0).getNmeasurement());
		return hm;
	}


	// retuns the number of people
	// to get the total number of records
	@GET
	@Path("{measurment}")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public Healthymeasure getMeasurment(@PathParam("measurment") String mt) {
		System.out.println("Healthymeasure for specific type of measure");

		Healthymeasure hm = Healthymeasure.getHealthymeasureById(mt);
		if (hm == null)
			throw new RuntimeException("Get: Healthymeasure with " + mt + " not found");
		return hm;
	}

	
}
