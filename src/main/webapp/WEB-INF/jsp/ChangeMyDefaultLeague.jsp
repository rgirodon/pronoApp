<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
  <head>
    <title><spring:message code="myDefaultLeague.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
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
	  	<%@include file="Menu.jsp" %>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2><spring:message code="myDefaultLeague.header"/></h2>

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
			    <label for="login"><spring:message code="myDefaultLeague.defaultLeague"/></label>
			    <select id="chooseDefaultLeagueSelect" name="defaultLeagueId" class="form-control">
			  		<option value="-1"><spring:message code="myDefaultLeague.choose"/></option>
			  		<c:forEach var="leagueInvolvedIn" items="${user.openLeaguesInvolvedIn}">
			       		<option value="<c:out value="${ leagueInvolvedIn.id }" />"><c:out value="${ leagueInvolvedIn.name }" /></option>
			       	</c:forEach>
				</select>
			  </div>
			  
          	  <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> <spring:message code="myDefaultLeague.change"/></button>
		  </form>

          
		</div>
	  </div>
	</div>
  </body>
</html>