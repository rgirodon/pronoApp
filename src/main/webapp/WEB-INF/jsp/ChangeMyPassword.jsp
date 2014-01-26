<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="mypassword.title"/></title>
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
          
          <h2><spring:message code="mypassword.header"/></h2>

		  <c:if test="${ not empty changeMyPasswordInformation }">
		  	<div class="alert alert-success registerInformation"><c:out value="${ changeMyPasswordInformation }" /></div>
		  </c:if>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>

		  <form method="post" role="form" action="ChangeMyPassword.do">
			  <div class="form-group">
			    <label for="login"><spring:message code="mypassword.email"/></label>
			    <input disabled="true" type="text" class="form-control" id="login" value="<c:out value="${ user.login }" />">
			  </div>
			  <div class="form-group">
			    <label for="oldPassword"><spring:message code="mypassword.oldPassword"/></label>
			    <input required="true" type="password" class="form-control" name="oldPassword" />
			  </div>
			  <div class="form-group">
			    <label for="newPassword"><spring:message code="mypassword.newPassword"/></label>
			    <input required="true" type="password" class="form-control" name="newPassword" />
			  </div>
			  <div class="form-group">
			    <label for="reEnterNewPassword"><spring:message code="mypassword.reEnterNewPassword"/></label>
			    <input required="true" type="password" class="form-control" name="reEnterNewPassword" /> 
			  </div>
			  
          	  <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> <spring:message code="mypassword.change"/></button>
		  </form>

          
		</div>
	  </div>
	</div>
  </body>
</html>