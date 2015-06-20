package projsde.rest.resources;

import projsde.rest.model.Personprofile;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Stateless
@LocalBean
public class PersonResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Personprofile getPersonprofile() {
		Personprofile personprofile = this.getPersonprofileById(id);
		if (personprofile == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		System.out.print("name of the person"+personprofile.getLname());
		return personprofile;
	}

	 // for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Personprofile getPersonHTML() {
		Personprofile personprofile = this.getPersonprofileById(id);
		if (personprofile == null)
			throw new RuntimeException("Get: Person with " + id + " not found");

		return personprofile;
	}

	
	@PUT
	@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Personprofile putPersonprofile(Personprofile personprofile) {
		System.out.println("--> Updating Person... " +this.id);
		System.out.println("--> "+personprofile.toString());
		//Personprofile.updatePersonprofile(personprofile);
		
		Response res;
		
		Personprofile existing = getPersonprofileById(this.id);
		if (existing == null) {
			res = Response.noContent().build();
			return null;
		} else {
			existing.setDofb(personprofile.getDofb());
			existing.setFname(personprofile.getFname());
			existing.setLname(personprofile.getLname());
			existing.setSex(personprofile.getSex());
			

			res = Response.created(uriInfo.getAbsolutePath()).build();
			personprofile.setPid(this.id);
			//return Personprofile.updatePersonprofile(personprofile);
			return Personprofile.updatePersonprofile(existing);
		}

		

		
	}

	@DELETE
	public void deletePersonprofile() {
		Personprofile c = getPersonprofileById(id);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		Personprofile.removePersonprofile(c);
	}
	
	@Path("Reminder")
	public ReminderResource getReminder() {
		
		return new ReminderResource(uriInfo, request, id);
	}

	
	@Path("Healthprofile")
	public HealthprofileResource getHealthprofile() {
		
		return new HealthprofileResource(uriInfo, request, id);
	}

	@Path("Healthprofilegoal")
	public HealthprofilegoalResource getHealthprofilegoal() {
		
		return new HealthprofilegoalResource(uriInfo, request, id);
	}
	
	@Path("Activity")
	public PersonalactivityResource getPersonalactivity(){
		return new PersonalactivityResource(uriInfo, request, id);
	}
	
	@Path("Activitygoal")
	public ActivityGoalResources getActivityGoal()
	{
		return new ActivityGoalResources (uriInfo, request, id);
	}
	
	@Path("Hpgoalstatus")
	
	public HealthprofileGoalstatuscheckerResource getHealthprofileGoalstatuschecker()
	{
		return new HealthprofileGoalstatuscheckerResource(uriInfo, request, id);
	}
	
	@Path("PAGstatus")
	public PersonalActivityGoalStatusResources getPersonalActivityGoalStatusResources()
	{
		return new PersonalActivityGoalStatusResources(uriInfo, request, id);
	}

	@Path("bmi")
	public PersonalBmiResources getPersonalBmiResources()
	{
		return new PersonalBmiResources(uriInfo, request, id);
	}
	
	@Path("bmistatus")
	public PersonalBmiStatusResources getPersonalBmiStatusResources()
	{
		return new PersonalBmiStatusResources(uriInfo, request, id);
	}
	
	@Path("DailyReport")
	public DailyReportResources getDailyReport()
	{
		return new DailyReportResources(uriInfo, request, id);
	}
	@Path("GeneralCurrentStatus")
	public GeneralCurrentStatusResources getGeneralCurrentStatus()
	{
		return new GeneralCurrentStatusResources(uriInfo, request, id);
	}

/**/
	
	public Personprofile getPersonprofileById(int personId) {
		System.out.println("Reading person from DB with id: "+personId);
		//Person person = entityManager.find(Person.class, personId);
		
		Personprofile personprofile = Personprofile.getPersonprofileById(personId);
		System.out.println("Person: "+personprofile.toString());
		return personprofile;
	}
}
