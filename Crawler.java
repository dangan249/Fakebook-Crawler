import java.util.ArrayList ;
import java.net.URL ;
public class Crawler {

	private HTTPClient client ;
	private String id ;
	private String password ;
	private URL rootURL = new URL("http://cs5700.ccs.neu.edu/") ;
	private URL logInURL = new URL("http://cs5700.ccs.neu.edu/accounts/login/?next=/fakebook/")
	private List<URL> visitedURL ;
	private Queue<URL> frontierURL ;
	private List<String> secretFlags ;
	private Map<String, String> cookies ;

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
}