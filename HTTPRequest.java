import java.net.URL ;
import java.util.Map ;

public class HTTPRequest{

	private URL url ;
	private String requestBody ;
	private Map<String, String> headers ;

	public HTTPRequest(){

	}

	public HTTPRequest( URL url, Map<String, String> headers){
		this( url, headers, "" ) ;
	}

	public HTTPRequest( URL url, Map<String, String> headers, String requestBody){
		this.url = url ;
		this.headers = headers ;
		this.requestBody = requestBody ;
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

	public void setHeaders(Map<String, String> headers){
		this.headers = headers ;
	}

	public Map<String, String> getHeaders(){
		return this.headers ;
	}

		@Override
	public String toString(){
		return  "REQUEST :" + "\n" + 
				"URL : " + url.toString() + "\n" 				
				+ "HEADERS: " + "\n"
				+ headers.toString() + "\n"  
				+ "BODY : " + "\n" 
				+ (requestBody == null ? "" : requestBody )+ "\n" ;
	}

}