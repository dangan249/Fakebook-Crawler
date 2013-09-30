/*
	Programmers: An Dang + Pedro Benedicte
	Network Fundamentals
	Project 2 - Fakebook Web Crawler

*/

package ccs.neu.edu.andang ;

import java.util.List ;
import java.util.LinkedList ;
import java.util.Set ;
import java.util.HashSet ;
import java.util.Queue ;
import java.util.Map ;
import java.util.HashMap ;
import com.google.common.collect.Multimap ;
import com.google.common.collect.HashMultimap ;

import java.net.UnknownHostException ;
import java.net.SocketException ;
import java.net.MalformedURLException;
import java.io.IOException ;

import java.net.URL ;

public class Crawler {

	private final String HOST = "http://cs5700.ccs.neu.edu/" ;
	private final String LOG_IN_URL = "http://cs5700.ccs.neu.edu/accounts/login/?next=/fakebook/" ;
	private HTTPClient client ;
	private String id ;
	private String password ;
	private URL rootURL ;
	private URL logInURL ;
	private Set<URL> visitedURL ;
	private Queue<URL> frontierURL ;
	private List<String> secretFlags ;
	private Map<String, String> cookies ;

	public Crawler( String id, String password ){
		this.id = id ;
		this.password = password ;
		this.client = new HTTPClient() ;
		this.visitedURL = new HashSet<URL>() ;
		this.frontierURL = new LinkedList<URL>() ;
		this.cookies = new HashMap<String,String>() ;
		try{
			this.rootURL = new URL( HOST )  ;
			this.logInURL = new URL( LOG_IN_URL ) ;
		}
		catch (MalformedURLException ex){
			throw new RuntimeException( "Internal Crawler's error " + ex.toString() ) ;
		}
	}

	// TODO
	// log in Fakebook and get session + csrf cookies  
	// side-effect: change this.cookies
	//              and propably this.visitedURL, this.frontierURL, and this.secretFlags 
	public void login(){
		
		try{

			HTTPRequest request = new HTTPRequest( this.logInURL ) ;

			// First request to know his cookies
			Multimap<String,String> headers = HashMultimap.create() ;
			headers.put( "From" , "dang.an249@gmail.com" ) ;
			request.setHeaders( headers ) ;

			this.client.setRequest( request ) ;

			// GETTING THE LOGIN PAGE 
			client.doGet() ; 


			System.out.println( client.getRequest().toString() ) ;
			System.out.println( client.getResponse().toString() ) ;
			System.out.println("===================") ;

			this.cookies = client.getResponse().getCookies() ;

			request = new HTTPRequest( this.logInURL ) ;

			headers.put( "Host" , this.logInURL.getHost() ) ;
			headers.put( "Referer" , "http://cs5700.ccs.neu.edu/accounts/login/" ) ;
			headers.put( "Connection" , "keep-alive" ) ;
			headers.put( "Content-Type", "application/x-www-form-urlencoded" ) ;
			

			StringBuilder body = new StringBuilder(112) ;

			body.append("csrfmiddlewaretoken=") ;
			body.append( this.cookies.get( "csrftoken" )) ;
			body.append("&username=") ;
			body.append( this.id ) ;

			body.append("&password=") ;
			body.append( this.password ) ;
			body.append("&next=%2Ffakebook%2F") ;

			request.setRequestBody( body.toString() ) ;

			headers.put( "Content-Length", Integer.toString( request.getRequestBody().length() ) ) ;

			request.setHeaders( headers ) ;						
			request.addCookies( this.cookies ) ;

			client.setRequest( request ) ;
			System.out.println( client.getRequest().toString() ) ;

			client.doPostWithRedirect() ;
			System.out.println( client.getResponse().toString() ) ;

		}
		catch( UnknownHostException ex){
			System.out.println("Unable to connect to " + client.getRequest().getURL() + ". Unknown host" ) ;
		} 
		catch( SocketException ex){
			System.out.println( "Error with underlying protocol: " + ex.toString() ) ;
		}
		catch( IOException ex){
			System.out.println( ex.toString() ) ;
		}

	}

	// TODO
	// crawl the Fakebook
	// side-effect: modify this.visitedURL, this.frontierURL, this.secretFlags
	public void crawl(){

		// check to see if cookies is set otherwise throw error

		// make the GET call

		// HTTP client going to handle any kind redirect for you and give back the correct
		// response
		// so only deal with 403 and 500 here

		// parse the HTML to:
		// -- find the keys
		// -- enqueue new frontier URL (make sure you do not have duplicates)

	}

	// TODO
	// return true if we already visit this link
	private boolean URLVisited(URL u){
		return false ;
	}

	// TODO
	// print to StdOut all keys in this.secretFlags
	public void printSecretKeys(){
		
	}

	// TODO
	public String getSessionCookieName(){
		return "" ;
	}

	// TODO
	public String getSessionCookieValue(){
		return "" ;
	}

	public static void main(String args[]){

		Crawler crawler = new Crawler( args[0], args[1]) ;

		crawler.login() ;

		//crawler.crawl() ;

		//crawler.printSecretKeys() ;
	}
}
