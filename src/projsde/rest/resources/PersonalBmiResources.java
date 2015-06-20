package projsde.rest.resources;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;








import projsde.rest.model.Bodymassindex;


public class PersonalBmiResources {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonalBmiResources(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonalBmiResources(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Application integration
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Bodymassindex getBmi() throws SAXException, IOException, XPathExpressionException, ParserConfigurationException {
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());

		
		
	
		Response R;
		String statusR="";
		R= service.path("person/"+id+ "/Healthprofile/weight").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml=R.readEntity(String.class);	
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
		XPathExpression expr = xpath.compile("/healthprofile/pvalue/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);

		NodeList nodes = (NodeList) result;
		String sweight=nodes.item(0).getNodeValue();			
		System.out.println("The weight value is: "+sweight);

		Response R2;
		String statusR2="";
		R2= service.path("person/"+id+ "/Healthprofile/height").request().accept(MediaType.TEXT_XML).get(Response.class);
		String unformattedXml2=R2.readEntity(String.class);	
		System.out.println(unformattedXml2);
		
		DocumentBuilderFactory domFactory2 = DocumentBuilderFactory
				.newInstance();
		domFactory2.setNamespaceAware(true);
		DocumentBuilder builder2 = domFactory2.newDocumentBuilder();
		Document doc2 = builder2.parse(new InputSource(new StringReader(
				unformattedXml2)));
		//Document doc2;
		
		XPathFactory factory2 = XPathFactory.newInstance();
		XPath xpath2 = factory2.newXPath();
		XPathExpression expr2 = xpath.compile("/healthprofile/pvalue/text()");
		Object result2 = expr2.evaluate(doc2, XPathConstants.NODESET);

		NodeList nodes2 = (NodeList) result2;
		String sheight=nodes2.item(0).getNodeValue();			
		System.out.println("The height value is: "+sheight);
		
		
		float v;
		v=Float.parseFloat(sweight)/(Float.parseFloat(sheight)*Float.parseFloat(sheight));
		Bodymassindex bmi= new Bodymassindex(v);
		return bmi;
	}

	
	

	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:5900/projectsdeone/")
				.build();
	}

}
