<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Games Administration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet"> 
    <link href="../css/hello.css" rel="stylesheet">
    <link href="../css/datepicker.css" rel="stylesheet">
    <script src="../js/jquery-1.10.2.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datepicker.js"></script>
	<script id="js">
	
	$(function() {

		$('#strDate').datepicker({
			format : 'yyyy-mm-dd',
			autoclose: true
		});
	});

    </script>
  </head>
  <body>
    <div>
	  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
	    		<a class="navbar-brand" href="../Welcome.do">PronoApp</a>
	  		</div>
	  		<div class="collapse navbar-collapse">
	  			<ul class="nav navbar-nav">
	  				<c:if test="${ user.admin }">
		  				<li class="dropdown">
					    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
					        <ul class="dropdown-menu">
					          <li><a href="List.do">Games</a></li>
		<!-- 			          <li><a href="#">Users</a></li> -->
		<!-- 			          <li><a href="#">Pronostics</a></li> -->
					        </ul>
					    </li>
				    </c:if>
	  			</ul>
	  		</div>
		</nav>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <h2>Edit Game</h2>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>


	      <form class="form-inline" method="post" role="form" action="Save.do">
          
			  <p>
			  	<input id="strDate" required="true" type="text" class="form-control date-input" name="strDate" value="<c:out value="${ gameEdited.formattedDate }" />" />
			  	<input required="true" type="text" class="form-control team-input" name="team1" value="<c:out value="${ gameEdited.team1 }" />" />
			  		<input type="text" class="form-control score-input" name="strScoreTeam1" value="<c:out value="${ gameEdited.scoreTeam1 }" />" />
			  		 - 
			  		<input type="text" class="form-control score-input" name="strScoreTeam2" value="<c:out value="${ gameEdited.scoreTeam2 }" />" /> 
			  	<input required="true" type="text" class="form-control team-input" name="team2" value="<c:out value="${ gameEdited.team2 }" />" />
			  </p>
			  
	          <p>
	          	<a href="List.do" role="button" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> Cancel</a>
	          	<button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Save</button>
	          </p>
	          
          </form> 
          
		</div>
	  </div>
	</div>
  </body>
</html>