JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) -classpath "${CLASSPATH}:lib/*" $*.java

CLASSES = \
        ./ccs/neu/edu/andang/Crawler.java \
        ./ccs/neu/edu/andang/HTTPClient.java \
        ./ccs/neu/edu/andang/HTTPRequest.java \
        ./ccs/neu/edu/andang/HTTPResponse.java \
        ./ccs/neu/edu/andang/SocketClient.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) ./ccs/neu/edu/andang/*.class


javac -classpath "${CLASSPATH}:lib/*" @sources_list.txt


