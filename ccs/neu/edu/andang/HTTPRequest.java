package ccs.neu.edu.andang ;

import java.net.URL ;
import java.util.Map ;
import com.google.common.collect.Multimap ;
import com.google.common.collect.HashMultimap ; 
import java.lang.StringBuilder ;

public class HTTPRequest{

	private URL url ; // if users do not specify port, URL default it to 80
	private String requestBody ;
	private Multimap< String, String > headers ;
	private Map<String, String> cookies ;

	public HTTPRequest( URL url ){

		this.url = url ;
		this.headers = HashMultimap.create() ;
		this.requestBody = "" ;

	}


	// adding cookies to the header of sending request
	// side-effect: add more pairs in this.headers
	public void addCookies(Map<String, String> cookies) {

		this.cookies = cookies ;

		String key = "Cookie";
		StringBuilder builder = new StringBuilder() ;

		int i = 0 ;
		
		// TODO: cookie can be Cookie: value1; value2; name1=value1
		// this implementation cannot deal with that yet :(

		for( String cookieKey : cookies.keySet() ){

				builder.append( cookieKey ) ;
				builder.append( "=") ;
				builder.append( cookies.get( cookieKey ) )  ;

			if( i != cookies.size() - 1 ){
				builder.append( "; ") ;
			}

			i++ ;
		}

		this.headers.put( key, builder.toString() );

	}

	public void setURL(URL url){
		this.url = url ;
	}

	public URL getURL(){
		return this.url ;
	}

	public void setRequestBody(String requestBody){
		this.requestBody = requestBody ;
	}

	public String getRequestBody(){
		return this.requestBody ;
	}

	public void setHeaders( Multimap< String, String > headers){
		this.headers = headers ;
	}

	public Multimap< String, String > getHeaders(){
		return this.headers ;
	}

	public Map<String, String> getCookies(){
		return this.cookies ;
	}
	
	@Override
	public String toString(){
		return  "REQUEST :" + "\n" + 
				"URL : " + url.toString() + "\n" 				
				+ "HEADERS: " + "\n"
				+ headers.toString() + "\n"  
				+ "BODY : " + "\n" 
				+ requestBody + "\n" ;
	}

}
