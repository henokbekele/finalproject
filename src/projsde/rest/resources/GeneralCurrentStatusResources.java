package projsde.rest.resources;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.text.ParseException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import javax.xml.xpath.XPathExpressionException;

import org.glassfish.jersey.client.ClientConfig;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import projsde.rest.model.Activitygoals;
import projsde.rest.model.DailyReport;
import projsde.rest.model.DailyReports;
import projsde.rest.model.GeneralCurrentStatus;
import projsde.rest.model.HealthprofileGoalstatuschecker;
import projsde.rest.model.HealthprofileGoalstatuscheckers;
import projsde.rest.model.HealthymeasureStatus;
import projsde.rest.model.Personalactivitys;
import projsde.rest.model.Personprofile;
import projsde.rest.model.Reminders;

@Stateless
@LocalBean
public class GeneralCurrentStatusResources {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public GeneralCurrentStatusResources(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public GeneralCurrentStatusResources(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public GeneralCurrentStatus getGeneralCurrentStatus() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, DOMException, ParseException, JAXBException {

		
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		
		
		
		Response R;
		String statusR="";

		R= service.path("person/"+id+ "/DailyReport").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml=R.readEntity(String.class);	
		
		//List<Activitygoal> ToAG = R.readEntity(String.class);
		
		System.out.println(unformattedXml);
		
		System.out.print("henok unmarshal DailyReport start");
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(
				unformattedXml)));
		
		System.out.println("unmarshal start ailyReport2"+unformattedXml);

		
		//File file = new File(unformattedXml);
		JAXBContext jaxbContextDailyReport = JAXBContext.newInstance(DailyReport.class);
		System.out.println("unmarshal JAXBContext dailyReport2");
	
		Unmarshaller jaxbUnmarshallerDailyReport = jaxbContextDailyReport.createUnmarshaller();
		System.out.println("unmarshal jaxbUnmarshallerDailyReports dailyReport2");

		DailyReport dailyReport = (DailyReport) jaxbUnmarshallerDailyReport.unmarshal(doc);
		System.out.println("End DailyReport");
		

		
		
		
		Response R2;
		String statusR2="";

		R2= service.path("person/"+id+ "/Hpgoalstatus").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml2=R2.readEntity(String.class);	
		
		//List<Activitygoal> ToAG = R.readEntity(String.class);
		
		System.out.println(unformattedXml2);
		
		System.out.print("henok unmarshal  Hpgoalstatus start activity 2");
		DocumentBuilderFactory domFactory2 = DocumentBuilderFactory
				.newInstance();
		domFactory2.setNamespaceAware(true);
		DocumentBuilder builder2 = domFactory2.newDocumentBuilder();
		Document doc2 = builder2.parse(new InputSource(new StringReader(
				unformattedXml2)));
		
		System.out.print("\nunmarshal  start Hpgoalstatus activity 2");
		
		JAXBContext jaxbContextHealthprofileGoalstatuscheckers = JAXBContext.newInstance(HealthprofileGoalstatuscheckers.class);
	
		System.out.print("\n jaxbcontest");
		Unmarshaller jaxbUnmarshallerHealthprofileGoalstatuscheckers = jaxbContextHealthprofileGoalstatuscheckers.createUnmarshaller();
		System.out.print("\n unmarshller");
		HealthprofileGoalstatuscheckers healthprofileGoalstatuscheckers = (HealthprofileGoalstatuscheckers) jaxbUnmarshallerHealthprofileGoalstatuscheckers.unmarshal(doc2);

		System.out.print("henok unmarshal HealthprofileGoalstatuschecker end");
		System.out.println("End date healthprofileGoalstatuscheckers for the first activity"+healthprofileGoalstatuscheckers.getHealthprofileGoalstatuschecker().get(0).getMmeasure());

		
		
		
		
		Response R3;

		R3= service.path("person/"+id+ "/bmistatus").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml3=R3.readEntity(String.class);	
		
		//List<Activitygoal> ToAG = R.readEntity(String.class);
		
		System.out.println(unformattedXml3);
		
		System.out.print("henok unmarshal  start HealthymeasureStatus");
		DocumentBuilderFactory domFactory3 = DocumentBuilderFactory
				.newInstance();
		domFactory3.setNamespaceAware(true);
		DocumentBuilder builder3 = domFactory3.newDocumentBuilder();
		Document doc3 = builder3.parse(new InputSource(new StringReader(
				unformattedXml3)));
		
		System.out.print("\nunmarshal  start HealthymeasureStatus 3");
		
		JAXBContext jaxbContextHealthymeasureStatus = JAXBContext.newInstance(HealthymeasureStatus.class);
	
		System.out.print("\n jaxbcontest Reminder");
		Unmarshaller jaxbUnmarshallerHealthymeasureStatus = jaxbContextHealthymeasureStatus.createUnmarshaller();
		System.out.print("\n unmarshller Reminder");
		HealthymeasureStatus healthymeasureStatus = (HealthymeasureStatus) jaxbUnmarshallerHealthymeasureStatus.unmarshal(doc3);

		System.out.print("henok unmarshal HealthymeasureStatus end");
		System.out.println("End date Reminder for the first activity"+healthymeasureStatus.getMeasuretype());

		GeneralCurrentStatus generalCurrentStatus=new GeneralCurrentStatus();
		generalCurrentStatus.setDailRreport(dailyReport);
		generalCurrentStatus.setHealthprofileGoalstatuschecker(healthprofileGoalstatuscheckers.getHealthprofileGoalstatuschecker().get(0));
		generalCurrentStatus.setHealthymeasureStatus(healthymeasureStatus);
		
		return generalCurrentStatus;
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:5900/projectsdeone/")
				.build();
	}


}
