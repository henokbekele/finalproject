package projsde.rest.resources;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.glassfish.jersey.client.ClientConfig;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import projsde.rest.model.Activitygoal;
import projsde.rest.model.DailyActivityStatus;
import projsde.rest.model.Measurementdefinition;
import projsde.rest.model.PersonalActivityGoalStatusCheck;
import projsde.rest.model.Personalactivity;
import projsde.rest.model.PersonalactivityPK;
import projsde.rest.model.Personprofile;


public class PersonalActivityGoalStatusResources {

	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonalActivityGoalStatusResources(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonalActivityGoalStatusResources(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public List<PersonalActivityGoalStatusCheck> getPersonalActivityGoalStatusCheck() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, DOMException, ParseException {

		
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());

		
		
		
		System.out.println("select person ");
		Personprofile pp = Personprofile.getPersonprofileById(id);
		System.out.println("fech person activitygoallist list");
		List<Activitygoal> activitygoal = Activitygoal.getbypersonprofile(pp);
		System.out.println("Activitygoal listsize "+activitygoal.size());
		System.out.println("fech person healthprofile list");
		//List<Healthprofile> healthprofile = Healthprofile.getbypersonprofile(pp);
	//	System.out.println("healthprofile listsize "+healthprofile.size());
		List<PersonalActivityGoalStatusCheck> PerActGoalStatus= new ArrayList<PersonalActivityGoalStatusCheck>();
	
/*		if (healthprofilegoal == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
*/
		
		if (activitygoal != null){
		
			System.out.println("there are healthprofile");
		
		String activitytype;
		float plandactivityvalue;
		Date startdate;
		Date enddate;
		String iid = null;
		String aiid = null;
		
		int lgoalsize=activitygoal.size();
		for (int i=0; i<lgoalsize; i++)
		{
			iid=Integer.toString(pp.getPid())+"_"+Integer.toString(i);

			System.out.println("measure"+ activitygoal.get(i).getId().getAgmeasurement());
			//hpgoalsclist.get(i).setGid(i);
			//gcid=i;
			//System.out.println("iddddd");
			//hpgoalsclist.get(i).setHgoaldate(healthprofilegoal.get(i).getId().getGoaldate());
			startdate=activitygoal.get(i).getId().getAgstartdate();
			//hpgoalsclist.get(i).setHgvalue(healthprofilegoal.get(i).getGoalvalue());
			enddate=activitygoal.get(i).getAgenddate();
			
			activitytype=activitygoal.get(i).getId().getAgmeasurement();
			Measurementdefinition mda= Measurementdefinition.getMeasurementdefinitionByMeasurment(activitytype);
			plandactivityvalue=activitygoal.get(i).getAgvalue();
			List<DailyActivityStatus> dailyactivity = new ArrayList<DailyActivityStatus>();
			int numday =(int) ((enddate.getTime()-startdate.getTime())/(1000*60*60*24));
			
			System.out.println("daily activities size"+ dailyactivity.size()+" number of day is that"+numday);
			for (int d=0;d<=numday;d++)
				{
				
				System.out.println("for each date");
				Date acdate = new Date();

				acdate.setTime(startdate.getTime()+d*1000*60*60*24);
	
	/**/
	/*
				Response R2;
				String statusR2="";
				R2= service.path("person/"+pp.getPid()+ "/Activity/"+mda.getMeasurement()).request().accept(MediaType.TEXT_XML).get(Response.class);
				String unformattedXml=R2.readEntity(String.class);	

				System.out.println(unformattedXml);
			
				
				DocumentBuilderFactory domFactory = DocumentBuilderFactory
						.newInstance();
				domFactory.setNamespaceAware(true);
				DocumentBuilder builder = domFactory.newDocumentBuilder();
				Document doc = builder.parse(new InputSource(new StringReader(
						unformattedXml)));
				//Document doc2;
				XPathFactory factory = XPathFactory.newInstance();
				XPath xpath = factory.newXPath();
				XPathExpression expr = xpath.compile("/personalactivities/*");
				Object result = expr.evaluate(doc, XPathConstants.NODESET);

				NodeList nodes = (NodeList) result;
				int nodelength=nodes.getLength();			
				System.out.println("Node length is: "+nodelength);
				
				List<Personalactivity> peracti= new ArrayList<Personalactivity>(nodelength);
				peracti = null;
				System.out.println("percti");
				for (int ni=0; ni<nodelength; ni++)
				{
					System.out.println(ni);
*//*					doc2=	builder.parse(new InputSource(new StringReader(
							nodes.item(ni).getNodeValue())));
*//*
					Personalactivity tempda =new Personalactivity();
					//tempda=null;

					int nl=ni+1;
					XPathFactory factory2 = XPathFactory.newInstance();
					XPath xpath2 = factory2.newXPath();
					XPathExpression expr2 = xpath2.compile("/personalactivities/personalactivity["+nl+"]/id/padate/text()");
					Object result2 = expr2.evaluate(doc, XPathConstants.NODESET);				
					NodeList nodepadate = (NodeList) result2;
					System.out.println("creat id");
					PersonalactivityPK paid =new PersonalactivityPK();
					System.out.println("a");
					String dddate=nodepadate.item(0).getNodeValue();
					System.out.println("ddda date"+dddate);
					//Date mydate = null;
					String[] dadd= new String[4]; 
					int jj=0;
					  for (String retval: dddate.split("-", 3)){
						  dadd[jj]=retval;
						  jj++;
					  }
					  String part2=dadd[2];
					  jj=2;
					  for (String retval: part2.split("T", 2)){
						  dadd[jj]=retval;
						  jj++;
					  }
					  for (int ki=0; ki<dadd.length; ki++)
					  {
						  System.out.println(dadd[ki]);
					  }
					//  mydate.setTime(1000*60*60*24*dadd[3]);
				//	  mydate.set(Integer.parseInt(dadd[0]), Integer.parseInt(dadd[1]), Integer.parseInt(dadd[2]));
				//	System.out.println(mydate);

					Date mydate= new GregorianCalendar(Integer.parseInt(dadd[0]), Integer.parseInt(dadd[1]), Integer.parseInt(dadd[2])).getTime();
							System.out.println(mydate);

							//DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ");
					//System.out.println("ddd");
					
					Date dPadate;// = df.parse(dddate);
					//dPadate.
				//	dPadate.s
					//System.out.println("c");
					
					paid.setPadate(mydate);
					System.out.println("dateassienged");
					System.out.println("c");

					XPathExpression expr3 = xpath.compile("/personalactivities/personalactivity["+nl+"]/id/pameasurement/text()");
					System.out.println("c1");
					Object result3 = expr3.evaluate(doc, XPathConstants.NODESET);				
					System.out.println("c2");
					NodeList nodepameasurement = (NodeList) result3;
					String aa=nodepameasurement.item(0).getNodeValue();
					System.out.println(aa);
					paid.setPameasurement(nodepameasurement.item(0).getNodeValue());
					
					System.out.println("pamesurmentassienged");
					XPathExpression expr4 = xpath.compile("/personalactivities/personalactivity["+nl+"]/id/pid["+nl+"]/text()");
					Object result4 = expr4.evaluate(doc, XPathConstants.NODESET);				
					NodeList nodepid= (NodeList) result4;
					paid.setPid(Integer.parseInt(nodepid.item(0).getNodeValue()));				
					//peracti.get(ni).setId(paid);
					
					System.out.println("pidassienged");
					
					XPathExpression expr5 = xpath.compile("/personalactivities/personalactivity["+nl+"]/pavalue["+ni+"]/text()");
					System.out.println("p1");
					Object result5 = expr5.evaluate(doc, XPathConstants.NODESET);				
					System.out.println("p2");
					NodeList nodepvalue= (NodeList) result5;
					System.out.println("p3");
					//paid.se
					tempda.setPavalue(Integer.parseInt(nodepvalue.item(0).getNodeValue()));
					System.out.println("p4");
					//peracti.get(ni).setPavalue(Integer.parseInt(nodepvalue.item(0).getNodeValue()));
					tempda.setId(paid);
					System.out.println("pvalueassinged");
				}
				
				
	*/
				
				
	/**/			
				
				
				List<Personalactivity> personalactivities= Personalactivity.getbyPeProandMeDefi(pp, mda);
				System.out.println("for each personalactivities size "+ personalactivities.size()+" ...pa size is: ");
				Personalactivity personalactivity = new Personalactivity();

		//		System.out.println(personalactivity.getId().getPameasurement());
				
				int flag=0;
				for(int pi=0; pi<personalactivities.size();pi++)
				{
					System.out.println("pi :"+pi+ "date 1 " + personalactivities.get(pi).getId().getPadate()+ "date2 "+acdate);
					if(personalactivities.get(pi).getId().getPadate().equals(acdate))
					{
						flag=1;
						System.out.println(" the same date");
						PersonalactivityPK papk = new  PersonalactivityPK();
						papk.setPadate(personalactivities.get(pi).getId().getPadate());
						papk.setPameasurement(personalactivities.get(pi).getId().getPameasurement());
						papk.setPid(personalactivities.get(pi).getId().getPid());
						personalactivity.setId(papk);
					
						personalactivity.setMeasurementdefinition(mda);
						personalactivity.setPersonprofile(pp);
						personalactivity.setPavalue(personalactivities.get(pi).getPavalue());

					}
					
				}
				
				
				//System.out.println("personalactivity valu"+ personalactivity.getPavalue()+" type of day activity is that "+ personalactivity.getId().getPameasurement());
				
				Date adate;
				
				String acttype;
				
				float actvalue;
				
				String  actstatus;

				if(flag==0)
				{
					adate=acdate;
					acttype=mda.getMeasurement();
					actvalue=0;
					actstatus="faild";
					aiid=Integer.toString(pp.getPid())+"_"+Integer.toString(i)+acttype;
				}
				else {
					
					
					adate=acdate;
					acttype=mda.getMeasurement();
					actvalue=personalactivity.getPavalue();
					aiid=Integer.toString(pp.getPid())+"_"+Integer.toString(i)+acttype;
					
					if(actvalue >= plandactivityvalue)
					{
					actstatus="achived";
					}
					else
					{
						actstatus="tried but faild";
					}
				}
							
				dailyactivity.add(new DailyActivityStatus(aiid, adate, acttype, actvalue, actstatus));
				System.out.println("dailyactivity size "+ dailyactivity.size());
				}
			
			
			
			PerActGoalStatus.add(new PersonalActivityGoalStatusCheck(iid, activitytype, startdate, enddate, plandactivityvalue, dailyactivity) );
			
		}
		}
		
System.out.println("sample of PerActGoalStatus size is"+ PerActGoalStatus.size());
		
		return PerActGoalStatus;
	}
	
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:5900/projectsdeone/")
				.build();
	}


	
	
}
