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


import java.net.URL ;
public class Crawler {

	private HTTPClient client ;
	private String id ;
	private String password ;
	private URL rootURL = new URL("http://cs5700.ccs.neu.edu/") ;
	private URL logInURL = new URL("http://cs5700.ccs.neu.edu/accounts/login/?next=/fakebook/")
	private Set<URL> visitedURL ;
	private Queue<URL> frontierURL ;
	private List<String> secretFlags ;
	private Map<String, String> cookies ;

	public Crawler( String id, String password ){
		this.id = id ;
		this.password = password ;
		this.HTTPClient = new HTTPClient() ;
		this.visitedURL = new HashSet<URL>() ;
		this.frontierURL = new LinkedList<URL>() ;
		this.cookies = new HashMap<URL>() ;
	}

	// TODO
	// log in Fakebook and get session + csrf cookies  
	// side-effect: change this.cookies
	//              and propably this.visitedURL, this.frontierURL, and this.secretFlags 
	public void login(){

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

	public void static main(String args[]){

		Crawler crawler = new Crawler( args[0], args[1]) ;

		crawler.login() ;

		crawler.crawl() ;

		crawler.printSecretKeys() ;
	}
}