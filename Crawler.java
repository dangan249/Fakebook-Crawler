/*
	Programmers: An Dang + Pedro Benedicte
	Network Fundamentals
	Project 2 - Fakebook Web Crawler

*/

import java.util.List ;
import java.util.LinkedList ;
import java.util.Set ;
import java.util.HashSet ;
import java.util.Queue ;
import java.util.Map ;
import java.util.HashMap ;

import java.net.UnknownHostException ;
import java.net.SocketException ;
import java.net.MalformedURLException;
import java.io.IOException ;

import java.net.URL ;

public class Crawler {

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
			this.rootURL = new URL("http://cs5700.ccs.neu.edu/")  ;
			this.logInURL = new URL("http://cs5700.ccs.neu.edu/accounts/login/?next=/fakebook/") ;
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
			Map<String,String> headers = new HashMap<String,String>() ;
			headers.put( "From" , "dang.an249@gmail.com" ) ;
			request.setHeaders( headers ) ;
			this.client.setRequest( request ) ;
			client.doGet(cookies) ;
		
			// Second request with the body specified and the cookies
			String s1 = "csrfmiddlewaretoken=";
			String s2 = this.cookies.get("csrftoken");
			String s3 = "&username=001908844&password=YEN05M01&next=%2Ffakebook%2F";
			String body = new String(s1+s2+s3);
			request.setRequestBody(body);
			request.addCookies(this.cookies);
			//System.out.println(headers.toString());
			client.doGet(cookies) ;
			System.out.println( client.getRequest().toString() ) ;
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
