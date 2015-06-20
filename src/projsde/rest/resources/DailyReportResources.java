package projsde.rest.resources;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import projsde.rest.model.Activitygoal;
import projsde.rest.model.Activitygoals;
import projsde.rest.model.Bodymassindex;
import projsde.rest.model.DailyGoalActivityReport;
import projsde.rest.model.DailyReport;
import projsde.rest.model.PersonalActivityGoalStatusCheck;
import projsde.rest.model.Personalactivity;
import projsde.rest.model.Personalactivitys;
import projsde.rest.model.Reminders;


public class DailyReportResources {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public DailyReportResources(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public DailyReportResources(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(MediaType.TEXT_XML)
	public DailyReport getPersonalDailyReportk() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, DOMException, ParseException, JAXBException {

		
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		
		
		
		Response R;
		String statusR="";

		R= service.path("person/"+id+ "/Activitygoal/Today").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml=R.readEntity(String.class);	
		
		//List<Activitygoal> ToAG = R.readEntity(String.class);
		
		System.out.println(unformattedXml);
		
		System.out.print("henok unmarshal  start");
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(
				unformattedXml)));
		
		
		
		//File file = new File(unformattedXml);
		JAXBContext jaxbContextActivitygoals = JAXBContext.newInstance(Activitygoals.class);
		Unmarshaller jaxbUnmarshallerActivitygoals = jaxbContextActivitygoals.createUnmarshaller();
		Activitygoals activitGoalsForToday = (Activitygoals) jaxbUnmarshallerActivitygoals.unmarshal(doc);
		System.out.println("End date");
		

		
		
		
		Response R2;
		String statusR2="";

		R2= service.path("person/"+id+ "/Activity/Today").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml2=R2.readEntity(String.class);	
		
		//List<Activitygoal> ToAG = R.readEntity(String.class);
		
		System.out.println(unformattedXml2);
		
		System.out.print("henok unmarshal  start activity 2");
		DocumentBuilderFactory domFactory2 = DocumentBuilderFactory
				.newInstance();
		domFactory2.setNamespaceAware(true);
		DocumentBuilder builder2 = domFactory2.newDocumentBuilder();
		Document doc2 = builder2.parse(new InputSource(new StringReader(
				unformattedXml2)));
		
		System.out.print("\nunmarshal  start activity 2");
		
		JAXBContext jaxbContextPersonalactivitys = JAXBContext.newInstance(Personalactivitys.class);
	
		System.out.print("\n jaxbcontest");
		Unmarshaller jaxbUnmarshallerPersonalactivitys = jaxbContextPersonalactivitys.createUnmarshaller();
		System.out.print("\n unmarshller");
		Personalactivitys personalactivitysForToday = (Personalactivitys) jaxbUnmarshallerPersonalactivitys.unmarshal(doc2);

		System.out.print("henok unmarshal end");
		System.out.println("End date Personalactivitys for the first activity"+personalactivitysForToday.getPersonalactivity().get(0).getId().getPameasurement());

		
		
		
		
		Response R3;

		R3= service.path("person/"+id+ "/Reminder/Today").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml3=R3.readEntity(String.class);	
		
		//List<Activitygoal> ToAG = R.readEntity(String.class);
		
		System.out.println(unformattedXml3);
		
		System.out.print("henok unmarshal  start reminder");
		DocumentBuilderFactory domFactory3 = DocumentBuilderFactory
				.newInstance();
		domFactory3.setNamespaceAware(true);
		DocumentBuilder builder3 = domFactory3.newDocumentBuilder();
		Document doc3 = builder3.parse(new InputSource(new StringReader(
				unformattedXml3)));
		
		System.out.print("\nunmarshal  start reminder 3");
		
		JAXBContext jaxbContextReminders = JAXBContext.newInstance(Reminders.class);
	
		System.out.print("\n jaxbcontest Reminder");
		Unmarshaller jaxbUnmarshallerReminders = jaxbContextReminders.createUnmarshaller();
		System.out.print("\n unmarshller Reminder");
		Reminders remidersForToday = (Reminders) jaxbUnmarshallerReminders.unmarshal(doc3);

		System.out.print("henok unmarshal end");
		System.out.println("End date Reminder for the first activity"+remidersForToday.getReminder().get(0).getToberemind());

		
		
		
		
		
		int mn=1;
		
		Response R4;

		R4= service.path("Motivationalphras/"+mn).request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml4=R4.readEntity(String.class);	
		System.out.println(unformattedXml4);
		
		DocumentBuilderFactory domFactory4 = DocumentBuilderFactory
				.newInstance();
		domFactory4.setNamespaceAware(true);
		DocumentBuilder builder4 = domFactory4.newDocumentBuilder();
		Document doc4 = builder4.parse(new InputSource(new StringReader(
				unformattedXml4)));
		//Document doc2;
		
		XPathFactory factory4 = XPathFactory.newInstance();
		XPath xpath = factory4.newXPath();
		XPathExpression expr4 = xpath.compile("/motivationalphras/motivationph/text()");
		Object result4 = expr4.evaluate(doc4, XPathConstants.NODESET);

		NodeList nodes4 = (NodeList) result4;
		String motivation=nodes4.item(0).getNodeValue();			
		System.out.println("Todays quote: "+motivation);

		
		
		List<DailyGoalActivityReport> dailyGoalActivityReport= new ArrayList<DailyGoalActivityReport>();

		
		int sizeofpa=personalactivitysForToday.getPersonalactivity().size();
		int sizeofag=activitGoalsForToday.getActivitygoal().size();
		List<Personalactivity> personalactivity=personalactivitysForToday.getPersonalactivity();
		List<Activitygoal> activitygoal =activitGoalsForToday.getActivitygoal();
		
		String activitytype=null;
		float plandactivityvalue=0;
		float personalactivityvalue=0;
		int flag=0;
		String actStatus=null;

		int[] arraypa= new int[sizeofpa];
		
		for(int index=0;index<sizeofpa;index++)
		{
			arraypa[index]=0;
		}
		//String aiid = null;

		for (int i=0;i<sizeofag;i++)
		{
			flag=0;
			for(int j=0;j<sizeofpa;j++)
			{
				if(activitygoal.get(i).getId().getAgmeasurement().equalsIgnoreCase(personalactivity.get(j).getId().getPameasurement())){
					flag=1;
					activitytype=activitygoal.get(i).getId().getAgmeasurement();
					plandactivityvalue=activitygoal.get(i).getAgvalue();
					personalactivityvalue=personalactivity.get(j).getPavalue();
					
				}
				
				arraypa[j]=1;
			}
			if(flag==0)
			{
				activitytype=activitygoal.get(i).getId().getAgmeasurement();
				plandactivityvalue=activitygoal.get(i).getAgvalue();
				personalactivityvalue=0;

			}
			
			if(personalactivityvalue==0){
				actStatus="failed to try";
			}
			else if(plandactivityvalue>personalactivityvalue){
				actStatus="Goal tried but not achived";
			}
			else if(plandactivityvalue==personalactivityvalue){
				actStatus="Goal achived";
			}
			else{
				actStatus="Goal achived and outperformed";
			}
			dailyGoalActivityReport.add(new DailyGoalActivityReport(plandactivityvalue,personalactivityvalue,activitytype,actStatus) );

		}
		
		for(int index=0;index<sizeofpa;index++)
		{
			if(arraypa[index]==0){
				activitytype=personalactivity.get(index).getId().getPameasurement();
				plandactivityvalue=0;
				personalactivityvalue=personalactivity.get(index).getPavalue();
				actStatus="You are inspired today and performed "+activitytype+" which was not pland";

			}
			dailyGoalActivityReport.add(new DailyGoalActivityReport(plandactivityvalue,personalactivityvalue,activitytype,actStatus) );
		}
		
		
		DailyReport  dailyReport = new DailyReport();
		
		//to asssign the today setDailyGoalActivityReport, reminder and motivation for dailyReport object
		
		dailyReport.setDailyGoalActivityReport(dailyGoalActivityReport);
		dailyReport.setReminder(remidersForToday.getReminder());
		dailyReport.setMotivationalphrase(motivation);
		
		//return activitGoalsForToday.getActivitygoal();

		return dailyReport;
		
		}

		
		private static URI getBaseURI() {
			return UriBuilder.fromUri("http://localhost:5900/projectsdeone/")
					.build();
		}



}
