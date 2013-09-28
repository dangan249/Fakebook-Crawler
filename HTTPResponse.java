import java.net.URL ;
import java.util.Map ;

public class HTTPResponse{

	private URL url ;
	private String responseBody ;
	private Map<String, String> headers ;
	private HTTPClient.StatusCode code ;

	public void setURL(URL url){
		this.url = url ;
	}

	public URL getURL(){
		return this.url ;
	}

	public void setStatusCode( HTTPClient.StatusCode code ){
		this.code = code ;
	}

	public HTTPClient.StatusCode getStatusCode(){
		return this.code ;
	}

	public void setResponseBody(String responseBody){
		this.responseBody = responseBody ;
	}

	public String getResponseBody(){
		return this.responseBody ;
	}

	public void setHeaders(Map<String, String> headers){
		this.headers = headers ;
	}

	public Map<String, String>  getHeaders(){
		return this.headers ;
	}


	@Override
	public String toString(){
		return  "RESPONSE :" + "\n" + 
				"URL : " + url.toString() + "\n" 				
				+ "STATUS CODE : " + Integer.toString( code.value() )  + "\n"  
				+ "HEADERS: " + "\n"
				+ headers.toString() + "\n"  
				+ "BODY : " + "\n" 
				+ responseBody + "\n" ;
	}

}