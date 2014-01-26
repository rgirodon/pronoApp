<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="game.create.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
     <script src="js/bootstrap-datepicker.js"></script>
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
	  	<%@include file="Menu.jsp" %>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2><spring:message code="game.create.header"/></h2>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>


	      <form class="form-inline" method="post" role="form" action="FinishGame.do">
          
			  <p>
			  	<input id="strDate" placeholder="<spring:message code="game.create.date"/>" required="true" type="text" class="form-control date-input" name="strDate" value="<c:out value="${ gameBeingCreated.formattedDate }" />" />
			  	<input required="true" placeholder="<spring:message code="game.create.team1"/>" type="text" class="form-control team-input" name="team1" value="<c:out value="${ gameBeingCreated.team1 }" />" />
			  		 - 
			  	<input required="true" placeholder="<spring:message code="game.create.team2"/>" type="text" class="form-control team-input" name="team2" value="<c:out value="${ gameBeingCreated.team2 }" />" />
			  </p>
			  
	          <p>
	          	<a href="ListGames.do" role="button" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> <spring:message code="game.create.cancel"/></a>
	          	<button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> <spring:message code="game.create.finish"/></button>
	          </p>
	          
          </form> 
          
		</div>
	  </div>
	</div>
  </body>
</html>