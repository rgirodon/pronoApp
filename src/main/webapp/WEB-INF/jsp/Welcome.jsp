<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Welcome to PronoClub</title>
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
		  <h2>Welcome <c:out value="${ user.displayName }" /></h2>
		  <p>We are very pleased to see you here.</p>
		  <p>You are involved in <c:out value="${ user.nbInvolvedLeagues }" /> league(s)</p>
		  <p>Your have <c:out value="${ nbInvitations }" /> pending invitation(s)</p>
		  <p>If you want to leave <a class="btn btn-lg btn-default" href="SignOut.do" role="button"><span class="glyphicon glyphicon-off"></span> Sign Out</a></p>
		</div>		
	</div>
    
  </body>
</html>