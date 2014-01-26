<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="welcome.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/theme.bootstrap.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>
    <div>
	  	<%@include file="Menu.jsp" %>
	</div>
  	<div class="jumbotron">
		<div class="container">	    
		  <h2><spring:message code="welcome.header"/> <c:out value="${ user.displayName }" /></h2>
		  <p><spring:message code="welcome.pleased"/></p>
		  <p><spring:message code="welcome.involved"/> <c:out value="${ user.nbInvolvedLeagues }" /> <spring:message code="welcome.leagues"/></p>
		  <p><spring:message code="welcome.youHave"/> <c:out value="${ nbInvitations }" /> <spring:message code="welcome.pending.invitations"/></p>
		  <p><spring:message code="welcome.youLeave"/> <a class="btn btn-lg btn-default" href="SignOut.do" role="button"><span class="glyphicon glyphicon-off"></span> <spring:message code="welcome.signout"/></a></p>
		</div>		
	</div>
    
  </body>
</html>