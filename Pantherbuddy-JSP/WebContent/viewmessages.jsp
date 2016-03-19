<%@page import="pantherbuddy.business.model.UserModel"%>
<%@page import="pantherbuddy.business.model.MessageModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Oxygen:400,300,700">
<style type="text/css">
/*!
Styles for full screen background video demo
*/
/* =============================================================================
  RESETS
============================================================================= */
/* html, body {
	background: url("img/fiu1.jpg") no-repeat center center fixed;
	-webkit-background-size: 100% 100%;
	-moz-background-size: 100% 100%;
	-o-background-size: 100% 100%;
	background-size: 100% 100%;
} */

div, video {
	margin: 0;
	padding: 0;
}

/* =============================================================================
  HTML, BODY
============================================================================= */
html, body {
	height: 100%;
}

body {
	font-size: 16px;
	font-family: "Oxygen", sans-serif;
	line-height: 1.5;
}

/* =============================================================================
  CONTENT
============================================================================= */
.content {
	position: relative;
	top: 30%;
	z-index: 2;
	margin: 0 auto;
	max-width: 720px;
	text-align: center;
}

.content__heading {
	margin-bottom: 24px;
	color: #ffffff;
	font-size: 44px;
}

.content__teaser {
	margin-bottom: 24px;
	color: #ffffff;
	font-size: 22px;
}

.content__cta {
	display: inline-block;
	margin: 0;
	padding: 12px 48px;
	color: #ff3c64;
	font-size: 22px;
	text-decoration: none;
	border: solid 4px #ff3c64;
}

/* =============================================================================
  VIDEO
============================================================================= */
.video {
	position: fixed;
	top: 50%;
	left: 50%;
	z-index: 1;
	min-width: 100%;
	min-height: 100%;
	width: auto;
	height: auto;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
</style>
</head>
<body bgcolor="#D8D8D8">
<%
//allow access only if session exists
Integer userid = null;
if(session.getAttribute("userid") == null){
    response.sendRedirect("login.jsp");
}else userid = (Integer) session.getAttribute("userid");
String user = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("userid")) user = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
    sessionID = session.getId();
}
%>
<div class="container">
	<div class="navbar navbar-inverse navbar-fixed-top">
  	<div class="container">
    	<div class="navbar-header">
      		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
      		</button>
      		<a class="navbar-brand" href="#">
                <img src="img/logo.png" alt="">
            </a>
    	</div>
    	<ul class="nav navbar-nav navbar-right" style="display: inline-flex; text-decoration: none; text-transform: none;">
       	<!-- <li><a href="profile.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li> -->
       	<form action="MainController" method="post">
       		<input name="action" value="viewprofile" style="display: none;" />
       		<li> <input type="submit" name="profile" value="Profile" /> </li>
       	</form>
       	<form action="MainController" method="post">
       		<input name="action" value="logout" style="display: none;" />
        	<li> <input type="submit" name="profile" value="Logout" /> </li>
        </form>
      	</ul>
	</div>
  	</div>
</div>
</br>
</br></br>
</br></br>
<div class="container">
	<div class="container">
	<form action="MainController" method="post">
  	<div class="edit">
      	<div class="form-group">
        	<label for="message" class="col-sm-10 control-label"  style="color:black">Post Message</label></br>
            <div class="col-sm-8">
              	<textarea class="form-control" rows="4" name="message" maxlength="499"></textarea>
            </div>
		</div>
          <div class="form-group" align="right">
          	<div class="col-sm-6 col-sm-offset-2">
          		<input name="action" value="postmessage" style="display: none;" />
            	<input id="submit" name="submit" type="submit" value="Post" class="btn btn-primary">
         	</div>
      	</div>
 	</div>
 	</form>
 	</div>
</div>
<span style="text-align: center;"><font color="red" size="4" style="text-transform: uppercase;"><%if(request.getAttribute("error") != null){ out.println(request.getAttribute("error"));}%></font></span>
<br/>
<%
	ArrayList<MessageModel> messages = (ArrayList<MessageModel>) session.getAttribute("messages");
	for(MessageModel message :messages){
%>
<div class="container" style="border-top: 1px solid; border-bottom: 1px solid; padding-bottom: 5px; padding-top: 5px;">
  	<div class="edit">
      	<div class="media">
      		<form action="MainController" method="post">
             <a class="pull-left" href="#">
                <img class="media-object" src="" alt=""></a><input name="action" value="viewprofile" style="display: none;"/>
                <input name="userid" value="<% out.println(message.getUserId()); %>" style="display: none;"/>
                <input type="submit" style="background: transparent none repeat scroll 0% 0%; border: medium none; color: blue;" name="profile" value="<% out.println(message.getUsername()); %>">
            </form>
                   <div class="media-body">
                      <h4 class="media-heading"></h4>
                         <p style="color:black"><%out.println(message.getMessage()); %></p>
        				<!-- Second Media Object -->
        					<%if(userid == message.getUserId()){ %>
                             <div class="media">
                             		<form action="MainController" method="post">
                                   		<a class="pull-left" href="#">
    									<ul class="nav navbar-nav navbar-right">
    										<input name="action" value="deletemessage" style="display: none;" />
    										<input name="msgid" style="display: none;" value="<%out.println(message.getMessageId());%>"/>
        									<li><input type="submit" value="Delete"></li>
        								</ul>
        								</a>
        							</form>
    						</div>
    						<%} %>
     				</div>
    	</div>
 	</div>
</div>
<%} %>


<br>
<div id="footer">
<font color="black" size="2px" >
Copyright © PantherbuddyFIU.com</font>
</div>
</body>
</html>