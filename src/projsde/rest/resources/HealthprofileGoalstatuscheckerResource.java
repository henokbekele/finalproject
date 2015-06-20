package projsde.rest.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import projsde.rest.model.Healthprofile;
import projsde.rest.model.HealthprofileGoalstatuschecker;
import projsde.rest.model.Healthprofilegoal;
import projsde.rest.model.Personprofile;

public class HealthprofileGoalstatuscheckerResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public HealthprofileGoalstatuscheckerResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public HealthprofileGoalstatuscheckerResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<HealthprofileGoalstatuschecker> getHealthprofileGoalstatuschecker() {
		System.out.println("select person ");
		Personprofile pp = Personprofile.getPersonprofileById(id);
		System.out.println("fech person healthprofilegoal list");
		List<Healthprofilegoal> healthprofilegoal = Healthprofilegoal.getbypersonprofile(pp);
		System.out.println("healthprofilegoal listsize "+healthprofilegoal.size());
		System.out.println("fech person healthprofile list");
		List<Healthprofile> healthprofile = Healthprofile.getbypersonprofile(pp);
		System.out.println("healthprofile listsize "+healthprofile.size());
		List<HealthprofileGoalstatuschecker> hpgoalsclist= new ArrayList<HealthprofileGoalstatuschecker>();
	
/*		if (healthprofilegoal == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
*/
		
		if (healthprofilegoal != null){
		
			System.out.println("there are healthprofile");
		
		String mms;
		Date gdate;
		int gcid;
		float gvalue;
		Date pdate = null;
		float pvalue=0;
		int lgoalsize=healthprofilegoal.size();
		for (int i=0; i<lgoalsize; i++)
		{
			System.out.println("measure"+ healthprofilegoal.get(i).getId().getGmeasurement());
			//hpgoalsclist.get(i).setGid(i);
			gcid=i;
			System.out.println("iddddd");
			//hpgoalsclist.get(i).setHgoaldate(healthprofilegoal.get(i).getId().getGoaldate());
			gdate=healthprofilegoal.get(i).getId().getGoaldate();
			//hpgoalsclist.get(i).setHgvalue(healthprofilegoal.get(i).getGoalvalue());
			gvalue=healthprofilegoal.get(i).getGoalvalue();
			mms=healthprofilegoal.get(i).getId().getGmeasurement();
			//hpgoalsclist.get(i).setMmeasure(mms);
			
			for(int j=0; j<healthprofile.size(); j++)
			{
				if(mms.equals(healthprofile.get(j).getId().getPmeasurement()))
						{
					System.out.println("healthprofile date"+ healthprofile.get(j).getPdate());
					//hpgoalsclist.get(i).setHprofiledate(healthprofile.get(j).getPdate());
					pdate=healthprofile.get(j).getPdate();
					
					//hpgoalsclist.get(i).setHpvalue(healthprofile.get(j).getPvalue());
						
					pvalue=healthprofile.get(j).getPvalue();
						}
			}
			
			//checking the current value with its goal
			String rstatus="You ";
			float diff=pvalue - gvalue;
			
			if (diff<0)
			{
				diff=diff*-1;
				rstatus=rstatus.concat("are below your gooal by ");
				rstatus=rstatus.concat( Float.toString(diff));
			}
			else if(diff>0)
			{
				rstatus=rstatus.concat("are above your gooal by ");
				rstatus=rstatus.concat( Float.toString(diff));

			}
			else{
				rstatus=rstatus.concat(" achived your gooal ");
				
			}
			hpgoalsclist.add(new HealthprofileGoalstatuschecker( pdate, gdate, mms, pvalue, gvalue,rstatus));
		}
		}
		
System.out.println("sample of recommendation");
System.out.println( hpgoalsclist.get(0).getHgvalue() +"gvalue and  "+ hpgoalsclist.get(0).getHpvalue()+" pvalue and "+ hpgoalsclist.get(0).getMmeasure()+ " "+ hpgoalsclist.get(0).getStatus());
		
		return hpgoalsclist;
	}


	
}
