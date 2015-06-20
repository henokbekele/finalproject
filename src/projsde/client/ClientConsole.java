package projsde.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;


import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;


public class ClientConsole {

	
	static javax.ws.rs.core.Response Res = null;
	static ClientConfig clientConfig = new ClientConfig();
	static javax.ws.rs.client.Client client = ClientBuilder.newClient(clientConfig);	
	static WebTarget service = client.target("http://localhost:5900/projectsdeone/");
	
	static BufferedReader BFR = new BufferedReader(new InputStreamReader(System.in));
	static Scanner inputscanner = new Scanner(System.in); 
	 
	public static void main(String[] args) throws IOException {
	consuleapplication();	      
	}
	public static void consuleapplication() throws IOException 

	{	 
	System.out.print("Enter the Address of the resource:  ");
	String path = BFR.readLine();	
	System.out.print("Enter the Method (GET, POST, PUT, DELETE):  ");
	String method = BFR.readLine();
	if(method.equalsIgnoreCase("GET"))
	{
	getM(path);
	}
	else if (method.equalsIgnoreCase("POST")) {
	System.out.println("Enter the data to be added in XML Format: ");
	String data = inputscanner.nextLine();
	postM(path,data);
	}
	else if (method.equalsIgnoreCase("PUT")) {
	System.out.println("Enter the data to be updated in XML Format: ");
	String data = inputscanner.nextLine();	
	putM(path, data);
	}
	else if (method.equalsIgnoreCase("DELETE")) {
	deleteM(path);
	}
	else{
	System.out.print("Wrong Method\n");
	}	
	System.out.print("Do you want to continue YES or NO\n");
	String choicev=inputscanner.nextLine();
	
	if (choicev.equalsIgnoreCase("yes")){

		consuleapplication();
	}
	}

	private static void getM(String path) {
	Res =service.path(path).request().accept(MediaType.TEXT_XML).get();
	System.out.println("  Result: "+ " \n  HTTP Status: "+ Res.getStatus()+ "\n\n");
	if (Res.getStatus()==200)
	{
	System.out.println(new ClientConsole().format(Res.readEntity(String.class)));
	}
	}

	private static void postM(String path, String data) {
	Res =service.path(path).request().accept(MediaType.APPLICATION_XML).post(Entity.entity(data, MediaType.TEXT_XML));//.post(Entity.entity(data, MediaType.TEXT_XML))
	System.out.println("  Result: \n"+ " \n  HTTP Status: "+ Res.getStatus()+ "\n\n");
	if (Res.getStatus()==200)
	{
	System.out.println(new ClientConsole().format(Res.readEntity(String.class)));
	}
	}

	private static void putM(String path, String data) {

	Res =service.path(path).request().accept(MediaType.APPLICATION_XML).put(Entity.entity(data, MediaType.TEXT_XML));//javax.ws.rs.client.Entity.xml(data));
	System.out.println("  Result: "+ " \n  HTTP Status: "+ Res.getStatus()+ "\n\n");
	
	if (Res.getStatus()==200 )
	{
	System.out.println(new ClientConsole().format(Res.readEntity(String.class)));
	}
	}
	
	private static void deleteM(String path) {
	Res =service.path(path).request().accept(MediaType.TEXT_XML).delete();
	System.out.println("  Result:\n "+ " \n  HTTP Status: "+ Res.getStatus()+ "\n\n");
	if (Res.getStatus()==200 )
	{
	System.out.println(new ClientConsole().format(Res.readEntity(String.class)));
	}
	}



	 public String format(String xml) {

	        try {
	            final InputSource src = new InputSource(new StringReader(xml));
	            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
	            final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));

	            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
	            final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
	            final LSSerializer writer = impl.createLSSerializer();

	            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
	            writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.

	            return writer.writeToString(document);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }	
	
}
