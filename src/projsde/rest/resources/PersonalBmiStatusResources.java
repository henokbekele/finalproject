package projsde.rest.resources;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;











import org.glassfish.jersey.client.ClientConfig;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;












import externalSoap.lengthc.net.webservicex.LengthUnit;
import externalSoap.lengthc.net.webservicex.LengthUnitSoap;
import externalSoap.lengthc.net.webservicex.Lengths;
import externalSoap.weightc.net.webservicex.ConvertWeights;
import externalSoap.weightc.net.webservicex.ConvertWeightsSoap;
import externalSoap.weightc.net.webservicex.WeightUnit;
import projsde.rest.model.HealthymeasureStatus;

public class PersonalBmiStatusResources {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonalBmiStatusResources(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonalBmiStatusResources(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public HealthymeasureStatus getHealthymeasureStatus() throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());

		
		System.out.println("start.............");
	
		Response R;
		String statusR="";
		R= service.path("person/"+id+"/bmi").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml=R.readEntity(String.class);	
		System.out.println(unformattedXml);
		
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(
				unformattedXml)));
		//Document doc2;
		System.out.println(unformattedXml);
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile("/bodymassindex/bmi/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		//System.out.println("1");
		
		NodeList nodes = (NodeList) result;
		String sbmi=nodes.item(0).getNodeValue();			
		System.out.println("The bmi value is: "+sbmi);

		Response R2;
		//String statusR="";
		R2= service.path("HealthyMeasurement/bmi").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml2=R2.readEntity(String.class);	
		
		Document doc2 = builder.parse(new InputSource(new StringReader(
				unformattedXml2)));
		//Document doc2;
		
//		XPathFactory factory = XPathFactory.newInstance();
//		XPath xpath = factory.newXPath();
		XPathExpression expr2 = xpath.compile("/healthymeasure/nmaxvalue/text()");
		Object result2 = expr2.evaluate(doc2, XPathConstants.NODESET);

		NodeList nodes2 = (NodeList) result2;
		String smaxvalue=nodes2.item(0).getNodeValue();			
		System.out.println("The normal max value is: "+smaxvalue);


		XPathExpression expr3 = xpath.compile("/healthymeasure/nminvalue/text()");
		Object result3 = expr3.evaluate(doc2, XPathConstants.NODESET);

		NodeList nodes3 = (NodeList) result3;
		String snminvalue=nodes3.item(0).getNodeValue();			
		System.out.println("The normal min value is: "+snminvalue);

/*		XPathExpression expr7 = xpath.compile("/healthymeasure/nmeasurement/text()");
		Object result7 = expr7.evaluate(doc2, XPathConstants.NODESET);

		NodeList nodes7 = (NodeList) result7;
		String mtt=nodes7.item(0).getNodeValue();			
		System.out.println("The Measurement type min value is: "+mtt);

		
		
		
		List<String> mtype =new ArrayList<String>();
		List<String> mmaxvaluee =new ArrayList<String>();
		List<String> mminvaluee =new ArrayList<String>();
		
		for(int j=0; j<nodes2.getLength();j++ )
		{
			mmaxvaluee.add(nodes2.item(0).getNodeValue());
		}
		
		for(int j=0; j<nodes3.getLength();j++ )
		{
			mminvaluee.add(nodes3.item(0).getNodeValue());
		}

		for(int j=0; j<nodes7.getLength();j++ )
		{
			mtype.add(nodes7.item(0).getNodeValue());
		}
*/
		float bminvalue =Float.parseFloat(snminvalue);
		float bmaxvalue=Float.parseFloat(smaxvalue);
		float bmiv=Float.parseFloat(sbmi);
	/*	
		float bminvalue =1;
		float bmaxvalue=2;
		float bmiv=3;
*/
		
		
		
		
		Response Rw;
		String statusRw="";
		Rw= service.path("person/"+id+ "/Healthprofile/weight").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXmlw=Rw.readEntity(String.class);	
		System.out.println(unformattedXmlw);
		
		DocumentBuilderFactory domFactoryw = DocumentBuilderFactory
				.newInstance();
		domFactoryw.setNamespaceAware(true);
		DocumentBuilder builderw = domFactoryw.newDocumentBuilder();
		Document docw = builderw.parse(new InputSource(new StringReader(
				unformattedXmlw)));
		//Document doc2;
		
		XPathFactory factoryw = XPathFactory.newInstance();
		XPath xpathw = factoryw.newXPath();
		XPathExpression exprw = xpathw.compile("/healthprofile/pvalue/text()");
		Object resultw = exprw.evaluate(docw, XPathConstants.NODESET);

		NodeList nodesw = (NodeList) resultw;
		String sweightw=nodesw.item(0).getNodeValue();			
		System.out.println("The weight value is: "+sweightw);

		Response Rh;
		String statusRh="";
		Rh= service.path("person/"+id+ "/Healthprofile/height").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml2h=Rh.readEntity(String.class);	
		System.out.println(unformattedXml2h);
		
		DocumentBuilderFactory domFactory2h = DocumentBuilderFactory
				.newInstance();
		domFactory2h.setNamespaceAware(true);
		DocumentBuilder builder2h = domFactory2h.newDocumentBuilder();
		Document doc2h = builder2h.parse(new InputSource(new StringReader(
				unformattedXml2h)));
		//Document doc2;
		
		XPathFactory factory2h = XPathFactory.newInstance();
		XPath xpath2h = factory2h.newXPath();
		XPathExpression expr2h = xpath.compile("/healthprofile/pvalue/text()");
		Object result2h = expr2h.evaluate(doc2h, XPathConstants.NODESET);

		NodeList nodes2h = (NodeList) result2h;
		String sheighth=nodes2h.item(0).getNodeValue();			
		System.out.println("The height value is: "+sheighth);

		
		
		
		  ConvertWeights cwser = new ConvertWeights();
	       ConvertWeightsSoap cwso = cwser.getConvertWeightsSoap();
	      WeightUnit wu1=WeightUnit.KILOGRAMS; 
	       WeightUnit wu2=WeightUnit.POUNDS_TROY;
	       double wp = cwso.convertWeight(Double.parseDouble(sweightw), wu1, wu2);
	       
	       wp=Math.round(wp*100.0)/100.0;
	       System.out.println("weight in pound is "+wp);
	       
	       Lengths l1 = Lengths.METERS;
	       Lengths l2 = Lengths.FEET;
	       LengthUnit lu = new LengthUnit();
	     LengthUnitSoap lus =lu.getLengthUnitSoap();
	     double lf=lus.changeLengthUnit(Double.parseDouble(sheighth), l1, l2);
	     
	     lf=Math.round(lf*100.0)/100.0;
	     System.out.println("height in feet is "+lf);
		
		
		String rec1="Your Bmi is "+bmiv+", You weight "+ wp + " pounds, Your height in feet is "+lf;
		
		
		
		HealthymeasureStatus hms = new HealthymeasureStatus();
		hms.setMeasuretype("Bmi");
		if(bmiv<bminvalue)
		{
			hms.setRecomendation(rec1+ ". To have Normal Bmi, Increase weight by managing your diet");
			hms.setStatus("below normal");
		}
		else if(bmiv>bmaxvalue){
			hms.setRecomendation(rec1+ ". To have Normal Bmi, Decrease weight by managing your diet and making exercise");
			hms.setStatus("above normal");
		}
		else
		{
			hms.setRecomendation(rec1+ ". You have Normal Bmi, continue like this make exercise and managing your diet");
			hms.setStatus("you are in normal range");
		}

		return hms;
	
	}
	
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:5900/projectsdeone/")
				.build();
	}


}
