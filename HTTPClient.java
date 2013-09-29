// I/O classes
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.io.BufferedReader ;

// Exceptions 
import java.io.IOException ;
import java.net.UnknownHostException ;
import java.net.SocketException ;
import java.net.MalformedURLException;
import java.lang.RuntimeException ;

// Util classes
import java.util.Map ;
import java.util.HashMap ;
import java.lang.StringBuilder ;
import java.net.URL ; // Only used to represent an URL, not used to handle any networking activities

// HTTP 1.0
public class HTTPClient{


	private HTTPRequest request;
	// without making the request this.response will be null
	// ==> cause NullPointerException (not a bad way to inform client :) )
	private HTTPResponse response ; 

	public HTTPClient( ){
	}


	// TODO
	// create the entire HTTP 1.0 message from the given HTTPRequest
	// so this.sock can use it
	private String serializeRequest( HTTPMethod method, HTTPRequest request){
		// give StringBuilder some initial capacity will save time if the 
		// request is big
		StringBuilder builder = new StringBuilder(112) ;

		// BUILDING FIRST LINE 
		builder.append( method.value() ) ;
		builder.append( " " ) ;
		String path = request.getURL().getPath() ;
		builder.append(  path == "" ? "/" : path ) ; 
		builder.append( " " ) ;
		builder.append( "HTTP/1.0\r\n" ) ;


		// BUILDING HEADERS 
		Map<String,String> headers = request.getHeaders() ;
		for( String key : headers.keySet() ){
			builder.append( key ) ;
			builder.append( ":" ) ;
			builder.append( headers.get( key ) ) ;
			builder.append( "\r\n" ) ;
		}
 
		builder.append( "\r\n" ) ;

		String body = request.getRequestBody() ;
		if( body != null && !body.isEmpty() ){
			builder.append( body ) ;
		}	
		return builder.toString() ;
	}

	
	// TODO
	// parse the entire response message to create an HTTPResponse
	// side-effect: populate this.response
	private void deserializeResponse( InputStream input ) throws RuntimeException, IOException{
		

		BufferedReader reader = null ;
		// use BufferedReader to take advantage of its "readLine" method
		reader = new BufferedReader(new InputStreamReader( input , "US-ASCII") )  ;


		// START POPULATING this.response 

		this.response = new HTTPResponse() ;
		this.response.setURL( this.getRequest().getURL() ) ;

		// PARSING THE FIRST LINE
		String firstLine = reader.readLine() ;
		if( firstLine == null || firstLine.isEmpty() ){
			throw new RuntimeException( this.request.getURL().getHost() + 
				" does not return proper HTTP message") ;
		}

		String[] firstLineElements = firstLine.split("\\s") ;

		try{
			int code = Integer.parseInt( firstLineElements[1] ) ;
			switch (code){
				case 200: 
					this.response.setStatusCode( StatusCode.OK ) ;
					break;
				case 301: 
					this.response.setStatusCode( StatusCode.MOVED_PERMANENTLY ) ;
					break;
				case 302: 
					this.response.setStatusCode( StatusCode.MOVED_TEMPORARILY ) ;				
					break;
				case 403: 
					this.response.setStatusCode( StatusCode.FORBIDDEN ) ;
					break;
				case 500: 
					this.response.setStatusCode( StatusCode.INTERNAL_SERVER_ERROR ) ;
					break;	
				default:
					throw new RuntimeException( this.request.getURL().getHost() + 
						" return unknown HTTP status code") ;
			}

		}
		catch( NumberFormatException ex ){
			throw new RuntimeException( this.request.getURL().getHost() + 
				" does not return proper HTTP Status Code: " + ex.toString() ) ;
		}

			

		// PARSING HEADERS
		Map<String,String> headers = new HashMap<String,String>() ;

		String line = reader.readLine() ;
		while( line != null && !line.isEmpty() ){
			int colonIndex = line.indexOf( ':' ) ;
			if( colonIndex < 0 ) break ; // this may signal the end of headers or server just mess up :)
			
			headers.put( line.substring( 0, colonIndex ).trim() , 
						 line.substring( ++colonIndex ).trim() ) ;

			line = reader.readLine() ;
		}

		this.response.setHeaders( headers ) ;

		// PARSING BODY
		StringBuilder builder = new StringBuilder() ;
		while( (line = reader.readLine() ) != null ){
			builder.append( line ) ;
		}

		this.response.setResponseBody( builder.toString() ) ;

		// FINISH POPULATING this.response 
	}

	private void sendRequest( HTTPMethod method ) throws UnknownHostException, SocketException, IOException{
		
		SocketClient sock = null ;
		try{
			int port = this.request.getURL().getPort() ;
			sock = new SocketClient( this.request.getURL().getHost(), 
									 port == -1 ? 80 : port,
									 false ) ;
			sock.connect() ;

			String getMessage = serializeRequest( method ,this.request ) ;
			System.out.println( getMessage ) ;
			
			InputStream input = null ;
			input = sock.sendMessage( getMessage ) ;			
			deserializeResponse( input ) ;			
		}
		catch(Exception ex ){
			System.out.println( "Problem with sending request: " + ex.toString() ) ;
		}
		finally{
			sock.disconnect() ;
		}

	}

	public void doGet() throws UnknownHostException, SocketException, IOException{
		
		sendRequest( HTTPMethod.GET ) ;
		
	}

	public void doGetWithRedirect(){

	}

	public void doPost() throws UnknownHostException, SocketException, IOException{

		sendRequest( HTTPMethod.POST ) ;
	}

	public void doPostWithRedirect(){

	}

	public HTTPRequest getRequest(){
		return this.request ;
	}

	public void setRequest(HTTPRequest request){
		this.request = request ;
	}

	public HTTPResponse getResponse(){
		return this.response ;
	}

	public enum HTTPMethod{

		POST("POST"),
		GET("GET");

		private final String value ;
		HTTPMethod( String value ){
			this.value = value ;
		}

		String value(){
			return value ;
		}
	}

	public enum StatusCode {
		OK(200),
		NOT_FOUND(400),
		FORBIDDEN(403),
		INTERNAL_SERVER_ERROR(500),
		MOVED_PERMANENTLY(301),
		MOVED_TEMPORARILY(302);

		private final int value ;
		StatusCode( int value ){
			this.value = value ;
		}

		int value(){
			return value ;
		}
	}


}

