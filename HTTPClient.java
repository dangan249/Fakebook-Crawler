public class HTTPClient{

	private HTTPRequest request;
	private HTTPResponse response ;

	public void doGet();
	public void doGetWithRedirect();

	public void doPost();
	public void doPostWithRedirect();

	public HTTPRequest getRequest() ;
	public HTTPResponse getResponse() ;

	
}