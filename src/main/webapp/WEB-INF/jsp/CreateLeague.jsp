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

  </head>
  <body>
    <div>
	  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
	    		<a class="navbar-brand" href="Welcome.do">Pronostico</a>
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
			  </div>
			  	          
	          <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Finish</button>	          
	          
          </form> 
          
		</div>
	  </div>
	</div>
  </body>
</html>