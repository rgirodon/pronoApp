<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Change my default league</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
	$(function() {
		$("#chooseDefaultLeagueSelect").val('-1');
		<c:if test="${ not empty user.defaultLeague }">
			$("#chooseDefaultLeagueSelect").val('<c:out value="${ user.defaultLeague.id }" />');
		</c:if>
	});
	</script>
  </head>
  <body>
    <div>
	  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
	    		<a class="navbar-brand" href="Welcome.do">PronoClub</a>
	  		</div>
	  		<div class="collapse navbar-collapse">
	  			<ul class="nav navbar-nav">
	  				
	  				<%@include file="MyAccountMenu.jsp" %>
	  				
	  				<%@include file="AdministrationMenu.jsp" %>
				    
	  			</ul>
	  			
	  			<%@include file="SelectLeagueMenu.jsp" %>
	  			
	  		</div>
		</nav>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2>Change my default league</h2>

		  <c:if test="${ not empty changeMyDefaultLeagueInformation }">
		  	<div class="alert alert-success registerInformation"><c:out value="${ changeMyDefaultLeagueInformation }" /></div>
		  </c:if>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>

		  <form method="post" role="form" action="ChangeMyDefaultLeague.do">
			  <div class="form-group">
			    <label for="login">My default league</label>
			    <select id="chooseDefaultLeagueSelect" name="defaultLeagueId" class="form-control">
			  		<option value="-1">- No Default League -</option>
			  		<c:forEach var="leagueInvolvedIn" items="${user.leaguesInvolvedIn}">
			       		<option value="<c:out value="${ leagueInvolvedIn.id }" />"><c:out value="${ leagueInvolvedIn.name }" /></option>
			       	</c:forEach>
				</select>
			  </div>
			  
          	  <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Change</button>
		  </form>

          
		</div>
	  </div>
	</div>
  </body>
</html>