<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="notify.users.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
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
          
          <h2><spring:message code="notify.users.header"/> <c:out value="${ league.name }" /></h2>

		  <c:if test="${ not empty actionMessage }">
		  	<div class="alert alert-success"><c:out value="${ actionMessage }" /></div>
		  </c:if>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>


	      <form method="post" role="form" action="NotifyLeagueUsers.do">
          
			  <div class="form-group">
			    <label for="name"><spring:message code="notify.users.subject"/></label>
			    <input type="text" class="form-control" id="subject" name="subject" placeholder="<spring:message code="notify.users.subject"/>" value="<c:out value="${ notificationDTO.subject }" />">
			  </div>
			  
			  <div class="form-group">
			    <label for="name"><spring:message code="notify.users.text"/></label>
			    <textarea class="form-control" id="text" name="text" placeholder="<spring:message code="notify.users.text"/>" rows="10"><c:out value="${ notificationDTO.text }" /></textarea>
			  </div>
			  	          
	          <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> <spring:message code="notify.users.finish"/></button>	          
	          
          </form> 
          
		</div>
	  </div>
	</div>
  </body>
</html>