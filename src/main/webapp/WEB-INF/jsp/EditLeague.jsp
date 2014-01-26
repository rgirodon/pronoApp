<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="editleague.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
	<script>
	$(function() {
		$("#confirmCloseButton").click(function() {
			
			console.log('confirmCloseButton');
			
			$("#confirmCloseModal").modal('hide');
			
			window.location.href = 'CloseLeague.do?leagueId=<c:out value="${ leagueBeingEdited.id }" />&userId=<c:out value="${ user.id }" />';
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
          
          <h2><spring:message code="editleague.header"/></h2>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>


	      <form method="post" role="form" action="EditLeague.do">
          
			  <div class="form-group">
			    <label for="name"><spring:message code="editleague.name"/></label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="editleague.enter.name"/>" value="<c:out value="${ leagueBeingEdited.name }" />">
			  </div>
			  	          
	          <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> <spring:message code="editleague.finish"/></button>	          
	          
          </form> 
          
          <br/>
          <br/>
          
          <div class="alert alert-danger"><spring:message code="editleague.close.warning"/><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmCloseModal"><span class="glyphicon glyphicon-ban-circle"></span> <spring:message code="editleague.close"/></button></div>
          
		</div>
	  </div>
	</div>
	<div class="modal fade" id="confirmCloseModal" tabindex="-1" role="dialog" aria-labelledby="confirmCloseModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="confirmCloseModalLabel"><spring:message code="editleague.close.confirm.title"/></h4>
	      </div>
	      <div class="modal-body">
	        <p><spring:message code="editleague.close.confirm.question"/></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="editleague.close.confirm.cancel"/></button>
	        <button id="confirmCloseButton" type="button" class="btn btn-danger"><spring:message code="editleague.close.confirm.close"/></button>
	      </div>
	    </div>
	  </div>
	</div>
  </body>
</html>