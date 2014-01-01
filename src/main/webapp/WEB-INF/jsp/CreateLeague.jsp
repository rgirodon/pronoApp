<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Create League</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
	<script>
		$(function() {
			
			$("#inheritsGamesFromLeagueId").val('-1');
			
			<c:if test="${ not empty leagueBeingCreated.inheritsGamesFromLeague }">
				$("#inheritsGamesFromLeagueId").val('<c:out value="${ leagueBeingCreated.inheritsGamesFromLeague.id }" />');
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
          
          <h2>Create League</h2>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>


	      <form method="post" role="form" action="CreateLeague.do">
          
			  <div class="form-group">
			    <label for="name">League Name</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="Enter League Name" value="<c:out value="${ leagueBeingCreated.name }" />">
			    
			    <c:if test="${ not leagueBeingCreated.isPublic }">
			    	<label for="name">Inherits Games from league</label>
			    	<select id="inheritsGamesFromLeagueId" name="inheritsGamesFromLeagueId" class="form-control">
				  		<option value="-1">- No League -</option>
				  		<c:forEach var="publicLeague" items="${publicLeagues}">
				       		<option value="<c:out value="${ publicLeague.id }" />"><c:out value="${ publicLeague.name }" /></option>
				       	</c:forEach>
					</select>
			    </c:if>
			    <c:if test="${ leagueBeingCreated.isPublic }">
			    	<input type="hidden" name="inheritsGamesFromLeagueId" value="-1" />
			    </c:if>
			    
			  </div>
			  	          
	          <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Finish</button>	          
	          
          </form> 
          
		</div>
	  </div>
	</div>
  </body>
</html>