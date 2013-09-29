#all: crawler httprequest httpclient httpresponse socketclient
all: httpclient

crawler: Crawler.java
	javac Crawler.java

httprequest: HTTPRequest.java
	javac HTTPRequest.java

httpclient: HTTPClient.java
	javac HTTPClient.java

httpresponse: HTTPResponse.java
	javac HTTPResponse.java

socketclient: SocketClient.java
	SocketClient.java
