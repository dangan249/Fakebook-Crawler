Fakebook-Crawler
================

CS 4700 / CS 5700 - Network Fundamentals
Project 2: Web Crawler

Description
================================================================================
This assignment is intended to familiarize you with the HTTP protocol. Students are required to implement the HTTP protocol themselvels without using any native HTTP client.

The goal in this assignment is to implement a web crawler that gathers data from a fake social networking website that we have set up for you. 
The site is available here: Fakebook.

What is a Web Crawler?
A web crawler (sometimes known as a robot, a spider, or a screen scraper) is a piece of software that automatically gathers and traverses documents on the web. For example, lets say you have a crawler and you tell it to start at www.wikipedia.com. The software will first download the Wikipedia homepage, then it will parse the HTML and locate all hyperlinks (i.e. anchor tags) embedded in the page. The crawler then downloads all the HTML pages specified by the URLs on the homepage, and parses them looking for more hyperlinks. This process continues until all of the pages on Wikipedia are downloaded and parsed.
Web crawlers are a fundamental component of today's web. For example, Googlebot is Google's web crawler. Googlebot is constantly scouring the web, downloading pages in search of new and updated content. All of this data forms the backbone of Google's search engine infrastructure.

Fakebook
================================================================================
We have set up a fake social network for this project called Fakebook. Fakebook is a very simple website that consists of the following pages:
Homepage: The Fakebook homepage displays some welcome text, as well as links to several random Fakebook users' personal profiles.
Personal Profiles: Each Fakebook user has a profile page that includes their name, some basic demographic information, as well as a link to their list of friends.
Friends List: Each Fakebook user is friends with one or more other Fakebook users. This page lists the user's friends and has links to their personal profiles.
In order to browse Fakebook, you must first login with a username and password. We will email each student to give them a unique username and password.

WARNING: DO NOT TEST YOUR CRAWLERS ON PUBLIC WEBSITES

High Level Requirements
The goal is to collect 5 secret flags that have been hidden somewhere on the Fakebook website. 
The flags are unique for each student, and the pages that contain the flags will be different for each student. Since you have no idea what pages the secret flags will appear on, your only option is to write a web crawler that will traverse Fakebook and locate your flags.

Your web crawler must execute on the command line using the following syntax:
./webcrawler [username] [password]


There are a few key things that all web crawlers must do in order function:


---- Track the Frontier: As your crawler traverses Fakebook it will observe many URLs. Typically, these uncrawled URLs are stored in a queue, stack, or list until the crawler is ready to visit them. These uncrawled URLs are known as the frontier.
    Watch Out for Loops: Your crawler needs to keep track of where it has been, i.e. the URLs that it has already crawled. Obviously, it isn't efficient to revisit the same pages over and over again. 
    
    If your crawler does not keep track of where it has been, it will almost certainly enter an infinite loop. 
    
    For example, if users A and B are friends on Fakebook, then that means A's page links to B, and B's page links to A. Unless the crawler is smart, it will ping-pong back and forth going A->B, B->A, A->B, B->A, ..., etc.
    Only Crawl The Target Domain: Web pages may include links that point to arbitrary domains (e.g. a link on google.com that points to cnn.com). Your crawler should only traverse URLs that point to pages on cs5700.ccs.neu.edu. 
    
    For example, it would be valid to crawl http://cs5700.ccs.neu.edu/fakebook/018912/, but it would not be valid to crawl http://www.facebook.com/018912/.
    
    
    In order to build a successful web crawler, you will need to handle several different aspects of the HTTP protocol:
    
    HTTP GET - These requests are necessary for downloading HTML pages.
    HTTP POST - You will need to implement HTTP POST so that your code can login to Fakebook. As shown above, you will pass a username and password to your crawler on the command line. The crawler will then use these values as parameters in an HTTP POST in order to log-in to Fakebook.
    Cookie Management - Fakebook uses cookies to track whether clients are logged in to the site. If your crawler successfully logs in to Fakebook using an HTTP POST, Fakebook will return a session cookie to your crawler. Your crawler should store this cookie, and submit it along with each HTTP GET request as it crawls Fakebook. If your crawler fails to handle cookies properly, then your software will not be able to successfully crawl Fakebook.
    In addition to crawling Fakebook, your web crawler must be able to correctly handle HTTP status codes. Obviously, you need to handle 200, since that means everything is okay. Your code must also handle:
    
    301 - Moved Permanently: This is known as an HTTP redirect. Your crawler should try the request again using the new URL given by the server.
    403 - Forbidden and 404 - Not Found: Our web server may return these codes in order to trip up your crawler. In this case, your crawler should abandon the URL that generated the error code.
    500 - Internal Server Error: Our web server may randomly return this error code to your crawler. In this case, your crawler should re-try the request for the URL until the request is successful.
    I highly recommend the HTTP Made Really Easy tutorial as a starting place for students to learn about the HTTP protocol. Furthermore, the developer tools built-in to the Chrome browser, as well as the Firebug extension for Firefox, are both excellent tools for inspecting and understanding HTTP requests.


---- Logging in to Fakebook
  In order to write code that can successfully log-in to Fakebook, you will need to reverse engineer the HTML form on the log-in page. Students should carefully inspect the form's code, since it may not be as simple as it initially appears.
  Language
  You can write your code in whatever language you choose, as long as your code compiles and runs on unmodified CCIS Linux machines on the command line. Do not use libraries that are not installed by default on the CCIS Linux machines. Similarly, your code must compile and run on the command line. You may use IDEs (e.g. Eclipse) during development, but do not turn in your IDE project without a Makefile. Make sure you code has no dependencies on your IDE.
